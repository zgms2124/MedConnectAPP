package com.example.myapplication.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.bean.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Notification[] notifications;

    public NotificationAdapter(Notification[] notifications) {
        this.notifications = notifications;
    }

    public void updateData(Notification[] newNotifications) {
        this.notifications = newNotifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.bind(notifications[position]);
    }

    @Override
    public int getItemCount() {
        return notifications.length;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNotificationTitle;
        private TextView txtNotificationContent;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            txtNotificationTitle = itemView.findViewById(R.id.txt_notification_title);
            txtNotificationContent = itemView.findViewById(R.id.txt_notification_content);
        }

        public void bind(Notification notification) {
            txtNotificationTitle.setText(notification.getTitle());
            txtNotificationContent.setText(notification.getContent());
        }
    }
}