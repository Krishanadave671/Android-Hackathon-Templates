package com.krishana.androidhackathontemplates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    private List<IngredientModel> IngredientList;
    private Context context;

    public IngredientAdapter(List<IngredientModel> IngredientList, Context context) {
        this.IngredientList = IngredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_layout, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        IngredientModel IngredientModel = IngredientList.get(position);
        holder.A_ingredient_name.setText(IngredientModel.getName());
        //Picasso.get().load(IngredientModel.getImage()).into(holder.A_ingredient_img);
        holder.A_value.setText( IngredientModel.getValue());
        holder.A_unit.setText(IngredientModel.getUnit());



    }

    @Override
    public int getItemCount() {
        return IngredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //define view objects
        CardView A_cv2;
        TextView A_ingredient_name, A_value, A_unit ;
        //ImageView A_ingredient_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            A_cv2 = itemView.findViewById(R.id.cv2);
            A_ingredient_name = itemView.findViewById(R.id.ingredient_name);
            // A_ingredient_img = itemView.findViewById(R.id.ingredient_img);
            A_unit = itemView.findViewById(R.id.unit);
            A_value = itemView.findViewById(R.id.value);


        }
    }
}
