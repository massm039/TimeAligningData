/**
 * @author Jacob Massmann
 * Jul 5, 2015
 */
package helpers;

import java.util.ArrayList;

public class DataFunc {
	
	public static double timeAlign(ArrayList<Double> input, ArrayList<Double> output) { //aligns two datasets by removing off the top of the first
		ArrayList<Double> removable = new ArrayList<Double>();
		int minSize = input.size() / 3;
		for (double i : input) {
			removable.add(i);
		}
		double maxCorrelation = 0;
		int maxCorrIndex = 0, index = 0;
		while (removable.size() > minSize) {
			double correlation = correlationCoefficient(removable, output);
			if (correlation > maxCorrelation) {
				maxCorrelation = correlation;
				maxCorrIndex = index;
			}
			index++;
			removable.remove(0);
		}
		//Max Correlation Coefficient is found
		for (int i=0; i<maxCorrIndex; i++) { //data is removed from the top of the input arraylist until the lists are aligned
			input.remove(0);
		}
		return maxCorrelation; //returns the correlation between the two datasets when aligned
	}
	
	public static double correlationCoefficient(ArrayList<Double> input, ArrayList<Double> output) {//returns the correlation coefficient of a sample
		int n = input.size();
		double sumXY = 0, sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0;
		for (int i=0; i<input.size(); i++) {
			sumX += input.get(i);
			sumX2 += input.get(i)*input.get(i);
			sumY += output.get(i);
			sumY2 += output.get(i)*output.get(i);
			sumXY += input.get(i)*output.get(i);
		}
		double corrCo = (n*sumXY - sumX*sumY) / (Math.sqrt( (n*sumX2 - sumX*sumX) * (n*sumY2 - sumY*sumY) ) );
		return corrCo;
	}
	
	public static double cov(ArrayList<Double> xData, ArrayList<Double>yData) { //returns the covariance of two data sets (UNCHECKED)
		double cov = 0, avgX = avg(xData), avgY = avg(yData);
		for (int i=0; i<xData.size(); i++) {
			cov += (xData.get(i)-avgX) * (yData.get(i)-avgY);
		}
		cov = cov/(xData.size());
		return cov;
	}
	
	public static double standardDev(ArrayList<Double> data) { //returns the standard deviation of a data set
		double s = 0;
		double avg = avg(data);
		for (int i=0; i<data.size(); i++) {
			s += Math.pow(data.get(i)-avg, 2);
		}
		s = s/data.size();
		return Math.sqrt(s);
	}
	
	public static double avg(ArrayList<Double> data) { //returns the average value in a list of data
		double avg = 0;
		for (int i=0; i<data.size(); i++) {
			avg += data.get(i);
		}
		avg = avg/data.size();
		return avg;
	}
	
	public static ArrayList<Double> getDataList(ArrayList<ArrayList<String>> data, int index) { //returns the column of data at the given index
		ArrayList<Double> dataList = new ArrayList<Double>();
		for (int i=1; i<data.size(); i++) {
			String x = data.get(i).get(index);
			dataList.add(Double.parseDouble(x));
		}
		return dataList;
	}
	
}