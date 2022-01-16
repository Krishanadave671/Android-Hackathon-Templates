package com.krishana.androidhackathontemplates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class LogInActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
  private lateinit var emailEditText : TextInputLayout
  private lateinit var passwordEditText: TextInputLayout
  private lateinit var loginButton : Button
  private lateinit var signuptextview : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        emailEditText = findViewById(R.id.Email_login)
        passwordEditText = findViewById(R.id.Password_login)
        loginButton = findViewById(R.id.Button_login)
        signuptextview = findViewById(R.id.textview_goto_RegisterActivity)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener{
            loginuser()

        }
        signuptextview.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
            finish()
        }

    }

    private fun loginuser() {
        val email = emailEditText.editText?.text.toString()
        val password = passwordEditText.editText?.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main){
                        checkloggedinstate()
                    }

                }catch (e : Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@LogInActivity,"Logged in successfully ", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

    private fun checkloggedinstate() {
        if(auth.currentUser == null) { // not logged in
            Toast.makeText(this, "You are not logged in ", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "You are  logged in ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun onStart() {
        super.onStart()
        checkloggedinstate()
    }
}