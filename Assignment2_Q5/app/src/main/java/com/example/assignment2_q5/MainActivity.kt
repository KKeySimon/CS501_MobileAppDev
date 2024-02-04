package com.example.assignment2_q5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlin.math.sqrt
import android.text.TextWatcher
import android.text.Editable
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var numButtons: Array<Button>
    private lateinit var dotButton: Button
    private lateinit var displayText: EditText
    private lateinit var doubleOpButtons: Array<Button>
    private lateinit var calcButton: Button
    private lateinit var sqrtButton: Button

    private var displayNum = 0.0
    private var dotEnabled = false
    private var decMultiplier = 10
    private var operator = '?'
    private var num1 = 0.0
    private var num2 = 0.0

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
        doubleOpButtons = arrayOf(
            findViewById(R.id.plus),
            findViewById(R.id.times),
            findViewById(R.id.minus),
            findViewById(R.id.div)
        )
        dotButton = findViewById(R.id.dot)
        displayText = findViewById(R.id.number)
        sqrtButton = findViewById(R.id.sqrt)
        calcButton = findViewById(R.id.equals)

        numButtons.forEach { num ->
            num.setOnClickListener { view: View ->
                if (!dotEnabled) {
                    displayNum *= 10
                    displayNum += num.text.toString().toInt()
                } else {
                    displayNum += num.text.toString().toDouble() / decMultiplier
                    decMultiplier *= 10
                }
                num1 = displayNum
                displayText.setText(displayNum.toString())
            }
        }

        // Figured out how to use this function with ChatGPT
        displayText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val inputText = s.toString()
                if (inputText.isNotEmpty()) {
                    displayNum = inputText.toDouble()
                } else {
                    displayNum = 0.0
                }
                num1 = displayNum
            }

            override fun afterTextChanged(s: Editable) {}
        })

        doubleOpButtons.forEach { op ->
            op.setOnClickListener { view: View ->
                if (num2 != 0.0) {
                    calc(view)
                }
                when (op.text) {
                    "+" -> operator = '+'
                    "-" -> operator = '-'
                    "*" -> operator = '*'
                    "/" -> operator = '/'
                }
                num2 = num1
                displayNum = 0.0
                num1 = displayNum
                dotEnabled = false
            }
        }

        dotButton.setOnClickListener { view: View ->
            dotEnabled = true
            decMultiplier = 10
        }
        sqrtButton.setOnClickListener { view: View ->
            dotEnabled = false
            decMultiplier = 10
            num2 = 0.0
            num1 = sqrt(num1)
            displayNum = num1
            displayText.setText(displayNum.toString())
            displayNum = 0.0
        }

        calcButton.setOnClickListener { view: View ->
            calc(view)
            num2 = 0.0
            operator = '?'
        }
    }
    private fun calc(view : View) {
        when (operator) {
            '+' -> displayNum = num2 + num1
            '-' -> displayNum = num2 - num1
            '*' -> displayNum = num2 * num1
            '/' -> {
                if (num1 == 0.0) {
                    Snackbar.make(
                        view,
                        R.string.div_by_zero_err,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    displayNum = 0.0
                } else {
                    displayNum = num2 / num1
                }
            }
            '?' -> {
                Snackbar.make(
                    view,
                    R.string.empty_op_err,
                    Snackbar.LENGTH_SHORT
                ).show()
                displayNum = 0.0
            }
        }
        num1 = displayNum
        displayText.setText(displayNum.toString())
        displayNum = 0.0
        dotEnabled = false
    }
}