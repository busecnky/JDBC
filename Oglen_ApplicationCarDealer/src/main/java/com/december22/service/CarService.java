package com.december22.service;

import com.december22.entity.Car;
import com.december22.main.Manager;
import com.december22.repository.CarRepository;
import com.december22.utility.Constant;
import com.december22.utility.FileUtils;

public class CarService {
	
	CarRepository carRepository;

	public CarService() {
		carRepository = new CarRepository();
	}

	public void downloadFileToDataBase() {
		if (carRepository.databaseControl()) {
			System.out.println("Already Exist");
		} else {
			carRepository.saveAll2(FileUtils.getCarList(FileUtils.readFile(Constant.carFile)));
			System.out.println("Success");
		}
	}

	public void save(Car t) {
		carRepository.save(Manager.getCarInformation());
	}
}
