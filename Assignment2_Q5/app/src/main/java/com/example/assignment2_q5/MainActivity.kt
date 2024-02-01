package com.example.assignment2_q5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var numButtons: Array<Button>

    private var display_num = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numButtons = arrayOf(
            findViewById(R.id.one),
            findViewById(R.id.two),
            findViewById(R.id.three),
            findViewById(R.id.four),
            findViewById(R.id.five),
            findViewById(R.id.six),
            findViewById(R.id.seven),
            findViewById(R.id.eight),
            findViewById(R.id.nine),
            findViewById(R.id.zero)
        )

        numButtons.forEach { num ->
            num.setOnClickListener { view: View ->
                Snackbar.make(
                    view,
                    num.text,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}