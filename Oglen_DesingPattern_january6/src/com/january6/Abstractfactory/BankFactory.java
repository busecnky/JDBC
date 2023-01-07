package com.january6.Abstractfactory;

public class BankFactory extends AbstractFactory{

	@Override
	public Bank getBank(String bank) {
		if(bank== null) {
			return null;
		}
		if(bank.equalsIgnoreCase("Garanti")) {
			return new Garanti();
		}else if(bank.equalsIgnoreCase("HSBC")) {
			return new Garanti();
		}else if(bank.equalsIgnoreCase("Ziraat")) {
			return new Garanti();
		}
		return null;
	}

	@Override
	public Loan getLoan(String loan) {
		return null;
	}
	
}
