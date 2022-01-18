package com.krishana.androidhackathontemplates;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class WidgetsRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    String choiceString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets_recycler_view);

        Bundle bundle = getIntent().getExtras();
        choiceString = bundle.getString("tag");

        setRecyclerView();
    }

    private void setRecyclerView()
    {
        Query query = null;
        int choice = Integer.parseInt(choiceString);
        Log.e("tag",""+choice);
        switch(choice)
        {
            case 1:query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING).whereEqualTo("category","meat");break;
            case 2:query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING).whereEqualTo("category","dairy");break;
            case 3:query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING).whereEqualTo("category","fruit");break;
            case 4:query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING).whereEqualTo("category","leftovers");break;
            case 5:query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING).whereEqualTo("category","drinks");break;
            case 6:query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING).whereEqualTo("category","vegetables");break;
        }

        FirestoreRecyclerOptions<RecyclerViewData> options = new FirestoreRecyclerOptions.Builder<RecyclerViewData>()
                .setQuery(query, RecyclerViewData.class)
                .build();

        recyclerViewAdapter = new RecyclerViewAdapter(options);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recyclerViewAdapter.deleteData(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapter.stopListening();
    }
}