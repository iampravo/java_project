import com.IntergalacticT20.*;
import com.IntergalacticT20.exception.CricketException;
import com.IntergalacticT20.utility.CricketUtility;
import com.IntergalacticT20.model.Match;
import com.IntergalacticT20.model.Player;
import com.IntergalacticT20.model.Team;

/**
 * Main class for this project
 *
 * @param  : weather & time from program argument
 * @Author : Pravinkumar Singh
 * @Email : pravinsinghkumar@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        try {
            //Two input parameter are read and null-checked
            if (args == null || args[0] == null || args[1] == null) {
                //Custom Exception is thrown on mismatch
                throw new CricketException("Incorrect Input");
            }
            String weather = args[0];
            String time = args[1];

            /*
             * Static match data is stored in weather and time map
             * this Map will be used in deciding the toss with considering the winning criteria
             */
            CricketUtility.initializeStaticData();
            System.out.println("**********************************");
            System.out.println("Welcome to Intergalactic T20 Match");
            System.out.println("**********************************");
            System.out.println();

            /*
             * Legaburu team is created and initialized with newly created players.
             */
            Team proponentTeam = Team.createTeam(CricketUtility.LENGABARU);
            Player[] proponentPlayers = CricketUtility.getPlayers(CricketUtility.LENGABARU);
            proponentTeam.initializeTeam(proponentPlayers);

            /*
             * Enchai team is created and initialized with newly created players.
             */
            Team opponentTeam = Team.createTeam(CricketUtility.ENCHAI);
            Player[] opponentPlayers = CricketUtility.getPlayers(CricketUtility.ENCHAI);
            opponentTeam.initializeTeam(opponentPlayers);

            Team[] teams = {proponentTeam, opponentTeam};

            /*
             * Match object is using getInstance of a singleton class
             */
            Match match = Match.getInstance();
            match.initializeMatch(weather, time);


       /*
        *  Problem 1 : Toss
        *  in this problem random class is used to generate number between 0 and 1
        *  which is used to decide the toss winner
        */
            System.out.println("**********************************");
            System.out.println("Problem 1 : Toss");
            System.out.println("**********************************");
            MatchAction.doToss(match, teams);
            Commentary.doTossCommentary(match);
            System.out.println();


        /*
         * Problem 2 : Last Four Over
         * In this problem the scorecard is manually set to a specific match condition and
         * match is simulated, further commentary is added as per situation
         */
            System.out.println("**********************************");
            System.out.println("Problem 2 : Last Four Over");
            System.out.println("**********************************");
            CricketUtility.initializeStaticScorecardProblem1(match);
            MatchAction.play(match);
            Commentary.matchUpdate(match);


        /*
         * Problem 3 : The Tie Breaker
         * in this problem scorecard is manually set a specific TIE Breaker condition
         * and match is simulated, further commentary is needed as per requirement.
         */
            System.out.println("\n**********************************");
            System.out.println("Problem 3 : The Tie Breaker");
            System.out.println("**********************************");
            match.initializeMatch(weather, time);

            MatchAction.doToss(match, teams);
            Commentary.doTossCommentary(match);

            CricketUtility.initializeStaticScorecardProblem2(match, 1);
            Commentary.doInningCommentary(match);
            MatchAction.play(match);

            CricketUtility.changeInning(match);

            CricketUtility.initializeStaticScorecardProblem2(match, 2);
            Commentary.doInningCommentary(match);
            MatchAction.play(match);

            Commentary.matchUpdate(match);

        } catch (CricketException e) {
            e.printErrorMessage();
        }
    }
}
