package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderPicBinding

class PicAdapter(private val items: List<String>, private val onImageSelected: (String) -> Unit) : RecyclerView.Adapter<PicAdapter.PicViewHolder>() {

    private var selectedPosition = -1

    // View Holder
    inner class PicViewHolder(val binding: ViewholderPicBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                // Handle item click here
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onImageSelected(items[selectedPosition])
            }
        }
    }

    // Implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        val binding = ViewholderPicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.pic.loadImage(item)

        // Update background based on selection
        holder.binding.picLayout.setBackgroundResource(if (selectedPosition == position) R.drawable.green_bg_selected else R.drawable.grey_bg)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

// Load image function (could be moved to a utility file)
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}
