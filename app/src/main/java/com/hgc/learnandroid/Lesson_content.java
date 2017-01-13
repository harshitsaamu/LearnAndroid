package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class Lesson_content extends AppCompatActivity {
private RequestQueue requestQueue;
    private ProgressDialog pdLoading;
    private TextView lesson_content;
    private String url="http://learnandroid.16mb.com/quiz.JSON";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_content);
        pdLoading = new ProgressDialog(Lesson_content.this);
        pdLoading.setMessage("Please wait Loading....");
        lesson_content=(TextView)findViewById(R.id.idlessoncontent);
        lesson_content.setText(Html.fromHtml("<p>Android is an open source and Linux-based <b>Operating System</b> for mobile devices such as smartphones and tablet computers. Android was developed by the <i>Open Handset Alliance</i>, led by Google, and other companies.</p><p>Android offers a unified approach to application development for mobile devices which means developers need only develop for Android, and their applications should be able to run on different devices powered by Android.</p><p>The first beta version of the Android Software Development Kit (SDK) was released by Google in 2007 where as the first commercial version, Android 1.0, was released in September 2008.</p><p>On June 27, 2012, at the Google I/O conference, Google announced the next Android version, 4.1 <b>Jelly Bean</b>. Jelly Bean is an incremental update, with the primary aim of improving the user interface, both in terms of functionality and performance.</p><p>The source code for Android is available under free and open source software licenses. Google publishes most of the code under the Apache License version 2.0 and the rest, Linux kernel changes, under the GNU General Public License version 2.</p><h2>Features of Android</h2><p>Android is a powerful operating system competing with Apple 4GS and supports great features. Few of them are listed below &minus;</p><table class=\"table table-bordered\"><tr><th>Sr.No.</th><th style=\"text-align:center;\">Feature &amp; Description</th></tr><tr><td>1</td><td><p><b>Beautiful UI</b></p><p>Android OS basic screen provides a beautiful and intuitive user interface.</p></td></tr><tr><td>2</td><td><p><b>Connectivity</b></p><p>GSM/EDGE, IDEN, CDMA, EV-DO, UMTS, Bluetooth, Wi-Fi, LTE, NFC and WiMAX.</p></td></tr><tr><td>3</td><td><p><b>Storage</b></p><p>SQLite, a lightweight relational database, is used for data storage purposes.</p></td></tr><tr><td>4</td><td><p><b>Media support</b></p><p>H.263, H.264, MPEG-4 SP, AMR, AMR-WB, AAC, HE-AAC, AAC 5.1, MP3, MIDI, Ogg Vorbis, WAV, JPEG, PNG, GIF, and BMP.</p></td></tr><tr><td>5</td><td><p><b>Messaging</b></p><p>SMS and MMS</p></td></tr><tr><td>6</td><td><p><b>Web browser</b></p><p>Based on the open-source WebKit layout engine, coupled with Chrome's V8 JavaScript engine supporting HTML5 and CSS3.</p></td></tr><tr><td>7</td><td><p><b>Multi-touch</b></p><p>Android has native support for multi-touch which was initially made available in handsets such as the HTC Hero.</p></td></tr><tr><td>8</td><td><p><b>Multi-tasking</b></p><p>User can jump from one task to another and same time various application can run simultaneously.</p></td></tr><tr><td>9</td><td><p><b>Resizable widgets</b></p><p>Widgets are resizable, so users can expand them to show more content or shrink them to save space.</p></td></tr><tr><td>10</td><td><p><b>Multi-Language</b></p><p>Supports single direction and bi-directional text.</p></td></tr><tr><td>11</td><td><p><b>GCM</b></p><p>Google Cloud Messaging (GCM) is a service that lets developers send short message data to their users on Android devices, without needing a proprietary sync solution.</p></td></tr><tr><td>12</td><td><p><b>Wi-Fi Direct</b></p><p>A technology that lets apps discover and pair directly, over a high-bandwidth peer-to-peer connection.</p></td></tr><tr><td>13</td><td><p><b>Android Beam</b></p><p>A popular NFC-based technology that lets users instantly share, just by touching two NFC-enabled phones together.</p></td></tr></table><h2>Android Applications</h2><p>Android applications are usually developed in the Java language using the Android Software Development Kit.</p><p>Once developed, Android applications can be packaged easily and sold out either through a store such as <b>Google Play</b>, <b>SlideME</b>, <b>Opera Mobile Store</b>, <b>Mobango</b>, <b>F-droid</b> and the <b>Amazon Appstore</b>.</p><p>Android powers hundreds of millions of mobile devices in more than 190 countries around the world. It's the largest installed base of any mobile platform and growing fast. Every day more than 1 million new Android devices are activated worldwide.</p><p>This tutorial has been written with an aim to teach you how to develop and package Android application. We will start from environment setup for Android application programming and then drill down to look into various aspects of Android applications.</p>"));
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
