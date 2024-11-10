package com.IntergalacticT20.utility;

import com.IntergalacticT20.exception.CricketException;
import com.IntergalacticT20.model.Match;
import com.IntergalacticT20.model.Player;
import com.IntergalacticT20.model.Team;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CricketUtility {
    private static final SecureRandom random = new SecureRandom();
    public static final String LENGABARU = "Lengabaru";
    public static final String ENCHAI = "Enchai";
    private static final String CLEAR = "Clear";
    private static final String CLOUDY = "Cloudy";
    private static final String DAY = "Day";
    private static final String NIGHT = "Night";
    public static final String BATS = "Bats";
    public static final String BOWLS = "Bowls";
    public static Map<String, String> dayNightMap = new HashMap<>(4);
    public static Map<String, String> weatherMap = new HashMap<>(4);
    public static Map<String, int[]> playerProbabilityMap = new HashMap<>();
    private static Map<String, Player[]> playersCollections = new HashMap<>();

    /**
     * this method return the array of players of the team
     *
     * @param teamName team name
     * @return : array of players of the provided team
     * @throws CricketException : throws exception if collections is empty or team name is incorrect
     */
    public static Player[] getPlayers(String teamName) throws CricketException {
        if (playersCollections == null || playersCollections.isEmpty()) {
            throw new CricketException("Please provide initialize players details.");
        } else if (playersCollections.get(teamName) == null) {
            throw new CricketException("Player details are not available for team : " + teamName);
        } else {
            return playersCollections.get(teamName);
        }
    }

    /**
     * Statically initialize the  array of players for each team and store them into PlayersCollections
     */
    static {
        Player[] lengaburuPlayers = new Player[11];
        lengaburuPlayers[0] = new Player("A");
        lengaburuPlayers[1] = new Player("B");
        lengaburuPlayers[2] = new Player("C");
        lengaburuPlayers[3] = new Player("D");
        lengaburuPlayers[4] = new Player("E");
        lengaburuPlayers[5] = new Player("F");
        lengaburuPlayers[6] = new Player("Michel Holding");
        lengaburuPlayers[7] = new Player("Kirat Boli");
        lengaburuPlayers[8] = new Player("NS Nodhi");
        lengaburuPlayers[9] = new Player("R Rumrah");
        lengaburuPlayers[10] = new Player("Shashi Henra");

        playersCollections.put(CricketUtility.LENGABARU, lengaburuPlayers);

        Player[] enchaiPlayers = new Player[11];
        enchaiPlayers[0] = new Player("A");
        enchaiPlayers[1] = new Player("B");
        enchaiPlayers[2] = new Player("C");
        enchaiPlayers[3] = new Player("D");
        enchaiPlayers[4] = new Player("E");
        enchaiPlayers[5] = new Player("Shreenath");
        enchaiPlayers[6] = new Player("Wasim Akaram");
        enchaiPlayers[7] = new Player("Carl Hooper");
        enchaiPlayers[8] = new Player("Viv Richard");
        enchaiPlayers[9] = new Player("S Tait");
        enchaiPlayers[10] = new Player("Glenn McGrath");

        playersCollections.put(CricketUtility.ENCHAI, enchaiPlayers);

    }

    /**
     * generates the random int between 'from' and 'end', using secure random from Java Util.
     *
     * @param from start
     * @param end  last value
     * @return random int
     */
    public static int randomInt(int from, int end) {
        return random.nextInt((from + 1) - end) + end;
    }

    /**
     * Initialize the static data.
     */
    public static void initializeStaticData() {
        weatherMap.put(LENGABARU.toLowerCase() + CLEAR.toLowerCase(), BATS);
        weatherMap.put(LENGABARU.toLowerCase() + CLOUDY.toLowerCase(), BOWLS);
        weatherMap.put(ENCHAI.toLowerCase() + CLEAR.toLowerCase(), BOWLS);
        weatherMap.put(ENCHAI.toLowerCase() + CLOUDY.toLowerCase(), BATS);

        dayNightMap.put(LENGABARU.toLowerCase() + DAY.toLowerCase(), BATS);
        dayNightMap.put(LENGABARU.toLowerCase() + NIGHT.toLowerCase(), BOWLS);
        dayNightMap.put(ENCHAI.toLowerCase() + DAY.toLowerCase(), BOWLS);
        dayNightMap.put(ENCHAI.toLowerCase() + NIGHT.toLowerCase(), BATS);
    }

    /**
     * This method  initializes the match scorecard with PROBLEM 1's situation
     * Run Needed : 40
     * Wicket Left : 3
     * OverLeft : 4
     *
     * @param match : the match object
     */
    public static void initializeStaticScorecardProblem1(Match match) {
        generatePlayerProbabilityStaticDataProblem1();
        match.getScorecard().setRunNeeded(40);
        match.getScorecard().setOverLeft(4);
        match.getScorecard().setWicketLeft(3);
        match.getScorecard().setBallRemaining(match.getScorecard().getOverLeft() * 6);
        Player player = match.getProponentTeam().getPlayers()[11 - (match.getScorecard().getWicketLeft() + 1)];
        player.setHasPlayed(true);
        match.getScorecard().setStriker(player);
        player = match.getProponentTeam().getPlayers()[11 - match.getScorecard().getWicketLeft()];
        player.setHasPlayed(true);
        match.getScorecard().setRunner(player);
        match.getScorecard().setBowler(match.getOpponentTeam().getPlayers()[11 - match.getScorecard().getOverLeft()]);
    }

    /**
     * This method  initializes the match scorecard with PROBLEM 2's situation
     * Such as over left : 1, wicket left : 1
     * In first inning [inn =1 ], the players of each team are strategically placed in batting line up, as per
     * the problem 2 situation, Further 'RunNeeded' is set to -1, as its first inning.
     * In Second inning [inn =2 ], the run needed is set to the first inning's total score.
     *
     * @param match : the match object
     * @param inn
     */
    public static void initializeStaticScorecardProblem2(Match match, int inn) {
        match.getScorecard().setOverLeft(1);
        match.getScorecard().setWicketLeft(1);
        if (inn == 1) {
            Player[] lengabaru = playersCollections.get(CricketUtility.LENGABARU);
            Arrays.stream(lengabaru).forEach(player -> player.setHasPlayed(false));
            Player kb = new Player("Kirat Boli");
            Player ns = new Player("NS Nodhi");
            lengabaru[11 - (match.getScorecard().getWicketLeft() + 1)] = kb;
            lengabaru[11 - match.getScorecard().getWicketLeft()] = ns;

            Player db = new Player("DB Vellyers");
            Player hm = new Player("H Mamla");
            Player[] enchai = playersCollections.get(CricketUtility.ENCHAI);
            Arrays.stream(enchai).forEach(player -> player.setHasPlayed(false));
            enchai[11 - (match.getScorecard().getWicketLeft() + 1)] = db;
            enchai[11 - match.getScorecard().getWicketLeft()] = hm;
            generatePlayerProbabilityStaticDataProblem2();
            match.getProponentTeam().reset();
            match.getOpponentTeam().reset();
        }
        match.getScorecard().setBallRemaining(match.getScorecard().getOverLeft() * 6);
        match.getScorecard().setCurrentBall(0);
        if (inn == 1)
            match.getScorecard().setRunNeeded(-1);
        else
            match.getScorecard().setRunNeeded(match.getOpponentTeam().getScore() + 1);
        Player player = match.getProponentTeam().getPlayers()[11 - (match.getScorecard().getWicketLeft() + 1)];
        player.setHasPlayed(true);
        match.getScorecard().setStriker(player);
        player = match.getProponentTeam().getPlayers()[11 - match.getScorecard().getWicketLeft()];
        player.setHasPlayed(true);
        match.getScorecard().setRunner(player);
        match.getScorecard().setBowler(match.getOpponentTeam().getPlayers()[11 - match.getScorecard().getOverLeft()]);
    }

    /**
     * This method swap the proponent and opponent team after completion of first inning.
     *
     * @param match : the match object
     */
    public static void changeInning(Match match) {
        Team team = match.getProponentTeam();
        match.setProponentTeam(match.getOpponentTeam());
        match.setOpponentTeam(team);
    }

    /**
     * this method generates the player's weighted probability map for players required in PROBLEM 2
     * for each player, a weighted probability array of 100 elements,  is created depending on it's scoring probability given in requirement.
     * This weight probability array is then stored into playerProbabilityMap as a value with player Name as a key.
     * For calculating the shot player by player, a random number is generated between 0 and 99, this random number
     * works as a index for player's weighted probability array, and corresponding value is fetched from the array against that index.
     * and this is how player's shot is randomly calculated for each ball.
     */
    private static void generatePlayerProbabilityStaticDataProblem2() {
        if (!playerProbabilityMap.isEmpty())
            playerProbabilityMap.clear();
        playerProbabilityMap.put("Kirat Boli", generatePlayerProbability(5, 10, 25, 10, 25, 1, 14, 10));
        playerProbabilityMap.put("NS Nodhi", generatePlayerProbability(5, 15, 15, 10, 20, 1, 19, 15));
        playerProbabilityMap.put("DB Vellyers", generatePlayerProbability(5, 10, 25, 10, 25, 1, 14, 10));
        playerProbabilityMap.put("H Mamla", generatePlayerProbability(10, 15, 15, 10, 20, 1, 19, 10));
    }

    /**
     * this method generates the player's weighted probability map for players required in PROBLEM 1
     * for each player, a weighted probability array of 100 elements,  is created depending on it's scoring probability given in requirement.
     * This weight probability array is then stored into playerProbabilityMap as a value with player Name as a key.
     * For calculating the shot player by player, a random number is generated between 0 and 99, this random number
     * works as a index for player's weighted probability array, and corresponding value is fetched from the array against that index.
     * and this is how player's shot is randomly calculated for each ball.
     */
    private static void generatePlayerProbabilityStaticDataProblem1() {
        if (!playerProbabilityMap.isEmpty())
            playerProbabilityMap.clear();
        playerProbabilityMap.put("Kirat Boli", generatePlayerProbability(5, 30, 25, 10, 15, 1, 9, 5));
        playerProbabilityMap.put("NS Nodhi", generatePlayerProbability(10, 40, 20, 5, 10, 1, 4, 10));
        playerProbabilityMap.put("R Rumrah", generatePlayerProbability(20, 30, 15, 5, 5, 1, 4, 20));
        playerProbabilityMap.put("Shashi Henra", generatePlayerProbability(30, 25, 5, 0, 5, 1, 4, 30));
    }

    /**
     * this methods generated the weighted array of size 100, with given input
     * E.g. dot = 5, one = 5, two =3 ....
     * Output array = [0,0,0,0,0,1,1,1,1,1,2,2,2,.....]
     * This array is used to select the player shot randomly.
     *
     * @param dot   : probability of 0
     * @param one   : probability of 1
     * @param two   : probability of 2
     * @param three : probability of 3
     * @param four  : probability of 4
     * @param five  : probability of 5
     * @param six   : probability of 6
     * @param out   : probability of out
     * @return : array of weighted probability
     */
    private static int[] generatePlayerProbability(int dot, int one, int two, int three, int four, int five, int six, int out) {
        int[] prob = new int[100];
        int length = prob.length;
        for (int x = 0; x < length; x++) {
            if (x < dot)
                prob[x] = 0;
            else if (x < dot + one)
                prob[x] = 1;
            else if (x < dot + one + two)
                prob[x] = 2;
            else if (x < dot + one + two + three)
                prob[x] = 3;
            else if (x < dot + one + two + three + four)
                prob[x] = 4;
            else if (x < dot + one + two + three + four + five)
                prob[x] = 5;
            else if (x < dot + one + two + three + four + five + six)
                prob[x] = 6;
            else if (x < dot + one + two + three + four + five + six + out)
                prob[x] = -1;

        }
        return prob;

    }


}
