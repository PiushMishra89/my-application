package com.test.myapplication.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.test.myapplication.R
import com.test.myapplication.adepter.recyclerview.ProductRecyclerViewAdepter
import com.test.myapplication.network.DataSource
import com.test.myapplication.network.Response.ApiResponse
import com.test.myapplication.network.Response.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private lateinit var productRecyclerView: RecyclerView
    var adepter: ProductRecyclerViewAdepter? = null
    var allData: ArrayList<Product>? = null
    var gridLayoutManager: GridLayoutManager? = null
    lateinit var shimmerLayout: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        initView()
        shimmerLayout.startShimmer()
        shimmerLayout.visibility= View.VISIBLE
        allData = ArrayList()
        showProduct(allData!!)
        apiCall()
    }

    private fun initView() {
        productRecyclerView = findViewById(R.id.productRecyclerView)
        shimmerLayout=findViewById(R.id.shimmerLayout)
    }

    private fun apiCall() {
        DataSource().getAllShoping().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val response = response.body()
                val intent=intent
                val index=intent.getIntExtra("index",0)
                response.let {
                    if (response!=null){
                        for (item in it!!.categories[index].products) {
                            allData?.add(item)
                            adepter?.notifyDataSetChanged()
                        }
                    }else{
                        Toast.makeText(this@ProductActivity, "No data found", Toast.LENGTH_SHORT).show()
                    }
                }
                shimmerLayout.stopShimmer()
                shimmerLayout.visibility=View.GONE
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@ProductActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showProduct(product: List<Product>) {
        adepter = ProductRecyclerViewAdepter(product)
        gridLayoutManager =
            GridLayoutManager(this,2, RecyclerView.VERTICAL, false)
        productRecyclerView.layoutManager=gridLayoutManager
        productRecyclerView.adapter = adepter
    }
}