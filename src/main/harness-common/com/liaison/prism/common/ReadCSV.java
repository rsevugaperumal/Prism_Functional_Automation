package com.liaison.prism.common;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class ReadCSV {

	public static List<String[]> readData(String filepath) {
		CSVReader reader;
		List<String[]> datas = new ArrayList<String[]>();
		try {
			reader = new CSVReader(new FileReader(filepath), ',');
			final List<String[]> records = reader.readAll();
			final Iterator<String[]> iterator = records.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				final String[] record = iterator.next();
				datas.add(record);
			}
			reader.close();
			return datas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
}
