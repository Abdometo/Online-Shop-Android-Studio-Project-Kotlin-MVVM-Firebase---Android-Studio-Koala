package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.ListItemsAdapter
import com.example.myapplication.R
import com.example.myapplication.ViewModel.MainViewModel
import com.example.myapplication.databinding.ActivityListItemsBinding

class ListItemsActivity : BaseActivity() {
    // binding
    private lateinit var binding:ActivityListItemsBinding

    private val viewModel = MainViewModel()

    private var id:String = ""
    private var title:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBarList.visibility = View.VISIBLE
            viewModel.recommended.observe(this@ListItemsActivity, Observer {
                recyclerViewList.layoutManager=GridLayoutManager(this@ListItemsActivity,2)
                recyclerViewList.adapter=ListItemsAdapter(it)
                progressBarList.visibility = View.GONE

            })

            viewModel.loadFiltered(id)

        }
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryText.text = title
    }
}