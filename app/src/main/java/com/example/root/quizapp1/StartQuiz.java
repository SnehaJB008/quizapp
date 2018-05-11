package com.example.root.quizapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartQuiz extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Questions questions;
    private Adapter adapter;
    private Apiinterface apiinterface;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        fatchInformation();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);

    }
    public void fatchInformation(){
            apiinterface = ApiClient.getApiClient().create(Apiinterface.class);

        Call<Questions> call = apiinterface.getQuestions();
        call.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                Log.d("ResponseRetrofit",response.toString());
                questions = response.body();
                adapter = new Adapter(questions,StartQuiz.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                Log.d("ErrorResp",t.toString());
            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v == submit){
            int scorefinal = adapter.score;
            Toast.makeText(StartQuiz.this, "score is"+scorefinal,Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(StartQuiz.this, Result.class);
           // startActivity(intent);
        }
    }


}
