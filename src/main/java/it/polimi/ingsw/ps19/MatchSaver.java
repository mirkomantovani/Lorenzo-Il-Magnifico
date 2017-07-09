package it.polimi.ingsw.ps19;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MatchSaver {
	
	
	private MatchSaver(){
		
	}
	
	public static void saveMatchOnExistingFile(Match match, int id) throws IOException{
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(("src/main/resources/files/match" + id + ".txt")));
			(outStream).writeObject(match);
			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void saveMatch(Match match, int id) throws IOException{
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/files/match" + id + ".txt")));
			(outStream).writeObject(match);
			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Match readMatch(int id) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("src/main/resources/files/match" + id + ".txt"));
		Match match = (Match) inStream.readObject();
		inStream.close();
		return match;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, MatchFullException{
		Match match = new Match(2,null);
		match.addPlayer(new Player("matteo","red"));
		MatchSaver.saveMatch(match, 0);
		Match match2 = MatchSaver.readMatch(0);
		System.out.println(match2.getPlayers()[0].getName());
			
		
	}

}
