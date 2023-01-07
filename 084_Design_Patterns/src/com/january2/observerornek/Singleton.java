package com.january2.observerornek;


public class Singleton {

	
	private static MessageSubscriberHaberlesmeSaglayici messageSubscriberHaberlesmeSaglayici;
	
	public static MessageSubscriberHaberlesmeSaglayici getMessageSubscriberHaberlesmeSaglayici(){
		if(messageSubscriberHaberlesmeSaglayici == null) {
			messageSubscriberHaberlesmeSaglayici = new MessageSubscriberHaberlesmeSaglayici();
		}
		return messageSubscriberHaberlesmeSaglayici;
	}
	
}
