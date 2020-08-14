package com.example.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries extends AppCompatActivity {
    EditText etsearch;
    ListView lv1;
    SimpleArcLoader loader2;

    public static List<country_modal>country_modalList = new ArrayList<>();
    country_modal cm;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        etsearch = (EditText)findViewById(R.id.etsearch);
        lv1 = (ListView)findViewById(R.id.lv1);
        loader2 = (SimpleArcLoader)findViewById(R.id.loader2);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class).putExtra("position",i);
                startActivity(intent);
            }
        });

        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                customAdapter.getFilter().filter(charSequence);
                customAdapter.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ((item.getItemId()==android.R.id.home))
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/countries/";
        loader2.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String countryName = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String recovered = jsonObject.getString("recovered");
                                String critical = jsonObject.getString("critical");
                                String active = jsonObject.getString("active");
                                String TodayCases = jsonObject.getString("todayCases");
                                String TotalDeath = jsonObject.getString("deaths");
                                String TodayDeath = jsonObject.getString("todayDeaths");

                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flagurl = object.getString("flag");
                                cm = new country_modal(flagurl,countryName,cases,recovered,critical,active,TodayCases,TotalDeath,TodayDeath);
                                country_modalList.add(cm);

                            }
                            customAdapter = new CustomAdapter(Countries.this,country_modalList);
                            lv1.setAdapter(customAdapter);
                            loader2.stop();
                            loader2.setVisibility(View.GONE);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            loader2.stop();
                            loader2.setVisibility(View.GONE);
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loader2.stop();
                loader2.setVisibility(View.GONE);

                Toast.makeText(Countries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}