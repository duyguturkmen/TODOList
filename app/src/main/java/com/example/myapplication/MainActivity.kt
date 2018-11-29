package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabButton: FloatingActionButton
    private lateinit var adapter: TodoAdapter

    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fabButton = findViewById(R.id.fab)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val list: MutableList<Todo> = ArrayList()
        adapter = TodoAdapter(list, this@MainActivity)
        recyclerView.adapter = adapter

        adapter.setClickListener(object : TodoAdapter.TodoListener {
            override fun checkBoxChecked(pos: Int) {
                Log.v("TODOOO", " $pos")

            }

            override fun click(todo: Todo) {
                Log.v("TODOOO", todo.toString())
                var intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("TEXT", todo.text)
                startActivity(intent)
            }
        })

        fabButton.setOnClickListener {
            openPopup()
        }
    }

    fun openPopup() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Title")
        builder.setMessage("MEsaaaaj")

        val editText = EditText(this@MainActivity)
        editText.hint = "TODO Ekle"
        val lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10, 0, 10, 0)
        editText.layoutParams = lp

        builder.setView(editText)

        builder.setPositiveButton(
            android.R.string.yes
        ) { dialog, which ->
            val text = editText.text

            adapter.add(Todo("$text $index", false))
            index++
            dialog.dismiss()
        }
        builder.setNegativeButton(android.R.string.no) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.show()
    }
}
