package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class Lesson_content extends AppCompatActivity {
private RequestQueue requestQueue;
    private ProgressDialog pdLoading;
    private String url="http://learnandroid.16mb.com/quiz.JSON";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_content);
        pdLoading = new ProgressDialog(Lesson_content.this);
        pdLoading.setMessage("Please wait Loading....");
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.quizfab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz();
            }
        });
    }
    private void quiz()
    {  pdLoading.show();
        requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    String result=response.toString();
                    Intent intent=new Intent(Lesson_content.this,QuizActivity.class);
                    intent.putExtra("result",result);
                    pdLoading.dismiss();
                    startActivity(intent);

                }
                catch (Exception e)
                {
                    pdLoading.dismiss();
                    Toast.makeText(Lesson_content.this, "No response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pdLoading.dismiss();
                AlertDialog.Builder dialogbox = new AlertDialog.Builder(Lesson_content.this);
                dialogbox.setMessage("Can't fetch the questions...");
                dialogbox.setCancelable(true);
                dialogbox.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quiz();
                    }
                });
                dialogbox.show();
            }
        });
        requestQueue.add(arrayRequest);
    }
}
