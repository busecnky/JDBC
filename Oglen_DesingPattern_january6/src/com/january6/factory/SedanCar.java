package com.january6.factory;

public class SedanCar implements ICars{

	@Override
	public void placeGasTank() {
		System.out.println("50L tank inserted");
		
	}

	@Override
	public void insertTyres() {
		System.out.println("Inserted 20' tyres");
		
	}

}
