package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class login extends AppCompatActivity {
    EditText eN, eP;
    // Change this URL to your server's PHP file location
    private static final String LOGIN_URL = "http://192.168.3.103/backend/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize EditText fields
        eN = (EditText) findViewById(R.id.etname);
        eP = (EditText) findViewById(R.id.etpass);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void logIN(View v) {
        // Get username and password from EditText fields
        String username = eN.getText().toString().trim();
        String password = eP.getText().toString().trim();

        // Validate that fields are not empty
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Encode parameters for URL
        try {
            username = URLEncoder.encode(username, "UTF-8");
            password = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(this, "Encoding error", Toast.LENGTH_SHORT).show();
            return;
        }

        // Build URL with query parameters
        String url = LOGIN_URL + "?username=" + username + "&password=" + password;

        // Create a Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Create a StringRequest
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null && response.trim().equals("success")) {
                            Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to Products Activity
                            Intent intent = new Intent(login.this, ProductsActivity.class);
                            startActivity(intent);
                            finish(); // Close login activity
                        } else if (response != null && response.trim().equals("failed")) {
                            Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            eP.setText("");
                        } else {
                            Toast.makeText(login.this, "Server error: " + response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Connection error";
                        if (error.networkResponse != null) {
                            errorMessage = "Error: " + error.networkResponse.statusCode;
                        } else if (error.getMessage() != null) {
                            errorMessage = "Error: " + error.getMessage();
                        }
                        Toast.makeText(login.this, errorMessage + "\nCheck: Server running? URL correct?", Toast.LENGTH_LONG).show();
                    }
                }
        );

        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }

    // Called by the Register button in activity_login.xml (android:onClick="regIN")
    public void regIN(View v) {
        Intent i = new Intent(login.this, register.class);
        startActivity(i);
    }
}