package com.january6.factoryAlternatif;

public class JeepCar extends Car {
	public JeepCar() {
		super(CarType.JEEP);
		construct();

	}

	@Override
	protected void insertTyres() {
		System.out.println("Inserted 23' tyres");
	}

	@Override
	protected void placeGasTank() {
		System.out.println("60L tank inserted");
	}
}
