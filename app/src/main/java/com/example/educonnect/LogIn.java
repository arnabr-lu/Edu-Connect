package com.example.educonnect;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
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

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private AppBarConfiguration appBarConfiguration;
    private ActivityLogInBinding binding;
    private Button login;

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