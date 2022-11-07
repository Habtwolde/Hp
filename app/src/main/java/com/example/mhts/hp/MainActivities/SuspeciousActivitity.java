package com.example.mhts.hp.MainActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhts.hp.MainActivities.Adapter.RecyclerViewAdapter;
import com.example.mhts.hp.MainActivities.Model.Anime;
import com.example.mhts.hp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SuspeciousActivitity extends AppCompatActivity {
    private final String JSON_URL = "http://www.ose-ethiopia.org/TechTraffic/Api/GetSUspeciousAlert.php";
    private RequestQueue requestQueue;
    private List<Anime> listAnime;
    private RecyclerView recyclerView;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspeciousmain);
        setTitle("Suspecious Alerts");
        recyclerView = findViewById(R.id.recycler);
        listAnime = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(SuspeciousActivitity.this);
        jsonRequest2();
    }

    private void jsonRequest2() {

        final JsonObjectRequest request23 = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Server_Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        Anime anime = new Anime();
                        anime.setPlateNumber(hit.getString("PlaateNumber"));
                        anime.setCode(hit.getString("code"));
                        anime.setCrimeSection(hit.getString("CrimeSection"));
                        anime.setCrimeType(hit.getString("CrimeTYpe"));
                        anime.setRegion(hit.getString("Region"));
                        anime.setPriority(hit.getString("Priority"));
                        listAnime.add(anime);

                    }
                } catch (JSONException e) {
                    builder.setTitle("Response  Error");
                    builder.setMessage("");
                    displayAlert("Response_error");
                    e.printStackTrace();
                }

                setuprecyclerview(listAnime);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                builder.setTitle("Connection Error Occurred");
                displayAlert("connection_error");
            }
        });

        requestQueue.add(request23);

    }

    private void displayAlert(final String code) {

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("Driver_is_Not_Found")) {
                }
                if (code.equals("crime_before")) {
                }
                if (code.equals("Crime_Registeration_Failed")) {
                }
                if (code.equals("LicenseNumber_is_not_found")) {
                }
                if (code.equals("all_fields_empty")) {
                }
                if (code.equals("Response_error")) {
                } if (code.equals("connection_error")){

                }

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void setuprecyclerview(List<Anime> listAnime) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, listAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
