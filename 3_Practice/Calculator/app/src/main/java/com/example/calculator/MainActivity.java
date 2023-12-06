package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    private StringBuilder userInput = new StringBuilder();
    private String calculation;
    TextView tvUserInput;
    TextView tvCalculation;

    MaterialButton btnClear, btnAC, btnEqual;

    private static final int[] NUMBER_BUTTON_IDS = {
            R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive,
            R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnZero,
            R.id.btnDot
    };

    private static final int[] OPERATOR_BUTTON_IDS = {
            R.id.btnPlus, R.id.btnMinus, R.id.btnMul, R.id.btnDiv,
            R.id.btnLeftParentheses, R.id.btnRightParentheses
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        tvUserInput = findViewById(R.id.tvUserInput);
        tvCalculation = findViewById(R.id.tvCalculation);

        tvUserInput.setText("0");

        initializeButton(NUMBER_BUTTON_IDS, btn -> setButtonListener(btn, false));
        initializeButton(OPERATOR_BUTTON_IDS, btn -> setButtonListener(btn, true));

        btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(handleCalculation()) {
                    userInput = new StringBuilder(calculation);
                    tvUserInput.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Math Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userInput.length() > 0)
                    userInput.setLength(userInput.length() - 1);
                tvUserInput.setText(userInput);
            }
        });

        btnAC = findViewById(R.id.btnAC);
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation = "";
                userInput = new StringBuilder();
                tvUserInput.setText(userInput);
                tvCalculation.setText(calculation);
            }
        });



    }

    private void initializeButton(int[] buttonIDs, Consumer<MaterialButton> action) {
        for(int id : buttonIDs) {
            MaterialButton button = findViewById(id);
            action.accept(button);
        }
    }

    private void setButtonListener(MaterialButton btn, boolean isOperator) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput.append(btn.getText().toString());
                tvUserInput.setText(userInput);

                if(!isOperator)
                    handleCalculation();
            }
        });


    }

    private boolean isParentheses(MaterialButton btn) {
        int id = btn.getId();
        return id == R.id.btnLeftParentheses || id == R.id.btnRightParentheses;
    }

    private boolean handleCalculation() {
        try {
            double result = Calculator.calculate(userInput.toString());
            calculation = String.format("%.2f", result);
            if(calculation.endsWith(".0")) {
                calculation.replace(".0", "");
            } else if (calculation.endsWith(".00")) {
                calculation.replace(".00", "");
            }
            tvCalculation.setText(calculation);
            return true;
        } catch(Exception ex) {
            Log.e("MainActivity", "Error");
            return false;
        }
    }
}

