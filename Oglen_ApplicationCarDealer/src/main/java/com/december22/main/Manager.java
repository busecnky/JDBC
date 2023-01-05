package com.december22.main;

import java.util.Scanner;

import com.december22.entity.Car;
import com.december22.repository.CarRepository;
import com.december22.service.CarService;
import com.december22.utility.Constant;
import com.december22.utility.FileUtils;

public class Manager {

	static Scanner scanner = new Scanner(System.in);
	CarRepository carRepository;
	CarService carService;

	public Manager() {
		carRepository = new CarRepository();
		carService= new CarService();
	}

	public void menu() {
		Scanner scanner = new Scanner(System.in);
		int input = 0;

		do {
			System.out.println("0-çıkış");
			System.out.println("1-Dosyadan Databaseye verileri aktarma");
			System.out.println("2-Araba ekle");
			System.out.println("3-Araba güncelle");
			System.out.println("4-Araba Sil");
			System.out.println("5- Arabaları Listele");
			System.out.println("6- İsme göre DealerShipleri getir");

			input = Integer.parseInt(scanner.nextLine());

			switch (input) {
			case 1:
				carService.downloadFileToDataBase();
				break;
			case 2:
				carService.save(getCarInformation());
				break;
			case 3:
				System.out.println("ID: ");
				long updatedId = scanner.nextLong();
				scanner.nextLine();
				Car updatedCar = getCarInformation();
				carRepository.update(updatedCar, updatedId);
				System.out.println("Success new name-->" + updatedCar.getBrand());
			case 4:
				System.out.println("ID: ");
				long deletedId = scanner.nextLong();
				scanner.nextLine();
				carRepository.delete(deletedId);
				System.out.println("Success");

				break;
			case 5:
				carRepository.findAll().forEach(System.out::println);
				break;
			case 6:
				System.out.println("Marka Girin");
				String brand = scanner.nextLine();
				carRepository.findDealerShipByBrand(brand);
				break;

			default:
				break;
			}
		} while (true);
	}

	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.menu();
	}

	public static Car getCarInformation() {
		System.out.println("Lütfen Araba Markasını ismini Giriniz");
		String brand = scanner.nextLine();

		System.out.println("Lütfen Araba Modeli Giriniz");
		String carModel = scanner.nextLine();

		System.out.println("Lütfen Bayi belirtin");
		long dealershipId = Long.parseLong(scanner.nextLine());

		System.out.println("Lütfen Model Yılını Giriniz");
		String modelYear = scanner.nextLine();

		return new Car(brand, carModel, modelYear, dealershipId);
	}
}
