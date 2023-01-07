package com.january6.factoryAlternatif;

public class CarFactory {

	public static Car builCar(CarType model) {
		Car car = null;

		switch (model) {
		case HATCBACK:
			car = new HatcbackCar(model);
			break;
		case SEDAN:
			car = new SedanCar();
			break;
		case JEEP:
			car = new JeepCar();
			break;

		default:
			break;
		}
		return car;
	}
}
