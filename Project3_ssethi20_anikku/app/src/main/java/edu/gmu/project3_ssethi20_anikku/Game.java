package edu.gmu.project3_ssethi20_anikku;

public class Game {
    private String dateTime;
    private String outcome;

    public Game(String dateTime, String outcome) {
        this.dateTime = dateTime;
        this.outcome = outcome;
    }

    public String getDateTime() {
        return this.dateTime;
    }
    public String getOutcome() {
        return this.outcome;
    }

    public void setDateTime(String newDateTime) {
        this.dateTime = newDateTime;
    }

    public void setOutcome(String newOutcome) {
        this.outcome = newOutcome;
    }
}
