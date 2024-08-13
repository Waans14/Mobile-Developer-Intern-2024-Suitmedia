package com.afwansutdrajat.kmtestsuitmedia.ui.firstscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.afwansutdrajat.kmtestsuitmedia.R
import com.afwansutdrajat.kmtestsuitmedia.data.local.SharedPref
import com.afwansutdrajat.kmtestsuitmedia.databinding.ActivityMainBinding
import com.afwansutdrajat.kmtestsuitmedia.ui.secondscreen.SecondScreenActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAction()
        setupAnimation()
    }

    private fun setupAction() {
        binding.btnCheck.setOnClickListener {
            val text = binding.etPalindrom.text.toString()

            if (text.isEmpty()){
                binding.etPalindrom.error = "Text is required to check it is palindrome or not"
            }else{
                val isPalindrome = isPalindrome(text)

                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle(if (isPalindrome) "isPalindrome" else "not palindrome")
                alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }

        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()

            if (name.isEmpty()) {
                binding.etName.setError("Name is required")
            }else{
                SharedPref.setName(applicationContext, name)
                val intent = Intent(this, SecondScreenActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun isPalindrome(str: String): Boolean {
        val cleanedStr = str.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
        return cleanedStr == cleanedStr.reversed()
    }

    private fun setupAnimation() {
        ObjectAnimator.ofFloat(binding.ivImage, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val ivImage = ObjectAnimator.ofFloat(binding.ivImage, View.ALPHA, 1f).setDuration(300)
        val cvName = ObjectAnimator.ofFloat(binding.cvName, View.ALPHA, 1f).setDuration(300)
        val cvPalindrom = ObjectAnimator.ofFloat(binding.cvPalindrom, View.ALPHA, 1f).setDuration(300)
        val btnCheck = ObjectAnimator.ofFloat(binding.btnCheck, View.ALPHA, 1f).setDuration(300)
        val btnNext = ObjectAnimator.ofFloat(binding.btnNext, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(
                ivImage,
                cvName,
                cvPalindrom,
                btnCheck,
                btnNext
            )
            startDelay = 100
        }.start()
    }
}