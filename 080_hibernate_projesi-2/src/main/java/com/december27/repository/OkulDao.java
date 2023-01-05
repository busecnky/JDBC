package com.december27.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.december27.entity.Okul;
import com.december27.entity.Sinif;
import com.december27.utils.HibernateUtil;

public class OkulDao {

	public List<Okul> getAll() {

		Transaction transaction = null;
		List<Okul> list = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			// get Ogretmen objects
			list = session.createQuery("select okul from Okul okul").list();
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
	 * mesela okulu yazdırsak patlamıyo ama sınıfıda yazdırmak istersek session.close yapıyor!
	 * Çünkü try with resources ile kullanıyoruz. Fetch type lar default olduğu zaman LAZY dir.
	 * Lazy olursa yazdırana kadar session.close oluyor.
	 * 
	 * Eğer Fetch Type ı EAGER yaparsak session.close olana kadar yazdırır. Bu sayede sıkıntı yaşamadan
	 * çıktıyı çalıştırabiliriz.
	 * 
	 * for(Okul o : okullist) { System.out.println(o.getOkulAdi()); for(Sinif s :
	 * o.getSiniflar()) { System.out.println(s.getSinifID() + "  " +
	 * s.getLokasyon()); }
	 * 
	 * }
	 * 
	 * 
	 * Eğer alttaki gibi yaparsak session.close olmaz ve Fetch type ına ellemememize gerek olmaz.
	 * 
	 */

//	public List<Okul> getAll() {
//	Session session = HibernateUtil.getSessionFactory().openSession();
//	Query query = session.createQuery("select okul from Okul okul");  //--> select * from okul
//	return query.list();
//	}

}
