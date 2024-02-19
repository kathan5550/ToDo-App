package com.example.todo_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo_kotlin.databinding.ActivityCreateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCardActivity : AppCompatActivity() {

    private lateinit var database:myDatabase
    private lateinit var binding:ActivityCreateCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCreateCardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()


       binding.savebtn.setOnClickListener{
           if( binding.entertask.text.toString().trim{ it<=' '}.isNotEmpty()&& binding.enterpriority.text.toString().trim{it<=' '}.isNotEmpty())
           {
               var title=binding.entertask.getText().toString()
               var priority=binding.enterpriority.getText().toString()
               DataObject.setData(title,priority)

               GlobalScope.launch{
                   database.dao().inserttask(Entity(0,title,priority))
                   Log.i("kathan",database.dao().getTasks().toString())
               }

               val intent= Intent(this,MainActivity::class.java)
               startActivity(intent)
           }

       }


    }
}