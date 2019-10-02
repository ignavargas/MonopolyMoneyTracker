package com.example.monopolymoneytracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

public class DialogMaker extends Activity {

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    private EditText input;
    private int receivedValue;

    public DialogMaker(){
        receivedValue = 0;
    }

    public void showAmount(String title, String message){

        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(message);

        dialogBuilder.setNeutralButton("PERFECTO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void registerPayment(){

        Context con = this;
        dialogBuilder = new AlertDialog.Builder(con);
        View view = getLayoutInflater().inflate(R.layout.activity_input_dialog, null);
        dialogBuilder.setView(view);
        input = (EditText) view.findViewById(R.id.inputValue);
        dialogBuilder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setReceivedValue(Integer.getInteger(input.getText().toString()));
                dialog.dismiss();
            }
        });

        dialogBuilder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        dialog = dialogBuilder.create();
    }

    public void setReceivedValue(int receivedValue) {
        this.receivedValue = receivedValue;
    }

    public int getReceivedValue() {
        return receivedValue;
    }
}
