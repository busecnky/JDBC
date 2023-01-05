package com.january2.abstractfactory;

public class FactoryGeneratorSingletonGomulu {
	
	
	public static AbstractFactory getFactory(String factoryType) {
		
		if(factoryType == null || factoryType.isEmpty()) {
			return null;
		}
		
		//Abstract Factory kullanarak yaptığımız kısım
		
//		if(factoryType.equalsIgnoreCase(AbstractFactory.SHAPE)) {
//			return new ShapeFactory();
//		}else if(factoryType.equalsIgnoreCase(AbstractFactory.COLOR)) {
//			return new ColorFactory();
//		}
//		return null;
		
		
		
		//Singleton ile yapılan kısım
		//Mainde de yapabiliriz bunu ama adam 
		if(factoryType.equalsIgnoreCase(AbstractFactory.SHAPE)) {
			return Singleton.getShapeFactory();
		}else if(factoryType.equalsIgnoreCase(AbstractFactory.COLOR)) {
			return Singleton.getColorFactory();
		}
		return null;

	}
}
