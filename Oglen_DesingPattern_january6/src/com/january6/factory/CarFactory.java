package com.january6.factory;

public class CarFactory {

	public ICars createCar(ECar carType) {

		switch (carType) {
		case HATCBACK:
			return new HatcbackCar();
		case SEDAN:

			return new SedanCar();
		case JEEP:

			return new JeepCar();

		default:
			break;
		}
		return null;
	}
}
