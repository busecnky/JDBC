package com.january6.Abstractfactory;

public class Ziraat implements Bank {

	private String bankName;

	public Ziraat() {
		this.bankName = "Ziraat";
	}

	@Override
	public String getBankName() {

		return bankName;
	}

}
