package com.test.myapplication.ui.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.test.myapplication.R
import com.test.myapplication.adepter.recyclerview.CategoriesRecyclerViewAdepter
import com.test.myapplication.network.DataSource
import com.test.myapplication.network.Response.ApiResponse
import com.test.myapplication.network.Response.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesActivity : AppCompatActivity() {

    private lateinit var categoriesRecyclerView: RecyclerView
    var adepter: CategoriesRecyclerViewAdepter? = null
    var allData: ArrayList<Category>? = null
    lateinit var shimmerLayout:ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        initView()
        allData = ArrayList()
        showCategories(allData!!)
        shimmerLayout.startShimmer()
        shimmerLayout.visibility= View.VISIBLE
        apiCall()
    }

    private fun initView(){
        categoriesRecyclerView=findViewById(R.id.categoriesRecyclerView)
        shimmerLayout=findViewById(R.id.shimmerLayout)
    }

    private fun apiCall(){
        DataSource().getAllShoping().enqueue(object :Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val response=response.body()
                response?.let {
                    if (response!=null){
                        for (item in it.categories) {
                            allData?.add(item)
                            adepter?.notifyDataSetChanged()
                        }
                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility=View.GONE
                    }else{
                        Toast.makeText(this@CategoriesActivity, "No data found", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@CategoriesActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showCategories(categories: List<Category>) {
        adepter = CategoriesRecyclerViewAdepter(categories)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        categoriesRecyclerView.adapter = adepter
    }
}