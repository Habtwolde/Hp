package com.example.mhts.hp.MainActivities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mhts.hp.App;
import com.example.mhts.hp.R;

public class CrimeActivity extends AppCompatActivity  {
    private NotificationManagerCompat notificationManagerCompat;
    EditText editText1,editText2,editText3,editText4,editText5;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        setTitle("Crime Report");

        editText1 = findViewById(R.id.drivername);
        editText2 = findViewById(R.id.driverPhonenumber);
        editText3 = findViewById(R.id.platenumber);
        editText4 = findViewById(R.id.officerName);
        editText5 = findViewById(R.id.message);
        btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel(v);
            }
        });

        Spinner spinner = findViewById(R.id.code);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = findViewById(R.id.Region1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Regions, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    private void sendOnChannel(View v){

        String DriverName = editText1.getText().toString();

        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(this, App.Submit_Id_1)
                .setSmallIcon(R.drawable.ic_done_all_black_24dp)
                .setContentTitle(DriverName+"Is sent to DataBase Successfully")
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setLights(Color.RED,3000,3000)
                .setColor(R.color.crimecolorPrimary)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManagerCompat.notify(1,notification);
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
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
