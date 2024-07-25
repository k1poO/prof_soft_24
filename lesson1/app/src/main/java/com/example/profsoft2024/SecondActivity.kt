package com.example.profsoft2024

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.profsoft2024.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private var _binding: ActivitySecondBinding? = null
    private val binding: ActivitySecondBinding
        get() = _binding ?: throw RuntimeException("ActivitySecondBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = intent.getStringExtra(EXTRA_KEY)

        binding.buttonNotification.setOnClickListener {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val EXTRA_KEY = "text"

        fun newIntent(context: Context, text: String): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_KEY, text)
            return intent
        }
    }
}