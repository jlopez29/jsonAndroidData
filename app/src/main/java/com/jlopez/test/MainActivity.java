package com.jlopez.test;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MainActivity extends AppCompatActivity {
    String profileId;
    String pageId;
    String recordsId;
    String recordInfo;
    String token;

    ArrayList<Record> recordList = new ArrayList<>();

    RequestQueue queue;

    ArrayAdapter<String> lvAdapter;
    ListView lv;
    TextView proID;
    TextView pageIDview;

    ProgressBar pb;
    View pbV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String id;

        queue = Volley.newRequestQueue(this);

        String url0 = "https://app.iformbuilder.com/exzact/api/oauth/token";
        String url = "https://app.iformbuilder.com/exzact/api/v60/profiles/self";

        lv = (ListView)findViewById(R.id.recordsList);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        pbV = findViewById(R.id.progressBarLayout);

        pbV.setVisibility(View.VISIBLE);

        proID = (TextView)findViewById(R.id.proidObtained);
        pageIDview = (TextView)findViewById(R.id.pageidObtained);

        lvAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        lv.setAdapter(lvAdapter);
/*
        //My signed JWT
        String token = Jwts.builder()
                .setIssuer("3776c5bf8c838b2304e4b6a66bb7381878099001")
                .setHeaderParam(JwsHeader.TYPE,"JWT")
                .setAudience("https://app.iformbuilder.com/exzact/api/oauth/token")
                .setExpiration(new Date(SystemClock.elapsedRealtime() + (10000)))
                .setIssuedAt(new Date(SystemClock.elapsedRealtime()))
                .signWith(SignatureAlgorithm.HS256, "2bfc88e2d34edd9050b949747d2312593715dd65").compact();


        //Example from docs signed jwt
        long l1 = 1384370238;
        long l2 = 1384370228;
        String token1 = Jwts.builder()
                .setHeaderParam(JwsHeader.TYPE,"JWT")
                .setIssuer("1D38F6A6C89C868B6DE90819D9B4E46EE6BFD05A")
                .setAudience("HTTPS://COMPANY.IFORMBUILDER.COM/EXZACT/API/OAUTH/TOKEN")
                .setExpiration(new Date(l1))
                .setIssuedAt(new Date(l2))
                .signWith(SignatureAlgorithm.HS256, "6b19083c7f0889cdb7035a1f845320a298810cb0").compact();

        //docs example signed jwt
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICAgImlzcyI6ICIxZDM4ZjZhNmM4OWM4NjhiNmRlOTA4MTlkOWI0ZTQ2ZWU2YmZkMDVhIiwKICAgICJhdWQiOiAiaHR0cHM6Ly9jb21wYW55Lmlmb3JtYnVpbGRlci5jb20vZXh6YWN0L2FwaS9vYXV0aC90b2tlbiIsCiAgICAiZXhwIjogMTM4NDM3MDIzOCwKICAgICJpYXQiOiAxMzg0MzcwMjI4Cn0.H4FrISGS5lGiREX9bodxh7U-KVMHo7HgFh0N-LqdlI0
        //my example from docs signed jwt
        eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxZDM4ZjZhNmM4OWM4NjhiNmRlOTA4MTlkOWI0ZTQ2ZWU2YmZkMDVhIiwiYXVkIjoiaHR0cHM6Ly9jb21wYW55Lmlmb3JtYnVpbGRlci5jb20vZXh6YWN0L2FwaS9vYXV0aC90b2tlbiIsImV4cCI6MTM4NDM3MCwiaWF0IjoxMzg0MzcwfQ._GFp4bxmrlFO3uMjdIxS5535Pygv81VNfub6_dzou60

        Log.i("JWT", token.toString());


        //url concatenation for posting
        url0 += "?grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer&assertion=";
        url0 += token;

        //post for json access token response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url0,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON

                            // Get the JSON array

                            Log.i("RESPONSE",response.toString());

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.e("VOLLEY ", error.toString());
                    }
                }
        );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adds the JSON object request "obreq" to the request queue
        queue.add(jsonObjectRequest);
*/

        token = "Bearer c0844a23d67e30b687fc1bf14d0d969a637ee34e";

        makeRequest(url,"id", 0);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) lv.getItemAtPosition(position);

                String[] itemValues = itemValue.split(":");

                itemValue = itemValues[1].trim();

                recordsId = itemValue;

                proID.setVisibility(View.GONE);
                pageIDview.setVisibility(View.GONE);

                String recordInfoUrl = "https://app.iformbuilder.com/exzact/api/v60/profiles/" + profileId + "/pages/" + pageId + "/records/" + recordsId ;
                makeRequest(recordInfoUrl,"id", 1);

                pbV.setVisibility(View.VISIBLE);

//                for(int i = 0; i < recordList.size();i++)
//                {
//                    if(recordsId.equals(recordList.get(i).getId()))
//                    {
//                        Log.i("RECORD","EXISTS");
//
//
//
//                    }
//                }
            }
        });

    }

    public void makeRequest(String url, final String item, final int mode)
    {

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

                            if(mode == 0) {
                                profileId = response.getString(item);

                                Log.i("ID ", profileId);

                                proID.setVisibility(View.VISIBLE);

                                String pageUrl = "https://app.iformbuilder.com/exzact/api/v60/profiles/" + profileId + "/pages";
                                makeArrayRequest(pageUrl, "id", 0);
                            }
                            if(mode == 1)
                            {
                                recordInfo = response.toString();
                                Log.i("Record Info", recordInfo);

                                Record r = new Record(  response.getString("id"),
                                                        response.getString("name_field"),
                                                        response.getString("phone_field"),
                                                        response.getString("date_field"),
                                                        response.getString("age_field"),
                                                        response.getString("image_field"));

                                //recordList.add(r);

                                Intent intent = new Intent(getBaseContext(), ShowRecord.class);
                                intent.putExtra("EXTRA_ID", r.getId());
                                intent.putExtra("EXTRA_NAME", r.getName());
                                intent.putExtra("EXTRA_PHONE",r.getPhone());
                                intent.putExtra("EXTRA_DATE",r.getDate());
                                intent.putExtra("EXTRA_AGE",r.getAge());
                                intent.putExtra("EXTRA_IMAGE",r.getImage());

                                startActivity(intent);


                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Do something after 100ms
                                        pbV.setVisibility(View.GONE);
                                    }
                                }, 500);

                                //Log.i("Record List", recordList.toString());

//                                if(recordList.size() == 20)
//                                    pbV.setVisibility(View.GONE);
                            }

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
                params.put("Authorization", token);

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
                                JSONObject j = new JSONObject();

                                j = response.getJSONObject(i);

                                if(j.getString(item).equals("3639657") && mode == 0)
                                {
                                    pageId = j.getString(item);
                                    Log.i("Page ID", pageId);

                                    pageIDview.setVisibility(View.VISIBLE);

                                    String recordsUrl = "https://app.iformbuilder.com/exzact/api/v60/profiles/" + profileId + "/pages/" + pageId + "/records" ;
                                    makeArrayRequest(recordsUrl,"id", 1);
                                }

                                else if(mode == 1)
                                {
                                    recordsId = j.getString(item);
                                    Log.i("Records ID",recordsId);

                                    lvAdapter.add("Record: " + recordsId);

                                    lvAdapter.notifyDataSetChanged();

                                    pbV.setVisibility(View.GONE);

                                }

                            }

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
                params.put("Authorization", token);

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
