package com.example.monopolymoneytracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MoneyTracker extends AppCompatActivity {

    private String userName;
    private int total = 0;
    private static String percent = "10%";
    Button pay, deal, tax, payday, win;
    //Button acceptPay, cancelPay;
    EditText paymentDialog;
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
               final String percentValue = getPercent();
                dialogBuilder.setMessage(percentValue);
                dialogBuilder.setNeutralButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pay(Integer.parseInt(percentValue));
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

                        setPayday(200);
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            }
        });

        pay = (Button) findViewById(R.id.payButton);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MoneyTracker.this);
                View view = getLayoutInflater().inflate(R.layout.activity_input_dialog, null);
                paymentDialog = (EditText) view.findViewById(R.id.inputValueDialog);

                dialogBuilder.setView(view);
                dialogBuilder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String pago  = paymentDialog.getText().toString();
                        pay(Integer.parseInt(pago));
                        dialog.dismiss();
                    }
                });

                dialogBuilder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();

            }
        });

        win = (Button) findViewById(R.id.otherButton);
        win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MoneyTracker.this);
                View view = getLayoutInflater().inflate(R.layout.activity_input_dialog, null);
                paymentDialog = (EditText) view.findViewById(R.id.inputValueDialog);

                dialogBuilder.setView(view);
                dialogBuilder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String income  = paymentDialog.getText().toString();
                        setPayday(Integer.parseInt(income));
                        dialog.dismiss();
                    }
                });

                dialogBuilder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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

    public void pay(int value){

        accounter = new Accounter();
        this.total = accounter.updatePayment(this.total, value);
        amount.setText(Integer.toString(this.total));

    }

    public String getPercent(){

        accounter = new Accounter();

        int result = accounter.getTenPercent(this.total);

        return Integer.toString(result);
    }

    public void setPayday(int income) {

        accounter = new Accounter();
        this.total = accounter.updateIncome(total, income);
         amount.setText(Integer.toString(this.total));
    }
}
