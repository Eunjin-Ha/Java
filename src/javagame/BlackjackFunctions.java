package javagame;

public class BlackjackFunctions {

    // Add up the cards each received to determine the winner.
    public static int sumCards(int type, int[][] cardsInfo, int useA){
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
    public static int winner(int banker, int player){
        int bankerGap = 0;
        int playerGap = 0;
        int winner = 0; // 1:banker, 2:player, 3:draw

        bankerGap = 21 - banker;
        playerGap = 21 - player;

        // if player's total score is over 21,
        if(player > 21){
            // Banker win
            winner = 1;
        }else if(banker > 21){  // if banker's total score is over 21,
            // Player win
            winner = 2;
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
    public static boolean checkA(int[][] playerCardInfo){
        boolean result = false;

        for(int i=0; i <playerCardInfo.length; i++){
            if(playerCardInfo[i][1] == 0){
                result = true;
            }
        }
        return  result;
    }

}
