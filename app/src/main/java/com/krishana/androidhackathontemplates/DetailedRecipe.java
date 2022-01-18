package com.krishana.androidhackathontemplates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailedRecipe extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<IngredientModel> IngredientList;

    private RecyclerView recyclerView1;
    private RecyclerView.Adapter adapter1;
    private List<RecipeModel1> RecipeList;

    String ingredient_url = "";
    String recipe_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe);

        recyclerView = (RecyclerView) findViewById(R.id.rc2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        IngredientList = new ArrayList<>();

        recyclerView1 = (RecyclerView) findViewById(R.id.rc3);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        RecipeList = new ArrayList<>();

        ImageView d_recipe_img = findViewById(R.id.d_recipe_img);
        TextView d_recipe_name = findViewById(R.id.d_recipe_name);

        Bundle bundle = getIntent().getExtras();
        String D_title = bundle.getString("title");
        String D_image = bundle.getString("image");
        int D_id = bundle.getInt("id");

        Picasso.get().load(D_image).into(d_recipe_img);
        d_recipe_name.setText(D_title);

        ingredient_url = "https://api.spoonacular.com/recipes/"+D_id+"/ingredientWidget.json?apiKey=44299400980a47ffb14faf9e93af2e79";
        recipe_url = "https://api.spoonacular.com/recipes/"+D_id+"/analyzedInstructions?apiKey=44299400980a47ffb14faf9e93af2e79";


        loadRecyclerView();
        loadRecyclerView1();
    }

    private void loadRecyclerView() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING DATA....... ");
        progressDialog.show();

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, ingredient_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    //getting data  from json object
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("ingredients");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        JSONObject o1 = o.getJSONObject("amount");
                        JSONObject o2 = o1.getJSONObject("us");
                        IngredientModel item = new IngredientModel(
                                o.getString("name"),
                                o2.getString("value"),
                                o2.getString("unit")
                        );
//                        Log.e("tag",o.getString("name"));
//                        Log.e("tag",o.getString("image"));
//                        Log.e("tag",o2.getString("value"));
//                        Log.e("tag",o2.getString("unit"));

                        IngredientList.add(item);

                    }
                    adapter = new IngredientAdapter(IngredientList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "oops!! something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(stringRequest1);
    }


    private void loadRecyclerView1() {
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("LOADING DATA....... ");
//        progressDialog.show();

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, recipe_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                try {
                    //getting data  from json object
                    JSONArray jsonArray = new JSONArray(response);
                    //JSONObject jsonObject = jsonArray.JSONObject("");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        JSONArray a = o.getJSONArray("steps");

                        for(int j = 0; j< a.length(); j++ ){
                            JSONObject o1 = a.getJSONObject(j);
                            RecipeModel1 item1 = new RecipeModel1(
                                    o1.getString("number"),
                                    o1.getString("step")
                            );
                            RecipeList.add(item1);}



//
                    }
                    adapter1 = new RecipeAdapter1(RecipeList, getApplicationContext());
                    recyclerView1.setAdapter(adapter1);
                    //adapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "oops!! something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest2);
    }
}