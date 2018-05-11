package com.example.root.quizapp1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 8/5/18.
 */

public interface Apiinterface {
   //void getContacts();
   @GET("api.php?amount=10&type=multiple")
   Call<Questions> getQuestions();
}
