package com.bipros.DBAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseAccess {
	static Connection con;
    static final String driver = "com.mysql.cj.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost:3306/BUSTABLE";
    final static String username = "root";
    final static String password = "123456";
    public static void main(String[] args) throws SQLException {
    	DataBaseAccess dba = new DataBaseAccess();
    	System.out.println(Arrays.toString(dba.getMinimumTimeRoute(2, 7	, "9:00" )));

	}
    public void getConnection1() {
    	try {
			con = DriverManager.getConnection(url , username , password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

    
    public String[] getMinimumTimeRoute(int startNode ,int endNode , String sBusTime) throws SQLException {//BusTime-Arrival Time
    	getConnection1();
    	
    	String Query1 = "SELECT * FROM BUSDATA WHERE location =  ? and BusTime >= ? order by BusTime ";
    	//String Query1 = "SELECT * FROM BUSDATA2 WHERE location =  ? and BusTime >= ? order by BusTime ";
    	PreparedStatement ps = con.prepareStatement(Query1);
    	ps.setInt(1, startNode);
    	ps.setString(2, sBusTime);
    	ResultSet rs1 = ps.executeQuery();
    	ArrayList<String[]> arr1 = new ArrayList<>();
    	//add the data from database to the arraylist1
        while(rs1.next()) {
    		arr1.add(new String[] {Integer.toString(rs1.getInt(2)) , rs1.getString(3) , Integer.toString(rs1.getInt(4))} );
    	}
        
        String Query2 = "SELECT * FROM BUSDATA WHERE location = ? and BusTime >= ? order by BusTime"; 
    	//String Query2 = "SELECT * FROM BUSDATA2 WHERE location = ? and BusTime >= ? order by BusTime";
    	PreparedStatement ps2= con.prepareStatement(Query2);
    	ps2.setInt(1, endNode);
    	ps2.setString(2, sBusTime);
    	ResultSet rs2 = ps2.executeQuery();
    	
        ArrayList<String[]> arr2 = new ArrayList<>();
        //add the data from database to the arraylist2
        while(rs2.next()) {
    		arr2.add(new String[] {Integer.toString(rs2.getInt(2)) , rs2.getString(3) , Integer.toString(rs2.getInt(4)) } );
    	}
        
        String RequiredTime[] = new String[3];
        //st1-source 
        //st2-destination
      //it check the route  
        for(String[] st1:arr1) {
        	for(String[] st2: arr2) {
        		RequiredTime = st2;
        		if(st1[2].equals(st2[2])) {
        			System.out.print(st2[2]+"   ");
        			return st2;// if the bus no is same then it will return all details of 2nd bus
        		}
        	}
        	
        }

        System.out.print("No_Path  ");
    	return RequiredTime;
    	
    }

}

