package com.example.expensemanager.Model;

public class Data {
//    all the items we want to get from database
    private double amount;
    private String type;
    private String notes;
    private String date;
    private String id;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data(double amount, String type, String notes, String date, String id) {
        this.amount = amount;
        this.type = type;
        this.notes = notes;
        this.date = date;
        this.id = id;
    }

    public Data(){

    }
}
