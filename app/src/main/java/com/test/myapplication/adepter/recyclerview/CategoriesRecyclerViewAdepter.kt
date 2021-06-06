package com.test.myapplication.adepter.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.R
import com.test.myapplication.network.Response.Category
import com.test.myapplication.ui.products.ProductActivity

class CategoriesRecyclerViewAdepter(private val allCategory: List<Category>) :
    RecyclerView.Adapter<CategoriesRecyclerViewAdepter.AllCategorisViewHolder>() {

    class AllCategorisViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val categoriesName=view.findViewById<TextView>(R.id.categoriesName)
        val categoriesLayout=view.findViewById<ConstraintLayout>(R.id.categoriesLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategorisViewHolder {
        return AllCategorisViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.categories_cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllCategorisViewHolder, position: Int) {

        val category=allCategory[position]
        holder.categoriesName.text= category.name
        holder.categoriesLayout.setOnClickListener {
            val intent=Intent(holder.view.context,ProductActivity::class.java)
            intent.putExtra("index", position)
            holder.view.context.startActivity(intent)
        }
        //onClick(holder)
    }

    override fun getItemCount(): Int {
        if (allCategory != null) {
            return allCategory.size
        }
        return 0
    }

    private fun onClick(holder: AllCategorisViewHolder){
        holder.categoriesLayout.setOnClickListener {
            val intent=Intent(holder.view.context,ProductActivity::class.java)
            holder.view.context.startActivity(intent)
        }
    }
}