package com.example.myapplication.Activity

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.myapplication.Adapter.CategoryAdapter
import com.example.myapplication.Adapter.RecommendAdapter
import com.example.myapplication.Adapter.SliderAdapter
import com.example.myapplication.Model.SliderModel
import com.example.myapplication.R
import com.example.myapplication.ViewModel.MainViewModel
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    // Binding
    private lateinit var binding:ActivityMainBinding
    // View Model
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initBanner()
        initCategory()
        initRecommended()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartButton.setOnClickListener{
            startActivity(Intent(this@MainActivity,CartActivity::class.java))
        }
    }

    private fun initRecommended() {
        binding.recommendationProgressBar.visibility = View.VISIBLE
        viewModel.recommended.observe(this, Observer {
            binding.recommendationView.layoutManager=GridLayoutManager(this@MainActivity,2)
            binding.recommendationView.adapter=RecommendAdapter(it)
            binding.recommendationProgressBar.visibility=View.GONE
        })
        viewModel.loadRecommend()
    }

    private fun initCategory() {
        binding.categoryProgressBar.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer {
             binding.categoryView.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.categoryView.adapter=CategoryAdapter(it)
            binding.categoryProgressBar.visibility=View.GONE
        })
        viewModel.loadCategory()
    }


    private fun banner(image: List<SliderModel>){

        binding.viewPager2.adapter = SliderAdapter(image,binding.viewPager2)
        binding.viewPager2.clipToPadding=false
        binding.viewPager2.clipChildren=false
        binding.viewPager2.offscreenPageLimit=3
        binding.viewPager2.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer  =CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if(image.size>1){
            binding.dotsIndicator.visibility= View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPager2)
        }
    }

    private fun initBanner(){
        binding.progressBar.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer {
            banner(it)
            binding.progressBar.visibility=View.GONE
        })
        viewModel.loadBanner()
    }
}