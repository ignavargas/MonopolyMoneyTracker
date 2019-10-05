package com.example.monopolymoneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Trader extends AppCompatActivity {

    Button cancelButton, acceptButton;
    EditText myMoney, theirMoney;
    private int totalAmount;
    String name, money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader);
        Intent intent = getIntent();
        name = intent.getStringExtra("username");
        money = intent.getStringExtra("totalmoney");
        totalAmount = Integer.parseInt(money);

        cancelButton = (Button) findViewById(R.id.cancelTrateButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTrade();
            }
        });


        acceptButton = (Button) findViewById(R.id.acceptDealButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptTrade();
            }
        });

    }

    public void cancelTrade(){


        Intent intent = new Intent(this, MoneyTracker.class);
        intent.putExtra("username", name);
        intent.putExtra("totalmoney", Integer.toString(totalAmount));
        startActivity(intent);
    }

    public void acceptTrade(){


        myMoney = (EditText) findViewById(R.id.totalFromMe);
        theirMoney = (EditText) findViewById(R.id.totalFromThem);

        Accounter acc = new Accounter();

        totalAmount = acc.updatePayment(totalAmount, Integer.parseInt(myMoney.getText().toString()));
        totalAmount = acc.updateIncome(totalAmount, Integer.parseInt(theirMoney.getText().toString()));

        Intent intent = new Intent(this, MoneyTracker.class);
        intent.putExtra("username", name);
        intent.putExtra("totalmoney", Integer.toString(totalAmount));
        startActivity(intent);
    }
}
