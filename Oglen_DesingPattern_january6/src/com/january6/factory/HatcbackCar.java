package com.january6.factory;

public class HatcbackCar implements ICars{

	@Override
	public void placeGasTank() {
		System.out.println("60L tank inserted");
		
	}

	@Override
	public void insertTyres() {
		System.out.println("Inserted 23' tyres");
		
	}

}
