package it.polimi.ingsw.ps19.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * This class represents a User logged into the game
 * @author Mirko
 *
 */
public class User {
	
	private String username;
	
	private int pswHash;
	
	private int wonMatches;
	private int lostMatches;
	private int totalMatches;
	private int secondsPlayed;
	

	public User(String username, int pswHash, int wonMatches, int lostMatches, int totalMatches, int secondsPlayed) {
		this.username = username;
		this.pswHash = pswHash;
		this.wonMatches = wonMatches;
		this.lostMatches = lostMatches;
		this.totalMatches = totalMatches;
		this.secondsPlayed = secondsPlayed;
	}

	public User(String username, String password) {
		this(username,password.hashCode(),0,0,0,0);
	}

	public static void main(String[] args) throws IOException {
//		String s="";
//		int hash=s.hashCode();
//		System.out.println(hash);
		ArrayList<User> users = null;
		
		
		try {
			users=UsersCreator.getUsersFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		users.forEach(u->System.out.println(u));
		
//		users.get(0).incrementMatches();
//		
//		UsersCreator.updateFile(users);
		
		
	
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", \npswHash=" + pswHash + ", \n"
				+ "wonMatches=" + wonMatches + ", \nlostMatches="
				+ lostMatches + ", \ntotalMatches=" + totalMatches + ", \n"
						+ "secondsPlayed=" + secondsPlayed + "]";
	}
	
	public boolean correctPassword(String psw){
		return this.pswHash==psw.hashCode();
	}

	public String getUsername() {
		return username;
	}

	public int getPswHash() {
		return pswHash;
	}

	public int getWonMatches() {
		return wonMatches;
	}

	public int getLostMatches() {
		return lostMatches;
	}

	public int getTotalMatches() {
		return totalMatches;
	}

	public int getSecondsPlayed() {
		return secondsPlayed;
	}

	public void incrementMatches() {
		this.totalMatches++;
		
	}
	
	
	
	

}
