package com.example.wia2007_p4q1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_P4Q2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_p4q2);

        final Button btnSubmit = findViewById(R.id.BtnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox CBRed = findViewById(R.id.CBRed);
                CheckBox CBGreen = findViewById(R.id.CBGreen);
                CheckBox CBBlue = findViewById(R.id.CBBlue);

                ConstraintLayout CSLayout = findViewById(R.id.CLayoutCS);

                if (CBRed.isChecked()) {
                    CSLayout.setBackgroundColor(Color.RED);
                } else if (CBGreen.isChecked()) {
                    CSLayout.setBackgroundColor(Color.GREEN);
                } else if (CBBlue.isChecked()) {
                    CSLayout.setBackgroundColor(Color.BLUE);
                }
            }
        });
    }
}
