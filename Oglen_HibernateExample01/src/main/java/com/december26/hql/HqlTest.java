package com.december26.hql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;

import com.december26.entity.User;
import com.december26.util.HibernateUtils;

public class HqlTest {

	/*Bir değer gircez String olarak girdiğimiz değer ile başlayan isimleri bulalım
	 * 
	 * User tablosuna post number diye bi column eklicez
	 * sonrada post numberı 10dan büyük olanları getir
	 * 
	 * Postnumber
	 * min
	 * max
	 * ortalama bulalım
	 * 
	 * eticaret --> Tv fiyatları büyükten küçüğe sırala gibi  kullanabiliriz.
	 * 
	 * 
	 * girilen 2 değer arasındaki post numberları göstersin
	 * 
	*/
	
	
	
	public static void findAll() {
		String hql = "select user from User as user";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> users  = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	
	public static void startLike(String s) {
		//F ile başlayanlar
		
		String hql = "select user from User as user where user.name.lastname like '" + s + "%'";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> users  = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	
	public static void startLikes(String value) {
		//F ile başlayanlar
		
		String hql = "select user from User as user where user.name.lastname like : key";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		
		typedQuery.setParameter("key", value + "%");
		
		List<User> users  = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	
	public static void graterThan(int number) {
		String hql = "select user from User as user where user.postNumber >:key";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		
		typedQuery.setParameter("key", number);
		
		List<User> users  = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	
	public static void maxMinAvgSumCountPostNumber() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		
		String minHql = "Select min(user.postNumber) FROM User  user";
        String maxHql = "Select max(user.postNumber) FROM User  user";
        String avgHql = "Select avg(user.postNumber) FROM User  user";
        String sumHql = "Select sum(user.postNumber) FROM User  user";
        String countHql = "Select count(*) FROM User  user";

        Query minQuery = session.createQuery(minHql);
        Query maxQuery = session.createQuery(maxHql);
        Query avgQuery = session.createQuery(avgHql);
        Query sumQuery = session.createQuery(sumHql);
        Query countQuery = session.createQuery(countHql);
        System.out.println("Minimum post number in list : " + minQuery.getSingleResult());
        System.out.println("Maximum post number in list : " + maxQuery.getSingleResult());
        System.out.println("Average post numbers : " + avgQuery.getSingleResult());
        System.out.println("Sum of post numbers : " + sumQuery.getSingleResult());
        System.out.println("Count of post numbers : " + countQuery.getSingleResult());
	
	}
	public static void maxPostNumber() {
		String hql = "select user from User as user where user.postNumber in (select max(user.postNumber) from User as user)";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		
		
		User user  = typedQuery.getSingleResult();
		System.out.println(user);
}
		
	public static void minPostNumber() {
		String hql = "select user from User as user where user.postNumber in (select min(user.postNumber) from User as user)";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		
		
		User user  = typedQuery.getSingleResult();
		System.out.println(user);
	}
	
	public static void avgPostNumber() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String avgHql= "select avg(user.postNumber) from User as user";
		Double avg = session.createQuery(avgHql, Double.class).getSingleResult();
		System.out.println(avg);
	}
	
	public static void sumPostNumber() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String sumHql="select sum(user.postNumber) from User as user";
		long sum = session.createQuery(sumHql, Long.class).getSingleResult();
		System.out.println(sum);
	}
	
	
	public static void between(int number1, int number2) {
		String hql = "select user from User as user where user.postNumber between :key1 and :key2";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		
		typedQuery.setParameter("key1", number1);
		typedQuery.setParameter("key2", number2);
		
		List<User> users  = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	public static void main(String[] args) {
		//findAll();
		//startLike("K");
		//startLikes("Y");
		//graterThan(20);
		//maxMinAvgSumCountPostNumber();
		
//		maxPostNumber();
//		minPostNumber();
//		avgPostNumber();
//		sumPostNumber();
		
		
		between(20, 30);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
