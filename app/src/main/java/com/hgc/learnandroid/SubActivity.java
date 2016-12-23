package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {
//    private String url = "http://www.learnandroid.16mb.com/";
    private ProgressDialog pdLoading;
//    RequestQueue requestQueue;
    private SubActivityAdapter mAdapter;
    private RecyclerView mSubTopics;
//    private String updatedurl;
    private String jsonresult;
    private String quizjson;
    private int pos;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        pos = bundle.getInt("position");
        jsonresult=bundle.getString("jsonresult");
//        updatedurl=url+position+".JSON";
        pdLoading = new ProgressDialog(SubActivity.this);
        pdLoading.setMessage("Please wait Loading....");
        pdLoading.show();
        pizza();
    }

    private void pizza() {
        final List<SubActivityMembers> data = new ArrayList<>();
                try {
                    String result = jsonresult;
                    JSONArray parentArray = new JSONArray(result);
                    JSONObject parentObject=parentArray.getJSONObject(pos);
                    JSONArray childArray=parentObject.getJSONArray("subtopicarray");
                    for (int i = 0; i < childArray.length(); i++) {
                        SubActivityMembers carddata = new SubActivityMembers();
                        JSONObject finalObject = childArray.getJSONObject(i);
                        carddata.subtitle = finalObject.getString("subtitle");
                        data.add(carddata);

                    }
                    mSubTopics = (RecyclerView) findViewById(R.id.sub_activity_recy);
                    mAdapter = new SubActivityAdapter(SubActivity.this, data,result);
                    mSubTopics.setAdapter(mAdapter);
                    mSubTopics.setLayoutManager(new LinearLayoutManager(SubActivity.this));
                    pdLoading.dismiss();
                } catch (JSONException e) {
                    Toast.makeText(SubActivity.this, "error" + e, Toast.LENGTH_SHORT).show();
                    pdLoading.dismiss();
                }
            }


}