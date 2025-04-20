package com.example.hro_project;

public class Worker {
    private int id;
    private String name;
    private String phone;
    private String skills;
    private double totalPay;

    public Worker(int id, String name, String phone, String skills, double totalPay) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.skills = skills;
        this.totalPay = totalPay;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }
}

