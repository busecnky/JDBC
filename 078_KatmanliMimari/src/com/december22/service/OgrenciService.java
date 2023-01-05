package com.december22.service;

import java.util.ArrayList;

import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.december22.entity.Ogrenci;
import com.december22.repository.OgrenciRepository;

public class OgrenciService {

	private OgrenciRepository ogrenciRepository;

	
	public OgrenciService() {
		ogrenciRepository = new OgrenciRepository();
	}

	
	public ArrayList<Ogrenci> getAllOgrenci() {

		return ogrenciRepository.getAllOgrenci();
	}

	
	public boolean kayitSil(String ogrNo) {
		// id int mi değil mi kontrol et

		// APACHE COMMONS LANG

		if (StringUtils.isNumeric(ogrNo)) {

			 ogrenciRepository.delete(ogrNo);
			 return true;
		} else {
			return false;
		}

	}


	public void kayitEkle(Ogrenci ogrenci) {
		
		 ogrenciRepository.kayitEkle(ogrenci);
				
	}

}
