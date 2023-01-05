import com.december30.util.HibernateUtils;
import com.december30.util.LibrarySystemMenu;

public class LibraryApp {

	
	public static void main(String[] args) {
	

		//HibernateUtils.getSessionFactory().openSession();

		
		LibrarySystemMenu menu = new LibrarySystemMenu();
		while(true) {
			menu.menu();
		}
		
		
		
	}

}
