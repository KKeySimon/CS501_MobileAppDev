package com.example.assignment3_q3

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var cSlider: SeekBar
    private lateinit var fSlider: SeekBar
    private lateinit var cNum: TextView
    private lateinit var fNum: TextView
    private lateinit var msg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cSlider = findViewById(R.id.celsius_seekbar)
        fSlider = findViewById(R.id.fahrenheit_seekbar)
        cNum = findViewById(R.id.celsius_num)
        fNum = findViewById(R.id.fahrenheit_num)
        msg = findViewById(R.id.interesting_msg)

        fSlider.progress = 32
        fNum.text = "32"

        cSlider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                cNum.setText("$progress")
                fSlider.progress = progress * 9 / 5 + 32
                fNum.setText((progress * 9 / 5 + 32).toString())
                if (progress > 20) {
                    msg.setText(R.string.hot_msg)
                } else {
                    msg.setText(R.string.cold_msg)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        fSlider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                fNum.setText("$progress")
                if (fSlider.progress >= 32) {
                    cSlider.progress = (progress - 32) * 5 / 9
                    cNum.setText(((progress - 32) * 5 / 9).toString())
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (seekBar.progress < 32.0) {
                    seekBar.progress = 32
                    fNum.text = "32"
                }
            }
        })
    }
}