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

    // Getters and setters
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    // Method to save game history to Firebase
    public void saveToFirebase(String userId) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("gameHistory").child(userId);

        // Create a new game history entry
        DatabaseReference newEntryRef = ref.push();
        newEntryRef.child("dateTime").setValue(dateTime);
        newEntryRef.child("outcome").setValue(outcome);
    }
}
