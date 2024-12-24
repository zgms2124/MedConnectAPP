package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Doctor;
import com.example.myapplication.bean.User;
import com.google.gson.Gson;

public class DoctorDetailActivity extends AppCompatActivity {
    private Doctor doctor;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        String doctorJson = getIntent().getStringExtra("doctor");
        doctor = new Gson().fromJson(doctorJson, Doctor.class);
        user = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);
        Log.e("DoctorDetailActivity", "传来的数据：" + doctor);

        ImageView dimg = findViewById(R.id.dimg);
        TextView dname = findViewById(R.id.dname);
        TextView dlevel = findViewById(R.id.dlevel);
        TextView dinfo = findViewById(R.id.dinfo);
        TextView ddetail = findViewById(R.id.ddetail);


        if (doctor.getSex() == 1) {
            dimg.setImageResource(R.mipmap.doctor_male);
        } else {
            dimg.setImageResource(R.mipmap.doctor_female);
        }
        dname.setText(doctor.getDname());
        dlevel.setText(doctor.getDlevel());
        dinfo.setText(doctor.getDinfo());
        ddetail.setText(doctor.getDetail());

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorDetailActivity.this, ChooseDoctorActivity.class);
                intent.putExtra("departid", doctor.getDepartid());
                intent.putExtra("user", new Gson().toJson(user));
                startActivity(intent);
            }
        });


    }
}