package com.january2.abstractfactory;

public abstract class AbstractFactory {

	public static String SHAPE = "SHAPE";
	public static String COLOR = "COLOR";

	
	abstract IColor getColor(String color);   //create olur get olur başka bişi olur ama genelde get diye get seçtik
	
	abstract IShape getShape(String shape);

	
	
	
	
}
