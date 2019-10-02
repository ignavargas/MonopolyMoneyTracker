package com.example.monopolymoneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Trader extends AppCompatActivity {

    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader);

        cancelButton = (Button) findViewById(R.id.cancelTrateButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTrade();
            }
        });
    }

    public void cancelTrade(){

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        intent = new Intent(this, MoneyTracker.class);
        intent.putExtra("username", name);
        startActivity(intent);
    }
}
