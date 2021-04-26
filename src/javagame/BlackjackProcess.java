/**
 * Application Purpose: Final project
 * - Create class, variables, constructors and getter, setter and methods
 * - This class refers to blueprint of Blackjack rules
 * Author: Eunjin Ha
 * Date: 25 ~ 30, 3, 2021
 * Time: 10:30pm
 */

package javagame;

import java.util.Random;

public class BlackjackProcess {

    // fixed data - declared as final
    private final String[] kindCards = {"♥","♦","♣","♠"};
    private final String[] numCards = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    // Row : 5, column: 2 : max 5 cards
    private int[][] bankerCard;
    private int[][] playerCard;

    // public static String[] cards = new String[52];
    public Random ranNum  = new Random();

    // default constructor
    public BlackjackProcess(){

    }

    // Setter
    public void setBankerCard(int[][] bankerCard) {
        this.bankerCard = bankerCard;
    }
    public void setPlayerCard(int[][] playerCard) {
        this.playerCard = playerCard;
    }

    // Getter
    public String[] getKindCards() {
        return kindCards;
    }
    public String[] getNumCards() {
        return numCards;
    }
    public int[][] getBankerCard() {
        return bankerCard;
    }
    public int[][] getPlayerCard() {
        return playerCard;
    }

    // At first, generate 2 cards for banker & player
    public void generateCard(){

        // create variables for random numbers
        int bankerFirst,bankerSecond,playerFirst,playerSecond = 0;
        boolean isOverlap = false;

        bankerFirst = ranNum.nextInt(4);
        bankerSecond = ranNum.nextInt(13);
        playerFirst = ranNum.nextInt(4);
        playerSecond = ranNum.nextInt(13);

        for(int i =0; i < 2; i++){

            isOverlap = checkOverlap(bankerCard, playerCard, bankerFirst, bankerSecond);

           while (isOverlap) {
               bankerFirst = ranNum.nextInt(4);
               bankerSecond = ranNum.nextInt(13);

               if(checkOverlap(bankerCard, playerCard, bankerFirst, bankerSecond)){
                   isOverlap = true;
               }else{
                   isOverlap = false;
               }
           }

            // Create and insert the first two cards for banker
            bankerCard[i][0] = bankerFirst;
            bankerCard[i][1] = bankerSecond;

            isOverlap = checkOverlap(bankerCard, playerCard, playerFirst, playerSecond);

            while (isOverlap) {
                playerFirst = ranNum.nextInt(4);
                playerSecond = ranNum.nextInt(13);

                if(checkOverlap(bankerCard, playerCard, playerFirst, playerSecond)){
                    isOverlap = true;
                }else{
                    isOverlap = false;
                }
            }

            // Create the first two cards for player
            playerCard[i][0] = playerFirst;
            playerCard[i][1] = playerSecond;
        }
    }

    // overloaded with two parameters
    public void generateCard(int type, int times){
        // type-1 : bankerCard, type-2 : playerCard
        // create variables for random numbers
        int first,second = 0;
        boolean isOverlap = false;

        first = ranNum.nextInt(4);
        second = ranNum.nextInt(13);

        isOverlap = checkOverlap(bankerCard, playerCard, first, second);

        while (isOverlap) {
            first = ranNum.nextInt(4);
            second = ranNum.nextInt(13);

            if(checkOverlap(bankerCard, playerCard, first, second)){
                isOverlap = true;
            }else{
                isOverlap = false;
            }
        }

        if(type == 1) { // banker
            // insert cards into array index (times-1)
            bankerCard[times-1][0] = first;
            bankerCard[times-1][1] = second;

        }else if(type == 2){ // player
            // insert cards into array index (times-1)
            playerCard[times-1][0] = first;
            playerCard[times-1][1] = second;
        }
    }

