package com.example.assignment2_q4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var num1: EditText
    private lateinit var num2: EditText

    private lateinit var operatorGroup: RadioGroup

    private var a = 0.0
    private var b = 0.0
    private var res = 0.0
    private var op = '+'
    private var valid = true

    private lateinit var calcButton: Button
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.res)
        num1 = findViewById(R.id.num_1)
        num2 = findViewById(R.id.num_2)

        operatorGroup = findViewById(R.id.operators)
        operatorGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.plus -> op = '+'
                R.id.minus -> op = '-'
                R.id.mul -> op = '*'
                R.id.div -> op = '/'
                R.id.mod -> op = '%'
            }
        }

        calcButton = findViewById(R.id.calc_button)
        calcButton.setOnClickListener { view: View ->
            valid = true
            if (num1.text.toString().isEmpty() or num2.text.toString().isEmpty()) {
                Snackbar.make(
                    view,
                    R.string.empty_arg_err,
                    Snackbar.LENGTH_SHORT
                ).show()
                valid = false
            } else {
                a = num1.text.toString().toDouble()
                b = num2.text.toString().toDouble()
            }

            when (op) {
                '+' -> res = a + b
                '-' -> res = a - b
                '*' -> res = a * b
                '/' -> {
                    if (b == 0.0) {
                        Snackbar.make(
                            view,
                            R.string.div_by_zero_err,
                            Snackbar.LENGTH_SHORT
                        ).show()
                        valid = false
                    } else {
                        res = a / b
                    }
                }
                '%' -> {
                    if (b == 0.0) {
                        Snackbar.make(
                            view,
                            R.string.div_by_zero_err,
                            Snackbar.LENGTH_SHORT
                        ).show()
                        valid = false
                    } else {
                        res = a % b
                    }
                }
            }

            if (valid) {
                result.setText(res.toString())
            }
        }
    }
}