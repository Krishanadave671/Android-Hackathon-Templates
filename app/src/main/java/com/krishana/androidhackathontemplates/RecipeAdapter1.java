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

public class RecipeAdapter1 extends RecyclerView.Adapter<RecipeAdapter1.ViewHolder>{
    private List<RecipeModel1> RecipeList;
    private Context context;

    public RecipeAdapter1(List<RecipeModel1> RecipeList, Context context) {
        this.RecipeList = RecipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_step_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter1.ViewHolder holder, int position) {
        RecipeModel1 RecipeModel1 = RecipeList.get(position);
        holder.A_step_number.setText(RecipeModel1.getNumber());
        //Picasso.get().load(IngredientModel.getImage()).into(holder.A_ingredient_img);
        holder.A_step_detail.setText( RecipeModel1.getStep());


    }

    @Override
    public int getItemCount() {
        return RecipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //define view objects
        CardView A_cv3;
        TextView A_step_number, A_step_detail ;
        //ImageView A_ingredient_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            A_cv3 = itemView.findViewById(R.id.cv3);
            A_step_number = itemView.findViewById(R.id.step_number);
            // A_ingredient_img = itemView.findViewById(R.id.ingredient_img);
            A_step_detail = itemView.findViewById(R.id.step_details);



        }
    }
}
