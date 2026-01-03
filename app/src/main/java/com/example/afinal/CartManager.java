package com.example.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_ITEMS_KEY = "cart_items";
    private SharedPreferences sharedPreferences;

    public CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
    }

    public void addToCart(Laptop laptop) {
        List<Laptop> cart = getCart();
        // Create a new instance to avoid reference issues
        Laptop newLaptop = new Laptop(
            laptop.getId(),
            laptop.getName(),
            laptop.getDescription(),
            laptop.getPrice(),
            laptop.getImageResource(),
            laptop.getSpecs()
        );
        cart.add(newLaptop);
        saveCart(cart);
    }

    public void removeFromCart(int position) {
        List<Laptop> cart = getCart();
        if (position >= 0 && position < cart.size()) {
            cart.remove(position);
            saveCart(cart);
        }
    }

    public List<Laptop> getCart() {
        String json = sharedPreferences.getString(CART_ITEMS_KEY, "");
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Laptop> cart = new ArrayList<>();
        try {
            // Parse manual JSON format: [{"id":1,"name":"Laptop1","description":"Desc","price":999.99,"imageResource":"image1","specs":"specs1"},...]
            String[] items = json.split("\\},\\{");
            for (String item : items) {
                // Clean up the item string
                item = item.replace("[{", "").replace("}]", "").replace("{", "").replace("}", "");
                
                if (item.trim().isEmpty()) continue;
                
                // Parse individual fields
                String[] fields = item.split(",");
                int id = 0;
                String name = "";
                String description = "";
                double price = 0.0;
                String imageResource = "";
                String specs = "";
                
                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].replace("\"", "").trim();
                        String value = keyValue[1].replace("\"", "").trim();
                        
                        switch (key) {
                            case "id":
                                id = Integer.parseInt(value);
                                break;
                            case "name":
                                name = value;
                                break;
                            case "description":
                                description = value;
                                break;
                            case "price":
                                price = Double.parseDouble(value);
                                break;
                            case "imageResource":
                                imageResource = value;
                                break;
                            case "specs":
                                specs = value;
                                break;
                        }
                    }
                }
                
                if (!name.isEmpty()) {
                    cart.add(new Laptop(id, name, description, price, imageResource, specs));
                }
            }
        } catch (Exception e) {
            // If parsing fails, return empty cart
            return new ArrayList<>();
        }
        return cart;
    }

    public void clearCart() {
        sharedPreferences.edit().remove(CART_ITEMS_KEY).apply();
    }

    public int getCartItemCount() {
        return getCart().size();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Laptop laptop : getCart()) {
            total += laptop.getPrice();
        }
        return total;
    }

    private void saveCart(List<Laptop> cart) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        
        for (int i = 0; i < cart.size(); i++) {
            Laptop laptop = cart.get(i);
            json.append("{");
            json.append("\"id\":").append(laptop.getId()).append(",");
            json.append("\"name\":\"").append(laptop.getName()).append("\",");
            json.append("\"description\":\"").append(laptop.getDescription()).append("\",");
            json.append("\"price\":").append(laptop.getPrice()).append(",");
            json.append("\"imageResource\":\"").append(laptop.getImageResource()).append("\",");
            json.append("\"specs\":\"").append(laptop.getSpecs()).append("\"");
            json.append("}");
            
            if (i < cart.size() - 1) {
                json.append(",");
            }
        }
        
        json.append("]");
        sharedPreferences.edit().putString(CART_ITEMS_KEY, json.toString()).apply();
    }
}

