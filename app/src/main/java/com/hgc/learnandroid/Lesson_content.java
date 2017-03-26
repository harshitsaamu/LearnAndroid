package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Lesson_content extends AppCompatActivity {
private RequestQueue requestQueue;
    private ProgressDialog pdLoading;
    private TextView lesson_content;
    private String url="http://learnandroid.16mb.com/quiz.JSON";
    private int pos1,pos2;
   private String lessonNo;
    private String URL="http://learnandroid.16mb.com/lessoncontent.json.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_content);
        Bundle bundle=getIntent().getExtras();
        pos1= bundle.getInt("positionPrevious");
        pos2= bundle.getInt("position")+1;
        lessonNo="lesson"+Integer.toString(pos2);

        pdLoading = new ProgressDialog(Lesson_content.this);
        pdLoading.setMessage("Please wait Loading....");
        lesson_content=(TextView)findViewById(R.id.idlessoncontent);
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.quizfab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz();
            }
        });
        lesoon();
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
    private void lesoon(){
        pdLoading.show();
        final List<mainTopicsMembers> data=new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest =new JsonArrayRequest(Request.Method.GET, URL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try { String result=response.toString();
                    JSONArray parentArray = new JSONArray(result);
                        JSONObject finalObject = parentArray.getJSONObject(pos1);
                        String body=finalObject.getString(lessonNo);
                    lesson_content.setText(Html.fromHtml(body));
                    pdLoading.dismiss();
                }
                catch (JSONException e) {

                    pdLoading.dismiss();
                    AlertDialog.Builder dialogbox = new AlertDialog.Builder(Lesson_content.this);
                    dialogbox.setMessage("Can't fetch the data click to retry...");
                    dialogbox.setCancelable(false);
                    dialogbox.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            lesoon();
                        }
                    });
                    dialogbox.show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainContent.this, "volley error"+error, Toast.LENGTH_SHORT).show();
                        pdLoading.dismiss();
                        AlertDialog.Builder dialogbox = new AlertDialog.Builder(Lesson_content.this);
                        dialogbox.setMessage("Can't fetch the data click to retry...");
                        dialogbox.setCancelable(false);
                        dialogbox.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                lesoon();
                            }
                        });
                        dialogbox.show();
                    }
                }
        );

        requestQueue.add(arrayRequest);
    }
}
