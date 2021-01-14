package com.biye.demo.entity;

public class Account2 {
    private String  id;
    private double balance;

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account2{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
