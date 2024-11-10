package com.IntergalacticT20.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Singleton class, providing same instance of Match, throughout match simulation.
 *
 * @author pravinkumarsingh
 * @email pravinsinghkumar@gmail.com
 */
public class Match implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private static Match ourInstance;
    private Toss matchToss;
    private Team proponentTeam;
    private Team opponentTeam;
    private String weather;
    private String time;
    private Scorecard scorecard;

    /**
     * it return the same instance of the class match
     * It is expected that program is running in single threaded env.
     *
     * @return outInstance
     */
    public static Match getInstance() {
        if (ourInstance == null) {
            ourInstance = new Match();
        }
        return ourInstance;
    }

    private Match() {
    }

    public void initializeMatch(String weather, String time) {
        this.weather = weather;
        this.time = time;
        ourInstance.matchToss = new Toss();
        ourInstance.scorecard = new Scorecard();
    }

    public Toss getMatchToss() {
        return matchToss;
    }

    public Team getProponentTeam() {
        return proponentTeam;
    }

    public Team getOpponentTeam() {
        return opponentTeam;
    }

    public void setProponentTeam(Team proponentTeam) {
        this.proponentTeam = proponentTeam;
    }

    public void setOpponentTeam(Team opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public String getWeather() {
        return weather;
    }

    public String getTime() {
        return time;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public Match clone() {
        return ourInstance;
    }

    protected Object readResolve() {
        return ourInstance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchToss, match.matchToss) &&
                Objects.equals(proponentTeam, match.proponentTeam) &&
                Objects.equals(opponentTeam, match.opponentTeam) &&
                Objects.equals(weather, match.weather) &&
                Objects.equals(time, match.time) &&
                Objects.equals(scorecard, match.scorecard);
    }

    @Override
    public int hashCode() {

        return Objects.hash(matchToss, proponentTeam, opponentTeam, weather, time, scorecard);
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchToss=" + matchToss +
                ", proponentTeam=" + proponentTeam +
                ", opponentTeam=" + opponentTeam +
                ", weather='" + weather + '\'' +
                ", time='" + time + '\'' +
                ", scorecard=" + scorecard +
                '}';
    }
}
