package com.katalon.emirates
import org.json.JSONObject

import com.kms.katalon.core.annotation.Keyword

public class Json {

	@Keyword(keywordObject="JSON")
	public static void writeValue(String fileName, String key, String value) {
		JSONObject jsonData = readJsonFromFile(fileName);
		if (jsonData.has(key)) {
			jsonData.put(key, value);
		} else {
			jsonData.put(key, value);
		}

		writeJsonToFile(jsonData, fileName)
	}

	@Keyword(keywordObject="JSON")
	public String readValue(String fileName, String key) {
		JSONObject jsonData = readJsonFromFile(fileName);
		return jsonData.getString(key).toString();
	}


	private static void writeJsonToFile(JSONObject jsonData, String fileName) {
		FileWriter fileWriter = null
		try {
			fileWriter = new FileWriter(fileName)
			fileWriter.write(jsonData.toString())
			println "Data successfully written to $fileName"
		} catch (IOException e) {
			e.printStackTrace()
			println "Error writing to the file: ${e.message}"
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close()
				} catch (IOException e) {
					e.printStackTrace()
				}
			}
		}
	}


	private static JSONObject readJsonFromFile(String fileName) {
		try {
			return new JSONObject(Utils.readFileContent(fileName));
		} catch (Exception e) {
			return new JSONObject();
		}
	}
}

class Utils {
	public static String readFileContent(String fileName) throws IOException {
		try {
			FileReader fileReader = new FileReader(fileName)
			int data;
			StringBuilder jsonString = new StringBuilder();
			while ((data = fileReader.read()) != -1) {
				jsonString.append((char) data);
			}
			return jsonString.toString();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}