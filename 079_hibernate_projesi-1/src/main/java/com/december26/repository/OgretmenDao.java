package com.december26.repository;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.december26.entities.Ogretmen;
import com.december26.utils.HibernateUtil;

public class OgretmenDao {

	// Dao: Data Access Object

	// Hibernate'de tablo oluşturmak için özel bir komut yok, ilk save(insert)
	// işlemi esnasında tablo otomatik oluşur.
	
	
	public void saveOgretmen(Ogretmen ogrtmn) throws Exception {
		Transaction transaction = null;
		
		try(Session session =  HibernateUtil.getSessionFactory().openSession()){
			
			//start transaction
			transaction = session.beginTransaction();
			
			//save Ogretmen object
			session.save(ogrtmn);  //Tabloyuda oluşturuyor içine kaydıda atıyor
			
			//commit transaction
			transaction.commit();
			
			//close session
			session.close();
			
			
			
			
			
		}catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();  //birden fazla transaction yaptığında bir tanesinde bile
										//hata varsa hop hepsini geri alıyor.
			}
			throw new Exception("Kayıt başarısız!");
		}
		
	}
	
	
	public void update(Ogretmen ogr) throws Exception {
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			// start transaction
			transaction = session.beginTransaction();			
			// update Ogretmen object
			session.update(ogr);			//updatein save den tek farkı save yazması yerine burda update yazması
			// commit transaction
			transaction.commit();					
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}
		
	}



	public List<Ogretmen> getAll() throws Exception {
		Transaction transaction = null;
		List<Ogretmen> list = null;  
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			// start transaction
			transaction = session.beginTransaction();			
			//get Ogretmen objects
			list = session.createQuery("from Ogretmen", Ogretmen.class).list();	//--> list yazıyoruz ki listeye dönüştürsün yukarıda liste tanımladık
			
			// from Ogretmen -> select * from ogretmen
			//list = session.createQuery("from Ogretmen where id > 2", Ogretmen.class).list();			
			// from Ogretmen where id > 2 -> select * from ogretmen where id > 2
			
			
			// commit transaction
			transaction.commit();							
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}
		
		return list;
	}



	public List<Ogretmen> findWithOrder() throws Exception {
		Transaction transaction = null;
		List<Ogretmen> list = null;						
		
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			// start transaction
			transaction = session.beginTransaction();			
			//get Ogretmen objects
			list = session.createQuery("select ogretmen from Ogretmen ogretmen order by soyad").list();	//order by ı ne istiyorsak değiştirelim			
			// commit transaction
			transaction.commit();						
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}
		
		return list;
	}



	public Ogretmen getById(int id) throws Exception {
		Transaction transaction = null;
		Ogretmen ogretmen = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {			
			// start transaction
			transaction = session.beginTransaction();			
			// get Ogretmen object
			ogretmen = session.get(Ogretmen.class, id);			
			// commit transaction
			transaction.commit();									
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}
		
		return ogretmen;
	}


	// id'ye göre kayıt silme
	public void deleteById(int id) throws Exception {
		Transaction transaction = null;
		Ogretmen ogretmen = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {			
			// start transaction
			transaction = session.beginTransaction();			
			// get Ogretmen object
			ogretmen = session.get(Ogretmen.class, id);			
			// delete Ogretmen
			session.delete(ogretmen);
			// commit transaction
			transaction.commit();					
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}			
	}
	
	
	// Nesneye göre kayıt silme
	// Dikkat: Nesneye göre kayıt silerken sadece primary key'i dikkate alır.
	public void delete(Ogretmen ogretmen) throws Exception {
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {			
			// start transaction
			transaction = session.beginTransaction();						
			// delete Ogretmen
			session.delete(ogretmen);
			// commit transaction
			transaction.commit();					
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}			
	}
	
	
	
	
	
	
}
