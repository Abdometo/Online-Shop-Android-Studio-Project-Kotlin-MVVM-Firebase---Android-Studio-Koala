package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderModelBinding

class SelectModeAdapter(val items:MutableList<String>):RecyclerView.Adapter<SelectModeAdapter.SelectModelViewHolder>() {

    // class parameters
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context:Context




    inner class SelectModelViewHolder(val binding:ViewholderModelBinding):RecyclerView.ViewHolder(binding.root)


    // implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectModelViewHolder {
        context = parent.context
        val binding = ViewholderModelBinding.inflate(LayoutInflater.from(context),parent,false)
        return SelectModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectModelViewHolder, position: Int) {
        holder.binding.modelText.text = items[position]


        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position){
            holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_selected)
            holder.binding.modelText.setTextColor(context.resources.getColor(R.color.green))
        }else{
            holder.binding.modelLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.modelText.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}