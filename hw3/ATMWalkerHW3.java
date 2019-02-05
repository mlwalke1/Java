public class ATMWalkerHW3 {
  public static void main(String[] args) {
	System.out.println("Testing Account class ...");
    testAccount();

//    System.out.println("Testing ATM ...");
//    testATM();

    System.out.println("Testing extra accounts ...");
    testExtraAccounts();
  }
  public static void testAccount() {
    try {
      Account account1 = new Account(1122,20000);
      account1.setAnnualInterestRate(4.5);
      account1.withdraw(2500);
      account1.deposit(3000);

      Account account2 = new Account(1123,2000);
      account2.setAnnualInterestRate(4.5);
      account2.withdraw(2500);
      account2.deposit(3000);

      System.out.println(account1);
      System.out.println(account2);
    } 
    catch (InsufficientAmount ex) {
    System.out.println("Insufficient fund!!!");
    } 
	catch (Exception ex) {
    System.out.println("Exception thrown");
    }
  }
//  public static void testATM(){
    
//  }
  public static void testExtraAccounts() {
    SavingsAccount account1 = new SavingsAccount(1123,20000);
    account1.setAnnualInterestRate(5.5);
    CheckingAccount account2 = new CheckingAccount(1124,1000);
    account2.setAnnualInterestRate(5.0);
    account2.setOverdrawLimit(500);
    account2.withdraw(1100);		// exception should be thrown here
  	account2.deposit(300);

    System.out.println(account1);
    System.out.println(account2);
    }

}
