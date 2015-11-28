/**
 * @author Jacob Massmann
 * Jul 5, 2015
 */
package helpers;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileIO {
	
	public static ArrayList<String[]> getData(String fileName) {
		ArrayList<String[]> data = new ArrayList<String[]>();
		try {
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				String[] dataLine = line.split("  ");
				data.add(dataLine);
				line = br.readLine();
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
