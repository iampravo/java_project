package com.IntergalacticT20.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Model class of Team
 *
 * @author pravinkumarsingh
 * @email : pravinsinghkumar@gmail.com
 */
public class Team {

    private String teamName;
    private Player[] players;
    private Player captain;
    private int score;
    private int wickets;
    private int boundaries;
    private int sixers;
    private int extras;

    private Team(String teamName) {
        this.teamName = teamName;
    }

    public static Team createTeam(String teamName) {
        return new Team(teamName);
    }

    public void initializeTeam(Player[] players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public void incrementBoundaries() {
        this.boundaries++;
    }

    public void setBoundaries(int boundaries) {
        this.boundaries = boundaries;
    }

    public void incrementSixers() {
        this.sixers++;
    }

    public int getSixers() {
        return sixers;
    }

    public void setSixers(int sixers) {
        this.sixers = sixers;
    }

    public int getExtras() {
        return extras;
    }

    public void setExtras(int extras) {
        this.extras = extras;
    }

    public void addScore(int shot) {
        this.score += shot;
    }

    public void reset() {
        this.score = 0;
        this.boundaries = 0;
        this.wickets = 0;
        this.sixers = 0;
        this.extras = 0;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", players=" + Arrays.toString(players) +
                ", captain=" + captain +
                ", score=" + score +
                ", wickets=" + wickets +
                ", boundaries=" + boundaries +
                ", sixers=" + sixers +
                ", extras=" + extras +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return score == team.score &&
                wickets == team.wickets &&
                boundaries == team.boundaries &&
                sixers == team.sixers &&
                extras == team.extras &&
                Objects.equals(teamName, team.teamName) &&
                Arrays.equals(players, team.players) &&
                Objects.equals(captain, team.captain);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(teamName, captain, score, wickets, boundaries, sixers, extras);
        result = 31 * result + Arrays.hashCode(players);
        return result;
    }
}
