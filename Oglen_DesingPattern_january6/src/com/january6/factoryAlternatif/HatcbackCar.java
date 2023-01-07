package com.january6.factoryAlternatif;

public class HatcbackCar extends Car{

	public HatcbackCar(CarType model) {
		super(model);
		construct();
	}

	@Override
	protected void insertTyres() {
		System.out.println("Inserted 17' tyres");
		
	}

	@Override
	protected void placeGasTank() {
		System.out.println("40L tank inserted");
		
	}

}
