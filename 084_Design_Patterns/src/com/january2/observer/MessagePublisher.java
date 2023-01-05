package com.january2.observer;

import java.util.ArrayList;
import java.util.List;

public class MessagePublisher implements Subject {
	
	private List<Observer> observers = new ArrayList<>();

	@Override
	public void attach(Observer o) {
		if(!observers.contains(o)) {
			observers.add(o);
		}
		
	}

	@Override
	public void detach(Observer o) {
		if(observers.contains(o)) {
			observers.remove(o);
		}
		
	}

	@Override
	public void notifUpdate(Message m) {
		for (Observer o : observers) {
			o.update(m);
		}
	}



}
