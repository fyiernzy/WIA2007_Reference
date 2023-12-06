package com.example.uicustomviews;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {
            "Apple", "Banana", "Orange", "Watermelon", "Pear",
            "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear",
            "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"
    };

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Custom Action Bar
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null) {
//            actionBar.hide();
//        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
//                android.R.layout.simple_list_item_1, data);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(getApplicationContext(), R.layout.fruit_item, fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for(int i = 0; i < 2; i++) {
            addNewFruit("Apple", R.drawable.ic_launcher_foreground);
            addNewFruit("Banana", R.drawable.ic_launcher_foreground);
            addNewFruit("Orange", R.drawable.ic_launcher_foreground);
            addNewFruit("Watermelon", R.drawable.ic_launcher_foreground);
            addNewFruit("Pear", R.drawable.ic_launcher_foreground);
            addNewFruit("Grape", R.drawable.ic_launcher_foreground);
            addNewFruit("Pineapple", R.drawable.ic_launcher_foreground);
            addNewFruit("Strawberry", R.drawable.ic_launcher_foreground);
            addNewFruit("Cherry", R.drawable.ic_launcher_foreground);
            addNewFruit("Mango", R.drawable.ic_launcher_foreground);
        }
    }

    private void addNewFruit(String fruitName, int fruitImageId) {
        fruitList.add(new Fruit(fruitName, fruitImageId));
    }
}
