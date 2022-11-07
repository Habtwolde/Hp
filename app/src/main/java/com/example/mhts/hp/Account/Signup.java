package com.example.mhts.hp.Account;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.mhts.hp.MainActivity;
import com.example.mhts.hp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.squareup.picasso.Picasso;

import static android.content.Intent.createChooser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Signup extends AppCompatActivity {

    private Spinner spinner;
    AlertDialog.Builder builder;
    static final int REQUEST_IMAGE_CAPTURE = 1000;
    private static final int SELECT_FILE = 1000;
    private static final int PERMISSION_CODE = 1000;
    private static final String IMAGE_DIRECTORY = "/techtraffic";
    private Button sign_up_for_account;
    private EditText officerName, officerPhonenumber, officerEmail, officerpassword, officerpolicestation, officerbadgenumber;
    String REGISTER_URL = "http://192.168.43.241/TechTraffic/Register.php";
public ImageView imgg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signpp);
        setTitle("Sign Up");
        requestMultiplePermissions();
        builder = new AlertDialog.Builder(Signup.this);
        officerName = findViewById(R.id.officername2);
        officerPhonenumber = findViewById(R.id.officerPhone2);
        officerEmail = findViewById(R.id.officerEmail2);
        officerpassword = findViewById(R.id.officerPassword2);
        officerpolicestation = findViewById(R.id.policestation2);
        officerbadgenumber = findViewById(R.id.BatchNumber2);
        sign_up_for_account = findViewById(R.id.sign_up_for_account);
       imgg=findViewById(R.id.img);
       if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inten2t = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(inten2t, REQUEST_IMAGE_CAPTURE);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, SELECT_FILE);
            }
        });

        ImageButton ivVectorImage = findViewById(R.id.personcxc);
        ivVectorImage.setColorFilter(getResources().getColor(R.color.colorPrimary));

        ImageButton ivVectorImage2 = findViewById(R.id.pers34oncxc);
        ivVectorImage2.setColorFilter(getResources().getColor(R.color.colorPrimary));

        ImageButton ivVectorImage3 = findViewById(R.id.personcxjkjkc);
        ivVectorImage3.setColorFilter(getResources().getColor(R.color.colorPrimary));

        ImageButton ivVectorImage4 = findViewById(R.id.pe12rsoncxc);
        ivVectorImage4.setColorFilter(getResources().getColor(R.color.colorPrimary));

        ImageButton ivVectorImage5 = findViewById(R.id.pe90rsoncxc);
        ivVectorImage5.setColorFilter(getResources().getColor(R.color.colorPrimary));

        ImageButton ivVectorImage6 = findViewById(R.id.dsdasdasd);
        ivVectorImage6.setColorFilter(getResources().getColor(R.color.colorPrimary));

        sign_up_for_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(validate()){
                final String mOfficerName = officerName.getText().toString();
                final String mOfficerphone = officerPhonenumber.getText().toString();
                final String mOfficerEmail = officerEmail.getText().toString();
                final String mOfficerpassword = officerpassword.getText().toString();
                final String mOfficerpolicestation = officerpolicestation.getText().toString();
                final String mOfficerbadgenumber = officerbadgenumber.getText().toString();

                if (!mOfficerName.isEmpty() && !mOfficerphone.isEmpty() && !mOfficerEmail.isEmpty() && !mOfficerpassword.isEmpty() && !mOfficerpolicestation.isEmpty() && !mOfficerbadgenumber.isEmpty()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if (code.equals("Success")) {
                                    finish();
                                    builder.setTitle("Congratulations!");
                                    builder.setMessage("Registeration is Successfull");
                                    displayAlert("Success");
                                } else if (code.equals("User_Found")) {
                                    builder.setTitle("Something is wrong");
                                    builder.setMessage("Officer is Found With this Phone and Email");
                                    displayAlert("user_Found");

                                } else {
                                    builder.setTitle("Something is wrong");
                                    builder.setMessage("Please Try Again Lator");
                                    displayAlert("somethig_is_wrong");
                                }

                            } catch (JSONException e) {
                                Toast.makeText(Signup.this, "Registeration is Successfulio ", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Signup.this, "Error Listener", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("officerName", mOfficerName);
                            params.put("officer_PhoneNumber", mOfficerphone);
                            params.put("officer_email", mOfficerEmail);
                            params.put("officer_Password", mOfficerpassword);
                            params.put("officer_policestaion", mOfficerpolicestation);
                            params.put("officer_badgeNumber", mOfficerbadgenumber);
                            return params;
                        }
                    };
                    MyAdapterclass.getmInstance(Signup.this).addToRequestque(stringRequest);
                } else {
                    builder.setTitle("Something is wrong");
                    builder.setMessage("Please make sure you fill all fields!");
                    displayAlert("input_error");
                }
            }
            else{
    officerEmail.setError("Incorrect Email Address");
            }}
    });
    }

    private void displayAlert(final String code) {

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("input_error")) {

                }
                if (code.equals("user_Found")) {

                    officerName.setText("");
                    officerPhonenumber.setText("");
                    officerEmail.setText("");
                    officerpassword.setText("");
                    officerpolicestation.setText("");
                    officerbadgenumber.setText("");

                }
                if (code.equals("Success")) {
                    startActivity(new Intent(Signup.this, LoginActivity.class));
                }
                if (code.equals("somethig_is_wrong")) {
                    startActivity(new Intent(Signup.this, MainActivity.class));
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void picImageFromGallery() {
        Intent intent12 = new Intent(Intent.ACTION_PICK);
        intent12.setType("images/*");
        startActivityForResult(intent12, SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    picImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (resultCode == REQUEST_IMAGE_CAPTURE) {
                Bundle bundle = data.getExtras();
                final Bitmap photo = (Bitmap) bundle.get("data");
                imgg.setImageBitmap(photo);
               // Picasso.get().load(bundle).into(imgg);
               //  Create an image file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File image = null;/* directory */
                try {
                    image = File.createTempFile(
                            imageFileName,
                            ".jpg",/* suffix */
                            storageDir );
                } catch (IOException e) {
                    e.printStackTrace();
                }
// Save a file: path for use with ACTION_VIEW intents
                String photoPath = image.getAbsolutePath();
                SharedPreferences preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("path",photoPath);
                Toast.makeText(getBaseContext(), photoPath, Toast.LENGTH_LONG).show();


            } else if (resultCode == SELECT_FILE) {

                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        String path = saveImage(bitmap);
                        Toast.makeText(Signup.this, path, Toast.LENGTH_SHORT).show();
                        Picasso.get().load(path).into(imgg);


                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Signup.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());
            Toast.makeText(Signup.this, "Image Savedqw!", Toast.LENGTH_SHORT).show();
String x=f.getAbsolutePath().toString();

            Glide.with(this).load(x).into(imgg);return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void requestMultiplePermissions(){
        Dexter.withActivity(this).withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
// check if all permissions are granted
                if (report.areAllPermissionsGranted()) {
                    // check for permanent denial of any permission
                }
                if (report.isAnyPermissionPermanentlyDenied()) {// show alert dialog navigating to Settings

//openSettingsDialog();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();}}).withErrorListener(new PermissionRequestErrorListener() {
            @Override public void onError(DexterError error) {
                Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();}
        }).onSameThread().check();
    }




    public boolean validate() {
        boolean valid = true;

        String email = officerEmail.getText().toString();
        String password = officerpassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            officerEmail.setError("enter a valid email address");
            valid = false;
        } else {
            officerEmail.setError(null);
        }


        return valid;
    }
}
