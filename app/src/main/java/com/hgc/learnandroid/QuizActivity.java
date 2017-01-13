package com.hgc.learnandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {
    private int flag=0;
    private TextView question,bnext;
    private RadioGroup radio_group;
    private RadioButton radioButton[]=new RadioButton[5];
    private int marks=0;
    private JSONArray parentArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String result;
        super.onCreate(savedInstanceState);
         CardView button_next;
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result = bundle.getString("result");
        final AlertDialog.Builder dialogBox=new AlertDialog.Builder(this);
        question = (TextView) findViewById(R.id.text_question);
        radioButton[0]=(RadioButton)findViewById(R.id.idoption1);
        radioButton[1]=(RadioButton)findViewById(R.id.idoption2);
        radioButton[2]=(RadioButton)findViewById(R.id.idoption3);
        radioButton[3]=(RadioButton)findViewById(R.id.idoption4);
        radioButton[4]=(RadioButton)findViewById(R.id.idoption5);
        radio_group = (RadioGroup) findViewById(R.id.idradiogroup);
        button_next = (CardView) findViewById(R.id.but_next);
        bnext = (TextView) findViewById(R.id.idbnext);
        try {
             parentArray = new JSONArray(result);
        } catch (JSONException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        set();
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if(radioButton[4].isChecked()){
                Toast.makeText(QuizActivity.this, "Select any option first..", Toast.LENGTH_SHORT).show();
            }
                else{
                if (check()) {
                    marks++;
                }
                if (flag == parentArray.length()-1) {
                    dialogBox.setMessage( "Your result is "+marks+" Out of "+parentArray.length());
                    dialogBox.setTitle("RESULT");
                    dialogBox.setIcon(R.drawable.ic_result);
                      dialogBox.setPositiveButton("Go to Lesson", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    dialogBox.setCancelable(false);
                    dialogBox.show();
                }
                else {
                    if(flag==parentArray.length()-2)
                        bnext.setText("RESULT");
                    flag++;
                set();
                }
                }
            }
        });
    }

    private void set() {
        radioButton[4].setChecked(true);
        String que="",opt1="",opt2="",opt3="",opt4="";
        try {
            JSONObject finalObject = parentArray.getJSONObject(flag);
            que = finalObject.getString("question");
            opt1 = finalObject.getString("option1");
            opt2 = finalObject.getString("option2");
            opt3 = finalObject.getString("option3");
            opt4 = finalObject.getString("option4");
        }
        catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        question.setText(que);
        radioButton[0].setText(opt1);
        radioButton[1].setText(opt2);
        radioButton[2].setText(opt3);
        radioButton[3].setText(opt4);
    }
    private boolean check()
    {     int ans=0;
        try {
            JSONObject ansObject=parentArray.getJSONObject(flag);
             ans=ansObject.getInt("correct_option");
        } catch (JSONException e) {
            Toast.makeText(this, "JSON error", Toast.LENGTH_SHORT).show();
        }
        if(radioButton[ans-1].isChecked())
        {
            return true;
        }
        else
        {
        return false;
        }
    }
}
