package com.krishana.androidhackathontemplates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class SettingsActivity : AppCompatActivity() {
    private lateinit var textInputLayoutFullname : TextInputLayout
    private lateinit var textInputLayoutUsername : TextInputLayout
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var githubTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var registerButton: Button
    private  lateinit var loginActivitytextview : TextView
    private  var email : String? = ""
    private  var password : String? = ""
    private var userName : String? = ""
    private  var fullname : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        textInputLayoutFullname = findViewById(R.id.Textinputlayout_fullname)
        textInputLayoutUsername = findViewById(R.id.Textinputlayout_username)
        emailTextInputLayout = findViewById(R.id.TextInputlayout_Email)
        githubTextInputLayout = findViewById(R.id.TextInputlayout_github)
        passwordTextInputLayout = findViewById(R.id.TextInputlayout_password)
        registerButton = findViewById(R.id.btn_Register)
        loginActivitytextview = findViewById(R.id.textview_goto_loginActivity)
        registerUser()
    }

    private fun registerUser() {
         email =    emailTextInputLayout.editText?.text.toString()
         password = passwordTextInputLayout.editText?.toString()
         fullname = textInputLayoutFullname.editText?.toString()
         userName = textInputLayoutUsername.editText?.toString()

    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    private  fun validatePassword() : Boolean{
        val checkPassword = Regex( "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{4,}" +  //at least 4 characters
                "$")

        return if (password!!.isEmpty()) {
            passwordTextInputLayout.setError("Field can not be empty")
            false
        } else if (!password !!.matches(checkPassword)) {
            passwordTextInputLayout.setError("Password should contain at least  4 characters!")
            false
        } else {
            passwordTextInputLayout.setError(null)
            passwordTextInputLayout.setErrorEnabled(false)
            true
        }
    }
    private fun validateEmail() : Boolean{
        val checkEmail = Regex("[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+")

        return if (email!!.isEmpty()) {
            emailTextInputLayout.setError("Field can not be empty")
            false
        } else if (!email!!.matches(checkEmail)) {
            emailTextInputLayout.setError("Invalid Email!")
            false
        } else {
            emailTextInputLayout.setError(null)
            emailTextInputLayout.setErrorEnabled(false)
            true
        }


    }

}