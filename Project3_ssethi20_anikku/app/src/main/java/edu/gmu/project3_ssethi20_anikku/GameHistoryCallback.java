package edu.gmu.project3_ssethi20_anikku;

import java.util.List;

public interface GameHistoryCallback {
    void onSuccess(List<Game> gameList);
    void onFailure(String errorMessage);
}
