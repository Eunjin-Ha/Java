/**
 * Application Purpose: Final project
 * run main logic such as invoke relative functions and set, get data
 * Author: Eunjin Ha
 * Date: 25 ~ 30, 3, 2021
 * Time: 10:30pm
 */

package javagame;
import java.util.Scanner;

public class BlackjackHarness {

    public static void main(String[] args) {

        // Create object of BlackjackMember and BlackjackProcess
        BlackjackMember bjMember = new BlackjackMember();
        BlackjackProcess cardsOJ = new BlackjackProcess();

        // Create variables
        int startGame = 0;
        boolean gameOver = false;
        Scanner data = new Scanner(System.in);

        // 1. do-while, start game at first, until seedMoney is zero or user want to stop game
        do{
            // Initialize variables of banker & player cards
            cardsOJ.setBankerCard(new int[][]{{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}});
            cardsOJ.setPlayerCard(new int[][]{{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}});

            // 1-1 if Nickname doesn't exist, create nickname
            if(!bjMember.getNickNameStatus()){

                // Print out progress of game
                System.out.println("Let's Start Game!");
                System.out.println("Please enter a nickname.");
                bjMember.createNickName(data.nextLine());

                // Whether the nickname is created,
                if(bjMember.getNickName() == null || bjMember.getNickName() == ""){

                    // Create nickname until NickNameStatus is ture
                    while (!bjMember.getNickNameStatus()) {
                        System.out.println("Please enter a nickname.");
                        bjMember.createNickName(data.nextLine());

                        // null & empty check
                        if (bjMember.getNickName() != null && bjMember.getNickName() != "") {
                            // if it's not null & empty, change the NickNameStatus as true
                            bjMember.setNickNameStatus(true);
                        }
                    }
                }else {
                    // if it's not null & empty, change the NickNameStatus as true
                    bjMember.setNickNameStatus(true);
                }
            }

            // 1-2 Print out of user info
            System.out.printf("%s \n %s, Your seed money is $%d  %n",
                    startGame ==0 ?"Welcome" : "Game Restarted!", bjMember.getNickName(),bjMember.getSeedMoney());

            // 2. Batting money
            try{
                // variable for valid bet
                boolean validCheck = false;

                // Receive a bet from the user input.
                System.out.println("\n Please enter the betting amount.");
                int batting = data.nextInt();

                while(!validCheck) {
                    // check - whether batting amount is negative and zero or not
                    if (batting <= 0) {
                        System.out.println("The betting amount cannot be negative or zero.");
                    }
                    // check - whether batting amount over seedMoney or not
                    else if(batting > bjMember.getSeedMoney()){
                        System.out.println("The betting amount cannot be greater than the seed money.");
                        System.out.printf("\n %s's seed money is $%d ", bjMember.getNickName(), bjMember.getSeedMoney());
                    }else{
                        // if betting money is not negative && zero && over seedMoney
                        validCheck = true;
                    }

                    // Keep looping until betting money is valid
                    if(!validCheck) {
                        System.out.println("\n Please enter the betting amount.");
                        batting = data.nextInt();
                        if (batting >= 0 && batting <= bjMember.getSeedMoney()) {
                            validCheck = true;
                        }
                    }
                }

                // if betting money is valid, save
                if(validCheck){
                    bjMember.setBattingAmount(batting);
                }
            }catch (RuntimeException e){
                // To avoid overflow error, use try-catch
                System.out.println("Error : Batting amount is overflow");
            }

            // 2-1. Show current bet
            System.out.println("You bet "+bjMember.getBattingAmount() +" dollars");

            // 3. Hands out cards to banker & player
            cardsOJ.generateCard();

            // print out of banker cards - It will show only one card
            System.out.println("\n" + cardsOJ.showCard(1,cardsOJ.getBankerCard()));

            // print out of player cards - It will show two cards
            System.out.println(bjMember.getNickName() + cardsOJ.showCard(2,cardsOJ.getPlayerCard()));

            // 4. Choose get more cards

            // Crete variables to get more cards
            int nextStep = 0;
            int times = 3;
            boolean next = false;

            // 4-1 Ask to user, press 1 : show result / press 2: get a card
            System.out.println("\n Do you get another card? (Stand - Press 1 / Hit - Press 2)");
            nextStep = data.nextInt();
            int bankerNum = 0;
            int playerNum = 0;

            // 4-2 : Press 2 - Hit - hand out one more cards to player
            if(nextStep == 2){

                // Invoke generate card function
                while (!next) {
                    try{
                        // Card can receive up to 5
                        if(times > 5){
                            System.out.println("✭ Note : You can only receive up to 5 cards.");
                            next = true;
                        }else{ // if under 5 received cards,

                            // Invoke generate card function
                            cardsOJ.generateCard(2, times);

                            // Show current cards
                            System.out.println(bjMember.getNickName() + cardsOJ.showCard(2,cardsOJ.getPlayerCard()));

                            // Ask to user, if you want to get one more card
                            System.out.println("\n Do you get another card? (Stand - Press 1 / Hit - Press 2)");
                            nextStep = data.nextInt();

                            // if user don't want get another, move to next step
                            if (nextStep == 1) {
                                next = true;
                            }
                        }
                        times++;
                    }catch (Exception e){
                        // To avoid outbound error, use try-catch
                        System.out.println(e.toString());
                    }
                }
            }

            // 4-3 Checking A, If user has A
            if(cardsOJ.checkA(cardsOJ.getPlayerCard())){

                // User can choice way of use A as 1 or 10
                System.out.println("\n Please select a number for A. (Use as 1 - Press 1 / Use as 10 - Press 2)");
                nextStep = data.nextInt();
            }

            // Invoke sum-card function to show result of game
            bankerNum = cardsOJ.sumCards(1, cardsOJ.getBankerCard(),0);
            playerNum =cardsOJ.sumCards(2, cardsOJ.getPlayerCard(),nextStep);

            // if banker has under 12 of total sum of cards, get one more card
            while (bankerNum <= 12){
                // Invoke generate card function
                cardsOJ.generateCard(1, times);

                // Invoke sum-card function
                bankerNum = cardsOJ.sumCards(1, cardsOJ.getBankerCard(),0);
                System.out.printf("✭ Note : Banker received the %dth cards \n", times);
                times ++;
            }

            // Show sum of cards of Banker & Player
            System.out.println("Banker: " + bankerNum);
            System.out.println("Player: " + playerNum);

            // get carried money and batting amount if the game draw before
            int carried = bjMember.getCarriedAmount();
            int batting = bjMember.getBattingAmount();
            String winner ="";
            String moneyStr ="";

            // Save the prize money into user seed money
            bjMember.calculateMoney(cardsOJ.winner(bankerNum,playerNum));

            // Print out winner and betting info
            switch (cardsOJ.winner(bankerNum,playerNum) ){
                case 1:   // Banker Win as closer 21 or player over 21
                case 10:
                    winner = cardsOJ.winner(bankerNum,playerNum) == 1?"\n ✭✭✭ Winner is Banker ✭✭✭" : "\n ✭✭✭ Winner is Banker - (Player is over 21) ✭✭✭";
                    moneyStr = carried>0 ? "\n You lost batting: $" + batting + " & carried: $" +carried : "\n You lost $" + batting;
                    break;
                case 2:  // Player Win as closer 21 or Banker over 21
                case 20:
                    winner = cardsOJ.winner(bankerNum,playerNum) == 2?"\n ✭✭✭ Winner is "+ bjMember.getNickName()+" ✭✭✭" : "\n ✭✭✭ Winner is "+ bjMember.getNickName()+" - (Banker is over 21)  ✭✭✭";
                    moneyStr = carried>0 ? "\n You earned $" + (batting*2+ carried*2)  + " (Batting Amount x2 + Carried Amount x2)" : "\n You earned $" + batting*2 + " (Batting Amount x2)";

                    break;
                case 3: // Draw
                    winner = "\n ✭✭✭ Draw this game ✭✭✭ ";
                    moneyStr = "$"+batting + " will be carried the next game ";
                    bjMember.setSeedMoney(bjMember.getSeedMoney()-batting);
                    break;
            }

            // Print out of current information (Winner and Seed Money)
            System.out.println(winner);
            System.out.println(moneyStr);

            // Show player's current seed money
            System.out.printf("✭ Now your seed money is $%d", bjMember.getSeedMoney());

            // If the player bankruptcy, GAME OVER!
            if(bjMember.getSeedMoney() <= 0){
                System.out.println("\n ====== GAME OVER ====== : You lost all money");
                gameOver = true;
            }else {
                // Ask to user that keep play game or not
                System.out.println("\n Do you want to play one more? (Yes - Press 1 / No - Press 2)");
                startGame = data.nextInt();

                switch (startGame){
                    case 1: // Start game again
                        gameOver = false;
                        break;
                    case 2: // End game
                        gameOver = true;
                        break;
                }
            }

            // Show history of game (win & lose count)
            if(gameOver){
                System.out.printf("✭ %s - WIN : %d & LOSE: %d",bjMember.getNickName(),bjMember.getWinCount(),bjMember.getLoseCount());
                System.out.println("\n\n ✭✭ Thank you for joining. Have a good day:) ✭✭");
            }
        }
        // Continue until the game is finished
        while (!gameOver);
    }
}




