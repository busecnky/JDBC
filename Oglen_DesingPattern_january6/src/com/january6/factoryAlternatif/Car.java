package com.january6.factoryAlternatif;

public abstract class Car {

	private CarType model;

	public Car(CarType model) {
		this.model = model;
	}

	protected abstract void insertTyres();

	protected abstract void placeGasTank();

	public void construct() {
		insertTyres();
		placeGasTank();
	}

	public CarType getModel() {
		return model;
	}

	public void setModel(CarType model) {
		this.model = model;
	}

}
