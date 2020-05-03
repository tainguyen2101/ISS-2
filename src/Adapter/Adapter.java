package Adapter;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Adapter {
	
	public Adapter() {
		
	}

	public void generateData() {
		
	}
	
	private void correctFileIO() {
		Scanner scOutside = null;
		PrintStream output = null;
		File formattedFile = null;
		
			try {
				formattedFile = new File("OutSide.txt"); 
				scOutside = new Scanner(formattedFile);
				output = new PrintStream("Weather Station 5.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(scOutside.hasNextLine()) {
				String line = scOutside.nextLine();
				String[] vals = line.split(",");
			}
	}
}
