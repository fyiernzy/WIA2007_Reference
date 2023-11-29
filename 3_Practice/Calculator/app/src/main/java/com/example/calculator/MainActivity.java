package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String display = "";

    MaterialButton btnOne, btnTwo, btnThree, btnFour, btnFive,
            btnSix, btnSeven, btnEight, btnNine, btnZero;
    MaterialButton btnClear, btnAC, btnDot, btnLeftParentheses, btnRightParentheses;
    MaterialButton btnPlus, btnMinus, btnMul, btnDiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvUserInput = findViewById(R.id.tvUserInput);
        MaterialButton btnOne = findViewById(R.id.btnOne);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += btnOne.getText().toString();
                tvUserInput.setText(display);
            }
        });
    }

    @Override
    public void onClick(View view) {
        
    }
}
