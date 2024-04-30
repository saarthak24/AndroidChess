package edu.gmu.project3_ssethi20_anikku;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameHistory {
    private String dateTime;
    private String outcome;

    public GameHistory(String dateTime, String outcome) {
        this.dateTime = dateTime;
        this.outcome = outcome;
    }

    // Method to save a game's result to Firebase
    public void saveToFirebase() {
        // Get a reference to the Firebase database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cs477-s24-project3-default-rtdb.firebaseio.com/");

        // Create a new game result entry in the database
        DatabaseReference newEntry = ref.push();
        newEntry.child("dateTime").setValue(dateTime);
        newEntry.child("outcome").setValue(outcome);
    }
}
