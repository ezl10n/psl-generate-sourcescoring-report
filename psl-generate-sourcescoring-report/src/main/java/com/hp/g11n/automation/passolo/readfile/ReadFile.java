package com.hp.g11n.automation.passolo.readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.apache.poi.ss.usermodel.Cell;  
//import org.apache.poi.ss.usermodel.Row;  
//import org.apache.poi.ss.usermodel.Sheet;  
//import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  


public class ReadFile {
	public List<String> readTxtFile(String filePath) {
		List<String> lst = new ArrayList<String>();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					lst.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("ERROR:not found the file!");
			}
		} catch (Exception e) {
			System.out.println("ERROR: can not read file ");
			e.printStackTrace();
		}
		return lst;
	}
//	public static void readExcelFile(String fileName){  
//        boolean isE2007 = false;    //判断是否是excel2007格式  
//        if(fileName.endsWith("xlsx"))  
//            isE2007 = true;  
//        try {  
//            InputStream input = new FileInputStream(fileName);  //建立输入流  
//            Workbook wb  = null;  
//            //根据文件格式(2003或者2007)来初始化  
//            if(isE2007)  
//                wb = new XSSFWorkbook(input);  
//            else  
//                wb = new HSSFWorkbook(input);  
//            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
//            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
//            while (rows.hasNext()) {  
//                Row row = rows.next();  //获得行数据  
//                System.out.println("Row #" + row.getRowNum());  //获得行号从0开始  
//                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
//                while (cells.hasNext()) {  
//                    Cell cell = cells.next();  
//                    System.out.println("Cell #" + cell.getColumnIndex());  
//                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据  
//                    case HSSFCell.CELL_TYPE_NUMERIC:  
//                        System.out.println(cell.getNumericCellValue());  
//                        break;  
//                    case HSSFCell.CELL_TYPE_STRING:  
//                        System.out.println(cell.getStringCellValue());  
//                        break;  
//                    case HSSFCell.CELL_TYPE_BOOLEAN:  
//                        System.out.println(cell.getBooleanCellValue());  
//                        break;  
//                    case HSSFCell.CELL_TYPE_FORMULA:  
//                        System.out.println(cell.getCellFormula());  
//                        break;  
//                    default:  
//                        System.out.println("unsuported sell type");  
//                    break;  
//                    }  
//                }  
//            }  
//        } catch (IOException ex) {  
//            ex.printStackTrace();  
//        }  
//    }  
//	public static void main(String argv[]) {
//		String filePath = "C:\\readFiles\\magrn.txt";
//		readTxtFile(filePath);
//	}

}
