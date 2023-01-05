package com.december27.view;

import java.util.Arrays;
import java.util.List;

import com.december27.entity.Dolap;
import com.december27.entity.Lab;
import com.december27.entity.Okul;
import com.december27.entity.Sinif;
import com.december27.entity.Student;
import com.december27.repository.OkulDao;
import com.december27.repository.SinifDao;
import com.december27.repository.StudentDao;

public class App {
	
	


	private static StudentDao studentDao;
	private static SinifDao sinifDao;
	private static OkulDao okulDao;
	
	
	public App() {
		studentDao = new StudentDao();
		sinifDao = new SinifDao();
		okulDao = new OkulDao();
	}
	
	public void save() {
		Student student = new Student("Erdem", "Çankaya", "erdemcankaya@gmail.com");
		try {
			studentDao.save(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		Student student =  new Student(1, "Aslı", "Çelik", "aslicelik@gmail.com");
		
		try {
			studentDao.update(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getAll() {
		List<Student> list = null;
		
		try {
			list = studentDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Student std : list) {
			System.out.println(std);
		}
		
	}
	
	
	public void getById() {
		Student std = null;
		try {
			std = studentDao.getById(1);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		if (std == null) {
			System.out.println("Kayıt bulunamadı!");
		}
		else
			System.out.println("\n" + std);
	}
	
	
	public void deleteById() {
		try {
			studentDao.deleteById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void nativeSQLQueryOrnek1() {
		List<Student> list = studentDao.nativeSQLQueryOrnek1();
		for (Student s : list) {
			System.out.println(s);
		}
	}

	public void nativeSQLQueryOrnek2() {
		studentDao.nativeSQLQueryOrnek2();
		
	}
	
	public void nativeSQLQueryOrnek3() {
		studentDao.nativeSQLQueryOrnek3();
		
	}
	
	public void nativeSQLQueryOrnek4() {
		studentDao.nativeSQLQueryOrnek4();
		
	}
	
	public void namedQueryFindAllStudent() {
		studentDao.namedQueryFindAllStudents();
		
	}
	
	public void namedQueryFindById() {
		studentDao.namedQueryFindById();
		
	}
	
	
//Sinif ve Lab sınıflarını oluşturduktan sonra yaptığımız örnekler:
	public void sinifLabOrnek() {
		Lab lab = new Lab(2, "B Binası No: 20");
		Sinif sinif = new Sinif(lab, "B Binası Zemin Kat No: 12");
		try {
			sinifDao.save(sinif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Soru: 
	//2. bir sınıf oluşturalım ve bu sınıfın labı 2 nolu lab olsun
	public void sinifLabOrnek2() {
		Lab lab = new Lab(2, "B Binası No: 20");
		Sinif sinif = new Sinif(lab, "A Binası Zemin Kat No: 5");
		try {
			sinifDao.save(sinif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Soru: 
	//3. bir sınıf oluşturalım ve bu sınıfın yeni labı olsun
	public void sinifLabOrnek3() {
		Lab lab = new Lab(3, "C Binası Kat:1 No: 15");
		Sinif sinif = new Sinif(lab, "C Binası Zemin Kat No: 1");
		try {
			sinifDao.save(sinif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
	
		App app = new App();
		
		
		//app.update();
		
		//app.sinifLabOrnek3();
		
		
		Lab lab = new Lab(1, "A Binası 1.kat No:15");
		Dolap dolap1 = new Dolap("A01");
		Dolap dolap2 = new Dolap("A02");
		Dolap dolap3 = new Dolap("A03");
		Okul okul = new Okul("Milli Piyango Anadolu Lisesi");
		Sinif sinif = new Sinif("A Binası Zemin kat no:1", lab, Arrays.asList(dolap1,dolap2,dolap3), okul);
		
		Lab lab2 = new Lab(1, "C Binası 1.kat No:15");
		Dolap dolap4 = new Dolap("C01");
		Dolap dolap5 = new Dolap("C02");
		Sinif sinif2 = new Sinif("C Binası Zemin kat no:11", lab2, Arrays.asList(dolap4,dolap5), okul);
		
		
		try {
			sinifDao.save(sinif);
			sinifDao.save(sinif2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Okul üzerinden okul listesine erişme +  (sınıfa erişme)
		List<Okul> okullist = okulDao.getAll();
		
		for(Okul o : okullist) {
			System.out.println(o.getOkulAdi());
			for(Sinif s : o.getSiniflar()) {
				System.out.println(s.getSinifID() + "  " + s.getLokasyon());
			}		
		}
		
		//Sınıf üzerinden sınıf listesine erişme + (dolaba erişme)
			List<Sinif> siniflist = sinifDao.getAll();
				
			for(Sinif s : siniflist) {
				System.out.println(s.getSinifID() + "  " + s.getLokasyon());
				for(Dolap d : s.getDolaplar()) {
					System.out.println(d.getId() + "  " + d.getDolapno());
				}			
			}
		
		
	}


}
