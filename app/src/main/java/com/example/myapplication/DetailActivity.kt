package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    lateinit var detailsTextView: TextView
    lateinit var detailsCheckBox: CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detailsTextView = findViewById(R.id.detailTextView)
        detailsCheckBox = findViewById(R.id.detailCheckBox)

        var text = intent.getStringExtra("TEXT")
        detailsTextView.text = text

        Log.v("HELLO", text)//burada!
    }
}
