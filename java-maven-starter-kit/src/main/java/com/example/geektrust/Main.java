package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream("sample_input\\input1.txt");
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            Map<String, Integer[]> mp = new HashMap<String, Integer[]>();
            Map<String, Integer> cost = new HashMap<String, Integer>(); 
            cost.put("ADULT", 200);
            cost.put("SENIOR_CITIZEN", 100); cost.put("KID", 50);
            Map<String, Integer> collection = new HashMap<String, Integer>();
            collection.put("CENTRAL", 0);collection.put("AIRPORT", 0);
            Map<String, Integer> trips = new HashMap<String, Integer>();
            trips.put("ADULT", 0);trips.put("SENIOR_CITIZEN", 0);trips.put("KID", 0);
            while (sc.hasNextLine()) {
            	//System.out.println(sc.nextLine());
            	String[] line = sc.nextLine().split(" ");
            	if(line[0].equals("BALANCE")) {
            		mp.put(line[1], new Integer[]{Integer.parseInt(line[2]),0});
            	}
            	else if(line[0].equals("CHECK_IN")) {
            		if((mp.get(line[1])[1])%2==0) {
            			if(mp.get(line[1])[0]<cost.get(line[2])) {
            				int excess = cost.get(line[2]) - mp.get(line[1])[0];
            				mp.get(line[1])[0] = 0;
            				collection.put(line[3], collection.get(line[3])+(cost.get(line[2]) + (excess/50)));
            			}
            			else {
            				mp.get(line[1])[0] = mp.get(line[1])[0] - cost.get(line[2]);
            				collection.put(line[3], collection.get(line[3])+(cost.get(line[2])));
            			}
            		}
            		else {
            			if(mp.get(line[1])[0]<(cost.get(line[2])/2)) {
            				int excess = (cost.get(line[2])/2) - mp.get(line[1])[0];
            				mp.get(line[1])[0] = 0;
            				collection.put(line[3], collection.get(line[3])+((cost.get(line[2])/2) + (excess/50)));
            			}
            			else {
            				mp.get(line[1])[0] = mp.get(line[1])[0] - (cost.get(line[2])/2);
            				collection.put(line[3], collection.get(line[3])+(cost.get(line[2])/2));
            			}
            		}
            		mp.get(line[1])[1]++;
            		trips.put(line[2], trips.get(line[2])+1);
            	}
            	else {
            		
            	}
            }
            System.out.println(mp);
            System.out.println(collection);
            System.out.println(trips);
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        
    }
}
