package com.IntergalacticT20.model;

import java.util.Objects;

/**
 * Model class of Player
 *
 * @author pravinkumarsingh
 * @email : pravinsinghkumar@gmail.com
 */
public class Player {
    private String playerName;
    private int run;
    private int ballFaced;
    private int boundaries;
    private int sixers;
    private boolean isOut;
    private boolean hasPlayed;


    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public boolean isHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRun() {
        return run;
    }

    public int getBallFaced() {
        return ballFaced;
    }

    /**
     * this method updates the run scored by current player and also updates the sixers & boundaries statistics
     *
     * @param run : run to be added
     */
    public void addRun(int run) {
        if (run == 4) {
            this.boundaries++;
        } else if (run == 6) {
            this.sixers++;
        }
        this.run += run;
    }

    public void incrementBallFaced() {
        ++ballFaced;
    }

    /**
     * Here we are assuming the player name is unique.
     *
     * @param o comparing object
     * @return result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", run=" + run +
                ", ballFaced=" + ballFaced +
                ", boundaries=" + boundaries +
                ", sixers=" + sixers +
                ", isOut=" + isOut +
                ", hasPlayed=" + hasPlayed +
                '}';
    }
}
