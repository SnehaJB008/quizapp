package com.example.root.quizapp1;

import java.util.ArrayList;

/**
 * Created by root on 9/5/18.
 */

public class Questiondetail {
    public  String selectedans;
    public String getselectedans()
    {
        return selectedans;
    }
    public void setselectedans(String selectedans) {

        this.selectedans = selectedans;
    }


    public String getQuestion() {
        return question;
    }

    private String question;

    public void setQuestion(String question) {
        this.question = question;
    }
    private String correct_answer;

    public String getCurrect_answer() {
        return correct_answer;
    }

    public void setCurrect_answer(String currect_answer){
        this.correct_answer = currect_answer;
    }

    public ArrayList<String> getIncurrect_answers() {

        return incorrect_answers;
    }

    public void setIncurrect_answers(ArrayList<String> incurrect_answers) {
        this.incorrect_answers = incurrect_answers;
    }


    private ArrayList<String> incorrect_answers;

}
