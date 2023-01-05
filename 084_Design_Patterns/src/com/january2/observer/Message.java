package com.january2.observer;

public class Message {

	final String messageContent;  //Diğer sınıflar mesaj içeriğini değiştiremesin diye final yapıldı.
	
	public Message(String m) {
		this.messageContent = m;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
}
