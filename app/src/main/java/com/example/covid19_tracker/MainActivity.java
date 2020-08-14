package com.example.covid19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvcases, tvrecovered, tvcritical, tvactive,tvtodaycase, tvdeath, tvtodaydeath, tvaffectedcountries;
    Button btnTrack;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvcases = (TextView)findViewById(R.id.tvcases);
        tvcritical = (TextView)findViewById(R.id.tvcritical);
        tvrecovered = (TextView)findViewById(R.id.tvrecovered);
        tvactive = (TextView)findViewById(R.id.tvactive);
        tvtodaycase = (TextView)findViewById(R.id.tvtodaycase);
        tvdeath = (TextView)findViewById(R.id.tvdeath);
        tvtodaydeath = (TextView)findViewById(R.id.tvtodaydeath);
        tvaffectedcountries = (TextView)findViewById(R.id.tvaffectedcountries);

        btnTrack = (Button)findViewById(R.id.btnTrack);

        simpleArcLoader = (SimpleArcLoader)findViewById(R.id.loader);
        scrollView = (ScrollView)findViewById(R.id.scrollStats);
        pieChart = (PieChart)findViewById(R.id.piechart);

        fetchData();

        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Countries.class);
                startActivity(intent);
            }
        });

    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvcases.setText(jsonObject.getString("cases"));
                            tvrecovered.setText(jsonObject.getString("recovered"));
                            tvcritical.setText(jsonObject.getString("critical"));
                            tvactive.setText(jsonObject.getString("active"));
                            tvtodaycase.setText(jsonObject.getString("todayCases"));
                            tvdeath.setText(jsonObject.getString("deaths"));
                            tvtodaydeath.setText(jsonObject.getString("todayDeaths"));
                            tvaffectedcountries.setText(jsonObject.getString("affectedCountries"));

                            pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvrecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvdeath.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#29B6F6")));

                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}