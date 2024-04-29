package edu.gmu.project3_ssethi20_anikku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgainstComputerClicked(View view) {
        // Uses the PlayComputer.java file to load the page for player vs. pc
        Intent intent = new Intent(MainActivity.this, PlayComputer.class);
        if(intent != null && intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void playAgainstFriendClicked(View view) {
        Intent intent = new Intent(MainActivity.this, PlayFriend.class);
        if(intent != null && intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
