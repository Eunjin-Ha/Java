package bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

    public static Scanner data = new Scanner(System.in);

    public static void main(String[] args) {

        // initiate objects
        Account accountObj = new Account();
        Bank bankObj = new Bank();
        List<Bank> bankInfo = new ArrayList<Bank>();

        // Ask the user to input a Bank name and branch location
        System.out.println("Please enter Bank name");
        String bankName = data.nextLine();

        // Output : Welcome to BRANCH_LOCATION of BANK_NAME
        System.out.println("✧✧✧ ✧✧✧ ✧✧✧ Welcome to "+ bankName +" Bank ✧✧✧ ✧✧✧ ✧✧✧" );

        // What would you like to do
        String[] options = {"Exit", "Add Accounts","View Accounts", "Account Details", "Modify Account",
                "Delete Account", "Summary", "Help", };

        // Repeat until user wants to exit
        boolean isExit =false;

        // keep looping until user want to exit
        while(!isExit) {
            System.out.println("✧✧✧ Please choose the options ✧✧✧");
            for (int i = 0; i < options.length; i++) {
                if(options[i].equals("Summary") || options[i].equals("Help") ){
                    System.out.println("Please typing " + options[i]);
                }else{
                    System.out.println(i + "." + options[i]);
                }
            }
            String opt = data.next();

            switch (opt.toLowerCase()) {
                case "0":
                    isExit = true;
                    System.out.println("Thank you for using our service!!!");
                    break;
                //Add Accounts
                case "1":
                    System.out.println("=== Add Accounts ===");

                    // Create Add Variables
                    boolean isPass = false;
                    String accountNumber ="";
                    String accountName = "";
                    double balance = 0;


                    // Check the account number is valid
                    while(!isPass){
                        System.out.println("Please Enter the account number");
                        accountNumber = data.next();
                        isPass = accountObj.isLetter(1, accountNumber);

                        // Make sure you have an account number with the same name
                        if(bankObj.isExist(bankInfo,accountNumber)){
                            isPass = false;
                            System.out.println("The account already exist. Please try again.");
                        }
                    }

                    // if the account number is valid, move to next step
                    if(isPass) {
                        data.nextLine(); // clear scanner buffer

                        isPass = false; // initiate the isPass variable to check next valid data
                        while (!isPass) {
                            System.out.println("Please Enter the account name");
                            accountName = data.nextLine();
                            isPass = accountObj.isLetter(2, accountName);
                        }
                    }

                    // if the account name is valid, move to next step
                    if(isPass) {
                        isPass = false; // initiate the isPass variable to check next valid data

                        while (!isPass) {
                            System.out.println("Please Enter the account balance");
                            balance = data.nextDouble();

                            // Verify that the data is non-negative
                            if(isPass = accountObj.isDigit(balance)) {
                                // Verify that the data is numeric
                                bankInfo.add(new Bank(bankName, accountNumber, balance, accountName));
                                System.out.println("==== Completely save ====");
                                isPass = true;
                            }
                        }
                    }
                    break;
                //View Accounts
                case "2":
                    System.out.println("=== View Accounts === ");

                    // Call function to print bank info
                    System.out.println(bankObj.getBankInfo(bankInfo));
                    break;
                //Account Details
                case "3":
                    System.out.println("=== Account Details === ");
                    // check - whether the account has at least one
                    if(bankInfo.size() > 0) {
                        System.out.println("Please enter the account number you want to show details.");
                        String findAccount = data.next();

                        // Call function to print specific account details
                        Bank getBank2 = bankObj.findObj(bankInfo, findAccount);

                        if (getBank2.getAccountNum() == null) {
                            System.out.println("There is no bank account. Please try again");
                        } else {
                            System.out.println("Bank Name: " + getBank2.getBankName());
                            System.out.println("Account Number: " + getBank2.getAccountNum());
                            System.out.println("Account Name: " + getBank2.getAccountName());
                            System.out.println("Balance: " + getBank2.getBalance());
                            System.out.println("================== ==================");
                        }
                    }else{
                        System.out.println("[Note] The Bank accounts is empty. Please add account");
                    }
                    break;
                //Modify Accounts
                case "4":
                    System.out.println("=== Modify Accounts ===");

                    // variables - initiate
                    int optNum = 0;
                    boolean isNext = false;
                    String changeNumber = "";
                    String modifyNumber ="";
                    String modifyName   ="";
                    double modifyBalance = 0;

                    // check - whether the account has at least one
                    if(bankInfo.size() > 0){
                        // Please enter the account you want to change.
                        System.out.println("Please enter the account you want to change.");
                        changeNumber = data.next();

                        Bank getBank = bankObj.findObj(bankInfo,changeNumber);

                        if(getBank.getAccountNum() == null){
                        System.out.println("There is no bank account. Please try again");
                        }else{
                            // Ask the user what they want to change.
                            // Modify - Account Number
                            System.out.println("Would you like to change your account number?");
                            System.out.println("1.Yes 2.No");
                            optNum = data.nextInt();

                            // if the user want to change the account number
                            if(optNum == 1){
                                while (!isNext){
                                    System.out.println("Please Enter the account number");
                                    modifyNumber = data.next();

                                    // Keep looping when the data is valid
                                    // if the account number is valid
                                    if(accountObj.isLetter(1, modifyNumber)) {
                                        isNext = true;
                                    }
                                }
                            }else{
                                modifyNumber = getBank.getAccountNum();
                                isNext = true;
                            }

                            // Modify - Account Name
                            if(isNext){
                                // re-initiate
                                isNext = false;

                                while (!isNext){
                                    System.out.println("Would you like to change your account name?");
                                    System.out.println("1.Yes 2.No");
                                    optNum = data.nextInt();

                                    // if the user want to change the account name
                                    if(optNum == 1){

                                        data.nextLine(); // clear scanner buffer

                                        System.out.println("Please Enter the account Name");
                                        modifyName = data.nextLine();

                                        // Keep looping when the data is valid
                                        while (!isNext){
                                            // if the account number is valid
                                            if(accountObj.isLetter(2, modifyName)) {
                                                isNext = true;
                                            }
                                        }
                                    }else{
                                        modifyName = getBank.getAccountName();
                                        isNext = true;
                                    }
                                }
                            }
                            }
                            // Modify - Balance
                            if(isNext){
                            // re-initiate
                            isNext = false;
                            System.out.println("Would you like to change your account Balance?");
                            System.out.println("1.Yes 2.No");
                            optNum = data.nextInt();

                            // if the user want to change the account name
                            if(optNum == 1){
                                System.out.println("Please Enter the account Balance");
                                modifyBalance = data.nextInt();

                                // Keep looping when the data is valid
                                while (!isNext){
                                    // if the account number is valid
                                    if(accountObj.isDigit(modifyBalance)) {
                                        isNext = true;
                                    }
                                }
                            }else {
                                modifyBalance = getBank.getBalance();
                                isNext = true;
                            }
                        }
                            // call update function
                            System.out.println(bankObj.modify(bankInfo, changeNumber, modifyNumber, modifyName, modifyBalance));
                        }else{
                            System.out.println("[Note] The Bank accounts is empty. Please add account");
                        }

                    break;
                //Delete Accounts
                case "5":
                    System.out.println("=== Delete Accounts ===");

                    // check whether the bank account exist
                    if(bankInfo.size() > 0) {
                        System.out.println("Please enter the account number you want to delete.");
                        String delAccount = data.next();

                        // find data
                        Bank getBank3 = bankObj.findObj(bankInfo,delAccount);

                        // null check
                        if(getBank3.getAccountNum() == null) {
                            System.out.println("There is no bank account. Please try again");
                        }else{
                            // Call delete function
                            System.out.println(bankObj.deleteBankInfo(bankInfo, delAccount));
                        }
                    }else{
                        System.out.println("[Note] The Bank accounts is empty. Please add account");
                    }

                    break;
                //Summary
                case "summary":
                    System.out.println("=== Summary ===");

                    // Call summary function
                    System.out.println(bankObj.summary(bankInfo));
                    break;
                //Help
                case "help":
                    System.out.println("=== Help ===");
                    System.out.println(helpInfo());
                    break;
                default:
                    isExit = true;
                    break;
            }
        }
    }

    // show help info
    public static String helpInfo(){
        String val = "";

        val = "✧✧✧ DESCRIPTION ✧✧✧ \n " +
                "There are five options\n" +
                "1.Add Accounts - Create Bank Account \n" +
                "2.View Accounts - View all of Bank Accounts\n" +
                "3.Account Details - View specific Info of the Bank Account using account number \n" +
                "4.Modify Account - Change your account Info (Account number, Account name, Balance)\n" +
                "5.Delete Account - Delete specific bank account using account number\n" +
                "================== ==================";

        return val;
    }
}
