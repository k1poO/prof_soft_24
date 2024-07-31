package com.example.lesson5.ui.main_screen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson5.databinding.ViewPagerItemBinding

class ViewPagerViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val binding = ViewPagerItemBinding.bind(item)

    fun bind(text: String) {
        binding.textViewViewPager.text = text
    }
}