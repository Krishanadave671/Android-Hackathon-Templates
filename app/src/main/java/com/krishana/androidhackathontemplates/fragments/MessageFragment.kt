package com.krishana.androidhackathontemplates.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import com.krishana.androidhackathontemplates.R

class MessageFragment : Fragment() {
        private  lateinit var category_EditText : AutoCompleteTextView
        private lateinit var  addItemEditText : EditText
        private lateinit var  expiryDate : TextView
        private lateinit var  saveTextView: TextView
        private lateinit var  cancelTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_message, container, false)
        category_EditText = view.findViewById(R.id.textfield_category)
        addItemEditText = view.findViewById(R.id.add_item_edit_text)
        expiryDate = view.findViewById(R.id.textview_date)
        saveTextView = view.findViewById(R.id.textView_save)
        cancelTextView = view.findViewById(R.id.textView_cancel)
        var days = resources.getStringArray(R.array.subgroups_fridge_items)
        val ArrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown, days
        )
        category_EditText.setAdapter(ArrayAdapter)
        return  view;

    }

}