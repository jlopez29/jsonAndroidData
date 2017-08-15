package com.jlopez.test;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MainActivity extends AppCompatActivity {
    ExampleObject ex;
    ExampleObject ex1;
    RequestQueue queue;
    String companyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String id;

        queue = Volley.newRequestQueue(this);

        final String tag = "JSON GET REQUEST";

        companyID = "";

        String url0 = "https://app.iformbuilder.com/exzact/api/oauth/token";
        String url = "https://app.iformbuilder.com/exzact/api/v60/profiles/self";
        String url1 = "https://app.iformbuilder.com/exzact/api/v60/profiles/470100/pages/3639657/records";
        //String url = "http://httpbin.org/get";

//        JSONObject obj = new JSONObject();
//
//        //Key key1 = "2bfc88e2d34edd9050b949747d2312593715dd65";
//
//        String token = Jwts.builder()
//                .setIssuer("3776c5bf8c838b2304e4b6a66bb7381878099001")
//                .setHeaderParam(JwsHeader.TYPE,"JWT")
//                .setAudience("HTTPS://COMPANY.IFORMBUILDER.COM/EXZACT/API/OAUTH/TOKEN")
//                .setExpiration(new Date(SystemClock.elapsedRealtime() + (10000)))
//                .setIssuedAt(new Date(SystemClock.elapsedRealtime()))
//                .signWith(SignatureAlgorithm.HS256, "2bfc88e2d34edd9050b949747d2312593715dd65").compact();
//        long l1 = 1384370238;
//        l1 = TimeUnit.MILLISECONDS.toSeconds(l1);
//        long l2 = 1384370228;
//        l2 = TimeUnit.MILLISECONDS.toSeconds(l2);
//        String token1 = Jwts.builder()
//                .setIssuer("1d38f6a6c89c868b6de90819d9b4e46ee6bfd05a")
//                .setAudience("https://company.iformbuilder.com/exzact/api/oauth/token")
//                .setExpiration(new Date(l1))
//                .setIssuedAt(new Date(l2))
//                .signWith(SignatureAlgorithm.HS256, "6b19083c7f0889cdb7035a1f845320a298810cb0")
//                .setHeaderParam(JwsHeader.TYPE,"JWT").compact();
//
//        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICAgImlzcyI6ICIxZDM4ZjZhNmM4OWM4NjhiNmRlOTA4MTlkOWI0ZTQ2ZWU2YmZkMDVhIiwKICAgICJhdWQiOiAiaHR0cHM6Ly9jb21wYW55Lmlmb3JtYnVpbGRlci5jb20vZXh6YWN0L2FwaS9vYXV0aC90b2tlbiIsCiAgICAiZXhwIjogMTM4NDM3MDIzOCwKICAgICJpYXQiOiAxMzg0MzcwMjI4Cn0.H4FrISGS5lGiREX9bodxh7U-KVMHo7HgFh0N-LqdlI0
//        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxZDM4ZjZhNmM4OWM4NjhiNmRlOTA4MTlkOWI0ZTQ2ZWU2YmZkMDVhIiwiYXVkIjoiaHR0cHM6Ly9jb21wYW55Lmlmb3JtYnVpbGRlci5jb20vZXh6YWN0L2FwaS9vYXV0aC90b2tlbiIsImV4cCI6MTM4NDM3MCwiaWF0IjoxMzg0MzcwfQ._GFp4bxmrlFO3uMjdIxS5535Pygv81VNfub6_dzou60
//        Log.i("JWT", token1.toString());
//
//        //ExampleObject ex = new ExampleObject(token);
//        ex = new ExampleObject(token);
//
//        url0 += "?grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer&assertion=";
//        url0 += token;
//
//        StringRequest sr = new StringRequest(Request.Method.POST, url0, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i("JWT RESPONSE", response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("JWT ERROR", error.toString());
//            }
//        }) {
//        };
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        sr.setRetryPolicy(new DefaultRetryPolicy(5000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        queue.add(sr);
        makeRequest(url,"id");
        Log.i("IDS",ex.getCount());



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                String pageUrl = "https://app.iformbuilder.com/exzact/api/v60/profiles/" + ex.getCount() + "/pages" ;
                makeArrayRequest(pageUrl,"id",0);
            }
        }, 15000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                String recordsUrl = "https://app.iformbuilder.com/exzact/api/v60/profiles/" + ex.getCount() + "/pages/" + ex1.getCount() + "/records" ;
                makeArrayRequest(recordsUrl,"id", 1);
            }
        }, 25000);



//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest req1 = new StringRequest(Request.Method.GET,
//                url1,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i(tag,response.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("volley", "Error: " + error.getMessage());
//                error.printStackTrace();
//            }
//        }) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer 8d38a69fcc7b7ec6aaf5dda461c3ad905853d612");
//
//                return params;
//            }
//
//        };
//
//        req1.setRetryPolicy(new DefaultRetryPolicy(5000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        queue.add(req1);
        //queue.add(request);



    }

    public void makeRequest(String url, String item)
    {


        ex = new ExampleObject("");

        final ExampleObject ex1 = new ExampleObject(item);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            ex = new ExampleObject(response.getString(ex1.getCount()));

                            Log.i("ID ", ex.getCount());

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.e("VOLLEY ", error.toString());
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer e50aea95e02cefea0b90176af2aae2f06c9cd1ae");

                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adds the JSON object request "obreq" to the request queue
        queue.add(jsonObjectRequest);
    }

    public void makeArrayRequest(String url, final String item, final int mode)
    {

        ex1 = new ExampleObject("");

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array

                            for(int i = 0; i < response.length();i++)
                            {
                                JSONObject j = response.getJSONObject(i);
                                if(j.getString(item).equals("3639657") && mode == 0)
                                    ex1 = new ExampleObject(j.getString(item));
                                else if(mode == 1)
                                    ex1 = new ExampleObject(j.getString(item));
                            }


                            Log.i("ID ", ex1.getCount());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.e("VOLLEY ", error.toString());
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer e50aea95e02cefea0b90176af2aae2f06c9cd1ae");

                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adds the JSON object request "obreq" to the request queue
        queue.add(jsonObjectRequest);
    }

        public class ExampleObject {
            private String count;

            public ExampleObject(String count) {
                this.count = count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getCount() {
                return count;
            }
        }

}
