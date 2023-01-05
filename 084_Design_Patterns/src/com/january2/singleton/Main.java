package com.january2.singleton;

public class Main {

	public static void main(String[] args) {
		

		
		//AlanHesaplayici alanHesaplayici = new AlanHesaplayici();
		int alan = Singleton.getAlanHesaplayici().dikdortgenAlanHesapla(3, 5);
		System.out.println("Alan: " + alan );
		
		
		int cevre = Singleton.getCevreHesaplayici().dikdortgenCevreHesapla(3, 5);
		System.out.println("Çevre: " + cevre );


	}

}