    // overlap check
    public boolean checkOverlap(int[][] bankerCard, int[][]playerCard, int bankerFirst, int bankerSecond){
        boolean result = false;
        int cnt = 0;

        // Total count of banker cards received
        for(int i=0; i < bankerCard.length; i++){
            if(bankerCard[i][0] >= 0){
                cnt = i+1;
            }
        }
        // check value as much count above
        for(int j=0; j < cnt; j++){
            if(bankerCard[j][0] == bankerFirst && bankerCard[j][1] == bankerSecond){
                result = true;
            }
        }

        // Total count of player cards received
        for(int i=0; i < playerCard.length; i++){
            if(playerCard[i][0] >= 0){
                cnt = i+1;
            }
        }

        // check value as much count above
        for(int j=0; j < cnt; j++){
            if(playerCard[j][0] == bankerFirst && playerCard[j][1] == bankerSecond){
                result = true;
            }
        }

        return result;
    }

    // print out only cards received for player (if the value -1, not print out)
    public String showCard(int type, int[][] cardsInfo ){
        String firstCard,secondCard, result = "";

        for(int i =0; i < cardsInfo.length; i++){
            if(cardsInfo[i][0] >= 0){
                if(type == 1) { // show banker cards
                    firstCard = kindCards[cardsInfo[i][0]];
                    secondCard = numCards[cardsInfo[i][1]];

                    // banker only shows the one card
                    if (i == 0) {
                        result = "Banker: " + firstCard + " " + secondCard + "|";
                    } else {
                        result += "*";
                    }
                }else if(type == 2){
                    if(cardsInfo[i][0] >= 0){
                        firstCard = kindCards[cardsInfo[i][0]];
                        secondCard = numCards[cardsInfo[i][1]];

                        // player  shows two cards
                        if(i ==0){
                            result = ": ";
                        }
                        //System.out.printf("%s %s |", firstCard, secondCard);
                        result += firstCard +" " +secondCard +"|";
                    }
                }
            }
        }
        return result;
    }

    // Add up the cards each received to determine the winner.
    public int sumCards(int type, int[][] cardsInfo, int useA){
        // Use A - 0: none, 1:use as 1, 2: use as 10
        int sumValue = 0;

        switch (type){
            case 1:
                // sum banker card value : array value + 1 = card number
                for(int i=0; i <cardsInfo.length; i++){
                    if(cardsInfo[i][0] >= 0) {
                        sumValue += cardsInfo[i][1] + 1;
                        if (cardsInfo[i][1] == 0) { // if card is A, chose 1 or 10
                            // total number is less then 11, A use as 10
                            if (sumValue <= 11) {
                                sumValue += 9;
                            }
                        }
                    }
                }
                break;
            case 2:
                // sum banker card value : array value + 1 = card number
                for(int i=0; i <cardsInfo.length; i++){
                    if(cardsInfo[i][0] >= 0) {
                        sumValue += cardsInfo[i][1] + 1;
                    }
                }
                if (useA == 2) {
                    //A use as 10, Since A has already been added to 1, it adds 9 more.
                    sumValue += 9;
                }
                break;
        }
        return sumValue;
    }

    // find who is winner
    public int winner(int banker, int player){
        int bankerGap = 0;
        int playerGap = 0;
        int winner = 0; // 1:banker, 2:player, 3:draw

        bankerGap = 21 - banker;
        playerGap = 21 - player;

        // if player's total score is over 21,
        if(player > 21){
            // Banker win
            winner = 10;
        }else if(banker > 21){  // if banker's total score is over 21,
            // Player win
            winner = 20;
        }else{
            if(playerGap > bankerGap){ // If the player's score difference is greater,
                // Banker win
                winner = 1;
            }
            else if(bankerGap> playerGap){ // If the banker's score difference is greater,
                // Player win
                winner = 2;
            }else if(playerGap == playerGap){ //if the score difference is same,
                // draw
                winner =3;
            }
        }
        return winner;
    }

    // Check if A is included.
    public boolean checkA(int[][] playerCardInfo){
        boolean result = false;

        for(int i=0; i <playerCardInfo.length; i++){
            if(playerCardInfo[i][1] == 0){
                result = true;
            }
        }
        return  result;
    }

}

