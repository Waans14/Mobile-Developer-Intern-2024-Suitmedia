package com.afwansutdrajat.kmtestsuitmedia.ui.thirdscreen

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.afwansutdrajat.kmtestsuitmedia.R
import com.afwansutdrajat.kmtestsuitmedia.databinding.ActivityThirdScreenBinding
import com.afwansutdrajat.kmtestsuitmedia.ui.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding

    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAction()
        setupListUser()
    }

    private fun setupAction() {
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupListUser() {
        showLoading(true)
        val userAdapter = UserAdapter()
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = userAdapter
        viewModel.getUser().observe(this) {
            showLoading(false)
            userAdapter.submitData(lifecycle, it)
        }
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}