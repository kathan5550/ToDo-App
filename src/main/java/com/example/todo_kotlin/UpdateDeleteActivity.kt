package com.example.todo_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo_kotlin.databinding.ActivityUpdateDeleteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateDeleteActivity : AppCompatActivity() {

    private lateinit var database: myDatabase
    private lateinit var binding: ActivityUpdateDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            binding.updatetask.setText(title)
            binding.updatepripority.setText(priority)

            binding.deletebtn.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deletetask(
                        Entity(
                            pos + 1,
                            binding.updatetask.text.toString(),
                            binding.updatepripority.text.toString()
                        )
                    )
                }
                myintent()
            }

            binding.updatebtn.setOnClickListener {
                DataObject.UpdateData(
                    pos,
                    binding.updatetask.text.toString(),
                    binding.updatepripority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updatetask(
                        Entity(
                            pos + 1,
                            binding.updatetask.text.toString(),
                            binding.updatepripority.text.toString()
                        )
                    )
                }
                myintent()
            }
        }

    }

    private fun myintent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}