package com.service.bmhtpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    ArrayList district_list = new ArrayList();
    AppCompatSpinner category_spinner;
//    MaterialBetterSpinner category_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        category_spinner = findViewById(R.id.category_spinner);

        district_list.add("-- Select Category --");
        district_list.add("-- Demo1 --");
        district_list.add("-- Demo2 --");
        district_list.add("-- Demo3 --");
        district_list.add("-- Demo4 --");
        ArrayAdapter<String> district_adapter = new ArrayAdapter<>(HomePageActivity.this,
                R.layout.spinner_layout, district_list);
        district_adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_layout);
        category_spinner.setAdapter(district_adapter);

//        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(HomePageActivity.this, android.R.layout.simple_spinner_dropdown_item, district_list);
//        category_spinner.setAdapter(stateAdapter);
    }
}