package com.example.mhts.hp.MainActivities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhts.hp.MainActivities.Adapter.RecyclerViewAdapter;
import com.example.mhts.hp.MainActivities.Adapter.RecyclerViewAdapter1;
import com.example.mhts.hp.MainActivities.Model.Anime;
import com.example.mhts.hp.MainActivities.Model.Anime2;
import com.example.mhts.hp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementActivity extends AppCompatActivity {

    private final String JSON_URL = "http://www.ose-ethiopia.org/TechTraffic/Api/GetAnnouncement.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime2> listAnime2;
    private RecyclerView recyclerView;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        setTitle("Announcements");

        recyclerView = findViewById(R.id.recycler1);
        listAnime2 = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(AnnouncementActivity.this);
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
                            Anime2 anime2 = new Anime2();
                            anime2.setAnnouncement_title(hit.getString("Announcement_title"));
                            anime2.setAnnouncement_details(hit.getString("Announcement_details"));
                            listAnime2.add(anime2);

                        }
                    } catch (JSONException e) {
                        builder.setTitle("Response  Error");
                        builder.setMessage("");
                        displayAlert("Response_error");
                        e.printStackTrace();
                    }

                    setuprecyclerview(listAnime2);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    builder.setTitle("Connection Error Occurred");
                    displayAlert("connection_error");
                }
            });
            requestQueue = Volley.newRequestQueue(AnnouncementActivity.this);
            requestQueue.add(request23);

        }

    private void setuprecyclerview(List<Anime2> listAnime2) {

        RecyclerViewAdapter1 myadapter = new RecyclerViewAdapter1(this,listAnime2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }


    private void displayAlert(final String code) {

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (code.equals("Response_error")) {
                } if(code.equals("connection_error")){

                }

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
