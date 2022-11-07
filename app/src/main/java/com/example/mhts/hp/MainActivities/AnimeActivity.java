package com.example.mhts.hp.MainActivities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mhts.hp.R;

import org.w3c.dom.Text;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        setTitle("Anime Activity");
        getSupportActionBar().hide();

        String CrimeType = getIntent().getExtras().getString("CrimeType");
        String CrimeSection = getIntent().getExtras().getString("CrimeSection");
        String Code = getIntent().getExtras().getString("Code");
        String PlateNumber = getIntent().getExtras().getString("PlateNumber");
        String Region = getIntent().getExtras().getString("Region");
        String Priority1221 = getIntent().getExtras().getString("Priority");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView CrimeType1 = findViewById(R.id.aa_CrimeTyper);
        TextView CrimeSection1 = findViewById(R.id.aa_crimeSectionasd);
        TextView PlateNumber1 = findViewById(R.id.aa_platenumber67);
        TextView Code1 = findViewById(R.id.aa_codej);
        TextView Region1 = findViewById(R.id.aa_Region12);
        TextView Priority122 = findViewById(R.id.aa_priority12);

        CrimeType1.setText(CrimeType);
        CrimeSection1.setText(CrimeSection);
        Code1.setText(Code);
        Region1.setText(Region);
        PlateNumber1.setText(PlateNumber);
        Priority122.setText(Priority1221);
        collapsingToolbarLayout.setTitle("P-"+PlateNumber+"C-"+Code+"R-"+Region);
    }
}
