package com.example.root.quizapp1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by root on 8/5/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Questions question;
    Questiondetail storeans;
    private Context context;
    static int score;
    String currectans;
    String ans;
    int x, i = 0, cuId, ansId,randomcount=0;
    ArrayList<String> cur = new ArrayList<>();
    ArrayList<String> ansu = new ArrayList<>();
    HashMap anslist = new HashMap();
    ArrayList<String> list;
    static  int state[];
    static int rendom[];
    static int w;
    static String[] selectedItem;
   // String[][] option = new String[w][4];

    //        int size=getItemCount();


    public Adapter(Questions question, Context context) {

        this.context = context;
        this.question = question;
        w=getItemCount();
        state=new int[w];
        rendom=new int[w];
        selectedItem=new String[w];
        rendom=setRendom();

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.questiontext.setText(question.getResults().get(position).getQuestion());

        currectans = question.getResults().get(position).getCurrect_answer();
        //option[x] = currectans;
        list = question.getResults().get(position).getIncurrect_answers();

        //holder.setOPtion(currectans,list,position);

        String[] optiong = new String[4];
        optiong[rendom[position]] = currectans;
        int s = 0;
        int a[] = new int[3];
        for (int i = 0; i < 4; i++) {
            if (rendom[position] != i) {
                a[s] = i;
                s++;
            }
        }
        for (i = 0; i < 3; i++) {
            optiong[a[i]] = list.get(i);
        }
        holder.op1.setText(optiong[0]);
        holder.op2.setText(optiong[1]);
        holder.op3.setText(optiong[2]);
        holder.op4.setText(optiong[3]);



        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //int id=holder.rg.getCheckedRadioButtonId();

                if (checkedId == holder.op1.getId()) {
                    question.setResults();
                    storeans.setselectedans(holder.op1.getText().toString());
                    //selectedItem[position] =holder.op1.getText().toString() ;
                    holder.op1.setChecked(true);
                    //Log.d("list", selectedItem[position]);

                } else if (checkedId == holder.op2.getId()) {
                    storeans.setselectedans(holder.op1.getText().toString());

                   // selectedItem[position] = holder.op4.getText().toString();
                    holder.op2.setChecked(true);
                   // Log.d("list", selectedItem[position]);

                } else if (checkedId == holder.op3.getId()) {
                    storeans.setselectedans(holder.op1.getText().toString());

                    //selectedItem[position] = holder.op3.getText().toString();
                    holder.op3.setChecked(true);
                   // Log.d("list", selectedItem[position]);

                } else if (checkedId == holder.op4.getId()) {
                    storeans.setselectedans(holder.op1.getText().toString());

                    //selectedItem[position] = holder.op4.getText().toString();
                    holder.op4.setChecked(true);
                    //Log.d("list", selectedItem[position]);

                }
            }


        });
        if(ans!=null) {
            ans = question.getResults().get(position).getselectedans();
            Log.d("selected", ans);
        }
       /* if (selectedItem[position] == null) {
            holder.op1.setChecked(false);
            holder.op2.setChecked(false);
            holder.op3.setChecked(false);
            holder.op4.setChecked(false);



        }
        else{


        }*/



               // int id=holder.rg.getCheckedRadioButtonId();


                //holder.rg.setTag(position);
               /* if(id==holder.op1.getId()){
                    selectedItem[position]=holder.op1.getText().toString();
                    Log.d("list:",selectedItem[position]);
                }*/
    }

    @Override
    public int getItemCount() {
        w=question.getResults().size();
        return w;
    }

   public  int[] setRendom(){
       int rendom1[] = new int[w];
       for(int i=0;i<=w-1;i++){
           Random ran = new Random();
           x = ran.nextInt(2) + 1;
           rendom1[i]=x;
       }
       return rendom1;
   }

   public  String[] setSelectedItem(){
       String[] select1=new String[w];
       for(int i=0;i<w;i++){
           select1[i]=selectedItem[i];
       }
       return select1;
   }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView questiontext;
        RadioButton op1,op2,op3,op4;
        RadioGroup rg;
        public MyViewHolder(View itemView) {
            super(itemView);
            questiontext = (TextView) itemView.findViewById(R.id.question);
            rg = (RadioGroup) itemView.findViewById(R.id.radioGroup);
            op1 = (RadioButton)itemView.findViewById(R.id.option1);
            op2 = (RadioButton)itemView.findViewById(R.id.option2);
            op3 = (RadioButton)itemView.findViewById(R.id.option3);
            op4 = (RadioButton)itemView.findViewById(R.id.option4);
        }



        void setOPtion(String curectans,ArrayList<String> incorectans,int position){
            rg.setTag(position);

            String[] optiong = new String[4];
            optiong[rendom[position]]=currectans;
            int s=0;
            int a[]=new int[3];
            for(int i=0;i<4;i++){
                if(rendom[position]!=i){
                    a[s]=i;
                    s++;
                }
            }
            for(i=0;i<3;i++){
                optiong[a[i]]=list.get(i);
            }
            op1.setText(optiong[0]);
            op2.setText(optiong[1]);
            op3.setText(optiong[2]);
            op4.setText(optiong[3]);

            if(rg.isSelected()){
                int f=rg.getCheckedRadioButtonId();
            }else{
                op1.setChecked(false);
                op2.setChecked(false);
                op3.setChecked(false);
                op4.setChecked(false);
            }

        }
    }

    void sendScore(int an,int cu){
        Log.d("cu:",Integer.toString(cu));
        Log.d("an:",Integer.toString(an));
        if(an==cu){
            score++;
        }
    }
    void sendScore(String an,String cu){
        Log.d("cu:",cu);
        //Log.d("an:",Integer.toString(an));
        if(an==cu){
            score++;
        }
    }


}

