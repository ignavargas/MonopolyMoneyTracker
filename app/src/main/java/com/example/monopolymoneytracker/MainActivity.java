package com.example.monopolymoneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String username;
    private Button play;
    private EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.playButton);
        inputName = (EditText) findViewById(R.id.editText);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUsernameValue(inputName.getText().toString());
                startGame();
            }
        });
    }

    public void setUsernameValue(String input){

        this.username = input;
    }

    public void startGame(){

        Intent intent = new Intent(this, MoneyTracker.class);
        intent.putExtra("username", this.username);
        intent.putExtra("totalmoney", "2000");
        startActivity(intent);
    }
}
