package com.january2.abstractfactory;

public class Main {
	
	//Abstract Factory desing pattern'da nesneleri oluşturmaktan sorumlu fabrikaları oluşturan bir süper fabrika oluşturulur.
	
	//Factory vs. Abstract Factory
	// Factory Design pattern'da birbiri ile benzer nitelikteki nesneleri üreten bir fabrika sınıfı oluşturuyorduk.
    // Abstract Factory Design Pattern'da ise benzer nitelikteki nesneleri üretebilmek için he rnesne grubu için ayrı
    // bir fabrika sınıfı oluşturmamız gerekmektedir.
	

	public static void main(String[] args) {

		//Önce Factory Generator'ı kullanıp istediğimiz tipte bir factory üretiriz.
		AbstractFactory shapeFactory = FactoryGenerator.getFactory(AbstractFactory.SHAPE);
		//Singleton ı Factory Generator a gömdüğümüz zaman çağırma şeklimiz
		//AbstractFactory shapeFactory = FactoryGeneratorSingletonGomulu.getFactory(AbstractFactory.SHAPE);
		
		//Daha sonra ürettiğimiz bu fabrikayı kullanarak nesne üretebiliriz.
		IShape shape1 = shapeFactory.getShape(IShape.CIRCLE);
		shape1.drawShape();
		
		shapeFactory.getShape(IShape.RECTANGLE).drawShape();
		
		System.out.println("\n\n* * *\n\n");
		
		AbstractFactory colorFactory = FactoryGenerator.getFactory(AbstractFactory.COLOR);
		//Singleton ı Factory Generator a gömdüğümüz zaman çağırma şeklimiz
		//AbstractFactory colorFactory = FactoryGeneratorSingletonGomulu.getFactory(AbstractFactory.COLOR);
		
		//Daha sonra ürettiğimiz bu fabrikayı kullanarak nesne üretebiliriz.
		IColor color1 = colorFactory.getColor(IColor.BLUE);
		color1.fillColor();
		
		colorFactory.getColor(IColor.GREEN).fillColor();
		
	}

}
