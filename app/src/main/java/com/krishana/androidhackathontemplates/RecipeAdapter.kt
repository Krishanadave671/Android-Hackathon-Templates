package com.krishana.androidhackathontemplates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide


class recipeAdapter(val viewPager : ViewPager2, private val list : ArrayList<recipeModel>, private val context: Context) :
    RecyclerView.Adapter<recipeAdapter.ViewHolder>() {
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView_sandwich)
        val textView: TextView = itemView.findViewById(R.id.textView_sandwich)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.home_fragment_recipes_story, parent, false)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = list.get(position)
        Glide.with(context).load(ItemsViewModel.image).into(holder.imageView)
        holder.textView.setText(ItemsViewModel.title)

        if (position == list.size -2){
            viewPager.post(run)
        }
    }
    private val run = Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}