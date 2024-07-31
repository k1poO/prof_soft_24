package com.example.lesson5.ui.main_screen

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lesson5.databinding.RecyclerViewFirstItemBinding
import com.example.lesson5.databinding.RecyclerViewSecondItemBinding
import com.example.lesson5.domain.models.Item

sealed class RecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class ItemWithBGViewHolder(private val binding: RecyclerViewSecondItemBinding) :
        RecyclerViewHolder(binding) {

        fun bind(item: Item, clickListener: ((Item) -> Unit)?) {
            binding.textViewItemTitle.text = item.name
            binding.root.setOnClickListener {
                clickListener?.invoke(item)
            }
        }
    }

    class ItemWithoutBGViewHolder(private val binding: RecyclerViewFirstItemBinding) :
        RecyclerViewHolder(binding) {

        fun bind(item: Item, clickListener: ((Item) -> Unit)?) {
            binding.textViewItemTitle.text = item.name
            binding.root.setOnClickListener {
                clickListener?.invoke(item)
            }
        }
    }
}