package com.example.shawnli.pickupball;

import com.example.shawnli.pickupball.Model.Court;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Created by shawnli on 9/30/2018.
 */

public class Single {
    private static final Single ourInstance = new Single();

    public static Single getInstance() {
        return ourInstance;
    }

    private FirebaseFirestore db = null;

    private List<Court> courts = null;

    public List<Court> getCourts() {
        return courts;
    }

    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }

    public void SetDB(FirebaseFirestore db_){
        db = db_;
    }
    private Single() {

    }
}