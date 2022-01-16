package com.krishana.androidhackathontemplates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView

class SignOutActivity : AppCompatActivity() {
    private  lateinit var category_EditText : AutoCompleteTextView
    private lateinit var  addItemEditText : EditText
    private lateinit var  expiryDate : TextView
    private lateinit var  saveTextView: TextView
    private lateinit var  cancelTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_out)

        category_EditText = findViewById(R.id.textfield_category)
        addItemEditText = findViewById(R.id.add_item_edit_text)
        expiryDate = findViewById(R.id.textview_date)
        saveTextView = findViewById(R.id.textView_save)
        cancelTextView = findViewById(R.id.textView_cancel)
        var days = resources.getStringArray(R.array.subgroups_fridge_items)
        val ArrayAdapter = ArrayAdapter(
           this,
            R.layout.dropdown, days
        )
        category_EditText.setAdapter(ArrayAdapter)
    }



    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}