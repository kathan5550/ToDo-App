package com.example.todo_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.todo_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: myDatabase
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        binding.plusbtn.setOnClickListener {
            val intent = Intent(this, CreateCardActivity::class.java)
            startActivity(intent)
        }

        binding.deleteall.setOnClickListener {
            DataObject.deleteall()
            GlobalScope.launch {
                database.dao().deleteall()
            }
            setrecycler()
        }

        setrecycler()


    }

    fun setrecycler() {
        binding.myrecycler.adapter = Adapter(DataObject.getAllData())
        binding.myrecycler.layoutManager = LinearLayoutManager(this)

    }
}