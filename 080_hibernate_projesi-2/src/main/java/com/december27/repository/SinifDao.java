package com.december27.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.december27.entity.Dolap;
import com.december27.entity.Okul;
import com.december27.entity.Sinif;
import com.december27.utils.HibernateUtil;

public class SinifDao {

	public void save(Sinif sinif) throws Exception {
		Transaction transaction = null;		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {			
			// start transaction
			transaction = session.beginTransaction();			
			// save  object
			session.save(sinif);			
			// commit transaction
			transaction.commit();					
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("kayıt başarısız!");
		}
		
	}

	public List<Sinif> getAll() {

		Transaction transaction = null;
		List<Sinif> list = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// get Ogretmen objects
			list = session.createQuery("select sinif from Sinif sinif").list();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return list;

	}
	/*
	 * Eğer yukarıdaki gibi yaparsak onetomany veya manytoone dan sonra eğer sadece
	 * mesela sınıfı yazdırsak patlamıyo ama dolabıda yazdırmak istersek session.close yapıyor!
	 * Çünkü try with resources ile kullanıyoruz. Fetch type lar default olduğu zaman LAZY dir.
	 * Lazy olursa yazdırana kadar session.close oluyor.
	 * 
	 * Eğer Fetch Type ı EAGER yaparsak session.close olana kadar yazdırır. Bu sayede sıkıntı yaşamadan
	 * çıktıyı çalıştırabiliriz.
	 * 
	 * for(Sinif s : siniflist) {
				System.out.println(s.getSinifID() + "  " + s.getLokasyon());
				for(Dolap d : s.getDolaplar()) {
					System.out.println(d.getId() + "  " + d.getDolapno());
				}			
			}
	 * 
	 * 
	 * Eğer alttaki gibi yaparsak session.close olmaz ve Fetch type ına ellemememize gerek olmaz.
	 * 
	 */

//	public List<Sinif> getAll() {
//	Session session = HibernateUtil.getSessionFactory().openSession();
//	Query query = session.createQuery("select sinif from Sinif sinif");  //--> select * from sinif"- 
//	return query.list();
//	}

}
