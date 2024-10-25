package com.example.myapplication.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Activity.DetailsActivity
import com.example.myapplication.Model.CategoryModel
import com.example.myapplication.Model.ItemsModel
import com.example.myapplication.databinding.ViewholderCategoryBinding
import com.example.myapplication.databinding.ViewholderRecommendBinding

class RecommendAdapter(val items:MutableList<ItemsModel>): RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {


    inner class RecommendViewHolder(val binding:ViewholderRecommendBinding):RecyclerView.ViewHolder(binding.root){}


    // implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ViewholderRecommendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding){
            titleText.text=item.title
            priceText.text="$${item.price}"
            ratingText.text=item.rating.toString()


            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(pic)


            root.setOnClickListener{

                val intent = Intent(holder.itemView.context,DetailsActivity::class.java).apply {
                    putExtra("object",item)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}