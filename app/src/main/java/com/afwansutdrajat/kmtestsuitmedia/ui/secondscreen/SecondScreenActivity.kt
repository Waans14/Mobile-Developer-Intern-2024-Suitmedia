package com.afwansutdrajat.kmtestsuitmedia.ui.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.afwansutdrajat.kmtestsuitmedia.R
import com.afwansutdrajat.kmtestsuitmedia.data.local.SharedPref
import com.afwansutdrajat.kmtestsuitmedia.databinding.ActivitySecondScreenBinding
import com.afwansutdrajat.kmtestsuitmedia.ui.firstscreen.MainActivity
import com.afwansutdrajat.kmtestsuitmedia.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAction()
        setupName()
    }

    private fun setupAction() {
        binding.toolbar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            SharedPref.clearData(applicationContext)
        }

        binding.btnChoose.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupName() {
        val name = SharedPref.getName(applicationContext)
        binding.tvName.text = name

        val selected = SharedPref.getSelectedName(applicationContext)
        if (selected == ""){
            "Selected User Name".also { binding.tvSelected.text = it }
        }else{
            binding.tvSelected.text = selected
        }
    }

    override fun onPause() {
        super.onPause()
        SharedPref.clearData(applicationContext)
    }
}