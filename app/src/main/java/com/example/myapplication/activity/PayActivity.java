package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Config;
import com.example.myapplication.R;
import com.example.myapplication.bean.Doctor;
import com.example.myapplication.bean.Schedule;
import com.example.myapplication.bean.User;
import com.example.myapplication.utils.NetUtil;
import com.google.gson.Gson;

public class PayActivity extends AppCompatActivity {
    private String baseurl = Config.baseUrl + "PayServlet";
    private User user;
    private Schedule schedule;
    private String result;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // 获取参数
        String userJson = getIntent().getStringExtra("user");
        String doctorJson = getIntent().getStringExtra("doctor");
        String scheduleJson = getIntent().getStringExtra("schedule");
        user = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);
        doctor = new Gson().fromJson(doctorJson, Doctor.class);
        schedule = new Gson().fromJson(scheduleJson, Schedule.class);

        // 显示挂号信息
        TextView uname = findViewById(R.id.uname);
        TextView dname = findViewById(R.id.dname);
        TextView departname = findViewById(R.id.departname);
        TextView time = findViewById(R.id.time);
        TextView price = findViewById(R.id.price);

        uname.setText(user.getUname());
        dname.setText(doctor.getDname());
        departname.setText(doctor.getDname()); // 假设 Doctor 类中有 getDepartname() 方法
        time.setText(schedule.getTime());
        price.setText("￥" + schedule.getPrice());

        // 按钮
        Button pay_yes = findViewById(R.id.pay_yes);
        Button pay_no = findViewById(R.id.pay_no);

        pay_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseurl += "?sid=" + schedule.getSid() + "&uid=" + user.getUid();
                Toast.makeText(getBaseContext(), "预约挂号成功！", Toast.LENGTH_SHORT).show();
                new PayAsyncTask().execute();
            }
        });

        pay_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "预约挂号取消成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PayActivity.this, ChooseTimeActivity.class);
                intent.putExtra("user",new Gson().toJson(user));
                intent.putExtra("doctor", new Gson().toJson(doctor));
                startActivity(intent);
            }
        });
    }

    public class PayAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            result = NetUtil.requestDataByGet(baseurl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Intent intent = new Intent(PayActivity.this, ChooseTimeActivity.class);
            intent.putExtra("doctor", new Gson().toJson(doctor));
            intent.putExtra("user",new Gson().toJson(user));
            startActivity(intent);
        }
    }
}