package com.example.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView cartListView;
    private TextView totalPriceTextView;
    private Button proceedButton;
    private CartManager cartManager;
    private List<Laptop> cartItems;
    private LaptopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        cartManager = new CartManager(this);
        
        cartListView = findViewById(R.id.cart_listview);
        totalPriceTextView = findViewById(R.id.total_price);
        proceedButton = findViewById(R.id.proceed_button);

        loadCartItems();
        updateTotal();

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItems.isEmpty()) {
                    Toast.makeText(CartActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Navigate to checkout/purchase activity
                    Toast.makeText(CartActivity.this, 
                        "Proceeding to checkout... Total: $" + String.format("%.2f", cartManager.getTotalPrice()), 
                        Toast.LENGTH_SHORT).show();
                    // You can add checkout activity here later
                }
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
        loadCartItems();
        updateTotal();
    }

    private void loadCartItems() {
        cartItems = cartManager.getCart();
        adapter = new LaptopAdapter(this, cartItems);
        cartListView.setAdapter(adapter);
        
        if (cartItems.isEmpty()) {
            totalPriceTextView.setText("Cart is empty");
            proceedButton.setEnabled(false);
        } else {
            proceedButton.setEnabled(true);
        }
    }

    private void updateTotal() {
        if (!cartItems.isEmpty()) {
            double total = cartManager.getTotalPrice();
            totalPriceTextView.setText("Total: $" + String.format("%.2f", total));
        }
    }
}

