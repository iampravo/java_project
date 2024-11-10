package com.IntergalacticT20;

import com.IntergalacticT20.exception.CricketException;
import com.IntergalacticT20.utility.CricketUtility;
import com.IntergalacticT20.model.Match;
import com.IntergalacticT20.model.Player;
import com.IntergalacticT20.model.Scorecard;
import com.IntergalacticT20.model.Team;

public class MatchAction {
    /**
     * this Method simulated the match toss.
     * Secure random is used to generate a random number.
     * Further weatherMap and dayNightMap [set with static data] is used
     * to decide the toss and elect between bats and bowls
     *
     * @param match the match object
     * @param teams two teams, proponent and opponent
     */
    public static void doToss(Match match, Team[] teams) {
        int winningInt = CricketUtility.randomInt(1, 0);
        String electedByWeather = CricketUtility.weatherMap.get(teams[winningInt].getTeamName().toLowerCase() + match.getWeather().toLowerCase());
        String electedByTime = CricketUtility.dayNightMap.get(teams[winningInt].getTeamName().toLowerCase() + match.getTime().toLowerCase());
        if (electedByTime.equalsIgnoreCase(electedByWeather) && CricketUtility.BOWLS.equalsIgnoreCase(electedByWeather)) {
            match.setOpponentTeam(teams[winningInt]);
            match.setProponentTeam(teams[Math.abs(winningInt - 1)]);
            match.getMatchToss().setTossWinnerElected(electedByTime);
        } else {
            match.setProponentTeam(teams[winningInt]);
            match.setOpponentTeam(teams[Math.abs(winningInt - 1)]);
            match.getMatchToss().setTossWinnerElected(CricketUtility.BATS);
        }
        match.getMatchToss().setTossWinningTeam(teams[winningInt].getTeamName());
    }

    /**
     * This method simulated the match
     * It has a while loop which will be processed based on numbers of over left and numbers of wickets remaining.
     * Further it has a for loop which simulates the 6 balls of a over.
     * One each ball executed, a random number generated between 0 and 99
     * This random number is used to pick the 'shot' from a player's weighted probability array[generated statically at start]
     * Depending on the numerical value of shot, match's and player's scorecard is updated.
     * Further the striker and runner are swapped after every over.
     * The Commentary is added in between as per the requirement
     *
     * @param match : the match object
     * @throws CricketException
     */
    public static void play(Match match) throws CricketException {
        Scorecard matchScorecard = match.getScorecard();
        while (matchScorecard.getOverLeft() > 0 && matchScorecard.getWicketLeft() > 0) {
            Commentary.overUpdate(match);
            for (int x = 0; x < 6; x++) {
                matchScorecard.decrementRemainingBall();
                int rand = CricketUtility.randomInt(99, 0);
                int[] probArray = CricketUtility.playerProbabilityMap.get(matchScorecard.getStriker().getPlayerName());
                if (probArray == null)
                    throw new CricketException("Statistics for : " + matchScorecard.getStriker().getPlayerName() + " is not available");
                int shot = probArray[rand];
                updateMatchScorecard(match, shot);
                updatePlayerScorecard(match, shot);
                Commentary.ballUpdate(match);
                if (shot % 2 == 1)
                    swapStrikerAndRunner(match);
                else if (shot == -1 && matchScorecard.getWicketLeft() > 0)
                    getNextBatsman(match);

                if (matchScorecard.getWicketLeft() == 0)
                    break;
                else if (matchScorecard.getRunNeeded() == 0)
                    break;
            }
            if (matchScorecard.getRunNeeded() == 0)
                break;
            matchScorecard.decrementOverLeft();
            swapStrikerAndRunner(match);
        }
    }

    /**
     * This method updates the striker with next batsman.
     * the index of the next batsman in the 'players' array is calculated based on no. of wicket left.
     * like this :  11 - (match.getScorecard().getWicketLeft())
     *
     * @param match : the match object
     */
    private static void getNextBatsman(Match match) {
        Player player = match.getProponentTeam().getPlayers()[11 - (match.getScorecard().getWicketLeft())];
        if (player != null) {
            player.setOut(false);
            player.setHasPlayed(true);
            match.getScorecard().setStriker(player);
        }
    }

    /**
     * this method swaps the striker ad runner
     *
     * @param match : the match object
     */
    private static void swapStrikerAndRunner(Match match) {
        Player player = match.getScorecard().getStriker();
        match.getScorecard().setStriker(match.getScorecard().getRunner());
        match.getScorecard().setRunner(player);
    }

    /**
     * this methods updates the match's scorecard.
     *
     * @param match : the match object
     * @param shot  : the shot hit on the last ball
     */
    private static void updateMatchScorecard(Match match, int shot) {
        Scorecard scorecard = match.getScorecard();
        scorecard.setLastRun(shot);
        scorecard.incrementBall();

        if (shot == 4) {
            match.getProponentTeam().incrementBoundaries();
        } else if (shot == 6) {
            match.getProponentTeam().incrementSixers();
        }

        if (shot == -1) {
            scorecard.updateWicketsLeft();
        } else {
            match.getProponentTeam().addScore(shot);
            match.getScorecard().updateRunNeeded(shot);
        }
    }

    /**
     * this methods updates the player's scorecard.
     *
     * @param match : the match object
     * @param shot  : the shot hit on the last ball
     */
    private static void updatePlayerScorecard(Match match, int shot) {
        match.getScorecard().getStriker().incrementBallFaced();
        if (shot == -1) {
            match.getScorecard().getStriker().setOut(true);
        } else {
            match.getScorecard().getStriker().addRun(shot);
        }
    }
}
