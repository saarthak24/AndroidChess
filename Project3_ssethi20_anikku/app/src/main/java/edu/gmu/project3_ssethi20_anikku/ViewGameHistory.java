package edu.gmu.project3_ssethi20_anikku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.List;

public class ViewGameHistory extends AppCompatActivity {
    ListView gameHistoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game_history);

        gameHistoryListView = findViewById(R.id.gameHistoryListView);

        GameHistory.retrieveFromFirebase(new GameHistoryCallback() {
            @Override
            public void onSuccess(List<Game> gameList) {
                // Create GameListAdapter to populate ListView with game details
                GameListAdapter adapter = new GameListAdapter(ViewGameHistory.this, R.layout.game_list_item, gameList);

                // Set adapter to ListView
                gameHistoryListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Handle failure
                System.out.println("Failed to retrieve game history: " + errorMessage);
            }
        });
    }
}
