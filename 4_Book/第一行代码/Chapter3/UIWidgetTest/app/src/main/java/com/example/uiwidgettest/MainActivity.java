package com.example.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_main);

        // Initialization
        Button button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                String inputText = "";
                if (id == R.id.button) {
//                    toastMessage();
//                    setImageView(R.drawable.img_2);
//                    toggleProgressBar();
//                    handleProgressBarHorizontal();
//                    handleAlertDialog();
                    handleProgressDialog();
                }
            }
        });
    }

    private void setImageView(int resID) {
        imageView.setImageResource(resID);
    }

    private void toastMessage() {
        String inputText = editText.getText().toString();
        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
    }

    private void toggleProgressBar() {
        progressBar.setVisibility(progressBar.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }

    private void handleProgressBarHorizontal() {
        int progress = progressBar.getProgress();
        progressBar.setProgress(progress == 100 ? 0 : progress + 10);
    }

    private void handleAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("This is Dialog");
        dialog.setMessage("Something important.");
        dialog.setCancelable(true);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.show();
    }

    private void handleProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
