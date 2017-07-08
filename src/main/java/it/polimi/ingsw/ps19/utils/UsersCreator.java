package it.polimi.ingsw.ps19.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UsersCreator {

	private static BufferedReader buffReader;

	private static String lineRead;

	private UsersCreator() {

	}

	public static ArrayList<User> getUsersFromFile() throws IOException{
		
		ArrayList<User> users=new ArrayList<>();
		
		 String username;
		
		 int pswHash;
		
		 int wonMatches;
		 int lostMatches;
		 int totalMatches;
		 int secondsPlayed;
		 
		 User u;
		
		try {
			buffReader = new BufferedReader(new FileReader("src/main/resources/files/users.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		lineRead = buffReader.readLine();  //line 1  	//The lineRead variable stores the first line of a card and uses it to check the while condition
		while (lineRead!=null) {
			
			username=lineRead;
			
			pswHash=Integer.parseInt(buffReader.readLine());
			wonMatches=Integer.parseInt(buffReader.readLine());
			lostMatches=Integer.parseInt(buffReader.readLine());
			totalMatches=Integer.parseInt(buffReader.readLine());
			secondsPlayed=Integer.parseInt(buffReader.readLine());
			
			u=new User(username,pswHash,wonMatches,lostMatches,totalMatches,secondsPlayed);
			
			users.add(u);
			
			lineRead = buffReader.readLine();
			
		}
		
		buffReader.close();
		
		return users;
		
		
	}

	public static void updateFile(ArrayList<User> users) {
		
	}

}
