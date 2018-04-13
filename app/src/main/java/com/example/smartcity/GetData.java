package com.example.smartcity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class GetData {

    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static Context context;
    public static void getData(){
        DocumentReference docRef = db.collection("Public transport").document("Vikram");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {

                        Map<String,Object> data = document.getData();
                        Object array = document.get("Vikram no. 3");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Toast.makeText(context,"Ho gaya bhai",Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public static void putContext(Context cont){
        context = cont;
    }

}
