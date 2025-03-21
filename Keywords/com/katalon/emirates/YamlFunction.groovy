package com.katalon.emirates

import org.yaml.snakeyaml.Yaml;

import com.kms.katalon.core.annotation.Keyword

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class YamlReader {

	@Keyword
	public static Object readValueFromYaml(String filePath, String keyPath) {
		try {
			FileReader reader = new FileReader(filePath)
			Yaml yaml = new Yaml();
			Map<String, Object> yamlData = yaml.load(reader);
			return getValueFromYaml(yamlData, keyPath);
		} catch (IOException e) {
			e.printStackTrace();
			return "Error reading YAML file";
		}
	}

	private static Object getValueFromYaml(Map<String, Object> yamlData, String keyPath) {
		String[] keys = keyPath.split("\\.");
		Object value = yamlData;

		for (String key : keys) {
			if (value instanceof Map && ((Map<?, ?>) value).containsKey(key)) {
				value = ((Map<?, ?>) value).get(key);
			} else {
				return "Key not found";
			}
		}
		return value;
	}
}
