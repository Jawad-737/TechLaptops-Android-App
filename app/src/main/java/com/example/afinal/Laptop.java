package com.example.afinal;

public class Laptop {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageResource; // Drawable resource name (e.g., "laptop1")
    private String specs; // Additional specifications

    public Laptop(int id, String name, String description, double price, String imageResource, String specs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResource = imageResource;
        this.specs = specs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getSpecs() {
        return specs;
    }
}

