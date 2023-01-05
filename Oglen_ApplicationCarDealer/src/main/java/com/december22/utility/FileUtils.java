package com.december22.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.december22.entity.Car;


public class FileUtils {

	
	
	public static void main(String[] args) {
		
			
		FileUtils file = new FileUtils();
		
		
//		List<Car> list = file.getCarList(file.readFile(Constant.carFile));
//		
//		for (Car car : list) {
//			System.out.println(car);
//		}
		
		List<String> list = file.readFile(Constant.carFile);

		for (String string : list) {
			System.out.println(string);
		}
	}
	
	
	
	public static List<String> readFile(String path) {
		
		List<String> list = new ArrayList<String>();
		
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))){
			
			//scanner.hasNextLine => okunacak satır kaldıysa true olur aksi halde false olur
			//Bunu bir while döngüsü içinde kullanarak tüm satırları okuyabiliriz.
			while (scanner.hasNextLine()){
				String okunanSatir = scanner.nextLine();
				list.add(okunanSatir);
				
			}

			
		} catch (FileNotFoundException e) {
			System.out.println("Dosya bulunamadı");
		}
		
		for (String string : list) {
			System.out.println(string);
		}
		return list;
	}
	
	
	//okuma yazma işlemlerinde iterator ve foreach farkı yoktur.
	public static List<Car> getCarList(List<String> list){
	
		List<Car> carList = new ArrayList<>();
		for(Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] array = string.split(",");
			carList.add(convertToCar(array));
 		}
		
	return carList;
			

	}

	
	public static List<Car> getCarListAlternatif(List<String> list){
		
		List<Car> carList = new ArrayList<>();
		for(String string : list) {
			carList.add(convertToCar(string.split(",")));
 		}
		
	return carList;
			

	}


	public static Car convertToCar(String[]arr) {
	
		Car car = new Car(Long.parseLong(arr[0].trim()), arr[1], arr[2], arr[3], Long.parseLong(arr[4].trim()));
		return car;		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
