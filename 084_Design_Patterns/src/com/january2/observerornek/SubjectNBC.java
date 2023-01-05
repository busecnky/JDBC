package com.january2.observerornek;

public interface SubjectNBC {
	public void attach(ObserverNBC o);
	public void detach(ObserverNBC o);
	public void notifyUpdate(MessageNBC m);
}
