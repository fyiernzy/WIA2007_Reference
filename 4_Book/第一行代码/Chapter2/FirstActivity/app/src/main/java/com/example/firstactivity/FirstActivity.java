package com.example.firstactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.first_layout);
        Log.d("FirstActivity", this.toString());
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Explicit Intent 1
                Intent intent1 = new Intent(getApplicationContext(), SecondActivity.class);

                // Explicit Intent 2
                Intent intent2 = new Intent(FirstActivity.this, SecondActivity.class);

                // Implicit intent
                Intent intent3 = new Intent("com.example.firstactivity.ACTION_START");
                intent3.addCategory("android.intent.category.MY_CATEGORY");

                // Implicit intent for calling a web application
                Intent intent4 = new Intent(Intent.ACTION_VIEW);
                intent4.setData(Uri.parse("https://www.google.com"));

                // Implicit intent for phone calls
                Intent intent5 = new Intent(Intent.ACTION_DIAL);
                intent5.setData(Uri.parse("tel:10086"));

                // Explicit intent to pass data
                Intent intent6 = new Intent(getApplicationContext(), SecondActivity.class);
                intent6.putExtra("extra_data", "Hello, SecondActivity");

                // Explicit intent to test standard
                Intent intent7 = new Intent(getApplicationContext(), FirstActivity.class);

                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_item) {
            Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.remove_item) {
            Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
