package com.example.mhts.hp.MainActivities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mhts.hp.Account.MyAdapterclass;
import com.example.mhts.hp.Account.Signup;
import com.example.mhts.hp.App;
import com.example.mhts.hp.MainActivity;
import com.example.mhts.hp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.reginald.editspinner.EditSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewCrimeReportActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    EditText DriverName1, CarPlateNumber1, Message1, licenseNumb, location;
    AlertDialog.Builder builder;
    private Button submit_btn;
    EditSpinner mEditSpinner1, mEditSpinner2;
    String CRIME_URL = "http://www.ose-ethiopia.org/TechTraffic/CrimeReport.php";
    private static final String TAG = "NewCrimeReportActivity";
    public String OfficerName11;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String OfficerName = intent.getStringExtra("officerName");
        OfficerName11 = OfficerName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_crime_report);
        setTitle("Crime Report");
        builder = new AlertDialog.Builder(NewCrimeReportActivity.this);
        licenseNumb = findViewById(R.id.LicencseNumber);
        CarPlateNumber1 = findViewById(R.id.platenumneber);
        Message1 = findViewById(R.id.message);
        location = findViewById(R.id.crimelocation);
        submit_btn = findViewById(R.id.submit_btn1);

        mEditSpinner1 = (EditSpinner) findViewById(R.id.edit_spinner_1);
        android.widget.ListAdapter adapter12 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edits_array_1));
        mEditSpinner1.setAdapter(adapter12);
        mEditSpinner1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "mEditSpinner1 popup window dismissed!");
            }
        });

        mEditSpinner1.setOnShowListener(new EditSpinner.OnShowListener() {
            @Override
            public void onShow() {
                hideSoftInputPanel();
            }
        });

        mEditSpinner2 = findViewById(R.id.edit_spinner_2);
        android.widget.ListAdapter adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edits_array_2));
        mEditSpinner2.setAdapter(adapter4);

        mEditSpinner2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "mEditSpinner1 popup window dismissed!");
            }
        });

        mEditSpinner2.setOnShowListener(new EditSpinner.OnShowListener() {
            @Override
            public void onShow() {
                hideSoftInputPanel();
            }
        });

        final Spinner spinner = findViewById(R.id.code);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner spinner1 = findViewById(R.id.Region1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Regions, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();

                FirebaseUser user = mAuth.getCurrentUser();
                String OfficerName = user.getDisplayName();

                final String PlateNumber = CarPlateNumber1.getText().toString();
                final String Message = Message1.getText().toString();
                final String LicenseNumber = licenseNumb.getText().toString();
                final String crimelocation = location.getText().toString();
                final String codde = spinner.getSelectedItem().toString();
                final String Region = spinner1.getSelectedItem().toString();
                final String CrimeType = mEditSpinner1.getText().toString();
                final String CrimeSection = mEditSpinner2.getText().toString();
                final String Officername = OfficerName;


                if (!LicenseNumber.isEmpty() && !PlateNumber.isEmpty() && !CrimeSection.isEmpty() && !CrimeType.isEmpty() && !Message.isEmpty()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, CRIME_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("Success")) {
                                    finish();
                                    builder.setTitle("Congratulations!");
                                    builder.setMessage("Report is Successfull");
                                    displayAlert("Success");

                                } else if (code.equals("Report_UnSuccessful")) {
                                    builder.setTitle("Something is wrong");
                                    builder.setMessage("Can't Update Car and Driver Database");
                                    displayAlert("Report_UnSuccessful");

                                } else if (code.equals("LicenseNumber_is_not_found")) {
                                    builder.setTitle("Something is wrong");
                                    builder.setMessage("License Number is not Found");
                                    displayAlert("LicenseNumber_is_not_found");

                                } else if (code.equals("Crime_Registeration_Failed")) {

                                    builder.setTitle("Something is wrong!");
                                    builder.setMessage("Crime Reporting is Failed. Try Again Lator!");
                                    displayAlert("Crime_Registeration_Failed");

                                } else if (code.equals("Driver_is_Not_Found")) {
                                    builder.setTitle("Fake License Card Alert!!");
                                    builder.setMessage("License is need to be checked");
                                    displayAlert("Driver_is_Not_Found");

                                } else if (code.equals("crime_before")) {
                                    builder.setTitle("Repeated Crimes");
                                    builder.setMessage("Repeated Crimes With this License Number");
                                    displayAlert("crime_before");
                                    sendOnChannel(code);

                                } else {
                                    builder.setTitle("Something is wrong");
                                    builder.setMessage("Please Try Again Lator");
                                    displayAlert("somethig_is_wrong");
                                }

                            } catch (JSONException e) {
                                builder.setTitle("Response  Error");
                                builder.setMessage("");
                                displayAlert("Response_error");
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            builder.setTitle("Connection Error Occurred");
                            displayAlert("connection_error");
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("PlateNumber", PlateNumber);
                            params.put("CrimeType", CrimeType);
                            params.put("CrimeSection", CrimeSection);
                            params.put("Message", Message);
                            params.put("LicenseNumbers", LicenseNumber);
                            params.put("crimelocation", crimelocation);
                            params.put("Code", codde);
                            params.put("Region", Region);
                            params.put("OfficerName11", Officername);
                            return params;
                        }
                    };

                    MyAdapterclass.getmInstance(NewCrimeReportActivity.this).addToRequestque(stringRequest);
                } else {

                    licenseNumb.setError("Please fill the License Number");
                    CarPlateNumber1.setError("Please fill the Plate Number");
                    Message1.setError("Please fill Crime Details");
                    location.setError("Please fill Location");
                    builder.setTitle("Something is wrong");
                    builder.setMessage("Please make sure you fill all fields!");
                    displayAlert("all_fields_empty");
                }
            }
        });

        notificationManagerCompat = NotificationManagerCompat.from(this);

    }

    private void displayAlert(final String code) {

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("Success")) {
                    finish();
                }

                if (code.equals("Driver_is_Not_Found")) {

                    licenseNumb.setText("");
                }
                if (code.equals("crime_before")) {

                    CarPlateNumber1.setText("");
                    Message1.setText("");
                    licenseNumb.setText("");

                }
                if (code.equals("Crime_Registeration_Failed")) {
                }
                if (code.equals("LicenseNumber_is_not_found")) {
                    licenseNumb.setText("");
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

    private void hideSoftInputPanel() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mEditSpinner1.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(mEditSpinner2.getWindowToken(), 0);
        }
    }

    public void sendOnChannel(String view) {
        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(this, App.Submit_Id_1)
                .setSmallIcon(R.drawable.ic_done_all_black_24dp)
                .setContentTitle("Crime is Reported Successfully")
                .setVibrate(new long[]{1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setColor(R.color.crimecolorPrimary)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManagerCompat.notify(1, notification);
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
