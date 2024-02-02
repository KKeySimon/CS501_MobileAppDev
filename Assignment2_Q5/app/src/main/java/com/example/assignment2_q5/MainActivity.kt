package com.example.assignment2_q5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var numButtons: Array<Button>
    private lateinit var dotButton: Button
    private lateinit var displayText: TextView
    private lateinit var doubleOpButtons: Array<Button>
    private lateinit var calcButton: Button
    private lateinit var sqrtButton: Button

    private var displayNum = 0.0
    private var dotEnabled = false
    private var decMultiplier = 10
    private var operator = '?'
    private var num1 = 0.0

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
                    displayText.text = displayNum.toString()
                } else {
                    displayNum += num.text.toString().toDouble() / decMultiplier
                    decMultiplier *= 10
                    displayText.text = displayNum.toString()
                }
            }
        }

        doubleOpButtons.forEach { op ->
            op.setOnClickListener { view: View ->
                when (op.text) {
                    "+" -> operator = '+'
                    "-" -> operator = '-'
                    "*" -> operator = '*'
                    "/" -> operator = '/'
                }
                num1 = displayNum
                displayNum = 0.0
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
            displayNum = sqrt(displayNum)
            displayText.text = displayNum.toString()
        }

        calcButton.setOnClickListener { view: View ->
            when (operator) {
                '+' -> displayText.text = (num1 + displayNum).toString()
                '-' -> displayText.text = (num1 - displayNum).toString()
                '*' -> displayText.text = (num1 * displayNum).toString()
                '/' -> {
                    if (displayNum == 0.0) {
                        Snackbar.make(
                            view,
                            R.string.div_by_zero_err,
                            Snackbar.LENGTH_SHORT
                        ).show()
                        displayText.text = 0.toString()
                    } else {
                        displayText.text = (num1 / displayNum).toString()
                    }
                }
                '?' -> {
                    Snackbar.make(
                        view,
                        R.string.empty_op_err,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    displayText.text = 0.0.toString()
                }
            }
            displayNum = 0.0
            operator = '?'
            dotEnabled = false
        }
    }
}