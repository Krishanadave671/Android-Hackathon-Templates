package com.krishana.androidhackathontemplates;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FireBaseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText foodItem,editTextNumber;
    TextView expiryDate;
    Button btn ;
    String expiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);


        foodItem = findViewById(R.id.foodItem);
        expiryDate = findViewById(R.id.expiryDate);
        btn = findViewById(R.id.button);
        editTextNumber = findViewById(R.id.editTextNumber);


        expiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String food = foodItem.getText().toString();
                int quantity = Integer.parseInt(editTextNumber.getText().toString());
                uploadData(food,expiry,quantity);
                foodItem.getText().clear();
                expiryDate.setText("Date");
                editTextNumber.getText().clear();
            }
        });


    }

    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONDAY),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void uploadData(String food,String expiry,int quantity)
    {
        Map<String, Object> item = new HashMap<>();
        item.put("expiryDate",expiry);
        item.put("item",food);
        item.put("quantity",quantity);

        db.collection("yash")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("tag", "Error adding document", e);
                    }
                });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        expiry = i+"/"+(i1+1)+"/"+i2;
        expiryDate.setText(expiry);
    }


}