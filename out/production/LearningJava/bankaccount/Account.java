package Assignment2_200466613;

public class Account {
    // Create appropriate variables
    private String accountNum;
    private double balance;
    private String accountName;

    // Create Constructors
    public Account(){}

    public Account(String accountNum, double balance, String accountName) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.accountName = accountName;
    }

    // Getter & Setter
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }


    // Custom exceptions
    public static class ShowErrorMsg extends Exception{
        // Create default constructor
        public ShowErrorMsg(String message){
            super(message);
        }
    }

    // check letter
    public static boolean isLetter(int type, String str){
        boolean val = true;

        // type - 1:AccountNum / 2:accountName
        switch (type){
            // AccountNum
            case 1:
                try{
                    for(int i=0; i<str.length(); i++){
                        if(!Character.isLetterOrDigit(str.charAt(i))){
                            val = false;
                            throw new ShowErrorMsg("[ERROR] The account number can only contain alphanumerical characters.");
                        }
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    // check - the account name only allow alphabetical + '-' and spance
                    for (int i = 0; i < str.length(); i++) {
                        char charVal = str.charAt(i);
                        // compare all data
                        if('a'<=charVal && charVal<='z') continue;
                        if('A'<=charVal && charVal<='Z') continue;
                        if(charVal==' ') continue;
                        if(charVal=='-') continue;

                        val = false;

                        // if the value include the number, print out error message
                        if(!val)
                            throw new ShowErrorMsg("[ERROR] The account name can only contain alphabetical characters, as well as spaces and hyphens.");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
        }
        return val;
    }

    // check number
    public static boolean isDigit(double balance){
        boolean val = true;
        try{
            if(balance < 0){
                val = false;
                throw new ShowErrorMsg("The account balance can only contain non-negative values.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return val;
    }
}
