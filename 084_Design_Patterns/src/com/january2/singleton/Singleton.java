package com.january2.singleton;

public class Singleton {

	
	private static AlanHesaplayici alanHesaplayici;
	
	public static AlanHesaplayici getAlanHesaplayici(){
		if(alanHesaplayici == null) {
			alanHesaplayici = new AlanHesaplayici();
		}
		return alanHesaplayici;
	}
	
	

	private static CevreHesaplayici cevreHesaplayici;
	
	public static CevreHesaplayici getCevreHesaplayici(){
		if(cevreHesaplayici == null) {
			cevreHesaplayici = new CevreHesaplayici();
		}
		return cevreHesaplayici;
	}
	
	
	
}
