package com.example.profsoft2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.profsoft2024.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private var _binding: ActivityFirstBinding? = null
    private val binding: ActivityFirstBinding
        get() = _binding ?: throw RuntimeException("ActivityFirstBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnActivity2.setOnClickListener {
            startActivity(SecondActivity.newIntent(this, NOTIFICATION_TEXT))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val NOTIFICATION_TEXT = "Уведомление"
    }
}