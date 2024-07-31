package com.example.lesson5.ui.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lesson5.databinding.RecyclerViewFirstItemBinding
import com.example.lesson5.databinding.RecyclerViewSecondItemBinding
import com.example.lesson5.domain.models.Item

class RecyclerViewAdapter :
    ListAdapter<Item, RecyclerViewHolder>(ItemDiffCallback()) {

    var onItemClickListener: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType) {
            VIEW_TYPE_WITH_BG -> RecyclerViewHolder.ItemWithBGViewHolder(
                RecyclerViewSecondItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            VIEW_TYPE_WITHOUT_BG -> RecyclerViewHolder.ItemWithoutBGViewHolder(
                RecyclerViewFirstItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerViewHolder, position: Int) {
        val item = getItem(position)
        when (viewHolder) {
            is RecyclerViewHolder.ItemWithBGViewHolder -> viewHolder.bind(item, onItemClickListener)
            is RecyclerViewHolder.ItemWithoutBGViewHolder -> viewHolder.bind(
                item,
                onItemClickListener
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isBigItem) {
            VIEW_TYPE_WITH_BG
        } else {
            VIEW_TYPE_WITHOUT_BG
        }
    }

    companion object {
        const val VIEW_TYPE_WITH_BG = 101
        const val VIEW_TYPE_WITHOUT_BG = 100
    }
}
