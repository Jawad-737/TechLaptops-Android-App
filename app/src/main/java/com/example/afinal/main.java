package com.example.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Called by the Register button (android:onClick="goToRegister")
    public void goToRegister(View v) {
        Intent i = new Intent(main.this, register.class);
        startActivity(i);
    }

    // Called by the Login button (android:onClick="goToLogin")
    public void goToLogin(View v) {
        Intent i = new Intent(main.this, login.class);
        startActivity(i);
    }

    // Called by the About Us TextView (android:onClick="openAboutUs")
    // For now, it's ready but you can add the webpage URL later
    public void openAboutUs(View v) {
        // TODO: Replace with your webpage URL when ready
        // String url = "https://yourwebsite.com/about";
        // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        // startActivity(i);
        
        // For now, you can add a placeholder or remove this method
        // and add the URL later when you're ready
    }
}

