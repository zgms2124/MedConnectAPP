package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Config;
import com.example.myapplication.R;
import com.example.myapplication.bean.HealthRecommendation;
import com.example.myapplication.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private static final String BASE_URL = Config.baseUrl + "healthRecommendationServlet";
    private ImageView userAvatar;
    private TextView welcomeUname;
    private Button btnLogout;
    private CardView cardNotifications;
    private RecyclerView rvHealthRecommendations;
    private HealthRecommendationAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if (getIntent().hasExtra("user")) {
            String userJson = getIntent().getStringExtra("user");
            user = new Gson().fromJson(userJson, User.class);
        }

        initView();
        initEvent();
        fetchHealthRecommendations();
    }

    private void initView() {
        userAvatar = findViewById(R.id.user_avatar);
        welcomeUname = findViewById(R.id.welcome_uname);
        btnLogout = findViewById(R.id.btn_logout);
        cardNotifications = findViewById(R.id.card_notifications);
        rvHealthRecommendations = findViewById(R.id.rv_health_recommendations);
        rvHealthRecommendations.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initEvent() {
        userAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(IndexActivity.this, MyInfoActivity.class);
            intent.putExtra("user", new Gson().toJson(user));
            startActivity(intent);
        });

        if (user != null) {
            welcomeUname.setText(user.getUname() + "，祝您身体健康！");
            userAvatar.setImageResource(R.mipmap.user_avatar); // 设置用户头像图片资源
        }

        btnLogout.setOnClickListener(v -> {
            Toast.makeText(IndexActivity.this, "退出账户成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IndexActivity.this, LoginActivity.class);
            startActivity(intent);
            user = null; // 清除用户信息
        });

        cardNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(IndexActivity.this, NotificationsActivity.class);
            intent.putExtra("user", new Gson().toJson(user));
            startActivity(intent);
        });

        TextView txtNotifications = findViewById(R.id.txt_notifications);
        txtNotifications.setText("您有3条未读消息");

        Button btnBookAppointment = findViewById(R.id.btn_book_appointment);
        btnBookAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(IndexActivity.this, ChooseDepartActivity.class);
            intent.putExtra("user", new Gson().toJson(user));
            startActivity(intent);
        });

        Button btnRecentAppointments = findViewById(R.id.btn_recent_appointments);
        btnRecentAppointments.setOnClickListener(v -> {
            Intent intent = new Intent(IndexActivity.this, RecentAppointmentsActivity.class);
            intent.putExtra("user", new Gson().toJson(user));
            startActivity(intent);
        });

        Button btnOnlineConsultation = findViewById(R.id.btn_online_consultation);
        btnOnlineConsultation.setOnClickListener(v -> {
            Intent intent = new Intent(IndexActivity.this, OnlineConsultationActivity.class);
            intent.putExtra("user", new Gson().toJson(user));
            startActivity(intent);
        });
    }

    private void fetchHealthRecommendations() {
        new Thread(() -> {
            String response = requestDataByGet(BASE_URL);
            runOnUiThread(() -> {
                if (response != null) {
                    List<HealthRecommendation> recommendations = new Gson().fromJson(response, new TypeToken<List<HealthRecommendation>>(){}.getType());
                    adapter = new HealthRecommendationAdapter(recommendations);
                    rvHealthRecommendations.setAdapter(adapter);
                } else {
                    // Handle error or use default recommendations
                    List<HealthRecommendation> defaultRecommendations = new ArrayList<>();
                    defaultRecommendations.add(new HealthRecommendation("每天至少喝8杯水，保持身体水分平衡"));
                    defaultRecommendations.add(new HealthRecommendation("保持规律的作息时间，每晚至少7-8小时的睡眠"));
                    adapter = new HealthRecommendationAdapter(defaultRecommendations);
                    rvHealthRecommendations.setAdapter(adapter);
                }
            });
        }).start();
    }

    public String requestDataByGet(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } else {
                // Handle server error
            }

            connection.disconnect();
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}