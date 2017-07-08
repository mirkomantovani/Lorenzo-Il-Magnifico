package it.polimi.ingsw.ps19.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * This class handles the I/O for the users.txt file
 * 
 * @author Mirko
 *
 */
public class UsersCreator {

	private static BufferedReader buffReader;
	private static BufferedWriter buffWriter;

	private static String lineRead;

	private UsersCreator() {

	}

	public static ArrayList<User> getUsersFromFile() throws IOException {

		ArrayList<User> users = new ArrayList<>();

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

		lineRead = buffReader.readLine(); 
											
		while (lineRead != null) {

			username = lineRead;

			pswHash = Integer.parseInt(buffReader.readLine());
			wonMatches = Integer.parseInt(buffReader.readLine());
			lostMatches = Integer.parseInt(buffReader.readLine());
			totalMatches = Integer.parseInt(buffReader.readLine());
			secondsPlayed = Integer.parseInt(buffReader.readLine());

			u = new User(username, pswHash, wonMatches, lostMatches, totalMatches, secondsPlayed);

			users.add(u);

			lineRead = buffReader.readLine();

		}

		buffReader.close();

		return users;

	}

	public static void updateFile(ArrayList<User> users) {

		
		File fout = new File("src/main/resources/files/users.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));


//		lambda reference expression to write user fields into the file
		final Consumer<User> writeFields = us -> {
			try {
				bw.write(us.getUsername());
				bw.newLine();
				bw.write(""+us.getPswHash());
				bw.newLine();
				bw.write(""+us.getWonMatches());
				bw.newLine();
				bw.write(""+us.getLostMatches());
				bw.newLine();
				bw.write(""+us.getTotalMatches());
				bw.newLine();
				bw.write(""+us.getSecondsPlayed());
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		
		

		
		//using the lambda expression on every object of the list
		users.forEach(writeFields);
//		User us=users.get(0);
		
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
