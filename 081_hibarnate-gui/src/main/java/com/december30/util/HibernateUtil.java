package com.december30.util;

import java.util.Properties;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.december30.entity.Student;



public class HibernateUtil {

	//Bir projede tek bir factory yetiyorsa ve benim işlerimi hallediyorsa static bir kere oluşturayım bundan sonra hep bunu kullanayım
	//bizim projemiz artık run edip run bitene kadar bi tane session factory nesnesini oluşturup kullancak
	
	//Bu bitane oluşturmaya singleton demişler!
	
	//Static nesneler sürekli yaşıyorlar
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		
		if(sessionFactory == null) { //ilk defa oluşuyorsa buraya gelir
			
			try {
			//Hibernate configuration nesnesi oluşturulur
			Configuration configuration = new Configuration();
			
			//Aşağıdakiler yapcağım configuration nasıl olsun kısmı settings diyoruz oralar ayarlamalar en sonda da setProperties diyerek ayarlıyoruz.
			
			//Hibernate setting nesnesi oluşturulur. (hibernate.cfg.xml)
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "org.postgresql.Driver");
			settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/dbtest");
			settings.put(Environment.USER, "postgres");
			settings.put(Environment.PASS, "4628");
			
			settings.put(Environment.HBM2DDL_AUTO, "update");   //update ve create farkı:
																//create dersek:  her çalıştırdığımızda belirtilen ORM tablosunu siler(içeriğiyle birlikte) ve yeniden oluşturur
																//Test amaçlı çalışmalarda bu şekilde kullanılabilir.
																//update dersek: silmez. Yoksa yeni bir tablo oluşturur. Varsa var olanın üzerinden günceller.
			
			
			settings.put(Environment.SHOW_SQL, "true"); //Hibernate üzerinden VT'ye giden SQL komutlarını görmemize yarar, false yaparsak göremeyiz.
			settings.put(Environment.FORMAT_SQL, "true"); //Hibernate üzerinden VT'ye giden SQL komutlarının bizlerin anlayacağı basitlikte düzenler.
			
			
			configuration.setProperties(settings);
			
			//ORM mapping işlemine tabi tutulacak anotasyon içeren sınıfları buraya yazıyoruz. Kullandığımız hangi class varsa onu buraya alt alta eklememiz lazım.
			
			//ENTITYLERI BELİRLEDİKTEN SONRA BU CLASSI GÜNCELLEYECEĞİZ
			configuration.addAnnotatedClass(Student.class);
		
			
			
			//hibernate den import ediyoruz.
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			//Session factory nesnesi üretiliyor...
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			//Hatayı kendisi yakalamıyor o yüzden biz kendimiz try catch yazmamız gerekiyor. Bağlanamazsa görmemiz lazım.
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return sessionFactory;  //eğer bi kere oluşmuşsa burdan devam edilir.
		
	}
	
	
	
}
