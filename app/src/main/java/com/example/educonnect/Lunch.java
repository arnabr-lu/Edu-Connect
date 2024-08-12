package com.example.educonnect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.educonnect.ImgBbObj.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class Lunch extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lunch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();



        new Handler().postDelayed(() -> {
            Intent logIn = new Intent(Lunch.this, LogIn.class);
            Intent mainDrawer = new Intent(Lunch.this, MainDrawer.class);

            if(currentUser != null){
                FirebaseUser user = currentUser;
                db = FirebaseFirestore.getInstance();
                // Example: Fetching a single document

//                Krishn

                try {
                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            System.out.println("HK: "+document.getId());
//                                            Log.d(TAG, document.getId() + " => " + document.getData());
                                        }
                                    } else {
//                                        Log.w(TAG, "Error getting documents.", task.getException());
                                        System.out.println("HK error");
                                    }
                                }
                            });
                }catch (Exception e)
                {
                    System.out.println("HK prblm");
                    System.out.println(e);
                }
                startActivity(mainDrawer);
            }
            else
            {
                startActivity(logIn);
            }
            finish();
        }, 4000);
    }
}