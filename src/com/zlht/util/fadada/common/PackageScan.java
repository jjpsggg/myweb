package com.zlht.util.fadada.common;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public abstract class PackageScan {

	public static List<String> getClassName(String packageName) {
		List<String> classNames = new ArrayList<String>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			String resourceName = packageName.replaceAll("\\.", "/");
			URL url = loader.getResource(resourceName);
			assert url != null;
			File urlFile = new File(url.toURI());
			File[] files = urlFile.listFiles();
			assert files != null;
			for (File f : files)
				getClassName(packageName, f, classNames);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return classNames;
	}

	private static void getClassName(String packageName, File packageFile, List<String> list) {
		if (packageFile.isFile()) {
			list.add(packageName + "." + packageFile.getName().replace(".class", ""));
		} else {
			File[] files = packageFile.listFiles();
			String tmPackageName = packageName + "." + packageFile.getName();
			assert files != null;
			for (File f : files) {
				getClassName(tmPackageName, f, list);
			}
		}
	}

	public static void main(String[] args) {
//		List<String> serviceInterfaces = PackageScan.getClassName(BaseDao.class.getPackage().getName()+".impl");
//		for(String clazz : serviceInterfaces){
//			String key = clazz.substring(clazz.lastIndexOf(".")+1,clazz.length());
//			key = key.substring(0, 1).toLowerCase()+key.substring(1,key.length()-4);
//			try {
//				BaseDao dao = (BaseDao) Class.forName(clazz).newInstance();
//				System.out.println(key+"\t"+dao);
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
	}
}