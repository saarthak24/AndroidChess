package edu.gmu.project3_ssethi20_anikku;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
    public GameHistory() {
    }

    // Method to save a game's result to Firebase
    public static void saveToFirebase(Game g) {
        // Get a reference to the Firebase database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cs477-s24-project3-default-rtdb.firebaseio.com/");

        // Create a new game result entry in the database
        DatabaseReference newEntry = ref.push();
        newEntry.child("dateTime").setValue(g.getDateTime());
        newEntry.child("outcome").setValue(g.getOutcome());
    }

    public static void retrieveFromFirebase(GameHistoryCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cs477-s24-project3-default-rtdb.firebaseio.com/");
        List<Game> gameHistoryList = new ArrayList<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String dateTime = snapshot.child("dateTime").getValue(String.class);
                    String outcome = snapshot.child("outcome").getValue(String.class);
                    Game g = new Game(dateTime, outcome);
                    gameHistoryList.add(g);
                }
                callback.onSuccess(gameHistoryList); // Notify caller with populated list
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onFailure(databaseError.getMessage()); // Notify caller of failure
            }
        });
    }
}