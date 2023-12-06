package com.example.wia2007_p4q1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity_P4Q1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculate: Button = this.findViewById(R.id.BtnCalculate)
        btnCalculate.setOnClickListener {
            val num1: EditText = findViewById(R.id.ETNum1)
            val num2: EditText = findViewById(R.id.ETNum2)
            val tvResult: TextView = findViewById(R.id.tvResult)

            val value1 = num1.text.toString()
            val value2 = num2.text.toString()

            if (value1.isNotEmpty() && value2.isNotEmpty()) {
                val result = value1.toDouble() + value2.toDouble()
                tvResult.text = result.toString()
            } else {
                tvResult.text = "Please enter both numbers."
            }
        }
    }
}
