//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBControl {

	/*
	 * Instantiate a DBControl in the GUI to get access to all of its functions
	 * DB Information: Change at your discretion -------------- Benton's Info:
	 * String url = "jdbc:mysql://localhost:3306/157aDB"; String user = "root";
	 * String password = "sheep157a";
	 */
	String url = "jdbc:mysql://localhost:3306/157aDB";
	String user = "root";
	String password = "sheep157a";

	// Instantiate DB Control -- Constructor
	public DBControl() {

	}

	// Connect to the DB
	public void dbConnect(String url, String user, String password) {
		System.out.println("Connecting to the database...");
		Connection conn = null;
		try (Connection cnTest = (Connection) DriverManager.getConnection(url,
				user, password)) {
			conn = (Connection) DriverManager
					.getConnection(url, user, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect to the database!",
					e);
		}
	}

	// Add entry to *VehicleDetails* table only (at the moment...)
	/*
	 * mID = Model ID cID = Color ID The others are self explanatory...
	 */
	public void tableAdd(Connection con, String license, String mID,
			String year, String cID, String mileage, String price)
			throws SQLException {
		String query = "INSERT INTO 157aDB.VehicleDetails (carRegistration, modelID, yearManufactured, colorID, currentMileage,"
				+ " currentVehiclePrice) VALUES ('"
				+ license
				+ "', '"
				+ mID
				+ "', '"
				+ year
				+ "', '"
				+ cID
				+ "', '"
				+ mileage
				+ "', '"
				+ price + "')";
		try {
			Statement stmt = (Statement) con.createStatement();
			System.out.println(query);
			stmt.executeUpdate(query);
			System.out.println("Success!");
		} catch (SQLException e) {
			System.out.println("ERROR! " + e.getMessage());
		}
	}

	// Delete entry to *VehicleDetails* only (at the moment...)
	public void tableDelete(Connection con, String license, String mID,
			String year, String cID, String mileage, String price)
			throws SQLException {
		String query = "DELETE FROM 157aDB.VehicleDetails WHERE carRegistration = '"
				+ license
				+ "' AND modelID = '"
				+ mID
				+ "' AND yearManufactured = '"
				+ year
				+ "' AND colorID = '"
				+ cID
				+ "' AND currentMileage = '"
				+ mileage
				+ "' AND currentVehiclePrice = '" + price + "'";
		try {
			Statement stmt = (Statement) con.createStatement();
			System.out.println(query);
			stmt.executeUpdate(query);
			System.out.println("Success!");
		} catch (SQLException e) {
			System.out.println("ERROR! " + e.getMessage());
		}
	}

	// Update entry to *VehicleDetails* only (at the moment...)
	/*
	 * Provide the attribute you want to change, then provide the plates for the
	 * car. Fill in newValue with the new value you want to replace.
	 */
	public void tableUpdate(Connection con, String attribute,
			String registration, String newValue) {
		String query = "UPDATE 157aDB.VehicleDetails SET " + attribute + " = '"
				+ newValue + "' " + "WHERE carRegistration = '" + registration
				+ "'";
		try {
			Statement stmt = (Statement) con.createStatement();
			System.out.println(query);
			stmt.executeUpdate(query);
			System.out.println("Success!");
		} catch (SQLException e) {
			System.out.println("ERROR! " + e.getMessage());
		}
	}

	/*
	 * Get all of the values in a specific column in a specific table. This is
	 * used to populate the dropdown boxes IMPORTANT: ****************** Return
	 * an array of String instead of void for GUI purposes...
	 * *****************************
	 */
	public String getColumnValues(Connection con, String columnName,
			String table) throws SQLException {
		String query = "SELECT " + columnName + " FROM " + table;
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		ArrayList<String> result = new ArrayList<String>();
		while (rs.next()) {
			result.add(rs.getString(1));
		}
		for (String s : result) {
			System.out.println(s);
		}
		int i = 0;
		return result.get(i);
	}

	/*
	 * Get all of the elements from the dropdown boxes and put them into array
	 * FORMAT: Element @ 0 : Make / 1 : Year / 2 : Color / 3 : Mileage / 4 :
	 * Model
	 */
	public String searchDB(Connection con, String[] options)
			throws SQLException {
		String res = null;
		String query = "SELECT * FROM 157aDB.VehicleDetails WHERE yearManufactured = "
				+ options[1]
				+ " AND currentMileage <= "
				+ options[3]
				+ " AND modelID = (SELECT modelID FROM 157aDB.VehicleModels WHERE modelName = '"
				+ options[4]
				+ "' AND manufacturerID = (SELECT manufacturerID FROM 157aDB.VehicleManufacturers WHERE manufacturerName = '"
				+ options[0]
				+ "'))"
				+ " AND colorID = (SELECT colorID FROM 157aDB.VehicleColors WHERE colorDescription = '"
				+ options[2] + "')";
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			String registration = rs.getString("carRegistration");
			int year = rs.getInt("yearManufactured");
			int price = rs.getInt("currentVehiclePrice");
			res = "License Registration: " + registration + " | Year: " + year
					+ " | Price: $" + price;
		}
		System.out.println(res);
		return res;
	}
}
