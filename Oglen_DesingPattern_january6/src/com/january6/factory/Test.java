package com.january6.factory;

public class Test {
	/*
	 * 
	 * singelton, factory, absFactory, builder, adapter
	 * 
	 * 
	 * Araba sınfıı oluşturcaz
	 * subClasları harcback, sedan, jepp
	 * 
	 * placeGasTank bit method yapcaz 
	 * insertTyres methodu 
	 * 
	 * FactoryPattern ile 3 arabadan 1 er tane yarat, 
	 * Hepsinin depsounu ve tyreslerini bilgilerini yazdıralım
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		CarFactory carFactory = Singelton.getCarFactory();
		carFactory.createCar(ECar.HATCBACK).placeGasTank();
		carFactory.createCar(ECar.JEEP).placeGasTank();
		carFactory.createCar(ECar.SEDAN).placeGasTank();
	}
}
