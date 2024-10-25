package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    // binding
    lateinit var binding:ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // start button
        binding.startButton.setOnClickListener{
            startActivity(Intent(this@IntroActivity , MainActivity::class.java))
        }
    }
}