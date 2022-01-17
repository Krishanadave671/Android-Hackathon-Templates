package com.krishana.androidhackathontemplates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WidgetTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_test);
    }

    public void widgetSelector(View view)
    {
        String choice = view.getTag().toString();
        Intent intent = new Intent(WidgetTestActivity.this,WidgetsRecyclerView.class);
        intent.putExtra("tag",choice);
        startActivity(intent);

    }
}