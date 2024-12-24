package com.example.myapplication.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.myapplication.R;
import com.example.myapplication.bean.Notification;

public class NotificationsActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvNotifications;
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        // 设置 Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // 初始化 SwipeRefreshLayout 和 RecyclerView
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        rvNotifications = findViewById(R.id.rv_notifications);
        rvNotifications.setLayoutManager(new LinearLayoutManager(this));
        notificationAdapter = new NotificationAdapter(getNotificationsData());
        rvNotifications.setAdapter(notificationAdapter);

        // 设置下拉刷新监听器
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // 刷新数据逻辑
            refreshNotifications();
        });

        // 设置 Toolbar 导航点击事件
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void refreshNotifications() {
        // 模拟网络请求刷新数据
        notificationAdapter.updateData(getNotificationsData());
        swipeRefreshLayout.setRefreshing(false);
    }

    private Notification[] getNotificationsData() {
        return new Notification[] {
                new Notification("通知1", "内科搬至309室"),
                new Notification("通知2", "您的医保卡信息过期需要更新"),
                new Notification("通知3", "请及时完成未缴费的项目")
        };
    }
}