package com.zlht.util.fadada.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XMLUtil {

	private static Document userDocument;
	private static String xmlName = "t_yq_p2p_userinfo.xml";
	private static String xmlPath;

	public static void init(String path) {
		xmlPath = path + xmlName;
		SAXReader reader = new SAXReader();
		try {
			File userFile = new File(xmlPath);
			userDocument = reader.read(userFile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}



	@SuppressWarnings("rawtypes")
	public static boolean clearUserInfo() {
		try {
			Element element = userDocument.getRootElement();
			Iterator it = element.elementIterator("user");
			while (it.hasNext()) {
				// Element userElement = (Element)it.next();
				it.next();
				it.remove();
			}
			writeUserXml();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void writeUserXml() {
		XMLWriter writer = null;
		/** 格式化输出,类型IE浏览一样 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		/** 指定XML编码 */
		format.setEncoding("UTF-8");
		try {
			// writer= new XMLWriter(new FileWriter(new File(xmlPath)),format);
			writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlPath), "UTF-8"));
			writer.write(userDocument);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String checkNoNull(String str) {
		if (null == str) {
			str = "";
		}
		return str;
	}
}
