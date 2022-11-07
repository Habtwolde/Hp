package com.example.mhts.hp.Account;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhts.hp.Account.models.UserModel;
import com.example.mhts.hp.App;
import com.example.mhts.hp.MainActivities.Model.Anime;
import com.example.mhts.hp.MainActivities.NewCrimeReportActivity;
import com.example.mhts.hp.MainActivities.SuspeciousActivitity;
import com.example.mhts.hp.MainActivity;
import com.example.mhts.hp.R;
import com.example.mhts.hp.splash;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
public class LoginActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;
    private EditText officerpwd, officerPhone;
    private Button btn_login;
    private ImageButton img_btn;
    AlertDialog.Builder builder;
    String URL_LOGIN = "http://www.ose-ethiopia.org/TechTraffic/Login.php";
    String JSON_URL = "http://www.ose-ethiopia.org/TechTraffic/Login.php";
    TextView txt;
    public static final int RC_SIGN_IN = 1;
    private RequestQueue requestQueue;
    public static final int RC_SIGN_IN1 = 2;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    public static final String TAG = "LOGINACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthListener;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Account");

        notificationManagerCompat = NotificationManagerCompat.from(this);

        officerPhone = findViewById(R.id.officerPhone);
        officerpwd = findViewById(R.id.officerPassword);
        btn_login = findViewById(R.id.btn_login);
        img_btn = findViewById(R.id.signinwithgoogle);
        txt = findViewById(R.id.sign_up);
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {

                    FirebaseUser user = mAuth.getCurrentUser();
                    String OfficerName = user.getDisplayName();
                    String OfficerEmail = user.getEmail();
                    sendOnChanne2(OfficerName);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("officerName", OfficerName);
                    intent.putExtra("officerEmail", OfficerEmail);
                    startActivity(intent);
                    updateUI(user);
                }
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "You Got An error", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, Signup.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String mPhone = officerPhone.getText().toString().trim();
                final String mPass = officerpwd.getText().toString().trim();

                if (!mPhone.isEmpty() || !mPass.isEmpty()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if (code.equals("Login_Failed")) {
                                    Toast.makeText(LoginActivity.this, "Login Failed ", Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                }

                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, "Login Failedd ", Toast.LENGTH_LONG).show();

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Error Listener", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("officer_phone", mPhone);
                            params.put("officer_Password", mPass);
                            return params;
                        }
                    };
                    MyAdapterclass.getmInstance(LoginActivity.this).addToRequestque(stringRequest);
                } else {
                    officerPhone.setError("Please Insert Your Phone Number");
                    officerpwd.setError("Please Insert Your Password");
                }
            }
        });

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
              builder.setTitle("Connection is Lost");
              builder.setMessage("Check your Connection and Try Again!");
              displayAlert("connection_needed");
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "GoogleSignIn access", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "GoogleSignIn Unaccess", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            onBackPressed();
        }
    }

    public void sendOnChanne2(String view) {

        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(this, App.Submit_Id_2)
                .setSmallIcon(R.drawable.ic_done_all_black_24dp)
                .setContentTitle("Welcome "+view)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setColor(R.color.green)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManagerCompat.notify(2, notification);
    }

    private void displayAlert(final String code) {

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("Driver_is_Not_Found")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
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

                }
                if (code.equals("connection_needed")) {

                }

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("You Need to Login First?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        finish();
                        finish();
                                            }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



}
