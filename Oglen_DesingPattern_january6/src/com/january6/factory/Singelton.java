package com.january6.factory;

public class Singelton {
	
	private static CarFactory carFactory;
	
	public static CarFactory getCarFactory() {
		if(carFactory== null) {
			carFactory = new CarFactory();
			
		}
		return carFactory;
	}
	
	//14:57
}
