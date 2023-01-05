package com.december26.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.december26.entity.Address;
import com.december26.entity.Name;
import com.december26.entity.User;
import com.december26.entity.enums.EAddressType;
import com.december26.entity.enums.EGender;
import com.december26.repository.UserDao;
import com.december26.repository.UserRepository;

public class UserController {

	public static void main(String[] args) {
		

		UserDao userDao = new UserDao();
		UserRepository repository = new UserRepository();
		
//		User user = new User("Mehmet", "1234", EGender.MAN);
//		User user1 = new User("Ahmet", "1234", EGender.MAN);
//		User user2 = new User("Tolga", "1234", EGender.MAN);
		
//		userDao.save(user);
//		userDao.save(user1);
//		userDao.save(user2);
		
	//	userDao.findById(1);
		
		//userDao.delete(1);
		
//		User user3 = new User("Ayşe" , "45687", EGender.WOMAN);
		//userDao.update(user3, 6);
		
		
		//userDao.findAll2();
		
	
//		User user4 = repository.findById(5);
//		System.out.println(user4.getUsername());
		
		
		Name name = new Name("Hakan", null , "Kış");
		Name name2 = new Name("Fahri", null , "Yaz");
		Name name3 = new Name("Ezgi", null , "İlkbahar");
//		User user = new User("username", "741852", EGender.MAN, name);
//		User user2 = new User("username111" , "564", EGender.MAN, name2);
//		User user3 = new User("username222" , "7895", EGender.WOMAN, name3);

		
		
		Address address = new Address("Bilkent", "Turkiye", "Ankara");
		Address address2 = new Address("Eryaman", "Turkiye", "Ankara");
		Map<EAddressType, Address> map = new HashMap<>();
		map.put(EAddressType.HOME, address);
		map.put(EAddressType.BUSSINESS, address2);

		
		List<String> interests = new ArrayList<>();
		interests.add("ReadingBook");
		interests.add("Walking");
		
		//user.setAreasOfInterest(interests);
		
		
		//user.setAddress(map);
		
		
//		repository.save(user);
//		repository.save(user2);
//		repository.save(user3);
		


	}

}
