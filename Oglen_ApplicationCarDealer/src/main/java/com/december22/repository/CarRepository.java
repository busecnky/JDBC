package com.december22.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.december22.entity.Car;
import com.december22.utility.DBConnection;

public class CarRepository<T> implements ICrud<Car> {
	
	DBConnection dbConnection = new DBConnection();
	Connection connection = dbConnection.connect();

	@Override
	public void save(Car t) {
		String sql = "insert into cars(brand, car_model,dealership_id,model_year) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, t.getBrand());
			preparedStatement.setString(2, t.getCarModel());
			preparedStatement.setLong(3, t.getDealerShipId());
			preparedStatement.setString(4, t.getModelYear());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Car t, long id) {
		String sql = "update cars set brand=?, car_model=? ,dealership_id=?,model_year=? where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, t.getBrand());
			preparedStatement.setString(2, t.getCarModel());
			preparedStatement.setLong(3, t.getDealerShipId());
			preparedStatement.setString(4, t.getModelYear());
			preparedStatement.setLong(5, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long id) {
		String sql = "delete from cars where id =" + id;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Car> findAll() {
		String sql = "select * from cars order by model_year desc";
		List<Car> carList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Car car = new Car(resultSet.getString("brand"), resultSet.getString("car_model"),
						resultSet.getString("model_year"), resultSet.getLong("dealership_id"));
				carList.add(car);

				// Heapte --> referans tiplerimiz lllll 357 new Car() .....

				// stack --> primitive int, float llllll 357
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carList;
	}

	@Override
	public void saveAll(List<Car> t) {
		String sql = "insert into cars values(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (Car car : t) {
				preparedStatement.setLong(1, car.getId());
				preparedStatement.setString(2, car.getBrand());
				preparedStatement.setString(3, car.getCarModel());
				preparedStatement.setString(4, car.getModelYear());
				preparedStatement.setLong(5, car.getDealerShipId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void saveAll2(List<Car> t) { String sql =
	 * "insert into cars values(default,?,?,?,?)"; try { PreparedStatement
	 * preparedStatement = connection.prepareStatement(sql); for (Car car : t) {
	 * preparedStatement.setString(1, car.getBrand());
	 * preparedStatement.setString(2, car.getCarModel());
	 * preparedStatement.setString(3, car.getModelYear());
	 * preparedStatement.setLong(4, car.getDealerShipId());
	 * preparedStatement.addBatch(); } preparedStatement.executeBatch();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */
	public void saveAll2(List<Car> t) {
		String sql = "insert into cars(brand, car_model,dealership_id,model_year) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (Car car : t) {
				preparedStatement.setString(1, car.getBrand());
				preparedStatement.setString(2, car.getCarModel());
				preparedStatement.setLong(3, car.getDealerShipId());
				preparedStatement.setString(4, car.getModelYear());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean databaseControl() {
		boolean control = false;
		String sql = "select id from cars";
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			control = resultSet.next(); // result setin içinde değer var ise true döncek
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return control; // --> true döncek ya da false
	}

	public void findDealerShipByBrand(String brand) {
		String sql = "select distinct(dealership_id) from cars where brand = '" + brand + "'";
		List<Integer> dealers = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				dealers.add(resultSet.getInt(1));
			}
			System.out.println(dealers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
