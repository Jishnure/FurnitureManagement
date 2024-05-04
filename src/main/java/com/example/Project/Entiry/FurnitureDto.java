package com.example.Project.Entiry;

import org.springframework.web.multipart.MultipartFile;


public class FurnitureDto {
    private String name;
    private String category;
    private double price;
    private String description;
    private MultipartFile imageFile;

    public FurnitureDto() {
    }

    public FurnitureDto(String name, String category, double price, String description, MultipartFile imageFile) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageFile = imageFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
