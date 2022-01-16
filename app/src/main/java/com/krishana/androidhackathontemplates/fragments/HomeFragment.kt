package com.krishana.androidhackathontemplates.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.krishana.androidhackathontemplates.*
import org.json.JSONArray
import org.json.JSONException
import java.lang.Math.abs

class HomeFragment : Fragment() {
    private lateinit var list : ArrayList<recipeModel>
    private lateinit var adapter : recipeAdapter
    private lateinit var viewPagerImgSlider: ViewPager2
    private lateinit var sliderHandle: Handler
    private lateinit var sliderRun :Runnable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewPagerImgSlider = view.findViewById(R.id.viewPagerImgSlider)
        loadrecyclerviewData()


        return view
    }



    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)

    }


    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun,2000)
    }

    private fun loadrecyclerviewData() {
        list = ArrayList()
        adapter = recipeAdapter(viewPagerImgSlider,list, requireContext())
        viewPagerImgSlider.adapter = adapter
        viewPagerImgSlider.clipToPadding = false
        viewPagerImgSlider.clipChildren = false
        viewPagerImgSlider.offscreenPageLimit = 1
        viewPagerImgSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPagerImgSlider.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            viewPagerImgSlider.currentItem = viewPagerImgSlider.currentItem + 1
        }

        viewPagerImgSlider.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun,2000)
                }
            })


        val stringRequest = StringRequest(
            Request.Method.GET, "https://api.spoonacular.com/recipes/findByIngredients?apiKey=44299400980a47ffb14faf9e93af2e79&ingredients="+ "carrot",
            { response ->

                try {
                    //getting data  from json object
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val o = jsonArray.getJSONObject(i)
                        val item = recipeModel(
                            o.getString("title"),
                            o.getString("image")
                        )
                        list.add(item)
                    }




                    //adapter.notifyDataSetChanged();
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) {
            Toast.makeText(
                requireContext(),
                "oops!! something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }
        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(stringRequest)

    }

}