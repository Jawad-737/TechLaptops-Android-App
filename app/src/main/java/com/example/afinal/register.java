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

public class register extends AppCompatActivity {
    EditText eFname, eLname, eUname, ePass;
    // Change this URL to your server's PHP file location
    private static final String REGISTER_URL = "http://192.168.3.103/backend/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.register);

        // Initialize EditText fields
        eFname = (EditText) findViewById(R.id.fname);
        eLname = (EditText) findViewById(R.id.lname);
        eUname = (EditText) findViewById(R.id.uname);
        ePass = (EditText) findViewById(R.id.passwd);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void regIN(View v) {
        // Get all inputs from EditText fields
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        String username = eUname.getText().toString().trim();
        String password = ePass.getText().toString().trim();

        // Validate that all fields are not empty
        if (fname.isEmpty() || lname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Encode parameters for URL
        try {
            fname = URLEncoder.encode(fname, "UTF-8");
            lname = URLEncoder.encode(lname, "UTF-8");
            username = URLEncoder.encode(username, "UTF-8");
            password = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(this, "Encoding error", Toast.LENGTH_SHORT).show();
            return;
        }

        // Build URL with query parameters
        String url = REGISTER_URL
                + "?fname=" + fname
                + "&lname=" + lname
                + "&username=" + username
                + "&password=" + password;

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
                            Toast.makeText(register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            eFname.setText("");
                            eLname.setText("");
                            eUname.setText("");
                            ePass.setText("");
                        } else if (response != null && response.trim().equals("exists")) {
                            Toast.makeText(register.this, "Username already exists. Please choose another one.", Toast.LENGTH_SHORT).show();
                            eUname.setText("");
                        } else {
                            Toast.makeText(register.this, "Server response: " + response, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(register.this, errorMessage + "\nCheck: Server running? URL correct?", Toast.LENGTH_LONG).show();
                    }
                }
        );

        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }

    // Called by the Login button in register.xml (android:onClick="logIN")
    public void logIN(View v) {
        Intent i = new Intent(register.this, login.class);
        startActivity(i);
    }
}