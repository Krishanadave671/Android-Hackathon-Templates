package com.krishana.androidhackathontemplates;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FireBaseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText foodItem,editTextCategory;
    TextView expiryDate;
    Button btn ;
    String expiry;
    long send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);


        foodItem = findViewById(R.id.foodItem);
        expiryDate = findViewById(R.id.expiryDate);
        btn = findViewById(R.id.button);
        editTextCategory = findViewById(R.id.editTextCategory);


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
                String category = editTextCategory.getText().toString();
                uploadData(food,send,category);
                foodItem.getText().clear();
                expiryDate.setText("Date");
                editTextCategory.getText().clear();
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

    public void uploadData(String food,long send,String category)
    {
        Map<String, Object> item = new HashMap<>();
        item.put("expiryDate",send);
        item.put("item",food);
        item.put("category",category);

        db.collection("yash")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(FireBaseActivity.this, "Item Uploaded!!", Toast.LENGTH_SHORT).show();
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
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date systemDate = new Date();
        String inputString1 = myFormat.format(systemDate);
        String inputString2 = i2+" "+(i1+1)+" "+i;

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            send = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        expiry = i+"/"+(i1+1)+"/"+i2;
        expiryDate.setText(expiry);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FireBaseActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();

    }
}