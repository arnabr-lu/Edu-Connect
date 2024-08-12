package com.example.educonnect;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.example.educonnect.ImgBbObj.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.educonnect.databinding.ActivityRegistrationBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private AppBarConfiguration appBarConfiguration;
    private ActivityRegistrationBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ShapeableImageView upldImg;
    String apiKey = "561ad6465766a7fc88a1dcd9efdb55eb";
    String uploadUrl = "https://api.imgbb.com/1/upload";
    Bitmap bitmap;
    byte[] imageBytes;
    Context context;
    String SelectedImagePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        mAuth = FirebaseAuth.getInstance();

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView gifView = findViewById(R.id.registrationgif);
        Glide.with(this)
                .load(R.drawable.registration_gif) // Or URL for remote GIFs
                .into(gifView);

        upldImg = findViewById(R.id.upldImg);
        upldImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });



        Spinner userTypeS = findViewById(R.id.userType);
        String[] items = {"Student", "Teacher"};
        final String[] userType = new String[1];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        userTypeS.setAdapter(adapter);
        userTypeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType[0] = parent.getItemAtPosition(position).toString();
//                Toast.makeText(MainActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });




        EditText nameEdit = (EditText) findViewById(R.id.nameInput);
        EditText numberEdit = (EditText)  findViewById(R.id.numberInput);
        EditText emailEdit = (EditText)  findViewById(R.id.emailInput);
        EditText passwordEdit = (EditText)  findViewById(R.id.passwordInput);
        EditText deptEdit = (EditText) findViewById(R.id.deptInput);






        TextView goToLogin = findViewById(R.id.goToLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, LogIn.class);
                startActivity(intent);
            }
        });

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register.setText("Loading...");
                Toast.makeText(Registration.this, "Registration clicked.",
                        Toast.LENGTH_SHORT).show();
                String name = nameEdit.getText().toString();
                String number = numberEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String dept = deptEdit.getText().toString();
                System.out.println("HK: register button clicked!");


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Authentication succed. Now Please LogIn",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser     user = mAuth.getCurrentUser();
                                    try {
                                        if (bitmap == null) {
                                            // Handle the case where no image is selected
                                            Toast.makeText(Registration.this, "No image selected", Toast.LENGTH_SHORT).show();
                                            return;
                                        }


                                        //Image upload start
                                        File imageFile = new File(SelectedImagePath);
                                        if (imageFile.exists()) {
                                            System.out.println("File exists");
                                        } else {
                                            System.out.println("File not exists");
                                        }

                                        RequestBody requestBody = new MultipartBody.Builder()
                                                .setType(MultipartBody.FORM)
                                                .addFormDataPart("image", "image.png",
                                                        RequestBody.create(MediaType.parse("image/png"), imageFile))
                                                .build();
                                        OkHttpClient client = new OkHttpClient();
                                        Request request = new Request.Builder()
                                                .url("https://api.imgbb.com/1/upload?expiration=3600&key=561ad6465766a7fc88a1dcd9efdb55eb")
                                                .post(requestBody)
                                                .build();
                                        client.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                System.out.println("HK fail:  "+e.getMessage());
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            String d = response.body().string();
                                                            JSONObject jsonObject = new JSONObject(d);
                                                            JSONObject dataObject = jsonObject.getJSONObject("data");
                                                            String imageUrl = dataObject.getString("url");
                                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                            Users usr = new Users(user.getUid(), imageUrl, name, number, dept, email, userType[0]);
                                                            Gson gson = new Gson();
                                                            String json = gson.toJson(usr);
                                                            db.collection("users").document(user.getUid()).set(usr)
                                                                    .addOnSuccessListener(documentReference -> {
                                                                        System.out.println("HK success db: "+documentReference);
                                                                        register.setText("Register");
                                                                        Intent intent = new Intent(Registration.this, MainDrawer.class);
                                                                        startActivity(intent);
                                                                    })
                                                                    .addOnFailureListener(e -> {
                                                                        System.out.println("HK failure db: "+e);

                                                                    });

                                                        } catch (IOException | JSONException e) {
                                                            System.out.println("HK:catch "+e.getMessage());
                                                            throw new RuntimeException(e);
                                                        }
                                                        catch (Exception e)
                                                        {
                                                            System.out.println(e);
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                    catch (Exception e) {
                                        String er = "Upload failed: " + e.getMessage();
                                        System.out.println("HB: "+er);
                                    }
                                } else {
                                    register.setText("Register");
                                    System.out.println("createUserWithEmail:failure"+task.getException());
                                    Toast.makeText(Registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }







    private String getImagePathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(
                context,
                uri,
                projection,
                null,
                null,
                null
        );

        Cursor cursor = cursorLoader.loadInBackground();

        String imagePath = null;
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            imagePath = cursor.getString(columnIndex);
            cursor.close();
        }

        return imagePath;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri selectedImageUri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                upldImg.setImageBitmap(bitmap);
                SelectedImagePath = getImagePathFromUri(selectedImageUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}