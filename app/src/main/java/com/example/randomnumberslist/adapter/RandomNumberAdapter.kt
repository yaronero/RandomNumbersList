package com.example.randomnumberslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.randomnumberslist.databinding.RandomNumberBinding

class RandomNumberAdapter : ListAdapter<Int, RandomNumberViewHolder>(RandomNumberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomNumberViewHolder {
        val binding = RandomNumberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RandomNumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RandomNumberViewHolder, position: Int) {
        val number = currentList[position]
        holder.bind(number)
    }
}