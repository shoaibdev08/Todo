package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_todo)

        val textViewTodoList = findViewById<TextView>(R.id.text_view_todo_list)
        val todoList = intent.getStringArrayListExtra("todo_list") ?: arrayListOf()
        textViewTodoList.text = todoList.joinToString("\n")
    }
}