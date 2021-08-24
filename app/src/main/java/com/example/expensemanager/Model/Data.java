package com.example.expensemanager.Model;
import static java.lang.Integer.parseInt;

public class Data {
//    all the items we want to get from database
    private int amount;
    private String date;
    private String id;
    private String notes;
    private String type;


//    No argument constructor for firebase.
    public Data(){ }



    public Data(int amount, String date, String id, String notes, String type) {
        this.amount = amount;
        this.date = date;
        this.id = id;
        this.notes = notes;
        this.type = type;
    }

    public int getAmount() {
        return parseInt(amount);
    }

    private int parseInt(int amount) {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }










}