/*
    String[] setoption(String currect,ArrayList<String> incurrect,int position){

        String[] option = new String[4];
        int[] a = new int[3];
        int s = 0;
        for(int i=0;i<=w;i++){
           if(rendom[position]==i){
               continue;
           } else{

           }
        }

        option[rendom[randomcount]] = currectans;
        randomcount++;
        int p = incurrect.size();



        for (int i = 0; i < 4; i++) {

            if (option[i] == null) {
                a[s] = i;
                s++;
            } else {
                continue;
            }
        }

        for (int i = 0; i < p; i++) {
            option[a[i]] =incurrect.get(i);
        }
        for (int i = 0; i < 3; i++) {
            if (option[a[i]] == null) {
                option[a[i]] = "none of above";
            }
        }
        return option;
    }*/

/*
//listner
        holder.op1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (holder.op1.isChecked()) {
        holder.rg.setVisibility(View.GONE);
        } else {
        holder.rg.setVisibility(View.VISIBLE);
        }
        state[position] = 0;
        setRadio(holder, state[position]);
        }

        });
        holder.op2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (holder.op2.isChecked()) {
        holder.rg.setVisibility(View.GONE);
        } else {
        holder.rg.setVisibility(View.VISIBLE);
        }
        state[position] = 1;
        setRadio(holder, state[position]);
        }

        });
        holder.op3.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (holder.op1.isChecked()) {
        holder.rg.setVisibility(View.GONE);
        } else {
        holder.rg.setVisibility(View.VISIBLE);
        }
        state[position] = 2;
        setRadio(holder, state[position]);
        }

        });
        holder.op4.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (holder.op1.isChecked()) {
        holder.rg.setVisibility(View.GONE);
        } else {
        holder.rg.setVisibility(View.VISIBLE);
        }
        state[position] = 3;
        setRadio(holder, state[position]);
        }

        });

        }


private void setRadio(final MyViewHolder holder, int selection) {
        holder.op1.setChecked(false);
        holder.op2.setChecked(false);
        holder.op3.setChecked(false);
        holder.op4.setChecked(false);

        if (selection == 0) holder.op1.setChecked(true);
        if (selection == 1) holder.op2.setChecked(true);
        if (selection == 2) holder.op3.setChecked(true);
        if (selection == 3) holder.op4.setChecked(true);

        }*/



        /*
        if(option[0]==currectans){
            ans=holder.op1.getText().toString();
            cuId = holder.op1.getId();
        }
        if(option[1]==currectans){
            cuId = holder.op2.getId();
            ans=holder.op2.getText().toString();

        }
        if(option[2]==currectans){
            cuId = holder.op3.getId();
            ans=holder.op3.getText().toString();

        }
        if(option[3]==currectans){
            cuId = holder.op4.getId();
            ans=holder.op4.getText().toString();

        }*/


