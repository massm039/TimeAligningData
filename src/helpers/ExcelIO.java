/**
 * @author Jacob Massmann
 * Jul 21, 2015
 */
package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelIO {
	
	public ExcelIO() {
		
	}
	
	public static ArrayList<ArrayList<String>> getData(String file) {
		
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		try {
			FileInputStream fis = new FileInputStream(file);
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
		    XSSFSheet sheet = wb.getSheetAt(0);
		    XSSFRow row;
		    XSSFCell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) {
		            	cols = tmp;
		            }
		        }
		    }

		    for(int r = 0; r < rows; r++) {
		        row = sheet.getRow(r);
		        data.add(new ArrayList<String>());
		        if(row != null) {
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                    data.get(r).add(c, cell.toString());
		                }
		            }
		        }
		    }
		    wb.close();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		
		return data;
	}
	
	public static void writeFile(String file, ArrayList<ArrayList<String>> data) throws IOException {
		
		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		for (int r=0; r<data.size(); r++ ) {
			XSSFRow row = sheet.createRow(r);
			for (int c=0; c<data.get(r).size(); c++) {
				XSSFCell cell = row.createCell(c);
				cell.setCellValue(data.get(r).get(c));
			}
		}

		FileOutputStream fileOut = new FileOutputStream(file);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		wb.close();
	}

}
