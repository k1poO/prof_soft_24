package com.example.lesson5.ui.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson5.databinding.ActivityMainBinding
import com.example.lesson5.domain.models.Item
import com.example.lesson5.ui.extension.toPx
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private var maxItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupViewPagerAndTabLayout()
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerViewMainScreen.adapter = recyclerViewAdapter
        setupClickListeners()
        loadItems()
    }

    private fun setupClickListeners() {
        recyclerViewAdapter.onItemClickListener = { item ->
            val newList = recyclerViewAdapter.currentList.toMutableList().apply {
                remove(item)
            }
            recyclerViewAdapter.submitList(newList)
        }

        binding.buttonAddItem.setOnClickListener {
            val newList = recyclerViewAdapter.currentList.toMutableList().apply {
                add(generateItem(maxItemId))
            }
            recyclerViewAdapter.submitList(newList)
        }
    }

    private fun loadItems() {
        val items = mutableListOf<Item>()
        for (i in 0..4) {
            items.add(generateItem(items.size))
        }
        recyclerViewAdapter.submitList(items)
    }

    private fun setupViewPagerAndTabLayout() {
        val viewPagerAdapter = ViewPagerAdapter()
        with(binding.viewPagerMainScreen) {
            adapter = viewPagerAdapter
            viewPagerAdapter.setItems(
                listOf(
                    "item 1",
                    "item 2",
                    "item 3",
                    "item 4",
                )
            )

            addItemDecoration(ViewPagerItemDecoration(64.toPx()))
            (getChildAt(0) as RecyclerView).apply {
                clipToPadding = false
                setPadding(12.toPx(), 0, 12.toPx(), 0)
            }
        }
        TabLayoutMediator(
            binding.tabLayoutMainScreen,
            binding.viewPagerMainScreen
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "item 1"
                1 -> tab.text = "item 2"
                2 -> tab.text = "item 3"
                3 -> tab.text = "item 4"
            }
        }.attach()
        binding.springDotsIndicatorMainScreen.setViewPager2(binding.viewPagerMainScreen)
    }

    private fun generateItem(size: Int): Item {
        maxItemId++
        return Item(id = size, name = "Item $size", Random.nextBoolean())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}