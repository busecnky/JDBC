package com.january6.Abstractfactory;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the name of Bank from where you want to take loan amount: ");
		String bankName = scanner.nextLine();

		System.out.println("Enter the type of loan : ");
		String loanName = scanner.nextLine();

		AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
		Bank b = bankFactory.getBank(bankName);

		System.out.println("Enter the interest rate for :" + b.getBankName() + " : ");
		double rate = Double.parseDouble(scanner.nextLine());

		System.out.println("Enter the loan amount you want to take: ");
		double loanAmount = Double.parseDouble(scanner.nextLine());

		System.out.println("Enter the number of years to pay your loan amount: ");
		int years = Integer.parseInt(scanner.nextLine());

		System.out.println("You are taking the loan from " + b.getBankName());

		AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");
		Loan loan = loanFactory.getLoan(loanName);
		loan.getInteresRate(rate);
		loan.calculateLoanPayment(loanAmount, years);
	}

}
