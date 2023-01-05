package com.january2.observerornek;

public class Message {

	final String messageContent ; // Rigen sanaflan mesai iserigini degistiremesin diye böyle yapıldı.
	final String havaAraciTipi;
	final int havaAraciHizi;
	final int aracinYonu;
	final int aciliyet; // 1:Çok acil 2: Acil 3: Normal
	
	public Message (String m, String tip, int hiz, int yon, int aciliyet) {
	this.messageContent = m;
	this.havaAraciHizi = hiz;
	this.havaAraciTipi = tip;
	this.aracinYonu = yon;
	this.aciliyet = aciliyet;
	}
	
	public String getMessageContent() {
		return "Message [messageContent=" + messageContent + ", havaAraciTipi=" + havaAraciTipi + ", havaAraciHizi="
				+ havaAraciHizi + ", aracinYonu=" + aracinYonu + ", aciliyet=" + aciliyet + "]";
	}

	public String getHavaAraciTipi() {
		return havaAraciTipi;
	}

	public int getHavaAraciHizi() {
		return havaAraciHizi;
	}

	public int getAracinYonu() {
		return aracinYonu;
	}

	public int getAciliyet() {
		return aciliyet;
	}
	
	
	
}
