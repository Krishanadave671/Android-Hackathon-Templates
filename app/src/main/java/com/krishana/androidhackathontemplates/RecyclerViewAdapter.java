package com.krishana.androidhackathontemplates;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecyclerViewAdapter extends FirestoreRecyclerAdapter<RecyclerViewData,RecyclerViewAdapter.MyViewHolder>{

    public void deleteData(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    public RecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<RecyclerViewData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull RecyclerViewData model) {
        holder.expirtDateTextView.setText(String.valueOf(model.getExpiryDate()));
        holder.itemTextView.setText(model.getItem());
        holder.categoryTextView.setText(model.getCategory());
        if(model.getExpiryDate()<=0)
        {
            holder.expirtDateTextView.setTextColor(Color.RED);
            holder.itemTextView.setTextColor(Color.RED);
            holder.categoryTextView.setTextColor(Color.RED);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView itemTextView;
        private TextView expirtDateTextView;
        private TextView categoryTextView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item);
            expirtDateTextView = itemView.findViewById(R.id.expiryDateTextView);
            categoryTextView = itemView.findViewById(R.id.editTextCategory);
        }

    }

}
