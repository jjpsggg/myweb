package com.zlht.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 读取配置文件工具 create in 2012.02.06
 */
public class PropUtil {
	
	/**
	 * logger Log4j日志记录对象
	 */
	private static Logger logger = Logger.getLogger(PropUtil.class);
	
	/**
	 * 获取指定值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String fileName, String key) {
		String resource = "/" + fileName + ".properties";
		Properties prop = new Properties();
		try {
			prop.load(new InputStreamReader(PropUtil.class.getClassLoader().getResourceAsStream(resource), "UTF-8"));
		} catch (Exception e) {
			logger.error("Exception when prop.load(" + resource + ").", e);
		}
		String value = prop.getProperty(key);
		if (value == null) {
			logger.error("prop.getProperty(" + key + ") return is null.");
		}
		return value == null ? "" : value;
	}
	
	/**
	 * 获取配置文件中所有的配置的一个Map对象。
	 * 
	 * @param fileName
	 * @return
	 */
	public static Map<String, String> getConfigMap(String fileName) {
		Map<String, String> entryMap = new HashMap<String, String>();
		String resource = "/" + fileName + ".properties";
		Properties prop = new Properties();
		try {
			prop.load(new InputStreamReader(PropUtil.class.getClassLoader().getResourceAsStream(resource), "UTF-8"));
		} catch (IOException e) {
			logger.error("Exception when prop.load(" + resource + ").");
		}
		Set<String> sets = prop.stringPropertyNames();
		for (final String s : sets) {
			entryMap.put(s, prop.getProperty(s));
		}
		return entryMap;
	}
}
