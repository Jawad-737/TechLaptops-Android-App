package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private ListView listView;
    private List<Laptop> laptops;
    private LaptopAdapter adapter;
    private CartManager cartManager;
    private TextView cartBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_products);
        
        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TechLaptops - Products");

        cartManager = new CartManager(this);
        
        listView = findViewById(R.id.laptops_listview);
        laptops = getLaptops();
        adapter = new LaptopAdapter(this, laptops);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Laptop selectedLaptop = laptops.get(position);
                Intent intent = new Intent(ProductsActivity.this, LaptopDetailActivity.class);
                // Pass laptop data using individual fields instead of serialization
                intent.putExtra("laptop_id", selectedLaptop.getId());
                intent.putExtra("laptop_name", selectedLaptop.getName());
                intent.putExtra("laptop_description", selectedLaptop.getDescription());
                intent.putExtra("laptop_price", selectedLaptop.getPrice());
                intent.putExtra("laptop_imageResource", selectedLaptop.getImageResource());
                intent.putExtra("laptop_specs", selectedLaptop.getSpecs());
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        MenuItem cartItem = menu.findItem(R.id.cart_menu_item);
        View actionView = cartItem.getActionView();
        cartBadge = actionView.findViewById(R.id.cart_badge);
        updateCartBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCart();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cart_menu_item) {
            openCart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateCartBadge() {
        if (cartBadge != null) {
            int count = cartManager.getCartItemCount();
            if (count > 0) {
                cartBadge.setText(String.valueOf(count));
                cartBadge.setVisibility(View.VISIBLE);
            } else {
                cartBadge.setVisibility(View.GONE);
            }
        }
    }

    private void openCart() {
        Intent intent = new Intent(ProductsActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private List<Laptop> getLaptops() {
        List<Laptop> laptopList = new ArrayList<>();
        
        // Using your existing images from drawable folder
        laptopList.add(new Laptop(1, "Acer Predator Triton 300SE", 
            "High-performance gaming laptop with powerful graphics", 
            1299.99, 
            "acer_predetor_triton_300se", 
            "Intel i7, 16GB RAM, 512GB SSD, RTX 3060, 15.6\" Display"));
        
        laptopList.add(new Laptop(2, "Acer TUF Gaming 16", 
            "Durable gaming laptop built for performance", 
            1199.99, 
            "acer_tuf_16", 
            "AMD Ryzen 7, 16GB RAM, 512GB SSD, RTX 3050, 16\" Display"));
        
        laptopList.add(new Laptop(3, "HP 15-fc000 2023", 
            "Affordable laptop for everyday computing", 
            599.99, 
            "hp_15fc000_2023", 
            "AMD Ryzen 5, 8GB RAM, 256GB SSD, 15.6\" Display"));
        
        laptopList.add(new Laptop(4, "HP Victus 15", 
            "Gaming laptop with excellent performance", 
            899.99, 
            "hp_victus15", 
            "Intel i5, 8GB RAM, 512GB SSD, GTX 1650, 15.6\" Display"));
        
        laptopList.add(new Laptop(5, "Lenovo LOQ 15", 
            "Modern laptop with sleek design", 
            1099.99, 
            "lenovo_loq15", 
            "Intel i7, 16GB RAM, 512GB SSD, RTX 3050, 15.6\" Display"));
        
        return laptopList;
    }
}

