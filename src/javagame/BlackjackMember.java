/**
 * Application Purpose: Final project
 * - Create class, variables, constructors and getter, setter and methods
 * - This class is management members who play game
 * - It must encapsulate because of personal information.
 * Author: Eunjin Ha
 * Date: 25 ~ 30, 3, 2021
 * Time: 10:30pm
 */

package javagame;

public class BlackjackMember {
    // Create variables
    private int seedMoney = 1000;
    private int battingAmount;
    private String nickName;
    private boolean nickNameStatus;
    private int carriedAmount;
    private int winCount;
    private int loseCount;

    // Create default constructor
    public BlackjackMember(){
    }

    // Create setter
    public void setSeedMoney(int seedMoney){
        this.seedMoney = seedMoney;
    }
    public void setBattingAmount(int battingAmount){
        this.battingAmount = battingAmount;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public void setNickNameStatus(boolean nickNameStatus) {
        this.nickNameStatus = nickNameStatus;
    }
    public void setCarriedAmount(int carriedAmount) {
        this.carriedAmount = carriedAmount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }
    public void setLoseCount(int loseCount) {
        this.loseCount = loseCount;
    }

    // Create getter
    public int getSeedMoney(){
        return seedMoney;
    }
    public int getBattingAmount(){
        return battingAmount;
    }
    public String getNickName(){
        return nickName;
    }
    public boolean getNickNameStatus(){
        return nickNameStatus;
    }
    public int getCarriedAmount() {
        return carriedAmount;
    }
    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    // Create User nickname
    public void createNickName(String nickname){
        // set the value
        setNickName(nickname);
    }

    // add or take away depends on result
    public void calculateMoney(int winner){
        int totalAmount = 0;
        switch (winner){
            case 1:
            case 10: //(player over 21 or Not as close to 21 as the banker)
                // Winner is banker
                // - take away batting amount from player's seed money
                totalAmount = getSeedMoney() - getBattingAmount();
                setSeedMoney(totalAmount);

                // if banker win, Initialize carriedAmount. - also lose all carried money
                setCarriedAmount(0);

                // save lose count
                setLoseCount(getLoseCount()+1);

                break;
            case 2:
            case 20: //(banker over 21 or Not as close to 21 as the player)
                // Winner is player - add batting amount * 2 into player's seed money
                // Amount carried x2 forward + batting x2
                totalAmount = (getCarriedAmount()*2) + (getSeedMoney()+getBattingAmount()*2);
                setSeedMoney(totalAmount);

                // if banker win, Initialize carriedAmount.
                setCarriedAmount(0);

                // save win count
                setWinCount(getWinCount()+1);
                break;

            case 3:  // draw
                // Accumulate carriedAmount in case there are keep draw.
                totalAmount = getCarriedAmount() + getBattingAmount();
                setCarriedAmount(totalAmount);
                break;
        }

    }
}

