package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainTopics extends AppCompatActivity {

    private RecyclerView mTopics;
    private mainTopicsAdapter mAdapter;
    private ProgressDialog pdLoading;
    String URL = "http://information.16mb.com/android_maincards.JSON";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_topics);
        pdLoading = new ProgressDialog(MainTopics.this);
        pdLoading.setMessage("Please wait Loading....");
        pdLoading.show();
        halwa();
    }
    private void halwa(){
        final List<mainTopicsMembers> data=new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest =new JsonArrayRequest(Request.Method.GET, URL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try { String result=response.toString();
                    JSONArray parentArray = new JSONArray(result);
                    for (int i =0; i<parentArray.length(); i++) {
                        mainTopicsMembers carddata=new mainTopicsMembers();
                        JSONObject finalObject = parentArray.getJSONObject(i);
                        carddata.title=finalObject.getString("title");
                        carddata.body=finalObject.getString("body");

                        data.add(carddata);
                    }
                    Toast.makeText(MainTopics.this,data+"", Toast.LENGTH_SHORT).show();
                    mTopics = (RecyclerView)findViewById(R.id.recy);
                    mAdapter = new mainTopicsAdapter(MainTopics.this, data);
                    mTopics.setAdapter(mAdapter);
                    mTopics.setLayoutManager(new LinearLayoutManager(MainTopics.this));
                    pdLoading.dismiss();
                }
                catch (JSONException e) {
                    Toast.makeText(MainTopics.this, "error"+e, Toast.LENGTH_SHORT).show();
                    pdLoading.dismiss();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainTopics.this, "volley error"+error, Toast.LENGTH_SHORT).show();
                        pdLoading.dismiss();
                    }
                }
        );

        requestQueue.add(arrayRequest);
    }
}

