package com.example.myapplication.bean;

public class HealthRecommendation {
    private int id;
    private String advice;

    // 无参构造函数
    public HealthRecommendation() {}
// 全参构造函数
    public HealthRecommendation(String advice) {

        this.advice = advice;
    }
    // 全参构造函数
    public HealthRecommendation(int id, String advice) {
        this.id = id;
        this.advice = advice;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}