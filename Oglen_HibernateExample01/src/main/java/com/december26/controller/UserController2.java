package com.december26.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.december26.entity.Address;
import com.december26.entity.Name;
import com.december26.entity.User;
import com.december26.entity.UserDetail;
import com.december26.entity.enums.EAddressType;
import com.december26.entity.enums.EGender;
import com.december26.repository.UserDao;
import com.december26.repository.UserRepository;

public class UserController2 {

	
	
	public static void main(String[] args) {
		UserRepository repository = new UserRepository();
		
		Name name = new Name("Hakan", null , "Kış");
		Name name2 = new Name("Fahri", null , "Yaz");
		Name name3 = new Name("Ezgi", null , "İlkbahar");
	
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
		
		UserDetail userDetail = new UserDetail(EGender.WOMAN, name3, map, interests, 5);
		User user3 = new User("cvbc", "123456", userDetail);
		
		repository.save(user3);
		
	}

}
