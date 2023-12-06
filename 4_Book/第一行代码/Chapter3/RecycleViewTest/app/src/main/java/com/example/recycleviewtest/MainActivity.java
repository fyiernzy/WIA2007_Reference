package com.example.recycleviewtest;

import java.util.*;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
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
        fruitList.add(new Fruit(getRandomLengthName(fruitName), fruitImageId));
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
