public class CheckingAccount extends Account {
	private int overdrawLimit;
	private int id;
	private double balance;
	
	public CheckingAccount(int id, double balance){
		this.id = id;
		this.balance = balance;
		
	}
	
	public void setOverdrawLimit(int amount){
		overdrawLimit = amount;
	}
	
}