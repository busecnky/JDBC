package com.busecnky.december19;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
		private String url = "jdbc:postgresql://localhost:5432/movie";
        private String username = "postgres";
        private String password = "4628";
        
        
		
    public Connection connect() {
    	Connection conn = null;
    	
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return conn;
    }
        
        
    public int getActorCount() {
    		String sql = "select count(*) from actor";
    		
    		int count =0;
    		
    		try(Connection connection= connect ();
    		PreparedStatement preparedStatement = connection.prepareStatement (sql);){
    		ResultSet resultSet = preparedStatement.executeQuery();
    		resultSet.next();
    		count = resultSet.getInt(1); //ilk kolonu al demek buraya 1 yazınca
    		
    		} catch (SQLException e) {
    		e.printStackTrace();
    		}
    		
    	return count;
    	
    }
    
    public  void getActors() {
    	String sql = "Select * from actor";
    	
    	try(Connection connection= connect ();
        		PreparedStatement preparedStatement = connection.prepareStatement (sql);){
        		ResultSet resultSet = preparedStatement.executeQuery();
        		resultSet.next();
        		displayActors(resultSet);
        		
        		} catch (SQLException e) {
        		e.printStackTrace();
        		}
        		
    }
        
    public static void displayActors(ResultSet resultSet) {
    	try {
			
    		while(resultSet.next()) {
    			System.out.println(resultSet.getString(1)+"\t\t"+resultSet.getString(2)+"\t\t"+resultSet.getString(3));
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    
    
    public int insertActor(Actor actor) {
    	int id = 0;
    	
    		String sql = "insert into actor (first_name, last_name) values (?,?)";
    		try (Connection connection = connect();
    		PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
    		preparedStatement.setString(1, actor.getFirstname()) ;
    		preparedStatement.setString(2,actor.getLastname());
    		int affectedRow = preparedStatement.executeUpdate() ;
    		
    		if (affectedRow > 0) {
    			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
    					if (resultSet.next()) {
    							id = resultSet.getInt(1);
    					}
    			}
    		}
    		System.err.println(actor.getFirstname()+ " Veri tabanına eklendi");
    		} catch (SQLException e) {
    		e.printStackTrace();
    		}
    		return id;
    		
    }
    
	//connection.setAutoCommit(false);  //bu performans arttırmaiçinmiş stackoverflowdan
    public void insertActors(List<Actor> ActorList) {
    	String sql = "insert into actor(first_name, last_name) values (?,?)";
    	
    	try (Connection connection = connect();
        		PreparedStatement preparedStatement = connection.prepareStatement(sql);){
        	
    		int count = 0;
    		for (Actor actor2 : ActorList) {
				preparedStatement.setString(1, actor2.getFirstname());
				preparedStatement.setString(2, actor2.getLastname());
				preparedStatement.addBatch();
				count++;
				//her 100 kayıtta bir execute ya da listenin size ına eşit olduğunda execute
				if(count %100 == 0 || count ==ActorList.size()) {
					preparedStatement.executeBatch();
				
			}

        			
        		}
        		} catch (SQLException e) {
        		e.printStackTrace();
        		}

    }
    
    public boolean updateLastName(int id , String lastname) {
    	String sql = "update actor set last_name = ? where actor_id = ?";
    	int affectedRow = 0;
    	boolean check = false;
    	
    	try (Connection connection = connect();
        		PreparedStatement preparedStatement = connection.prepareStatement(sql);){
        	
    			preparedStatement.setString(1, lastname);
    			preparedStatement.setInt(2, id);
    			affectedRow = preparedStatement.executeUpdate();

    			
    			if(affectedRow>0) {
    				check=true;
    			}
        	
        		
        		} catch (SQLException e) {
        		e.printStackTrace();
        		}
    	return check;
    }
    
    public boolean deleteActor(int id) {
    	boolean check = false;
    	String sql = "delete from actor where actor_id = ?";
    	int affectedRow = 0;
    	
    	
    	try (Connection connection = connect();
        		PreparedStatement preparedStatement = connection.prepareStatement(sql);){
        	
    			preparedStatement.setInt(1, id);
    			affectedRow = preparedStatement.executeUpdate();

    			
    			if(affectedRow>0) {
    				check=true;
    			}
        	
        		
        		} catch (SQLException e) {
        		e.printStackTrace();
        		}
    	return check;
    }
    
    public void deleteActor(Actor lastname, int id) {
    	String sql = "update student set name=?, age=?,city=? where id = ?";
    	
    	try (Connection connection = connect();
        		PreparedStatement preparedStatement = connection.prepareStatement(sql);){
        	
    		int count = 0;
    	
			
				count++;
			
				
			

        	
        		
        		} catch (SQLException e) {
        		e.printStackTrace();
        		}

    }
    
    
	public static void main(String[] args) {
		
		//dvdrental dbsine bağlanalım(movie bendeki), connect methodu oluşturalım
		
		//actor sayısını döndüren sorgu
		
		//getactors metodu yazalım, bu metodun içinde farklı bir metot daha kullancaz
		//displayActors methodu olacak parametre olarak result set alacak, id, f.name, l.name göstercek şekilde
		
		//insertActor methodu tek aktor ekleme
		
		//çoklu ekleme için
		//insertActors methodu yazalım List<Actor> parametre olarak
		//how to insert multiple object in jdbc
		
		
		
		Main main = new Main();
		
		//main.connect();

//		int total = main.getActorCount();
//		System.out.println(total);
		
	
		
		Actor actor1 = new Actor("Veli", "Kayar");
		//main.insertActor(actor1);
			
			
		List<Actor> actorList = new ArrayList<>();
		Actor actor2 = new Actor("Burcu", "Baş");
		Actor actor3 = new Actor("Kerem", "Bakar");
		Actor actor4 = new Actor("Hande", "Sayar");
		Actor actor5 = new Actor("Selim", "Görgün");
		Actor actor6 = new Actor("Fahri", "Zanan");
			
			actorList.add(actor2);
			actorList.add(actor3);
			actorList.add(actor4);
			actorList.add(actor5);
			actorList.add(actor6);
			main.insertActors(actorList);
			
			
			main.getActors();
	}
	
	
}
