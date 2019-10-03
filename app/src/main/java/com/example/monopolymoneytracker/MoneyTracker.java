package com.example.monopolymoneytracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    TextView title, amount;
    Accounter accounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_tracker);

        Intent intent = getIntent();
        setUserName(intent.getStringExtra("username"));
        title = (TextView) findViewById(R.id.userName);
        title.setText(this.userName);

        amount = (TextView) findViewById(R.id.totalMoney);
        this.total = Integer.parseInt(amount.getText().toString());

        deal = (Button) findViewById(R.id.dealButon);
        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeal();
            }
        });

        tax = (Button) findViewById(R.id.percentButton);
        tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MoneyTracker.this);
                dialogBuilder.setTitle("El 10% de su dinero es:");
               final String percentValue = getPercent(total);
                dialogBuilder.setMessage(percentValue);
                dialogBuilder.setNeutralButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        total -= Integer.parseInt(percentValue);
                        amount.setText(Integer.toString(total));
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();

            }
        });

        payday = (Button) findViewById(R.id.goButton);
        payday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MoneyTracker.this);
                dialogBuilder.setTitle("Salario | GO!");
                dialogBuilder.setMessage(" +200 ");
                dialogBuilder.setNeutralButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        total += 200;
                        amount.setText(Integer.toString(total));
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
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

        accounter = new Accounter();

        int result = accounter.getTenPercent(total);

        return Integer.toString(result);
    }

    public void setPayday(int income) {

    }
}
