package com.january2.singleton;

public class UtilityClass {

	public void hesaplamaYap() {
		//AlanHEsaplayici alanHesaplayici;
		for (int i = 0; i < 10000; i++) {
			
			//----> 10000 tane new alan hesaplayicisi nesnesi yaratmamak için bir kere utilty classta oluşturuyoruz
			//sonra bunu burda metodun içinde kullanıyoruz.
			//alanHesaplayici = new AlanHesaplayici();
			int toplamalan = Singleton.getAlanHesaplayici().dikdortgenAlanHesapla(3, 5) + Singleton.getAlanHesaplayici().kareAlanHesapla(3);
			
			
			
			//int toplamDikCevre = (new CevreHesaplayici().dikdortgenCevreHesapla(4, 7);
			//Bu şekilde de yaparsak hata almayız ama kodun kalitesi düşer.
			//İyi kod yazmak için alttaki design pattern ı seçmeliyiz.
			
			int toplamCevre = Singleton.getCevreHesaplayici().dikdortgenCevreHesapla(3, 5) + Singleton.getCevreHesaplayici().kareCevreHesapla(3);

			
		}
	}	
			
	
}
