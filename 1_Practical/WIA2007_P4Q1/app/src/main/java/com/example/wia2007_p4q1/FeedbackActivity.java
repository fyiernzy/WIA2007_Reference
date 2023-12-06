package com.example.wia2007_p4q1;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class FeedbackActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_feedback);

        RatingBar RateBarFeedback = findViewById(R.id.RateBarFeedback);
        TextView TVRating = findViewById(R.id.TVRating);
        EditText ETFeedback = findViewById(R.id.ETFeedback);
        Button BtnFeedback = findViewById(R.id.BtnFeedback);

        BtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Thank you for your feedback!";
                if(!ETFeedback.getText().toString().isEmpty())
                    message += " Please enjoy your RM5 Cashback: ABC123";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        RateBarFeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromuser) {
                TVRating.setText("You have rated " + rating);
            }
        });

    }
}