//sendScoreSt();
        /*
        holder.op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(v.getId()==cuId){
                   score++;
               }
            }
        });
        holder.op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==cuId){
                    score++;
                }
            }
        });
        holder.op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==cuId){
                    score++;
                }
            }
        });
        holder.op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==cuId){
                    score++;
                }
            }
        });*/

/*
        if(holder.op1.isChecked()){
            if(holder.op1.getId()==cuId){
                score++;
            }
            //sendScore(holder.op1.getId(),cuId);
        }
        if(holder.op2.isChecked()){
            sendScore(holder.op2.getId(),cuId);
        }
        if(holder.op3.isChecked()){
            sendScore(holder.op3.getId(),cuId);
        }
        if(holder.op4.isChecked()){
            sendScore(holder.op4.getId(),cuId);
        }*/






/*
  public void rbclick(View v){
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(radiobuttonid);
        radiovalue = rb.getText().toString();
        Log.d("radio button :" ,radiovalue);
    }

 */


/*
        if(holder.op2.getText()==""){
            holder.op2.setText(option[1]);
        }
        if(holder.op3.getText()==""){
            holder.op3.setText(option[2]);
        }
        if(holder.op4.getText()==""){
            holder.op4.setText(option[3]);
        }*/

       /* holder.op2.setText(option[1]);
        holder.op3.setText(option[2]);
        holder.op4.setText(option[3]);*/

//visiblity
/*
        if(option[0]==currectans){
            ans=holder.op1.getText().toString();
            cuId = holder.op1.getId();
        }
        if(option[1]==currectans){
            cuId = holder.op2.getId();
            ans=holder.op2.getText().toString();

        }
        if(option[2]==currectans){
            cuId = holder.op3.getId();
            ans=holder.op3.getText().toString();

        }
        if(option[3]==currectans){
            cuId = holder.op4.getId();
            ans=holder.op4.getText().toString();
        }*/
/* if(selectedItem[position]==holder.op1.getText().toString()){
                holder.op1.setChecked(true);
                holder.op4.setChecked(false);
                holder.op2.setChecked(false);
                holder.op3.setChecked(false);
            }
            if(selectedItem[position]==holder.op2.getText().toString()){
                holder.op2.setChecked(true);
                holder.op4.setChecked(false);
                holder.op1.setChecked(false);
                holder.op3.setChecked(false);
            }
            if(selectedItem[position]==holder.op3.getText().toString()){
                holder.op3.setChecked(true);
                holder.op4.setChecked(false);
                holder.op2.setChecked(false);
                holder.op1.setChecked(false);
            }
            if(selectedItem[position]==holder.op4.getText().toString()){
                holder.op4.setChecked(true);
                holder.op1.setChecked(false);
                holder.op2.setChecked(false);
                holder.op3.setChecked(false);
            }*/



