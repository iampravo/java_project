package com.IntergalacticT20.model;

import java.util.Objects;

/**
 * Model class of Toss
 *
 * @author pravinkumarsingh
 * @email : pravinsinghkumar@gmail.com
 */
public class Toss {
    private String tossWinningTeam;
    private String tossWinnerElected;

    public String getTossWinningTeam() {
        return tossWinningTeam;
    }

    public void setTossWinningTeam(String tossWinningTeam) {
        this.tossWinningTeam = tossWinningTeam;
    }

    public String getTossWinnerElected() {
        return tossWinnerElected;
    }

    public void setTossWinnerElected(String tossWinnerElected) {
        this.tossWinnerElected = tossWinnerElected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toss toss = (Toss) o;
        return Objects.equals(tossWinningTeam, toss.tossWinningTeam) &&
                Objects.equals(tossWinnerElected, toss.tossWinnerElected);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tossWinningTeam, tossWinnerElected);
    }

    @Override
    public String toString() {
        return "Toss{" +
                "tossWinningTeam='" + tossWinningTeam + '\'' +
                ", tossWinnerElected='" + tossWinnerElected + '\'' +
                '}';
    }
}
