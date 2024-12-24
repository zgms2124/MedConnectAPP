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

public class OnlineConsultationActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_consultation);

        // 在线咨询列表数据
        String[] consultations = {"咨询1", "咨询2", "咨询3"};
        ListView lvOnlineConsultation = findViewById(R.id.lv_online_consultation);
        lvOnlineConsultation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consultations));
        user = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);
        // 点击事件处理
        lvOnlineConsultation.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(OnlineConsultationActivity.this, "点击查看详情: " + consultations[position], Toast.LENGTH_SHORT).show();
        });

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnlineConsultationActivity.this, IndexActivity.class);
                intent.putExtra("user", new Gson().toJson(user));
                startActivity(intent);
            }
        });
    }
}