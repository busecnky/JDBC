package com.january2.observer;

public class Main {

	//Bütün olay observable olan subjwectin observerlar tarafından gözetilmesi
	
	public static void main(String[] args) {
		
		MessageSubscriberOne s1 = new MessageSubscriberOne();
		MessageSubscriberTwo s2 = new MessageSubscriberTwo();
		

		MessagePublisher p = new MessagePublisher();
		p.attach(s1);
		p.attach(s2);
		
		p.notifUpdate(new Message("Sıcaklık 40 C derece")); //s1 ve s2nin bu update i alması gerekiyor
		
		p.detach(s2);
		p.notifUpdate(new Message("Sıcaklık 35 C derece"));
		
		System.out.println("\n\n* * * \n\n");
		
		MessageSubscriberThree s3 = new MessageSubscriberThree();
		p.attach(s3);
		p.notifUpdate(new Message("Sıcaklık 25 C derece"));
		
		System.out.println("\n\n* * * \n\n");
		
		p.attach(s2);
		p.attach(s2); // iki kere attach etmemiz hiç bir sıkıntı olmamalı çünkü onu kontrol ediyoruz
		p.notifUpdate(new Message("Sıcaklık 20 C derece"));
	}

}
