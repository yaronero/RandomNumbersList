package com.example.randomnumberslist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.randomnumberslist.databinding.RandomNumberBinding

class RandomNumberViewHolder(
    private val binding: RandomNumberBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(number: Int) {
        binding.randomNumber.text = number.toString()
    }
}