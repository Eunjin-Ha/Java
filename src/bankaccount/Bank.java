package bankaccount;

import java.util.ArrayList;
import java.util.List;

public class Bank extends Account{

    // Create variables
    private String bankName;
    // private int branchLocation;

    public Bank(){};

    public Bank(String bankName, String accountNum, double balance, String accountName){
        this.bankName = bankName;
        this.setAccountNum(accountNum);
        this.setBalance(balance);
        this.setAccountName(accountName);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    // View Details - print bank info
    public static String getBankInfo(List<Bank> bank){
        String val ="";

        if(bank.size() >0){
            for(Bank items: bank){
                val += "\n Bank Name:" + items.getBankName();
                val += "\n Account Number:" + items.getAccountNum();
                val += "\n Account Name:"+ items.getAccountName();
                val += "\n Balance:" + items.getBalance();
                val += "\n ================== ================== ";
            }
        }else{
            val ="[Note] There is no matched account number. \n Please check again.";
        }
        return val;
    }

    // Modify - bank info based on user inputted account number
    public static String modify(List<Bank> bank, String changeNum, String accountNum, String accountName, double balance){
        String val ="";

        for(Bank items: bank){
            if(items.getAccountNum().equalsIgnoreCase(changeNum)){

                items.setAccountNum(accountNum);
                items.setAccountName(accountName);
                items.setBalance(balance);

                val = "✧✧✧ Update Complete ✧✧✧";
            }
        }

        return val;
    }

    // Delete - Bank info
    public static String deleteBankInfo(List<Bank> bank, String accountNum){
        String val ="";

        for(int i=0; i< bank.size(); i++){
            if(bank.get(i).getAccountNum().equalsIgnoreCase(accountNum)){
                bank.remove(i);
                val ="✧✧✧ Delete Complete ✧✧✧";
            }
        }
        return val;
    }

    // Summary - print bank info
    public static String summary(List<Bank> bank){
        String val ="";
        double totalBalance = 0;

        if(bank.size() > 0){
            val = "Total accounts: " + bank.size();

            for(Bank items: bank){
                totalBalance += items.getBalance();
            }
            val += "\n Total Balance: " + totalBalance;
            val += "\n Average Balance: " + totalBalance / bank.size();

        }else{
            val = "There is no data. Please add accounts";
        }
        val += "\n ================== ================== ";
        return val;
    }

    // Account Details - find object
    public static Bank findObj(List<Bank> bank, String accountNum){
        Bank bankObj = new Bank();

        if(bank.size() > 0){
            for(Bank items: bank){
                if(items.getAccountNum().equalsIgnoreCase(accountNum)){
                    bankObj = items;
                }
            }
        }
        return bankObj;
    }

    // check Account exist
    public static boolean isExist(List<Bank> bank, String accountNum){
        boolean val = false;

        for(Bank items: bank){
            if(items.getAccountNum().equalsIgnoreCase(accountNum)){
                return true;
            }
        }
        return val;
    }
}
