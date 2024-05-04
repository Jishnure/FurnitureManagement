package com.example.Project.Entiry;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    @JoinColumn(name ="furniture_id")
    private Furniture furniture;
    private String customerName;
    private int quantity;
    private Date date;
    private double totalPrice;

    public Book() {
    }

    public Book(Long id, String customerName, int quantity, Date date,double totalPrice,Furniture furniture) {
        this.id = id;
        this.customerName = customerName;
        this.quantity = quantity;
        this.date = date;
        this.totalPrice=totalPrice;
        this.furniture=furniture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
