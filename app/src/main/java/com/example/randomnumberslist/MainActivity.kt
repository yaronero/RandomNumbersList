package com.example.randomnumberslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomnumberslist.adapter.RandomNumberAdapter
import com.example.randomnumberslist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        RandomNumberAdapter()
    }

    private val viewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        viewModel.randomNumberList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setupAdapter() {
        binding.rvRandomNumber.adapter = adapter
    }
}