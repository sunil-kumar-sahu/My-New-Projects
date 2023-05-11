package com.bipros.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.bipros.DBAccess.DataBaseAccess;
import com.bipros.functionalities.GraphRepresent;



public class MainFunction {
	public static void main(String[] args) throws Exception {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Time : ");
			String time = sc.nextLine();
			System.out.println("Enter Source : ");
			int src = sc.nextInt();
			System.out.println("Enter Destionation : ");
			int dest = sc.nextInt();
			ArrayList<Integer[]> arr = GraphRepresent.getBusData(src, dest);
			System.out.println();
			DataBaseAccess dba = new DataBaseAccess();	
			
			for(Integer[] al : arr) {
				String ArrivalTime = "";
				System.out.println(Arrays.toString(al));
				for(int i = 0;i<al.length-1;i++) {
					
					ArrivalTime = dba.getMinimumTimeRoute(al[i] , al[i+1] , ArrivalTime)[1];
					if(ArrivalTime=="") {				
						break;
					}
					
				}
				if(ArrivalTime != "") {
					System.out.println("The Final Arrival Time: "+ArrivalTime+" ");
				}else {
					System.out.println("No Bus Found At current Time ");
				}
				while(ArrivalTime==null)//if there is no route is found then it show invalid route
				{
					System.out.println("Invalid Route");
					break;
				}
				
			}
		}
	}
}