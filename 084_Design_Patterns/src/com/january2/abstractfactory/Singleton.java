package com.january2.abstractfactory;

public class Singleton {
	
	//Ek olarak singletonda deniyoruz. Burasının abstract factory ile alakası yoktur.
	
	private static ShapeFactory shapeFactory;
	private static ColorFactory colorFactory;
	
	public static ShapeFactory getShapeFactory() {
		if(shapeFactory == null) {
			shapeFactory = new ShapeFactory();
		}
		
		return shapeFactory;
	}
	
	public static ColorFactory getColorFactory() {
		if(colorFactory == null) {
			colorFactory = new ColorFactory();
		}
		
		return colorFactory;
	}

}
