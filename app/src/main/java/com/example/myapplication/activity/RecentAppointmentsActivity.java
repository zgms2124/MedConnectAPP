package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.bean.User;
import com.google.gson.Gson;

public class RecentAppointmentsActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_appointments);
user = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);
        // 最近预约列表数据
        String[] appointments = {"2023-12-01 内科", "2023-12-05 外科", "2023-12-10 妇产科"};
        ListView lvRecentAppointments = findViewById(R.id.lv_recent_appointments);
        lvRecentAppointments.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointments));

        // 点击事件处理
        lvRecentAppointments.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(RecentAppointmentsActivity.this, "点击查看详情: " + appointments[position], Toast.LENGTH_SHORT).show();
        });

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecentAppointmentsActivity.this,IndexActivity.class);
                intent.putExtra("user", new Gson().toJson(user));
                startActivity(intent);
            }
        });
    }
}