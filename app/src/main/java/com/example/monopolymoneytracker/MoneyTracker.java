package com.example.monopolymoneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MoneyTracker extends AppCompatActivity {

    private String userName;
    private int total = 0;
    private String totalMoney;
    private static String percent = "10%";
    Button pay, deal, tax, payday; // there's a fifth button but it isn't in production yet
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_tracker);

        Intent intent = getIntent();
        setUserName(intent.getStringExtra("username"));
        title = (TextView) findViewById(R.id.userName);
        title.setText(this.userName);

        deal = (Button) findViewById(R.id.dealButon);
        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeal();
            }
        });
    }

    public void setUserName(String user){

        this.userName = user;
    }

    public void setDeal(){

        Intent intent = new Intent(this, Trader.class);
        intent.putExtra("username", this.userName);
        startActivity(intent);
    }

    public String pay(int value, int total){

        String result = "";

        return result;
    }

    public String getPercent(int total){

        String result = "";

        return result;
    }

    public void setPayday(int income) {

    }
}
