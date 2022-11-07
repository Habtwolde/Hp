package com.example.mhts.hp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
//import com.squareup.picasso.Picasso;
import com.bumptech.glide.Glide;
import com.example.mhts.hp.Account.AccountActivity;
import com.example.mhts.hp.Account.LoginActivity;
import com.example.mhts.hp.MainActivities.AnimeActivity;
import com.example.mhts.hp.MainActivities.AnnouncementActivity;
import com.example.mhts.hp.MainActivities.HistoryActivity;
import com.example.mhts.hp.MainActivities.SuspeciousActivitity;
import com.example.mhts.hp.MainActivities.NewCrimeReportActivity;
import com.example.mhts.hp.MainActivities.ReportViolation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView trafficstatus;
    ImageView suspecious;
    ImageView repviolation;
    ImageView crimereport;
    ImageView Historyicon;
    ImageView Announcements;
    private TextView userName;
    private TextView phone, email;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CircleImageView userImage;
    public String OfficerName12;
    public String OfficerEmail112;
    String call_center = "0941235986";

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Glide.with(this).load(user.getPhotoUrl()).into(userImage);
            userName.setText(user.getDisplayName());
            email.setText(user.getEmail());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestMultiplePermissions();
        trafficstatus = findViewById(R.id.trafficIcon);
        suspecious = findViewById(R.id.suspeciousicon);
        repviolation = findViewById(R.id.reporticon);
        crimereport = findViewById(R.id.crimereporticon);
        Announcements = findViewById(R.id.announcementicon);
        Historyicon = findViewById(R.id.History);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Historyicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        trafficstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
        suspecious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SuspeciousActivitity.class));
            }
        });

        crimereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String OfficerName = intent.getStringExtra("officerName");

                Intent intent78 = new Intent(MainActivity.this, NewCrimeReportActivity.class);
                intent.putExtra("officerName", OfficerName);
                startActivity(intent78);
            }
        });

        Announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, AnnouncementActivity.class));
            }
        });

        repviolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String OfficerName = intent.getStringExtra("officerName");

                Intent intent79 = new Intent(MainActivity.this, ReportViolation.class);
                intent.putExtra("officerName", OfficerName);
                startActivity(intent79);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        userName = navigationView.getHeaderView(0).findViewById(R.id.profilename);
        email = navigationView.getHeaderView(0).findViewById(R.id.personphone);
        userImage = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Home");

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do You Want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
        } else if (id == R.id.logout) {
            mAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Account) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (id == R.id.location) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
        } else if (id == R.id.notification) {
            startActivity(new Intent(MainActivity.this, AnnouncementActivity.class));
        } else if (id == R.id.verification) {
            startActivity(new Intent(MainActivity.this, ReportViolation.class));
        } else if (id == R.id.emergency) {

            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:" + call_center));
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this).withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                if (report.areAllPermissionsGranted()) {

                }
                if (report.isAnyPermissionPermanentlyDenied()) {
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
            }
        }).onSameThread().check();
    }
}