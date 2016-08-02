package com.hp.g11n.automation.passolo.readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

//	public static void main(String argv[]) {
//		String filePath = "C:\\readFiles\\magrn.txt";
//		readTxtFile(filePath);
//	}

}
