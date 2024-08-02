package com.example.educonnect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        EditText nameEdit = (EditText) findViewById(R.id.nameInput);
        EditText numberEdit = (EditText)  findViewById(R.id.numberInput);
        EditText emailEdit = (EditText)  findViewById(R.id.emailInput);
        EditText passwordEdit = (EditText)  findViewById(R.id.passwordInput);





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
                System.out.println("HK: register button clicked!");

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    register.setText("Register");
                                    Toast.makeText(Registration.this, "Authentication succed. Now Please LogIn",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    System.out.println("HK: registration succed!");
                                    try {
                                        if (bitmap == null) {
                                            // Handle the case where no image is selected
                                            Toast.makeText(Registration.this, "No image selected", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
//                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                                        byte[] imageData = baos.toByteArray();
//
//
//                                        ByteArrayOutputStream baods = new ByteArrayOutputStream();
//                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                                        byte[] imageBytes  = baos.toByteArray();


                                        System.out.println("HK: image dta: ");
                                        System.out.println(imageBytes);

//                                        String encodedImage = convertBitmapToBase64(bitmap);
//
//
//                                        JSONObject jsonObject = new JSONObject();
//                                        jsonObject.put("image", encodedImage);
//
//                                        // Create request body
//                                        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//                                        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());





                                        // Create the request body
                                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);

                                        // Build the request

                                        OkHttpClient client = new OkHttpClient();
                                        Request request = new Request.Builder()
                                                .url("https://api.imgbb.com/1/upload?expiration=600&key=561ad6465766a7fc88a1dcd9efdb55eb")
                                                .post(requestBody)
                                                .build();


//                                        client.newCall(request).enqueue(new Callback() {
//                                            @Override
//                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                                                System.out.println("HK on failure: "+e.getMessage());
//                                                e.printStackTrace();
//                                            }
//
//                                            @Override
//                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        try {
//                                                            System.out.println("HK: "+response.body().string());
//                                                        } catch (IOException e) {
//                                                            System.out.println("HK: from on response "+response.body());
//                                                            throw new RuntimeException(e);
//                                                        }
//                                                    }
//                                                });
//                                            }
//                                        });




//                                        Executor execute = Executors.newSingleThreadExecutor();
//                                        execute.execute(new Runnable() {
//                                            @Override
//                                            public void run()
//                                            {
//                                                try {
//                                                    Response response = client.newCall(request).execute();
//                                                    if (response.isSuccessful()) {
//                                                         String sc = response.body().string();
//                                                        System.out.println("HK: "+sc);
//                                                        Toast.makeText(Registration.this, "HK succed", Toast.LENGTH_SHORT).show();
//
//                                                    } else {
//                                                         String er = "Upload failed: " + response.code();
//                                                         System.out.println("HB: "+er);
//                                                        Toast.makeText(Registration.this, "HK failed", Toast.LENGTH_SHORT).show();
//
//                                                    }
//                                                }catch (Exception e)
//                                                {
//                                                    System.out.println("HKe: "+e);
//                                                    Toast.makeText(Registration.this, "asdf "+e, Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });


//                                        try (Response response = client.newCall(request).execute()) {
//                                            if (response.isSuccessful()) {
//                                                 String sc = response.body().string();
//                                                System.out.println("HK: "+sc);
//                                                Toast.makeText(Registration.this, "HK succed", Toast.LENGTH_SHORT).show();
//
//                                            } else {
//                                                 String er = "Upload failed: " + response.code();
//                                                 System.out.println("HB: "+er);
//                                                Toast.makeText(Registration.this, "HK failed", Toast.LENGTH_SHORT).show();
//
//                                            }
//                                        }catch (Exception e)
//                                        {
//                                            System.out.println("HKe: "+e);
//                                            Toast.makeText(Registration.this, "asdf "+e, Toast.LENGTH_SHORT).show();
//                                        }

                                    }

                                    catch (Exception e) {
                                        String er = "Upload failed: " + e.getMessage();
                                        System.out.println("HB: "+er);

                                    }








                                } else {
                                    register.setText("Register");

                                    // If sign in fails, display a message to the user.
                                    System.out.println("createUserWithEmail:failure"+task.getException());
                                    Toast.makeText(Registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }



//    public static void uploadImage(Bitmap image, String apiKey, String uploadUrl, OnImageUploadListener listener) {
//        // Convert bitmap to byte array (you can choose other formats if needed)
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageData = baos.toByteArray();
//
//
//        // Create the request body
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageData);
//
//        // Build the request
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(uploadUrl)
//                .addHeader("Authorization", "Client-ID " + apiKey) // Add your API key
//                .post(requestBody)
//                .build();
//
//        // Make the asynchronous request
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                if (listener != null) {
//                    listener.onUploadError(e.getMessage());
//
//                }
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String
//                    responseString = response.body().string();
//
//                    if (listener != null) {
//                        listener.onUploadSuccess(responseString); // Parse the response to get image URL
//                    }
//                } else {
//                    if (listener != null) {
//                        listener.onUploadError("Upload failed: " + response.code());
//                    }
//                }
//            }
//        });
//    }









    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();





            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                upldImg.setImageBitmap(bitmap);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes  = baos.toByteArray();
                System.out.println("HK: image dta: ");
                System.out.println(imageBytes);

//                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
//
//                // Build the request
//
                OkHttpClient client = new OkHttpClient();





                String encodedImage = convertBitmapToBase64(bitmap);


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("image", encodedImage);

                // Create request body
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());




                Request request = new Request.Builder()
                        .url("https://api.imgbb.com/1/upload?expiration=600&key=561ad6465766a7fc88a1dcd9efdb55eb")
                        .post(requestBody)
                        .build();




















                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        System.out.println("HK fail: "+e.getMessage());
                        e.printStackTrace();
                        System.out.println("HK fail1: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("HK:responseon "+response.body().string());
                                } catch (IOException e) {
                                    System.out.println("HK:catch "+e.getMessage());
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }
                });


                // Here, you can upload the bitmap to a server using a network library
                // ...
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



    public static String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }
}


