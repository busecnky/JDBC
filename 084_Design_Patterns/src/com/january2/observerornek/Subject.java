package com.january2.observerornek;

public interface Subject {

	void attach(Observer o);

    void detach(Observer o);

    void notifUpdate(Message m);
	
}
