package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainContent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView mTopics;
    private mainTopicsAdapter mAdapter;
    private ProgressDialog pdLoading;
    String URL = "http://learnandroid.16mb.com/main.JSON";
    RequestQueue requestQueue;
    TextView Textname;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);
        firebaseAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    finish();
                    startActivity(new Intent(MainContent.this,authActivity.class));
                }
            }
        };
        String name;
        //noinspection ConstantConditions
        name=firebaseAuth.getCurrentUser().getEmail();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Hey Buddy", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navheader=navigationView.inflateHeaderView(R.layout.nav_header_main_content);
        Textname=(TextView)navheader.findViewById(R.id.id_frag_name);
        Textname.setText(name);
        pdLoading = new ProgressDialog(MainContent.this);
        pdLoading.setMessage("Please wait Loading....");
        halwaa();
    }
    private void halwaa(){
        pdLoading.show();
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
                    mTopics = (RecyclerView)findViewById(R.id.recy);
                    mAdapter = new mainTopicsAdapter(MainContent.this, data,result);
                    mTopics.setAdapter(mAdapter);
                    mTopics.setLayoutManager(new LinearLayoutManager(MainContent.this));
                    pdLoading.dismiss();
                }
                catch (JSONException e) {

                    pdLoading.dismiss();
                    AlertDialog.Builder dialogbox = new AlertDialog.Builder(MainContent.this);
                    dialogbox.setMessage("Can't fetch the data click to retry...");
                    dialogbox.setCancelable(false);
                    dialogbox.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            halwaa();
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
                        AlertDialog.Builder dialogbox = new AlertDialog.Builder(MainContent.this);
                        dialogbox.setMessage("Can't fetch the data click to retry...");
                        dialogbox.setCancelable(false);
                        dialogbox.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                halwaa();
                            }
                        });
                        dialogbox.show();
                    }
                }
        );

        requestQueue.add(arrayRequest);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sign_out) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainContent.this,authActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem itema) {
        // Handle navigation view item clicks here.
        int id = itema.getItemId();

        if (id == R.id.nav_notes) {
           Intent notes_intent=new Intent(MainContent.this,Notes.class);
            startActivity(notes_intent);
        }
        else if (id == R.id.nav_signout) {
            firebaseAuth.signOut();
            finish();
        }
//        else if (id == R.id.nav_settings) {
//            Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
//        }
        else if (id == R.id.nav_share) {

        }
//        else if (id == R.id.nav_faq) {
//
//        }
//        else if (id == R.id.nav_assignments) {
//
//        }
//        else if (id == R.id.nav_leaderboard) {
//
//        }
        else if (id == R.id.nav_contactus) {
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"aakashandro@gmail.com"});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact us mail");
/* Send it off to the Activity-Chooser */
          startActivity(Intent.createChooser(emailIntent, "Send mail from.."));
        }
        else if (id == R.id.nav_account) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