/*
        if(selectedItem[position]==null){
            holder.op1.setChecked(false);
            holder.op2.setChecked(false);
            holder.op3.setChecked(false);
            holder.op4.setChecked(false);

            holder.op1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.op1.setChecked(true);
                    holder.op2.setChecked(false);
                    holder.op3.setChecked(false);
                    holder.op4.setChecked(false);
                    selectedItem[position]=holder.op1.getText().toString();
                    Log.d("list1:",selectedItem[position]);

                    /*if(holder.op1.isChecked()){
                        //anslist.put(position,holder.op1.getText().toString());

                        //storeans.setselectedans(holder.op1.getText().toString());
                       // state[position] = 0;
                       // setRadio(holder, state[position],position);

                    }
                }
            });
            holder.op2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                    holder.op2.setChecked(true);
                    holder.op1.setChecked(false);
                    holder.op3.setChecked(false);
                    holder.op4.setChecked(false);
                    selectedItem[position]=holder.op2.getText().toString();
                    Log.d("list1:",selectedItem[position]);}
                }
            });
           /* holder.op2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.op2.setChecked(true);
                    holder.op1.setChecked(false);
                    holder.op3.setChecked(false);
                    holder.op4.setChecked(false);
                    selectedItem[position]=holder.op2.getText().toString();
                    Log.d("list:",selectedItem[position]);
                    if(holder.op2.isChecked()) {
                        //anslist.put(position,holder.op2.getText().toString());

                    }
                }
            });
            holder.op3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.op3.setChecked(true);
                    holder.op1.setChecked(false);
                    holder.op2.setChecked(false);
                    holder.op4.setChecked(false);
                    selectedItem[position]=holder.op3.getText().toString();
                    Log.d("list1:",selectedItem[position]);
                    if(holder.op3.isChecked()) {
                       // anslist.put(position,holder.op3.getText().toString());

                    }
                }

            });
            holder.op4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.op4.setChecked(true);
                    holder.op1.setChecked(false);
                    holder.op2.setChecked(false);
                    holder.op3.setChecked(false);
                    selectedItem[position]=holder.op4.getText().toString();
                    Log.d("list1:",selectedItem[position]);
                    if(holder.op4.isChecked()) {
                        //anslist.put(position,holder.op4.getText().toString());

                    }
                }

            });

        }else{

            if(selectedItem[position].contentEquals(holder.op1.getText().toString())){
                holder.op1.setChecked(true);
                holder.op4.setChecked(false);
                holder.op2.setChecked(false);
                holder.op3.setChecked(false);
            }
            if(selectedItem[position].contentEquals(holder.op2.getText().toString())){
                holder.op2.setChecked(true);
                holder.op4.setChecked(false);
                holder.op1.setChecked(false);
                holder.op3.setChecked(false);
            }
            if(selectedItem[position].contentEquals(holder.op3.getText().toString())){
                holder.op3.setChecked(true);
                holder.op4.setChecked(false);
                holder.op2.setChecked(false);
                holder.op1.setChecked(false);
            }
            if(selectedItem[position].contentEquals(holder.op4.getText().toString())){
                holder.op4.setChecked(true);
                holder.op1.setChecked(false);
                holder.op2.setChecked(false);
                holder.op3.setChecked(false);
            }
            //selectedItem[position]=" ";
            holder.op1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedItem[position]=holder.op1.getText().toString();
                    Log.d("list2:",selectedItem[position]);
                    if(holder.op1.isChecked()){
                       // anslist.put(position,holder.op1.getText().toString());

                        //storeans.setselectedans(holder.op1.getText().toString());
                        // state[position] = 0;
                        // setRadio(holder, state[position],position);

                    }
                }
            });
            holder.op2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked){
                       selectedItem[position]=holder.op2.getText().toString();
                       Log.d("list:",selectedItem[position]);
                       holder.op2.setChecked(true);
                       holder.op4.setChecked(false);
                       holder.op1.setChecked(false);
                       holder.op3.setChecked(false);
                   }else{

                   }
                }
            });

           /* holder.op2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedItem[position]=holder.op2.getText().toString();
                    Log.d("list:",selectedItem[position]);
                    if(holder.op2.isChecked()) {
                        //anslist.put(position,holder.op2.getText().toString());
                    }
                }
            });
            holder.op3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedItem[position]=holder.op3.getText().toString();
                    Log.d("list:",selectedItem[position]);
                    if(holder.op3.isChecked()) {
                        //anslist.put(position,holder.op3.getText().toString());
                    }
                }

            });
            holder.op4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedItem[position]=holder.op4.getText().toString();
                    Log.d("list:",selectedItem[position]);
                    if(holder.op4.isChecked()) {
                        //anslist.put(position,holder.op4.getText().toString());
                    }
                }

            });

        }*/
    /*    selectedItem= setSelectedItem();
        for(int i =0;i<w;i++){
            if(selectedItem[i]!=null){
            Log.d("seleclist:",selectedItem[i]);}
            if(optiong[rendom[position]]==selectedItem[i]){
                score++;
            }
        }*/
