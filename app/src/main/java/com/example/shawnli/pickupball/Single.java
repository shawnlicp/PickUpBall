package com.example.shawnli.pickupball;

import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by shawnli on 9/30/2018.
 */

public class Single {
    private static final Single ourInstance = new Single();

    public static Single getInstance() {
        return ourInstance;
    }

    private FirebaseFirestore db = null;

    public void SetDB(FirebaseFirestore db_){
        db = db_;
    }
    private Single() {

    }
}
