package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Model.ItemsModel
import com.example.myapplication.databinding.ViewholderCartBinding
import com.example.myapplication.databinding.ViewholderCategoryBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart
import java.lang.reflect.Array

class CartAdapter(private val listItemSelected:ArrayList<ItemsModel>,
                  context:Context,
    var changeNumberItemListener:ChangeNumberItemsListener
    ): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    inner class CartViewHolder(val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)

    private val managmentCart  = ManagmentCart(context)

    // implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = listItemSelected[position]



        holder.binding.titleText.text = item.title
        holder.binding.feeEachTime.text="$${item.showRecommend}"
        holder.binding.totalEachItem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemText.text = item.numberInCart.toString()


        Glide
            .with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.imgCart)


        // plus button
        holder.binding.plusButton.setOnClickListener{
            managmentCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }

            })
        }



        // minus button
        holder.binding.minusButton.setOnClickListener{
            managmentCart.minusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }

            })

        }


    }

    override fun getItemCount(): Int {
        return listItemSelected.size
    }
}