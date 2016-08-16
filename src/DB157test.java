import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB157test {
	public static void main(String[] args) throws SQLException {
	
		DBControl test = new DBControl();

		
		
		
		CarRegisterTest welcomeScreen = new CarRegisterTest();
		welcomeScreen.populateMake();
		welcomeScreen.populateYear();
		welcomeScreen.populateColor();
		welcomeScreen.populateModel();
		welcomeScreen.populateMileage();
		
	}

}
