package com.december27.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/*

JPQL , HQL
JPQL : Java Persistance Query Language
HQL: Hibernate Query Language

NativeSQL

NamedQuery →
name = kullanabilmek için ad giriyoruz
query = tabloya yapılacak sorguyu giriyoruz (JPQL veya HQL olabilir)
Avantajı NativeSQL’e göre: Cache’e alır, aynı sorgu veritabanında hiç bir değişiklik olmadan 
gelirse cache den önceki sorgu sonucunu alır ve bize geri döner.
Nadir ve sık değişiklik yapılmayan yüksek maliyetli sorgularda kullanılabilir. 
(Aydan aya güncellenen bir tablo varsa kullanılabilir) 
Ancak her gün güncellenen verilerde kullanılmaz. Yani özel durumlarda kullanılır, her zaman ihtiyaç duyulmaz.
 */


@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),    // JPQL => select * from student
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")   // JPQL

})

@Entity
@Table(name = "student")
public class Student {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Identity dersen id veri tipini postgresqle bırakmak istiyorsak diyoruz
											//(strategy = GenerationType.IDENTITY) yazmazsak hibernate e bırakırız. Kendi numaralar.
	private int id;
	
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;


	
	
	//Entity sınıflarında mutlaka bir tane boş constructor olmalı
		public Student() {
		super();
	}




	public Student(int id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}




	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
	
}
