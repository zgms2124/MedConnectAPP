package com.example.myapplication.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.bean.HealthRecommendation;

import java.util.List;

public class HealthRecommendationAdapter extends RecyclerView.Adapter<HealthRecommendationAdapter.ViewHolder> {
    private List<HealthRecommendation> healthRecommendations;

    public HealthRecommendationAdapter(List<HealthRecommendation> healthRecommendations) {
        this.healthRecommendations = healthRecommendations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health_recommendation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(healthRecommendations.get(position));
    }

    @Override
    public int getItemCount() {
        return healthRecommendations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtHealthInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHealthInfo = itemView.findViewById(R.id.txt_health_info);
        }

        public void bind(HealthRecommendation healthInfo) {
            txtHealthInfo.setText(healthInfo.getAdvice());
        }
    }
}