package december19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Main {

	    public static void main(String[] args) {

	        // database oluşturalım school,
	        // student tablosu oluşturalım , name surname ve city
	        // mainde oluşturdugumuz databse bağlanalım.

	        // maven , gradle avatnajları dezavantajları,

	        // execute methodu yazalım parametre olarak Connection nesnesi birde sql sorgusu
	        // String şeklinde
	        // içine geçtiğimiz sql sorgusunu çalıştırsın

	 
	        // Student classı oluşturalım dataBase uygun şekilde
	        // create methodu yazalım, Student nesnesi ile
	        // connection nesnesi alsın parametre olarak.

	        // update methodu yazalım , connection, student , id

	    	//dışarıdan girdiğimiz şehir isimden kaç tane verimiz olduğunu bulalım parametre olarak connection,sehirismi

	        String url = "jdbc:postgresql://localhost:5432/school";
	        String username = "postgres";
	        String password = "4628";

	 

	        String sql = " insert into student(name,age,city) values('Makbule Baş',32,'İzmir') ";

	        Connection connect = null;
	        
	 

	        try {
	            Class.forName("org.postgresql.Driver");
	            connect = DriverManager.getConnection(url, username, password);
	            //execute(connect,sql);
	          
	            
//	            Student student  = new Student("Burcu", 20, "Ankara");
//	            create(connect,student);

	 

//	            Student student1 = new Student("Zarife", 20, "Bursa");
//	            update(connect, student1, 5);

	            sehirSayisi(connect, "ankara");
	          

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        } finally {
	            try {
	                connect.close();
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	        System.out.println("Opened database successfully");
	    }

	    
	    private static void execute (Connection conn, String sql) {
	    	
	    	try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.executeUpdate();
				System.out.println("Ekleme Başarılı");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public static void create (Connection conn, Student student) {
	    	String sql = " insert into student(name,age,city ) values (?,?,?)";
	    	
	    	PreparedStatement prp;

				try {
					prp = conn.prepareCall(sql);
					prp.setString(1, student.getName());
					prp.setInt(2, student.getAge());
					prp.setString(3, student.getCity());
					prp.executeUpdate();
					System.out.println(student.getName() + " veri tabanına eklendi.");
					prp.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
	    }
	    
	    
	    


	    private static void update(Connection connection, Student student, int id) {
	        String sql = " update student set name=?, age=?,city=? where id = ? ";
	        //String sql2 = " update student set name=?, age=?,city=? where id = " + id;
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, student.getName());
	            preparedStatement.setInt(2, student.getAge());
	            preparedStatement.setString(3, student.getCity());
	            preparedStatement.setInt(4, id);
	            int rowEffected = preparedStatement.executeUpdate();

	            if(rowEffected>0) {
	                System.out.println("Başarılı güncelleme ");
	            }else {
	                System.out.println("Güncelleme Başarısız idyi kontrol edin");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }

	    public static void sehirSayisi(Connection conn, String sehir) {
	    	String sql = " select count(*) from student where lower(city) = ? ";
	    	try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, sehir.toLowerCase());
				ResultSet rs = preparedStatement.executeQuery();
				rs.next();
				System.out.println(sehir + " Counted " + rs.getInt(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	

}