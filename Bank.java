package com;

public class Bank 
{
    //Constructs the bank
	public Bank(int n, double initialBalance)
	{
		accounts = new double[n];
		for(int i = 0; i < accounts.length; i++)
			accounts[i] = initialBalance;
	}
	
	//Transfer money from one account to another
	public void transfer(int from, int to, double amount)
	{
		if(accounts[from] < amount) return;
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
	}
	
	//Gets the sum of all account balances
	public double getTotalBalance()
	{
		double sum = 0;
		
		for(double a : accounts)
			sum += a;
		
		return sum;
	}
	
	//Gets the number of accounts in the bank
	public int size()
	{
		return accounts.length;
	}
	
	private final double[] accounts;
}
