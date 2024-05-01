package edu.gmu.project3_ssethi20_anikku;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static SoundPool sounds;
    private SparseIntArray soundMap;
    private void configureSounds() {
        createSoundPool();
        soundMap = new SparseIntArray(6);

        soundMap.put(1, sounds.load(this, R.raw.move_self, 1));
        soundMap.put(2, sounds.load(this, R.raw.capture, 1));
        soundMap.put(3, sounds.load(this, R.raw.game_start, 1));
        soundMap.put(4 , sounds.load(this, R.raw.promote, 1));
        soundMap.put(5, sounds.load(this, R.raw.welcome, 1));
    }



    protected void createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        } else {
            createOldSoundPool();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool(){
        sounds = new SoundPool.Builder()
                .setMaxStreams(5)
                .build();
    }

    @SuppressWarnings("deprecation")
    protected void createOldSoundPool(){
        sounds = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureSounds();
    }

    public void playAgainstComputerClicked(View view) {
        // Uses the PlayComputer.java file to load the page for player vs. pc
        Intent intent = new Intent(MainActivity.this, PlayComputer.class);
        if(intent != null && intent.resolveActivity(getPackageManager()) != null) {
            sounds.play(3, 1, 1, 1, 0, 1.0f);//Plays sound when user starts game
            startActivity(intent);
        }
    }

    public void playAgainstFriendClicked(View view) {
        Intent intent = new Intent(MainActivity.this, PlayFriend.class);
        if(intent != null && intent.resolveActivity(getPackageManager()) != null) {
            sounds.play(3, 1, 1, 1, 0, 1.0f);//Plays sound when user starts game
            startActivity(intent);
        }
    }

    public void viewGameHistory(View view) {
        Intent intent  = new Intent(MainActivity.this, ViewGameHistory.class);
        if(intent != null && intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
