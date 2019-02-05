import java.util.Date;

public class Account{
  private int id;
  private double balance;
  private double annualInterestRate;
  private Date dateCreated;
  
  public Account () {
    this.id = 0;
    this.balance = 0;
	this.annualInterestRate = 5.5;
	this.dateCreated = new Date();
  }
  public Account (int id, double balance){
	  this.id = id;
	  this.balance = balance;
	  this.annualInterestRate = 5.5;
	  this.dateCreated = new Date();
  }
  public int getID() {
	  return this.id;
  }
  public double getBalance () {
	  return this.balance;
  }
  public double getAnnualInterestRate () {
	return this.annualInterestRate;  
  }

  public Date getDateCreated () {
	  return this.dateCreated;
  }
//  public double getMonthlyInterest () {

//  }
  public void setAnnualInterestRate(double rate){
	  this.annualInterestRate = rate;
  }
  public void setID(int id){
	  this.id = id;
  }
  public void setBalance(double ammount){
	  this.balance = ammount;
  }
  public void withdraw (double ammount) {
    this.balance -= ammount;
  }
  public void deposit (double ammount) {
	this.balance += ammount;  
  }
  
  public String toString(){
	  String output = "id, date,balance,monthlyinterest"; 
	  return output;
  }

}