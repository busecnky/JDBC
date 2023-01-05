package com.january2.observerornek;

public class MessageSubscriberStingerBataryasi implements Observer {

	@Override
	public void update(Message m) {
		System.out.println("MessageSubscriberThree : "+m.getMessageContent());
		
		System.out.println("Network üzerinden bataryaya uyarı gönder");
		
	}


}
