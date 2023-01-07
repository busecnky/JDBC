package com.january6.Abstractfactory;

public class Garanti implements Bank {
	private String bankName;

	public Garanti() {
		this.bankName = "Garanti";
	}

	@Override
	public String getBankName() {

		return bankName;
	}
}
