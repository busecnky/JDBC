package com.december22.service;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.december22.entity.Personel;
import com.december22.repository.PersonelRepository;

public class PersonelService {
	
	private PersonelRepository personelRepository;
	
	

	public PersonelService() {
		personelRepository = new PersonelRepository();
	}
	
	public ArrayList<Personel> getAllPersonel() {
		
		return personelRepository.getAllPersonel();
	}

	
	
	public ArrayList<Personel> getPersonelById(String id) {
		
		//id int mi değil mi kontrol et
		
		//APACHE COMMONS LANG
		
		if(StringUtils.isNumeric(id)) {
			
			return personelRepository.getPersonelById(id);
			
		}else {
			return null;
		}
		
		
		
	}

	
	
}
