package com.krishana.androidhackathontemplates;

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
        holder.expirtDateTextView.setText(model.getExpiryDate());
        holder.itemTextView.setText(model.getItem());
        holder.quantityTextView.setText(String.valueOf(model.getQuantity()));
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
        private TextView quantityTextView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item);
            expirtDateTextView = itemView.findViewById(R.id.expiryDateTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
        }

    }

}
