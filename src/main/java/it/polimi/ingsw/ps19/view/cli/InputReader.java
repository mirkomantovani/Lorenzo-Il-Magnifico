package it.polimi.ingsw.ps19.view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the input reader: it will listen for an input and it will notify all the input listeners
 * as soon as it will receive it. 
 * 
 * @author Jimmy
 *
 */
public class InputReader implements Runnable{

	private BufferedReader reader;
	private ArrayList<InputListener> listeners;
	private String input;

	
	public InputReader(){
		reader = new BufferedReader(new InputStreamReader(System.in));
		listeners = new ArrayList<InputListener>();
	}
	
	@Override
	public void run() {
		while(true){
			try {
				input = reader.readLine();
				System.out.println("InputReader: ho letto input: "+ input);
				notifyListeners(input);	
			} catch (IOException e) {
				System.out.println("Unable to read the input");
				e.printStackTrace();
			}
		}
	}
	
	public void addListener(InputListener inputListener){
		listeners.add(inputListener);
	}
	
	private void notifyListeners(String input){
		for(InputListener listener : listeners)
			listener.notify(input);
	}
}
