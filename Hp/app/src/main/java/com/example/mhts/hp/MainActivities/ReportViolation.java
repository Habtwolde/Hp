package com.example.mhts.hp.MainActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.example.mhts.hp.MainActivity;
import com.example.mhts.hp.R;
import com.reginald.editspinner.EditSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReportViolation extends AppCompatActivity {
    private EditText CarPlateNumber1, CrimeType1, CrimeSection1, licenseNumb;
    AlertDialog.Builder builder;
    private Button notify_btn, checknow_btn;
    String Violate_url = "http://192.168.43.241/TechTraffic/ViolateReport.php";
    String Check_url = "http://192.168.43.241/TechTraffic/Check_url.php";
    EditSpinner mEditSpinner1;
    EditSpinner mEditSpinner2;
    private static final String TAG = "ReportViolation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_violation);
        setTitle("Violation Report");

        builder = new AlertDialog.Builder(ReportViolation.this);
        licenseNumb = findViewById(R.id.LicencseNumber121);
        CarPlateNumber1 = findViewById(R.id.platenumneber1);

        // EditSpinner 1:
        mEditSpinner1 = (EditSpinner) findViewById(R.id.edit_spinner_1);
        android.widget.ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edits_array_1));
        mEditSpinner1.setAdapter(adapter);
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

        // EditSpinner 2:

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

        notify_btn = findViewById(R.id.notify_btn);
        checknow_btn = findViewById(R.id.ChecklicenseNUmber);

        final Spinner spinner8 = findViewById(R.id.code);
        ArrayAdapter<CharSequence> adapter33 = ArrayAdapter.createFromResource(this, R.array.codes, android.R.layout.simple_spinner_item);
        adapter33.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter33);

        final Spinner spinner1 = findViewById(R.id.Region1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Regions, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String CarPlateNumber3 = CarPlateNumber1.getText().toString();
                final String CrimeSection3 = mEditSpinner2.getText().toString();
                final String codde = spinner8.getSelectedItem().toString();
                final String Region = spinner1.getSelectedItem().toString();
                final String CrimeType3 = mEditSpinner1.getText().toString();

                if (!CrimeSection3.isEmpty() && !CrimeType3.isEmpty() && !codde.isEmpty() && !Region.isEmpty()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Violate_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("Message");

                                if (code.equals("All_Officers_are_notified!")) {
                                    finish();
                                    Toast.makeText(ReportViolation.this, "Registeration is Successful ", Toast.LENGTH_LONG).show();
                                    builder.setTitle("All_Officers_are_notified!!");
                                    builder.setMessage(message);
                                    displayAlert("All_Officers_are_notified");

                                } else if (code.equals("Unabel_to_Notify")) {
                                    builder.setTitle("Something is wrong");
                                    builder.setMessage(message);
                                    displayAlert("Unabel_to_Notify");
                                } else {

                                }

                            } catch (JSONException e) {
                                Toast.makeText(ReportViolation.this, "Registeration is Successfulio ", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ReportViolation.this, "Data Base Connection Error", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("PlateNumber", CarPlateNumber3);
                            params.put("Code", codde);
                            params.put("Region", Region);
                            params.put("CrimeType3", CrimeType3);
                            params.put("CrimeSection3", CrimeSection3);
                            return params;
                        }
                    };
                    MyAdapterclass.getmInstance(ReportViolation.this).addToRequestque(stringRequest);
                } else {
                    builder.setTitle("Please Fill All the Fiels!");
                    builder.setMessage("Make Sure that you fill all the fields");
                    displayAlert("input_error");
                }
            }
        });

        checknow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String licenumber3 = licenseNumb.getText().toString();

                if (!licenumber3.isEmpty()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Check_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("Message");

                                if (code.equals("License_is_found")) {
                                    builder.setTitle("Verification is Successfull");
                                    builder.setMessage(message);
                                    displayAlert("License_is_found");

                                } else if (code.equals("License_is_Not_found")) {
                                    builder.setTitle("License is not Found");
                                    builder.setMessage(message);
                                    displayAlert("License_is_Not_found");
                                } else {

                                }

                            } catch (JSONException e) {
                                Toast.makeText(ReportViolation.this, "Registeration is Successfulio ", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ReportViolation.this, "Data Base Connection Error", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("licenumber3", licenumber3);
                            return params;
                        }
                    };
                    MyAdapterclass.getmInstance(ReportViolation.this).addToRequestque(stringRequest);
                } else {
                    licenseNumb.setError("Please fill the License Field");
                    builder.setTitle("Please fill the License Field!");
                    displayAlert("input_error");
                }
            }
        });
    }

    private void hideSoftInputPanel() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mEditSpinner1.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(mEditSpinner2.getWindowToken(), 0);
        }
    }

    private void displayAlert(final String code) {

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("All_Officers_are_notified")) {
                    CarPlateNumber1.setText("");
                    CrimeType1.setText("");
                    CrimeSection1.setText("");
                }
                if (code.equals("Unabel_to_Notify")) {
                    finish();
                }
                if (code.equals("License_is_found")) {
                    startActivity(new Intent(ReportViolation.this, MainActivity.class));
                }
                if (code.equals("License_is_Not_found")) {
                    licenseNumb.setText("");
                }
                if (code.equals("input_error")) {
                    Toast.makeText(ReportViolation.this, "Fill All the Fiels", Toast.LENGTH_LONG).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
