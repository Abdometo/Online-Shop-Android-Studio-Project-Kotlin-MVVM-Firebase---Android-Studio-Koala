package com.example.myapplication.Adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Activity.ListItemsActivity
import com.example.myapplication.Model.CategoryModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderCategoryBinding

class CategoryAdapter(private val items: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class CategoryViewHolder(val binding: ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleText.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        // Update the view based on selection
        updateViewHolder(holder, position)

        holder.binding.root.setOnClickListener {
            val position = position
            if (position != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition=position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context,ListItemsActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)

            },1000)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun updateViewHolder(holder: CategoryViewHolder, position: Int) {
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.green_button_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.white)))
            holder.binding.titleText.visibility = View.VISIBLE
            holder.binding.titleText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.black)))
            holder.binding.titleText.visibility = View.GONE
            holder.binding.titleText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
        }
    }

    private fun updateSelection(position: Int) {
        val previousSelectedPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousSelectedPosition)
        notifyItemChanged(selectedPosition)
    }

    private fun launchListItemsActivity(holder: CategoryViewHolder, item: CategoryModel) {
        val intent = Intent(holder.itemView.context, ListItemsActivity::class.java).apply {
            putExtra("id", item.id.toString())
            putExtra("title", item.title)
        }
        ContextCompat.startActivity(holder.itemView.context, intent, null)
    }
}
