package com.example.counter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)

        val editTextTodo = findViewById<EditText>(R.id.edit_text_todo)
        val buttonAddTodo = findViewById<Button>(R.id.button_add_todo)
        val buttonViewTodos = findViewById<Button>(R.id.button_view_todos)

        buttonAddTodo.setOnClickListener {
            val todo = editTextTodo.text.toString().trim()
            if (todo.isNotEmpty()) {
                addTodoToList(todo)
                editTextTodo.text.clear()
            }
        }

        buttonViewTodos.setOnClickListener {
            val todoList = getStoredTodos().toList()
            startActivity(Intent(this, DisplayTodoActivity::class.java).apply {
                putStringArrayListExtra("todo_list", ArrayList(todoList))
            })
        }
    }

    private fun addTodoToList(todo: String) {
        val todoSet = sharedPreferences.getStringSet("todo_list", mutableSetOf()) ?: mutableSetOf()
        todoSet.add(todo)
        sharedPreferences.edit().putStringSet("todo_list", todoSet).apply()
    }

    private fun getStoredTodos(): Set<String> {
        return sharedPreferences.getStringSet("todo_list", mutableSetOf()) ?: emptySet()
    }
}