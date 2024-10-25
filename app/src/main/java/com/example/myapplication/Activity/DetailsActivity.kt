package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.Adapter.PicAdapter
import com.example.myapplication.Adapter.SelectModeAdapter
import com.example.myapplication.Model.ItemsModel
import com.example.myapplication.databinding.ActivityDetailsBinding
import com.example.project1762.Helper.ManagmentCart

class DetailsActivity() : BaseActivity() {
    // binding
    lateinit var binding:ActivityDetailsBinding
    // Items Model
    private lateinit var item:ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart : ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)


        getBundle()
        initList()



    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleText.text = item.title
        binding.descriptionText.text = item.description
        binding.titleText.text = "$" + item.price
        binding.titleText.text = "${item.rating} Rating"
        binding.addToCartButton.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertItem(item)
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.cartButton.setOnClickListener {
            startActivity(Intent(this@DetailsActivity,CartActivity::class.java))
        }
    }


    private fun initList() {
        val modelList = ArrayList<String>()

        for (models in item.model) {
            modelList.add(models)
        }

        binding.modelList.adapter = SelectModeAdapter(modelList)
        binding.modelList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val picList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            picList.add(imageUrl)
        }

        Glide
            .with(this)
            .load(picList[0])
            .into(binding.img)


        binding.picList.adapter = PicAdapter(picList) { selectedImageUrl ->

            Glide
                .with(this)
                .load(selectedImageUrl)
                .into(binding.img)



        }

        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }



}