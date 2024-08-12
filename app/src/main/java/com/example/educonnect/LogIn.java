package com.example.educonnect;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.educonnect.ImgBbObj.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.educonnect.databinding.ActivityLogInBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private AppBarConfiguration appBarConfiguration;
    private ActivityLogInBinding binding;
    private Button login;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView gifView = findViewById(R.id.loggif);
        Glide.with(this)
                .load(R.drawable.login_animate) // Or URL for remote GIFs
                .into(gifView);


        EditText emailEdit = findViewById(R.id.emailLogin);
        EditText passwordEdit = findViewById(R.id.passwordLogin);






        TextView goToRegistration = findViewById(R.id.goToRegistration);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Registration.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();


        login = findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                login.setText("Loading...");

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LogIn.this, "login successfull", Toast.LENGTH_LONG).show();
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();



                                    db = FirebaseFirestore.getInstance();


                                    // Example: Fetching a single document
                                    DocumentReference docRef = db.collection("users").document(user.getUid());
                                    docRef.get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot d)
                                                {
                                                    if (d.exists()) {
                                                        // Convert d data to ApplicationData
//                                                    ApplicationData applicationData = new ApplicationData();
                                                        Users user = new Users();
                                                        user.uid = d.getString("uid");
                                                        user.imgUrl = d.getString("imgUrl");
                                                        user.name = d.getString("name");
                                                        user.phone = d.getString("phone");
                                                        user.dept = d.getString("dept");
                                                        user.email = d.getString("email");
                                                        user.userType = d.getString("userType");


//                                                    user.setSubject(d.getString("subject"));
//                                                    applicationData.setSubject(d.getString("to"));
//                                                    applicationData.setBody(d.getString("body"));
//                                                    applicationData.setSubject(d.getString("from"));
                                                        MainDrawer.user = user;

                                                    } else {
//                                                    Log.d("TAG", "No such document");
                                                    }
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(Exception e) {
//                                                    Log.w("TAG", "Error getting documents.", e);
                                                }
                                            });

                                    finish();
                                    Intent intent = new Intent(LogIn.this, MainDrawer.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
//                                    updateUI(user);
                                } else {
                                    login.setText("LogIn");
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LogIn.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }
                            }
                        });











//                finish();
            }
        });

    }


}