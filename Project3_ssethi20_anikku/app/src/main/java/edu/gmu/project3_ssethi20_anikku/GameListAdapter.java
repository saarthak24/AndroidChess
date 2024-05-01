package edu.gmu.project3_ssethi20_anikku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GameListAdapter extends ArrayAdapter<Game> {
    private Context mContext;
    private int mResource;

    public GameListAdapter(Context context, int resource, List<Game> games) {
        super(context, resource, games);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the custom layout for each item in the ListView
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        // Get the current game
        Game game = getItem(position);

        // Set the dateTime and outcome to TextViews
        TextView textViewDateTime = convertView.findViewById(R.id.textViewDateTime);
        textViewDateTime.setText("Date & Time: " + game.getDateTime());

        TextView textViewOutcome = convertView.findViewById(R.id.textViewOutcome);
        textViewOutcome.setText("Outcome: " + game.getOutcome());

        return convertView;
    }
}
