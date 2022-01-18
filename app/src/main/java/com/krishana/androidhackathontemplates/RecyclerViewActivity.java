package com.krishana.androidhackathontemplates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener)(new NavigationBarView.OnItemSelectedListener() {
            public final boolean onNavigationItemSelected(@NotNull MenuItem it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Class var10000;
                switch(it.getItemId()) {
                    case 1000291:
                        var10000 = RecyclerViewActivity.class;
                        break;
                    default:
                        var10000 = MainActivity.class;
                }

                Class destinationActivity = var10000;
                if(it.getItemId()!=R.id.nav_items){
                    RecyclerViewActivity.this.startActivity(new Intent((Context)RecyclerViewActivity.this, destinationActivity));

                }
                return true;
            }
        }));
        FloatingActionButton addButton = (FloatingActionButton)this.findViewById(R.id.add_items);
        addButton.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                RecyclerViewActivity.this.startActivity(new Intent((Context)RecyclerViewActivity.this, FireBaseActivity.class));
            }
        }));



        setRecyclerView();
    }

    private void setRecyclerView()
    {
        Query query = FirebaseFirestore.getInstance()
                .collection("yash")
                .orderBy("expiryDate", Query.Direction.ASCENDING);

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
                Toast.makeText(RecyclerViewActivity.this, "Item Deleted!!", Toast.LENGTH_SHORT).show();
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