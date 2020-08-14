package com.example.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private int positionCountry;
    TextView tvCountry1,tvCases1,tvRecovered1,tvCritical1,tvActive1,tvTodayCases1,tvDeaths1,tvTodayDeaths1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Country Details of"+Countries.country_modalList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tvCountry1 = (TextView)findViewById(R.id.tvCountry1);
        tvCases1 = (TextView)findViewById(R.id.tvCases1);
        tvRecovered1 = (TextView)findViewById(R.id.tvRecovered1);
        tvCritical1 = (TextView)findViewById(R.id.tvCritical1);
        tvActive1 = (TextView)findViewById(R.id.tvActive1);
        tvTodayCases1 = (TextView)findViewById(R.id.tvTodayCases1);
        tvDeaths1 = (TextView)findViewById(R.id.tvDeaths1);
        tvTodayDeaths1 = (TextView)findViewById(R.id.tvTodayDeaths1);

        tvCountry1.setText(Countries.country_modalList.get(positionCountry).getCountry());
        tvCases1.setText(Countries.country_modalList.get(positionCountry).getCases());
        tvRecovered1.setText(Countries.country_modalList.get(positionCountry).getRecovered());
        tvCritical1.setText(Countries.country_modalList.get(positionCountry).getCritical());
        tvActive1.setText(Countries.country_modalList.get(positionCountry).getActive());
        tvTodayCases1.setText(Countries.country_modalList.get(positionCountry).getTodaycases());
        tvDeaths1.setText(Countries.country_modalList.get(positionCountry).getDeaths());
        tvTodayDeaths1.setText(Countries.country_modalList.get(positionCountry).getTodaydeath());





    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ((item.getItemId()==android.R.id.home))
            finish();
        return super.onOptionsItemSelected(item);
    }
}