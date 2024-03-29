package com.example.firesnake;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FirebaseDB{


    public FirebaseDB(String name, int score){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("Nickname", name);
        user.put("Score", score);

// Add a new document with a generated ID
        db.collection("Leaderscore")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public String[] pull(){ //Map[] pull(){

        Map[] map = new Map[5];
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String[] tab = new String[50];

        db.collection("Leaderscore")
                .orderBy("Score", Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            int i = 0;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //document.getData();
                                //map[i] = (Map) document.getData();
                                tab[i] = document.getData().toString();
                                i++;
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return tab;
    }

}