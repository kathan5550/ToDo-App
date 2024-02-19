package com.example.todo_kotlin

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.todo_kotlin.databinding.ViewBinding
import java.util.Locale


class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CardInfo) {
            binding.title.text = data.title
            binding.priority.text = "Priority - " + data.priority

            when (data.priority.toLowerCase(Locale.getDefault())) {
                "high" -> binding.mylayout.setBackgroundColor(Color.parseColor("#bf3930"))
                "medium" -> binding.mylayout.setBackgroundColor(Color.parseColor("#54bf26"))
                else -> binding.mylayout.setBackgroundColor(Color.parseColor("#d9d029"))
            }

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, UpdateDeleteActivity::class.java)
                intent.putExtra("id", position)
                binding.root.context.startActivity(intent)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(data[position])
    }
}