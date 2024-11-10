package com.IntergalacticT20;

import com.IntergalacticT20.model.Match;
import com.IntergalacticT20.model.Scorecard;
import com.IntergalacticT20.model.Toss;

import java.util.Arrays;

public class Commentary {

    /**
     * this methods performs inning commentary.
     *
     * @param match
     */
    public static void doInningCommentary(Match match) {
        System.out.println();
        System.out.print(match.getProponentTeam().getTeamName() + " innings:");
    }


    /**
     * this methods performs toss commentary.
     *
     * @param match
     */
    public static void doTossCommentary(Match match) {
        Toss matchToss = match.getMatchToss();
        System.out.println(matchToss.getTossWinningTeam() + " wins toss and " + matchToss.getTossWinnerElected().toLowerCase());
    }


    /**
     * this methods performs the over update  commentary.
     *
     * @param match
     */
    public static void overUpdate(Match match) {
        System.out.println();
        System.out.println(match.getScorecard().getOverLeft() + " overs left. " + (match.getScorecard().getRunNeeded() > 0 ? match.getScorecard().getRunNeeded() + " runs to win" : ""));
    }


    /**
     * this methods performs ball update commentary.
     *
     * @param match
     */
    public static void ballUpdate(Match match) {
        Scorecard scorecard = match.getScorecard();
        if (scorecard.getLastRun() == -1)
            System.out.println(scorecard.getCurrentBall() + " " + scorecard.getStriker().getPlayerName()
                    + " is out  ");
        else
            System.out.println(scorecard.getCurrentBall() + " " + scorecard.getStriker().getPlayerName()
                    + " scores " + scorecard.getLastRun() + (scorecard.getLastRun() > 1 ? " runs" : " run"));
    }


    /**
     * this methods performs match commentary.
     * it also displays the run scored by player, who all have  played the inning, of the winning team.
     *
     * @param match
     */
    public static void matchUpdate(Match match) {
        System.out.println();
        if (match.getScorecard().getRunNeeded() > 0) {
            System.out.println(match.getOpponentTeam().getTeamName() + " won by " + match.getScorecard().getRunNeeded() + " runs");
        } else {
            int wicketLeft = match.getScorecard().getWicketLeft();
            int ballRemaining = match.getScorecard().getBallRemaining();
            System.out.println(match.getProponentTeam().getTeamName() + " won by " + wicketLeft + (wicketLeft > 1 ? " wickets " : " wicket") + " and "
                    + ballRemaining + (ballRemaining > 1 ? " balls" : " ball") + " remaining.\n");
        }
        Arrays.stream(match.getProponentTeam().getPlayers()).forEach(player -> {
            if (player.isHasPlayed()) {
                int ballFaced = player.getBallFaced();
                if (!player.isOut())
                    System.out.println(player.getPlayerName() + " - " + player.getRun() + "* (" + ballFaced + (ballFaced > 1 ? " balls" : " ball") + ')');
                else
                    System.out.println(player.getPlayerName() + " - " + player.getRun() + " (" + ballFaced + (ballFaced > 1 ? " balls" : " ball") + ')');
            }
        });

    }


}
