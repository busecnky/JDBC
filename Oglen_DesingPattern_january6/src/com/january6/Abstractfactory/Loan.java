package com.january6.Abstractfactory;

public abstract class Loan {

	protected double rate;

	abstract void getInteresRate(double rate);

	public void calculateLoanPayment(double loanAmount, int years) {

		double EMI;
		int n;

		n = years * 12; // kaç ay boyunca ödicez
		rate = rate / 1200; // yıllık faizi hesapladık
		EMI = ((rate * Math.pow((1 + rate), n)) / ((Math.pow((1 + rate), n)) - 1)) * loanAmount; // bi aylık taksidi

		System.out.println("Your monthly EMI is " + EMI + " for the amount " + loanAmount + " you have borrowed");

	}

}
