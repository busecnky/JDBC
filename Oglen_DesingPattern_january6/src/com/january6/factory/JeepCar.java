package com.january6.factory;

public class JeepCar implements ICars{
	
	

	@Override
	public void placeGasTank() {
		System.out.println("Inserted 17' tyres");
		
	}

	@Override
	public void insertTyres() {
		System.out.println("40L tank inserted");
		
	}

}
