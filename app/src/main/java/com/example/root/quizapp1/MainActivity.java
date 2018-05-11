package com.example.root.quizapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.button_start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == start){
            Intent intent = new Intent(MainActivity.this, StartQuiz.class);
            startActivity(intent);
        }
    }
}
