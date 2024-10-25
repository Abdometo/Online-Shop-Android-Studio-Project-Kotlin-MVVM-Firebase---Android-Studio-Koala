package com.example.myapplication.Activity

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.CartAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BaseActivity() {

    // binding
    private lateinit var binding:ActivityCartBinding
    // management Cart
    private lateinit var managementCart:ManagmentCart

    private var tax:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)


        setVariable()
        calculatorCart()
        initCartList()

    }

    private fun initCartList() {
        binding.viewCart.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.viewCart.adapter  = CartAdapter(managementCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculatorCart()
            }
        })
        with(binding){
            emptyText.visibility = if (managementCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility = if(managementCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setVariable() {

        binding.apply {

            // back button
            binding.backButton.setOnClickListener{
                finish()
            }
            // Method 1 payment
            method1.setOnClickListener{
                method1.setBackgroundResource(R.drawable.green_bg_selected)
                methodIc1.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.green))
                methodTitle1.setTextColor(getResources().getColor(R.color.green))
                methodSubtitle1.setTextColor(getResources().getColor(R.color.green))


                method2.setBackgroundResource(R.drawable.grey_bg_selected)
                methodIc2.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.black))
                methodTitle2.setTextColor(getResources().getColor(R.color.black))
                methodSubtitle2.setTextColor(getResources().getColor(R.color.grey))
            }


            method2.setOnClickListener {
                method2.setBackgroundResource(R.drawable.green_bg_selected)
                methodIc2.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.green))
                methodTitle2.setTextColor(getResources().getColor(R.color.green))
                methodSubtitle2.setTextColor(getResources().getColor(R.color.green))


                method1.setBackgroundResource(R.drawable.grey_bg_selected)
                methodIc1.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.black))
                methodTitle1.setTextColor(getResources().getColor(R.color.black))
                methodSubtitle1.setTextColor(getResources().getColor(R.color.grey))

            }
        }
    }


    private fun calculatorCart(){
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100.0
        val itemTotal = Math.round(managementCart.getTotalFee()*100)/100


        with(binding){
            totalFeeText.text = "$$itemTotal"
            taxText.text ="$$tax"
            deliveryText.text = "$$delivery"
            totalText.text = "$$total"

        }
    }
}