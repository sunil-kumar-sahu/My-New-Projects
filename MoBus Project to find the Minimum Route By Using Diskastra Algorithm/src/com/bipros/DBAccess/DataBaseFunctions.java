package com.bipros.DBAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DataBaseFunctions {
	static Connection con;
    static final String driver = "com.mysql.cj.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost:3306/BUSTABLE";
    final static String username = "root";
    final static String password = "123456";
	public static void main(String[] args) throws Exception {
//		System.out.println(Arrays.toString(locationAndTime("5 09:20")));
		
		ArrayList<String> fileal = new ArrayList<>();
		
	//Append The text format into the list	
		//fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Rt1.txt");
		//fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Rt2.txt");
		//fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Rt3.txt");
		fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Route1.txt");
		fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Route2.txt");
		fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Route3.txt");
		fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Route4.txt");
		fileal.add("C:\\Users\\USER\\OneDrive\\Desktop\\Eclipse Workspace\\spring\\Office Assignment2\\src\\com.bipros.DBAccessRecords\\Route5.txt");
	
		int i=1;
		//insert the text data into the database
		for(String s: fileal) {
			viewData(s, i++);//i-route
		}
	}
	
	public static  void viewData(String fileLocation  , int route ) throws  Exception{

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }catch (Exception e){}
        BufferedReader br = new BufferedReader(new FileReader(new File(fileLocation)));
        String st ;
        int i=1;

        while((st= br.readLine())!=null){
            if(st.length()!=0){
                i++;
                String[] temp = locationAndTime(st);
                insertDataIntoDB(con , temp[0] , temp[1],route);//insert name in temp[0],and time in temp[1]
            }

        }
	}
	
	public static String[] locationAndTime(String s) {
		String time = s.substring(s.indexOf(":")-2 ).trim();//return the index of : and trim the space of string 
		String name = s.substring(0 , s.indexOf(" "));//return string upto the index of space
		return new String[] {name , time};
		
	}
	//insert data into the database
	public static void insertDataIntoDB(Connection con, String s1 , String s2 , int route){
        try{
        	 PreparedStatement ps = con.prepareStatement("insert into BUSDATA  values (? , ? , ? , null)");
            // PreparedStatement ps = con.prepareStatement("insert into BUSDATA2  values (? , ? , ? , null)");
            ps.setInt(1, Integer.parseInt(s1));
            ps.setString(2, s2);
            ps.setInt(3, route);
            ps.executeUpdate();
            System.out.println("Insertion Sucessful");

        }catch (Exception e){
            System.out.println("could not insert into db  :  " +e);
        }

    }
}
