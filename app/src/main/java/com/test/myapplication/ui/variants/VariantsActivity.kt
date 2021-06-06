package com.test.myapplication.ui.variants

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.test.myapplication.R
import com.test.myapplication.network.DataSource
import com.test.myapplication.network.Response.Product
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VariantsActivity : AppCompatActivity() {

    private lateinit var productName:TextView
    private lateinit var priceTV:TextView
    private lateinit var selectColor:TextView
    private lateinit var sizeTV:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_variants)
        initView()
    }

    private fun initView(){
        productName=findViewById(R.id.productName)
        priceTV=findViewById(R.id.priceTV)
        selectColor=findViewById(R.id.selectColor)
        sizeTV=findViewById(R.id.sizeTV)
        val intent=intent
        val product=intent.getStringExtra("productName")
        val productPrice=intent.getStringExtra("productPrice")
        val productColor=intent.getStringExtra("productColor")
        val productSize=intent.getStringExtra("productSize")
        productName.text=product.toString()
        priceTV.text=productPrice.toString()
        selectColor.text=productColor.toString()
        sizeTV.text=productSize.toString()
    }
}