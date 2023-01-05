package com.january2.observerornek;

public class MessageSubscriberKomutanEkrani implements Observer,ObserverNBC {

	@Override
	public void update(Message m) {
		System.out.println("MessageSubscriberOne : " + m.getMessageContent());
		
	}
	@Override
	public void update(MessageNBC m) {
		System.out.println("Message Subscriber One (komutan ekranı) : "+ m.getMessageContent());
		
	}
}
