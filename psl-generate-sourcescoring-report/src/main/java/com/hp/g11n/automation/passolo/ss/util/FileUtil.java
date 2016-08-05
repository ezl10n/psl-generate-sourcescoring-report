package com.hp.g11n.automation.passolo.ss.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.g11n.sdl.psl.interop.core.IPassoloApp;
import com.hp.g11n.sdl.psl.interop.core.IPslProject;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;
import com.hp.g11n.sdl.psl.interop.core.impl.impl.PassoloApp;

public class FileUtil{
	private final Logger log = LoggerFactory.getLogger(getClass());
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
				log.info("ERROR:not found the file!");
			}
		} catch (Exception e) {
			log.error("ERROR: can not read file ");
			e.printStackTrace();
		}
		return lst;
	}

	public Map<String, String> readExcelFile(String filePath) {
		boolean isE2007 = false;
		if (filePath.endsWith("xlsx"))
			isE2007 = true;
		try {
			InputStream input = new FileInputStream(filePath);
			Workbook wb = null;
			if (isE2007) {
				wb = new XSSFWorkbook(input);
			} else {
				wb = new HSSFWorkbook(input);
			}

			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			List<Object> lstKey = new ArrayList<Object>();
			List<Object> lstValue = new ArrayList<Object>();
			while (rows.hasNext()) {
				Row row = rows.next();
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cell.getColumnIndex() == 0) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							lstKey.add(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:
							lstKey.add(cell.getStringCellValue());
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							lstKey.add(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							lstKey.add(cell.getCellFormula());
							break;
						default:
							log.debug("unsuported sell type");
							break;
						}
					}
					if (cell.getColumnIndex() == 1) {
						switch (cell.getCellType()) { // 根据cell中的类型来输出数据
						case HSSFCell.CELL_TYPE_NUMERIC:
							lstValue.add(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:
							lstValue.add(cell.getStringCellValue());
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							lstValue.add(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							lstValue.add(cell.getCellFormula());
							break;
						default:
							System.out.println("unsuported sell type");
							break;
						}
					}

				}
			}
			return lstToMap(lstKey, lstValue);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Map<String, String> lstToMap(List<Object> lstKey,
			List<Object> lstValue) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < lstKey.size(); i++) {
			map.put(lstKey.get(i).toString(), lstValue.get(i).toString());
		}
		return map;
	}

	public IPslSourceLists getSourceLists(String resoucePath) {
		IPassoloApp app = PassoloApp.getInstance();
		IPslProject project = app.open(resoucePath);
		return project.getSourceLists();
	}
	
	public static void  main(String[] args){
//		String  FILE_PATCH = "C:\\SourceScoring\\PatternConfig.xlsx";
//		FileUtil fu = new FileUtil();
//		for (Entry<String, String> entry : fu.readExcelFile(FILE_PATCH).entrySet()) {  
//			System.out.println(entry.getKey() +"---------"+entry.getValue());
//		}
		String a1="abbb";
		String a2="Abbb";
		System.out.println(a2.substring(0, 1));
	}
}
