package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var helloButton: Button
    private lateinit var textBox: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloButton = findViewById(R.id.hello_button)
        textBox = findViewById(R.id.text_box)
        helloButton.setOnClickListener {
            textBox.text = getString(R.string.hello_text)
        }
    }
}