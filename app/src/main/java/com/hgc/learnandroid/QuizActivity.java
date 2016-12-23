package com.hgc.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {
    private static int flag=0;
    public static  int con=0;
    private static int empty_option_checker[]={0,0,0,0,0};
    private RequestQueue requestQueue;
    private String url="http://learnandroid.16mb.com/quiz.JSON";
    private TextView question,option_one,option_two,option_three,option_four;
    private ImageView option1_icon,option2_icon,option3_icon,option4_icon;
    private static  int total_marks;
    private static int marks[];
    private static int original_sol[];
    private static int user_sol[]={0,0,0,0,0};
    private String result;
    private CardView button_next,button_prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result=bundle.getString("result");
          marks=new int[5];
        original_sol=new int[5];
        question=(TextView)findViewById(R.id.text_question);
        option_one=(TextView)findViewById(R.id.text_option1);
        option_two=(TextView)findViewById(R.id.text_option2);
        option_three=(TextView)findViewById(R.id.text_option3);
        option_four=(TextView)findViewById(R.id.text_option4);
        option1_icon=(ImageView)findViewById(R.id.icon_option1);
        option2_icon=(ImageView)findViewById(R.id.icon_option2);
        option3_icon=(ImageView)findViewById(R.id.icon_option3);
        option4_icon=(ImageView)findViewById(R.id.icon_option4);
        button_next=(CardView)findViewById(R.id.but_next);
        button_prev=(CardView)findViewById(R.id.but_prev);
//        buttonclicked();
//        flag++;
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==5){
                    Toast.makeText(QuizActivity.this, "result", Toast.LENGTH_SHORT).show();
                }
                else if(flag<5){
                        buttonclicked();
                        flag++;
                        con++;
                }
                else if(empty_option_checker[flag]==0){
                    Toast.makeText(QuizActivity.this, "Select any option first", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(QuizActivity.this, "something else", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag>0)
                {
                    flag--;
                    buttonclicked();

                }
                else {
                    Toast.makeText(QuizActivity.this, "No previous questions", Toast.LENGTH_SHORT).show();
                    flag=0;
                }
            }
        });
    }

    private void buttonclicked() {
        try {
            JSONArray parentArray = new JSONArray(result);
            if (flag< 5) {
                JSONObject finalObject = parentArray.getJSONObject(flag);
                String que = finalObject.getString("question");
                String opt1 = finalObject.getString("option1");
                String opt2 = finalObject.getString("option2");
                String opt3 = finalObject.getString("option3");
                String opt4 = finalObject.getString("option4");
//                final int solution = finalObject.getInt("correct_option");
//                original_sol[flag]=solution;
                question.setText(que);
                option_one.setText(opt1);
                option_two.setText(opt2);
                option_three.setText(opt3);
                option_four.setText(opt4);
                if(user_sol[flag]!=0)
                {
                    if(user_sol[flag]==1)
                    {
                        option1_icon.setImageResource(R.drawable.ic_star_fill);
                        option2_icon.setImageResource(R.drawable.ic_circle_empty);
                        option3_icon.setImageResource(R.drawable.ic_circle_empty);
                        option4_icon.setImageResource(R.drawable.ic_circle_empty);
                    }
                    else if(user_sol[flag]==2)
                    {
                        option1_icon.setImageResource(R.drawable.ic_circle_empty);
                        option2_icon.setImageResource(R.drawable.ic_star_fill);
                        option3_icon.setImageResource(R.drawable.ic_circle_empty);
                        option4_icon.setImageResource(R.drawable.ic_circle_empty);
                    }
                    else if(user_sol[flag]==3)
                    {
                        option1_icon.setImageResource(R.drawable.ic_circle_empty);
                        option2_icon.setImageResource(R.drawable.ic_circle_empty);
                        option3_icon.setImageResource(R.drawable.ic_star_fill);
                        option4_icon.setImageResource(R.drawable.ic_circle_empty);
                    }
                    else
                    {
                        option1_icon.setImageResource(R.drawable.ic_circle_empty);
                        option2_icon.setImageResource(R.drawable.ic_circle_empty);
                        option3_icon.setImageResource(R.drawable.ic_circle_empty);
                        option4_icon.setImageResource(R.drawable.ic_star_fill);
                    }
                }
                else {
                    option1_icon.setImageResource(R.drawable.ic_circle_empty);
                    option2_icon.setImageResource(R.drawable.ic_circle_empty);
                    option3_icon.setImageResource(R.drawable.ic_circle_empty);
                    option4_icon.setImageResource(R.drawable.ic_circle_empty);
                }
                option1_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        option1_icon.setImageResource(R.drawable.ic_star_fill);
                        option2_icon.setImageResource(R.drawable.ic_circle_empty);
                        option3_icon.setImageResource(R.drawable.ic_circle_empty);
                        option4_icon.setImageResource(R.drawable.ic_circle_empty);
                        empty_option_checker[flag]=10;
                        user_sol[flag]=1;
                    }
                });
                option2_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        option1_icon.setImageResource(R.drawable.ic_circle_empty);
                        option2_icon.setImageResource(R.drawable.ic_star_fill);
                        option3_icon.setImageResource(R.drawable.ic_circle_empty);
                        option4_icon.setImageResource(R.drawable.ic_circle_empty);
                        empty_option_checker[flag]=10;
                        user_sol[flag]=2;
                    }
                });
                option3_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        option1_icon.setImageResource(R.drawable.ic_circle_empty);
                        option2_icon.setImageResource(R.drawable.ic_circle_empty);
                        option3_icon.setImageResource(R.drawable.ic_star_fill);
                        option4_icon.setImageResource(R.drawable.ic_circle_empty);
                        empty_option_checker[flag]=10;
                        user_sol[flag]=3;
                    }
                });
                option4_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        option1_icon.setImageResource(R.drawable.ic_circle_empty);
                        option2_icon.setImageResource(R.drawable.ic_circle_empty);
                        option3_icon.setImageResource(R.drawable.ic_circle_empty);
                        option4_icon.setImageResource(R.drawable.ic_star_fill);
                        empty_option_checker[flag]=10;
                        user_sol[flag]=4;
                    }
                });
            }
            else
            {
                Toast.makeText(this, "previous", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }
}
