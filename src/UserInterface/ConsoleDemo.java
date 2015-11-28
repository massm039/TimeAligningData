/**
 * @author Jacob Massmann
 * Jul 21, 2015
 */
package UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import helpers.ExcelIO;
import helpers.DataFunc;

public class ConsoleDemo {
	
	public ConsoleDemo() {
		
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("File Name: ");
		String file = s.nextLine();
		ArrayList<ArrayList<String>> data = ExcelIO.getData(file);
		
		String output = "Data Types: ";
		for (String i : data.get(0)) {
			output += i+", ";
		}
		output = output.substring(0, output.length()-2);
		
		System.out.println(output);
		System.out.print("Input Two of the Above Data Types With a Known Correlation: ");
		String types = s.nextLine();
		
		int list1 = -1, list2 = -1;
		for (int i=0; i<data.get(0).size(); i++) {
			if (types.contains(data.get(0).get(i))) {
				if (list1 == -1) {
					list1 = i;
					//System.out.println(list1 + ": " + data.get(0)[i]);
				}
				else {
					list2 = i;
					//System.out.println(list2 + ": " + data.get(0)[i]);
				}
			}
		}
		
		ArrayList<Double> dataList1 = DataFunc.getDataList(data, list1);
		ArrayList<Double> dataList2 = DataFunc.getDataList(data, list2);
		System.out.println(DataFunc.timeAlign(dataList1, dataList2));
		
		ArrayList<ArrayList<String>> newData = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<dataList1.size(); i++) {
			newData.add(new ArrayList<String>());
			for (int j=0; j<data.get(i).size(); j++) {
				if (i == 0) {
					newData.get(i).add(data.get(i).get(j));
				}
				else if (j == list1) {
					newData.get(i).add(dataList1.get(i-1).toString());
				}
				else if (j == list2) {
					newData.get(i).add(dataList2.get(i-1).toString());
				}
				else if (data.get(i).size() > j) {
					newData.get(i).add(data.get(i).get(j));
				}
			}
		}
		
		try {
			ExcelIO.writeFile("new" + file, newData);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		s.close();
	}
}
