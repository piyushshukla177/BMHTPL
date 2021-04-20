package com.service.bmhtpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.service.bmhtpl.util.DatabaseHelper;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    Context context;
    ArrayList district_list = new ArrayList();
    AppCompatSpinner category_spinner;
    EditText id_et, vehical_no_et, km_start_et, km_close_et, km_run_et, time_start_et, time_close_et, extra_kms_et, extra_hrs_et;
    CheckBox out_station_checkbox, one_way_checkbox, no_halt_checkbox;
    Button save_record_et, print_record_btn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();
    }

    void init() {
        context = this;
        db = new DatabaseHelper(this);
        id_et = findViewById(R.id.id_et);
        vehical_no_et = findViewById(R.id.vehical_no_et);
        km_start_et = findViewById(R.id.km_start_et);
        km_close_et = findViewById(R.id.km_close_et);
        km_run_et = findViewById(R.id.km_run_et);
        time_start_et = findViewById(R.id.time_start_et);
        time_close_et = findViewById(R.id.time_close_et);
        extra_kms_et = findViewById(R.id.extra_kms_et);
        extra_hrs_et = findViewById(R.id.extra_hrs_et);
        out_station_checkbox = findViewById(R.id.out_station_checkbox);
        one_way_checkbox = findViewById(R.id.one_way_checkbox);
        no_halt_checkbox = findViewById(R.id.no_halt_checkbox);
        save_record_et = findViewById(R.id.save_record_et);
        print_record_btn = findViewById(R.id.print_record_btn);

        category_spinner = findViewById(R.id.category_spinner);

        district_list.add("-- Select Category --");
        district_list.add("Demo1");
        district_list.add("Demo2");
        district_list.add("Demo3");
        district_list.add("Demo4");
        ArrayAdapter<String> district_adapter = new ArrayAdapter<>(HomePageActivity.this,
                R.layout.spinner_layout, district_list);
        district_adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_layout);
        category_spinner.setAdapter(district_adapter);

        save_record_et.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveData();
                    }
                }
        );
    }

    void saveData() {
        if(check())
        {

        }
    }

    boolean check()
    {
        boolean b=true;
        return b;
    }
}