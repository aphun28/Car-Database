import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CarRegisterTest {
	static Connection con;
	static Statement st;
	static ResultSet rs;
	static String s;
	static DBControl JDBC;

	static JFrame mainFrame;
	static Container pane;
	static JPanel carPanel;
	static JLabel carTitle, makeTitle, yearTitle, colorTitle, mileageTitle,
			modelTitle, resultTitle, addRemoveMakeTitle, addRemoveYearTitle, 
			addRemoveColorTitle, addRemoveMileageTitle, addRemoveModelTitle, addRemoveTitle, searchTitle,
			licenseTitle, priceTitle;
	static JComboBox makeList, yearList, colorList, mileageList, modelList;
	static JTextField makeText, yearText, colorText, mileageText, modelText, licenseText,priceText;
	static JTextField textField;
	static JTextArea textArea;

	static ArrayList<String> makeString = new ArrayList<String>();
	static ArrayList<String> yearString = new ArrayList<String>();
	static ArrayList<String> colorString = new ArrayList<String>();
	static ArrayList<String> mileageString = new ArrayList<String>();
	static ArrayList<String> modelString = new ArrayList<String>();
	static ArrayList<String> optionString = new ArrayList<String>();
	static String[] option = new String[10];
	static JButton searchButton, addButton, removeButton;
	
	static String make = "";
	 static String year = "";
	 static String license = "";
	 static String cID = "";
	 static String mileage = "";
	 static String price = "";

	 static CarRegisterTest mainFile;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CarRegisterTest() {
		String url = "jdbc:mysql://localhost:3306/157aDB";
		String username = "root";
		String password = "sheep157a";
		JDBC = new DBControl();
		// JDBC.dbConnect(url, username, password);
		System.out.println("Connecting database...");

		// Connecting to Database

		try (Connection cnTest = (Connection) DriverManager.getConnection(url,
				username, password)) {
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		textField = new JTextField(100);
		textArea = new JTextArea(200, 200);
		textArea.setEditable(false);

		// Create Frame
		mainFrame = new JFrame();
		mainFrame.setSize(1000, 800);
		pane = mainFrame.getContentPane();
		carPanel = new JPanel(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// List Labels
		makeList = new JComboBox(makeString.toArray());
		yearList = new JComboBox(yearString.toArray());
		colorList = new JComboBox(colorString.toArray());
		mileageList = new JComboBox(mileageString.toArray());
		modelList = new JComboBox(modelString.toArray());

		// Title Labels
		carTitle = new JLabel("Welcome to the Car Database!");
		makeTitle = new JLabel("Make: ");
		yearTitle = new JLabel("Year: ");
		colorTitle = new JLabel("Color: ");
		mileageTitle = new JLabel("Mileage: ");
		modelTitle = new JLabel("Model: ");
		resultTitle = new JLabel("Results: ");
		addRemoveTitle = new JLabel("Fill out to Add and Remove:");
		searchTitle = new JLabel("Fill out to Search:");
		
		//TextBOXES
		makeText = new JTextField();
		yearText = new JTextField();
		colorText = new JTextField();
		mileageText = new JTextField();
		modelText = new JTextField();
		licenseText = new JTextField();
		priceText = new JTextField();
		
		//AddRemove Titles
		addRemoveMakeTitle = new JLabel("Make: ");
		addRemoveYearTitle = new JLabel("Year: ");
		addRemoveColorTitle = new JLabel("Color: ");
		addRemoveMileageTitle = new JLabel("Mileage: ");
		addRemoveModelTitle = new JLabel("Model: ");
		licenseTitle = new JLabel("License: ");
		priceTitle = new JLabel("Price: ");

		// Initialize Buttons
		searchButton = new JButton("Search!");
		addButton = new JButton("Add Car");
		removeButton = new JButton("Remove Car");

		// Adding Titles and etc.
		pane.add(carPanel);
		carPanel.add(textArea);
		carPanel.add(carTitle);
		carPanel.add(makeTitle);
		carPanel.add(yearTitle);
		carPanel.add(colorTitle);
		carPanel.add(mileageTitle);
		carPanel.add(modelTitle);
		carPanel.add(resultTitle);
		carPanel.add(addRemoveTitle);
		carPanel.add(searchTitle);

		// Adding Buttons
		carPanel.add(searchButton);
		carPanel.add(addButton);
		carPanel.add(removeButton);

		// Adding Lists
		// carPanel.add(textArea);
		carPanel.add(makeList);
		carPanel.add(yearList);
		carPanel.add(colorList);
		carPanel.add(mileageList);
		carPanel.add(modelList);
		
		//Adding addRemove Titles
		carPanel.add(addRemoveMakeTitle);
		carPanel.add(addRemoveYearTitle);
		carPanel.add(addRemoveColorTitle);
		carPanel.add(addRemoveMileageTitle);
		carPanel.add(addRemoveModelTitle);
		carPanel.add(priceTitle);
		carPanel.add(licenseTitle);
		
		//Adding addRemove TextBoxes
		carPanel.add(makeText);
		carPanel.add(yearText);
		carPanel.add(colorText);
		carPanel.add(mileageText);
		carPanel.add(modelText);
		carPanel.add(priceText);
		carPanel.add(licenseText);

		// Set Sizing for Titles
		carTitle.setFont(carTitle.getFont().deriveFont(25.0f));
		carTitle.setForeground(Color.BLUE);
		carTitle.setBounds(180, 0, 600, 400);
		makeTitle.setBounds(50, 200, 200, 100);
		yearTitle.setBounds(50, 280, 200, 100);
		colorTitle.setBounds(50, 360, 200, 100);
		mileageTitle.setBounds(50, 440, 200, 100);
		modelTitle.setBounds(50, 510, 200, 100);
		resultTitle.setBounds(50, 570, 200, 100);
		
		
		textArea.setBounds(50, 640, 600, 100);
		addRemoveTitle.setForeground(Color.red);
		addRemoveTitle.setBounds(550, 180, 200, 100);
		searchTitle.setForeground(Color.red);
		searchTitle.setBounds(50, 180, 200, 100);

		// Set Bounds for Buttons
		searchButton.setBounds(300, 250, 150, 100);
		addButton.setBounds(300, 400, 150, 100);
		removeButton.setBounds(300, 500, 150, 100);

		// Set Bounds for Lists
		makeList.setBounds(50, 230, 200, 100);
		yearList.setBounds(50, 310, 200, 100);
		colorList.setBounds(50, 390, 200, 100);
		mileageList.setBounds(50, 470, 200, 100);
		modelList.setBounds(50, 540, 200, 100);
		
		//Set Bounds for Text addRemove
		makeText.setBounds(550, 260, 200, 30);
		yearText.setBounds(550, 340, 200, 30);
		colorText.setBounds(550, 420, 200, 30);
		mileageText.setBounds(550, 500, 200, 30);
		modelText.setBounds(550, 580, 200, 30);
		licenseText.setBounds(780, 260, 200, 30);
		priceText.setBounds(780, 340, 200, 30);
		
		//Set Bounds for Titles of addRemove
		addRemoveMakeTitle.setBounds(550, 200, 200, 100);
		addRemoveYearTitle.setBounds(550, 280, 200, 100);
		addRemoveColorTitle.setBounds(550, 360, 200, 100);
		addRemoveMileageTitle.setBounds(550, 440, 200, 100);
		addRemoveModelTitle.setBounds(550, 510, 200, 100);
		priceTitle.setBounds(780, 200, 200, 100);
		licenseTitle.setBounds(780, 280, 200, 100);
		
		

		// Create ActionListeners

		searchButton.addActionListener(new SearchAction());
		addButton.addActionListener(new AddAction());
		removeButton.addActionListener(new RemoveAction());

		mainFrame.setResizable(false);
		mainFrame.setVisible(true);

		mainFile = this;
	}

	public static void populateMake() {
		int i = 0;
		try {
			
			st = con.createStatement();
			String manu = "select * from VehicleManufacturers";
			rs = st.executeQuery(manu);
			while (rs.next()) {
				String manus = rs.getString("manufacturerName");
			
				makeString.add(manus);
				makeList.addItem(makeString.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void populateYear() {
		int i = 0;
		try {
			st = con.createStatement();
			String year = "select * from VehicleDetails";
			rs = st.executeQuery(year);
			while (rs.next()) {
				String years = rs.getString("yearManufactured");
				yearString.add(years);
				yearList.addItem(yearString.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void populateColor() {
		int i = 0;
		try {
			st = con.createStatement();
			String color = "select * from VehicleColors";
			rs = st.executeQuery(color);
			while (rs.next()) {
				String colors = rs.getString("colorDescription");
				colorString.add(colors);
				colorList.addItem(colorString.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void populateModel() {
		int i = 0;
		try {
			st = con.createStatement();
			String make = "select * from VehicleModels";
			rs = st.executeQuery(make);

			while (rs.next()) {
				String name = rs.getString("modelName");
				modelString.add(name);
				modelList.addItem(modelString.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void populateMileage() {
		int i = 0;
		try {
			st = con.createStatement();
			String mileage = "select * from VehicleDetails";
			rs = st.executeQuery(mileage);
			while (rs.next()) {
				String mileages = rs.getString("currentMileage");
				mileageString.add(mileages);
				mileageList.addItem(mileageString.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void runSearch() throws SQLException {
		option[0] = (String) makeList.getSelectedItem();
		option[1] = (String) yearList.getSelectedItem();
		option[2] = (String) colorList.getSelectedItem();
		option[3] = (String) mileageList.getSelectedItem();
		option[4] = (String) modelList.getSelectedItem();
		String in = JDBC.searchDB(con, option);
		System.out.println(in);
		textArea.setText(in);
	}

	static class SearchThread implements Runnable {
		public void run() {
			try {
				runSearch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Runnable runnable = new SearchThread();
			Thread thread = new Thread(runnable);
			thread.start();
		}

	}

	static class AddRemoveAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//AddRemoveCarFrame addRem = new AddRemoveCarFrame();
			// mainFrame.dispose();
		}

	}
	
	static class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			  make = makeText.getText();
		      year = yearText.getText();
			  license = licenseText.getText();
			  cID = colorText.getText();
			  mileage = mileageText.getText();
			  price = priceText.getText();
			    try {
					JDBC.tableAdd(con, license, make, year, cID, mileage, price);
								
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				CarRegisterTest.populateMake();
				CarRegisterTest.populateYear();
				CarRegisterTest.populateColor();
				CarRegisterTest.populateModel();
				CarRegisterTest.populateMileage();
				mainFrame.validate();
				mainFrame.revalidate();
				mainFrame.repaint();
				pane.validate();
				pane.repaint();
			
			
				yearList.revalidate();
				yearList.validate();
				yearList.repaint();
		}
	
	}
	static class RemoveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	
			// TODO Auto-generated method stub
			  make = makeText.getText();
		      year = yearText.getText();
			  license = licenseText.getText();
			  cID = colorText.getText();
			  mileage = mileageText.getText();
			  price = priceText.getText();
			  try {
				 
					JDBC.tableDelete(con, license, make, year, cID, mileage, price);
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				CarRegisterTest.populateMake();
				CarRegisterTest.populateYear();
				CarRegisterTest.populateColor();
				CarRegisterTest.populateModel();
				CarRegisterTest.populateMileage();
				mainFrame.validate();
				mainFrame.revalidate();
				mainFrame.repaint();
				pane.validate();
				pane.repaint();
			
			
				yearList.revalidate();
				yearList.validate();
				yearList.repaint();
			
		}
	
	}
	
	
}
