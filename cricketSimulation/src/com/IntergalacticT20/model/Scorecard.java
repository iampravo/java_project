package com.IntergalacticT20.model;

import java.util.Objects;

/**
 * Model class of Scorecard
 *
 * @author pravinkumarsingh
 * @email : pravinsinghkumar@gmail.com
 */
public class Scorecard {
    private int overLeft;
    private int ballRemaining;
    private int runNeeded;
    private int wicketLeft;
    private int currentBall;
    private Player striker;
    private Player runner;
    private Player bowler;
    private int lastRun;

    public int getBallRemaining() {
        return ballRemaining;
    }

    public void setBallRemaining(int ballRemaining) {
        this.ballRemaining = ballRemaining;
    }

    public int getOverLeft() {
        return overLeft;
    }

    public void decrementOverLeft() {
        --overLeft;
    }

    public int getRunNeeded() {
        return runNeeded;
    }

    public int getWicketLeft() {
        return wicketLeft;
    }

    /**
     * this method return the number of ball in over format
     * e.g. currentBall : 9, output : 1.3
     * currentBall :13, output : 2.1
     *
     * @return over
     */
    public double getCurrentBall() {
        return currentBall / 6 + (currentBall % 6) / 10.0;
    }

    public void incrementBall() {
        ++currentBall;
    }

    public void decrementRemainingBall() {
        --ballRemaining;
    }

    public Player getStriker() {
        return striker;
    }

    public Player getRunner() {
        return runner;
    }

    public int getLastRun() {
        return lastRun;
    }

    public void setOverLeft(int overLeft) {
        this.overLeft = overLeft;
    }

    public void setRunNeeded(int runNeeded) {
        this.runNeeded = runNeeded;
    }

    public void setWicketLeft(int wicketLeft) {
        this.wicketLeft = wicketLeft;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public void setRunner(Player runner) {
        this.runner = runner;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public void setLastRun(int lastRun) {
        this.lastRun = lastRun;
    }

    /**
     * this methods updates the run needed to win
     * If current value of runNeeded is -1, then it is assumed the its first inning and no need to update 'runNeeded'
     * if 'runNeeded - shot' is less than 0 then runNeeded is set to 0, in order to avoid any conflict with '-1' scenario, as discussed in previous line
     * else runNeeded is updated accordingly
     *
     * @param shot
     */
    public void updateRunNeeded(int shot) {
        runNeeded = runNeeded == -1 ? -1 : runNeeded - shot < 0 ? 0 : runNeeded - shot;
    }

    public void updateWicketsLeft() {
        --wicketLeft;
    }

    public void setCurrentBall(int currentBall) {
        this.currentBall = currentBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scorecard scorecard = (Scorecard) o;
        return overLeft == scorecard.overLeft &&
                ballRemaining == scorecard.ballRemaining &&
                runNeeded == scorecard.runNeeded &&
                wicketLeft == scorecard.wicketLeft &&
                currentBall == scorecard.currentBall &&
                lastRun == scorecard.lastRun &&
                Objects.equals(striker, scorecard.striker) &&
                Objects.equals(runner, scorecard.runner) &&
                Objects.equals(bowler, scorecard.bowler);
    }

    @Override
    public int hashCode() {

        return Objects.hash(overLeft, ballRemaining, runNeeded, wicketLeft, currentBall, striker, runner, bowler, lastRun);
    }

    @Override
    public String toString() {
        return "Scorecard{" +
                "overLeft=" + overLeft +
                ", ballRemaining=" + ballRemaining +
                ", runNeeded=" + runNeeded +
                ", wicketLeft=" + wicketLeft +
                ", currentBall=" + currentBall +
                ", striker=" + striker +
                ", runner=" + runner +
                ", bowler=" + bowler +
                ", lastRun=" + lastRun +
                '}';
    }
}

