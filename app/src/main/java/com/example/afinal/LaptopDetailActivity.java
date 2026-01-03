package com.example.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LaptopDetailActivity extends AppCompatActivity {
    private Laptop laptop;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_laptop_detail);

        cartManager = new CartManager(this);
        
        // Get laptop data from intent using individual fields
        int id = getIntent().getIntExtra("laptop_id", 0);
        String name = getIntent().getStringExtra("laptop_name");
        String description = getIntent().getStringExtra("laptop_description");
        double price = getIntent().getDoubleExtra("laptop_price", 0.0);
        String imageResource = getIntent().getStringExtra("laptop_imageResource");
        String specs = getIntent().getStringExtra("laptop_specs");
        
        laptop = new Laptop(id, name, description, price, imageResource, specs);
        
        if (name == null || name.isEmpty()) {
            Toast.makeText(this, "Error loading laptop details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize views
        ImageView imageView = findViewById(R.id.detail_laptop_image);
        TextView nameTextView = findViewById(R.id.detail_laptop_name);
        TextView priceTextView = findViewById(R.id.detail_laptop_price);
        TextView descriptionTextView = findViewById(R.id.detail_laptop_description);
        TextView specsTextView = findViewById(R.id.detail_laptop_specs);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        // Set data
        nameTextView.setText(laptop.getName());
        priceTextView.setText("$" + String.format("%.2f", laptop.getPrice()));
        descriptionTextView.setText(laptop.getDescription());
        specsTextView.setText("Specifications:\n" + laptop.getSpecs());

        // Load image from drawable
        int imageId = getResources().getIdentifier(
            laptop.getImageResource(), 
            "drawable", 
            getPackageName()
        );
        if (imageId != 0) {
            imageView.setImageResource(imageId);
        } else {
            imageView.setImageResource(android.R.drawable.ic_menu_report_image);
        }

        // Add to cart button
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartManager.addToCart(laptop);
                Toast.makeText(LaptopDetailActivity.this, 
                    laptop.getName() + " added to cart!", 
                    Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

