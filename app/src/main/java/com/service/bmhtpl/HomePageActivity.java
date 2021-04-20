package com.service.bmhtpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.service.bmhtpl.model.ProductSqlLiteModel;
import com.service.bmhtpl.util.DatabaseClient;
import com.service.bmhtpl.util.DatabaseHelper;
import com.service.bmhtpl.util.InvoiceDb;
import com.service.bmhtpl.util.InvoiceTable;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class HomePageActivity extends AppCompatActivity {

    Context context;
    ArrayList district_list = new ArrayList();
    AppCompatSpinner category_spinner;
    EditText id_et, vehical_no_et, km_start_et, km_close_et, km_run_et, time_start_et, time_close_et, extra_kms_et, extra_hrs_et;
    CheckBox out_station_checkbox, one_way_checkbox, no_halt_checkbox;
    Button save_record_et, print_record_btn;
//    DatabaseHelper db;

    //    List<InvoiceTable> data = new List<>();
    static InvoiceDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();
    }

    void init() {
        context = this;
//        db = new DatabaseHelper(this);
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

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
                        if (validateFields()) {
                            saveData();
                        }
                    }
                }
        );

        km_close_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!km_start_et.getText().toString().isEmpty()) {
                        float km_run = Float.parseFloat(km_close_et.getText().toString()) - Float.parseFloat(km_start_et.getText().toString());
                        km_run_et.setText(String.valueOf(km_run));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<InvoiceTable> data = db.ItemsDao().getAllItems();
                if (data.size() > 0) {
                    setFields();
                }
            }
        });
    }

    void setFields() {
        try {
            List<InvoiceTable> data = db.ItemsDao().getAllItems();
            InvoiceTable p = data.get(0);
            new Handler(Looper.getMainLooper()).post(() -> {
                // Work in the UI thread
                id_et.setText(p.getLicense_id());
                vehical_no_et.setText(p.getVehical_no());
                km_start_et.setText(p.getKm_start());
                km_close_et.setText(p.getKm_Close());
                km_run_et.setText(p.getKm_Run());
                time_start_et.setText(p.getTime_Start());
                time_close_et.setText(p.getTime_Close());
                extra_hrs_et.setText(p.getExtra_hrs());
                extra_kms_et.setText(p.getExtra_Kms());
                if (p.getOut_station().equals("true")) {
                    out_station_checkbox.setChecked(true);
                }
                if (p.getOne_Way().equals("true")) {
                    one_way_checkbox.setChecked(true);
                }
                if (p.getNo_Halt().equals("true")) {
                    no_halt_checkbox.setChecked(true);
                }
                category_spinner.setSelection(Integer.parseInt(p.getCategory()));
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void saveData() {
        if (check()) {
            try {
                SetAdditionalInfoDb myTask = new SetAdditionalInfoDb();
                myTask.execute();

//                ProductSqlLiteModel m = new ProductSqlLiteModel();
//                m.setId(1);
//                m.setLicense_id(id_et.getText().toString());
//                m.setVehical_no(vehical_no_et.getText().toString());
//                m.setCategory(String.valueOf(category_spinner.getSelectedItemPosition()));
//                m.setKm_start(km_start_et.getText().toString());
//                m.setKm_Close(km_close_et.getText().toString());
//                m.setKm_Run(km_run_et.getText().toString());
//                m.setTime_Start(time_start_et.getText().toString());
//                m.setTime_Close(time_close_et.getText().toString());
//                m.setExtra_hrs(extra_hrs_et.getText().toString());
//                m.setExtra_Kms(extra_kms_et.getText().toString());
//                if (out_station_checkbox.isChecked()) {
//                    m.setOut_station("true");
//                } else {
//                    m.setOut_station("false");
//                }
//                if (one_way_checkbox.isChecked()) {
//                    m.setOne_Way("true");
//                } else {
//                    m.setOne_Way("false");
//                }
//                if (no_halt_checkbox.isChecked()) {
//                    m.setNo_Halt("true");
//                } else {
//                    m.setNo_Halt("false");
//                }
//                db.addProduct(m);
                Toast.makeText(context, " Record Saved ", Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            UpdateDb myTask = new UpdateDb();
            myTask.execute();
//            ProductSqlLiteModel m = new ProductSqlLiteModel();
//            m.setId(1);
//            m.setLicense_id(id_et.getText().toString());
//            m.setVehical_no(vehical_no_et.getText().toString());
//            m.setCategory(String.valueOf(category_spinner.getSelectedItemPosition()));
//            m.setKm_start(km_start_et.getText().toString());
//            m.setKm_Close(km_close_et.getText().toString());
//            m.setKm_Run(km_run_et.getText().toString());
//            m.setTime_Start(time_start_et.getText().toString());
//            m.setTime_Close(time_close_et.getText().toString());
//            m.setExtra_hrs(extra_hrs_et.getText().toString());
//            m.setExtra_Kms(extra_kms_et.getText().toString());
//            if (out_station_checkbox.isChecked()) {
//                m.setOut_station("true");
//            } else {
//                m.setOut_station("false");
//            }
//            if (one_way_checkbox.isChecked()) {
//                m.setOne_Way("true");
//            } else {
//                m.setOne_Way("false");
//            }
//            if (no_halt_checkbox.isChecked()) {
//                m.setNo_Halt("true");
//            } else {
//                m.setNo_Halt("false");
//            }
//            db.updateProductQty(m);
            Toast.makeText(context, " Record Updated ", Toast.LENGTH_SHORT).show();
        }
    }

    boolean b = true;

    boolean check() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<InvoiceTable> data = db.ItemsDao().getAllItems();
                if (data.size() > 0) {
                    b = false;
                }
            }
        });

        return b;
    }

    boolean validateFields() {
        boolean b = true;
        if (id_et.getText().toString().isEmpty()) {
            b = false;
            id_et.requestFocus();
            Toast.makeText(context, "Enter Id", Toast.LENGTH_SHORT).show();
        } else if (vehical_no_et.getText().toString().isEmpty()) {
            b = false;
            vehical_no_et.requestFocus();
            Toast.makeText(context, "Enter Vehicle Number", Toast.LENGTH_SHORT).show();
        } else if (category_spinner.getSelectedItemPosition() == 0) {
            b = false;
            category_spinner.requestFocus();
            Toast.makeText(context, "Select Category", Toast.LENGTH_SHORT).show();
        } else if (km_start_et.getText().toString().isEmpty()) {
            b = false;
            km_start_et.requestFocus();
            Toast.makeText(context, "Enter Km Start", Toast.LENGTH_SHORT).show();
        } else if (km_close_et.getText().toString().isEmpty()) {
            b = false;
            km_close_et.requestFocus();
            Toast.makeText(context, "Enter Km Close", Toast.LENGTH_SHORT).show();
        } else if (time_start_et.getText().toString().isEmpty()) {
            b = false;
            time_start_et.requestFocus();
            Toast.makeText(context, "Enter Time Start", Toast.LENGTH_SHORT).show();
        } else if (time_close_et.getText().toString().isEmpty()) {
            b = false;
            time_close_et.requestFocus();
            Toast.makeText(context, "Enter Time Close", Toast.LENGTH_SHORT).show();
        }
        return b;
    }


    private class SetAdditionalInfoDb extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String out_station = "false";
            String one_way = "false";
            String no_halt = "false";

            if (out_station_checkbox.isChecked()) {
                out_station = "true";
            }

            if (one_way_checkbox.isChecked()) {
                one_way = "true";
            }

            if (no_halt_checkbox.isChecked()) {
                no_halt = "true";
            }

            db.ItemsDao().insert(new InvoiceTable(1, id_et.getText().toString(), vehical_no_et.getText().toString(), String.valueOf(category_spinner.getSelectedItemPosition()),
                    km_start_et.getText().toString(), km_close_et.getText().toString(), km_run_et.getText().toString(), time_start_et.getText().toString(),
                    time_close_et.getText().toString(), out_station, one_way, no_halt, extra_kms_et.getText().toString(), extra_hrs_et.getText().toString()));
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

    private class UpdateDb extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String out_station = "false";
            String one_way = "false";
            String no_halt = "false";

            if (out_station_checkbox.isChecked()) {
                out_station = "true";
            }

            if (one_way_checkbox.isChecked()) {
                one_way = "true";
            }

            if (no_halt_checkbox.isChecked()) {
                no_halt = "true";
            }
            db.ItemsDao().update(new InvoiceTable(1, id_et.getText().toString(), vehical_no_et.getText().toString(), String.valueOf(category_spinner.getSelectedItemPosition()),
                    km_start_et.getText().toString(), km_close_et.getText().toString(), km_run_et.getText().toString(), time_start_et.getText().toString(),
                    time_close_et.getText().toString(), out_station, one_way, no_halt, extra_kms_et.getText().toString(), extra_hrs_et.getText().toString()));
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(String... text) {

        }
    }
}
