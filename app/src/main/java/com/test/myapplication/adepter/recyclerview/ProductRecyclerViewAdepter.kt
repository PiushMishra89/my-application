package com.test.myapplication.adepter.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.R
import com.test.myapplication.network.Response.Product
import com.test.myapplication.ui.variants.VariantsActivity

class ProductRecyclerViewAdepter(private val allProducts:List<Product>):RecyclerView.Adapter<ProductRecyclerViewAdepter.ProductViewHolder>() {

    class ProductViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val productName=view.findViewById<TextView>(R.id.productName)
        val productLayout=view.findViewById<ConstraintLayout>(R.id.productLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_cardview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product=allProducts[position]
        holder.productName.text=product.name
        holder.productLayout.setOnClickListener {
            val intent=Intent(holder.view.context,VariantsActivity::class.java)
            intent.putExtra("productName",product.name)
            intent.putExtra("productPrice",product.variants[0].price.toString())
            intent.putExtra("productColor",product.variants[0].color)
            intent.putExtra("productSize",product.variants[0].size.toString())
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        if (allProducts!=null){
            return allProducts.size
        }
        return 0
    }
}