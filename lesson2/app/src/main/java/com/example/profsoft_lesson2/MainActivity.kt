package com.example.profsoft_lesson2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.profsoft_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            Toast.makeText(this, "Open previous page...", Toast.LENGTH_LONG).show()
        }
        binding.btnChangeProfile.setOnClickListener {
            Toast.makeText(this, "Open edit profile page...", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}