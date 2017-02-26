/***********************************************************************   
*   
*   RandomUtil.java   
*   
*   @copyright     福建邮科通信 
*   @creator       kangzhishan
*   @email         kangzhisan@126.com    
*   @create-time   	 2009-8-10   下午04:08:36   
*   @revision        $Id:        
***********************************************************************/
package com.zlht.util.fadada.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomUtil {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";

    /**随机字符串，包括数字
     * @param length
     * @return
     */
    public static String generateString(int length) //参数为返回随机数的长度
    {
	     StringBuffer sb = new StringBuffer();
	     Random random = new Random();
	     for (int i = 0; i < length; i++)
	     {
	      sb.append(allChar.charAt(random.nextInt(allChar.length())));
	     }
	    return sb.toString();
    }
    
    /**随机字符串
     * @param length
     * @return
     */
    public static String generateCharacterString(int length) //参数为返回随机数的长度
    {
	     StringBuffer sb = new StringBuffer();
	     Random random = new Random();
	     for (int i = 0; i < length; i++)
	     {
	    	 char a=letterChar.charAt(random.nextInt(letterChar.length()));
	    	 sb.append(a);
	     }
	    return sb.toString();
    }
    
	private static Map<Integer, Integer[]> baseInt(int len) {
		Map<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
		if (len==1)	map.put(len, new Integer[]{1,10});
		if (len==2)	map.put(len, new Integer[]{10,99});
		if (len==3)	map.put(len, new Integer[]{100,999});
		if (len==4)	map.put(len, new Integer[]{1000,9999});
		if (len==5)	map.put(len, new Integer[]{10000,99999});
		if (len==6)	map.put(len, new Integer[]{100000,999999});
		if (len==7)	map.put(len, new Integer[]{1000000,9999999});
		if (len==8)	map.put(len, new Integer[]{10000000,99999999});
		if (len==9)	map.put(len, new Integer[]{100000000,999999999});
		return map;
	}
	
	private static Map<Integer, Long[]> baseLong(int len) {
		Map<Integer, Long[]> map = new HashMap<Integer, Long[]>();
//		if (len==10)	map.put(len, new Long[]{10000000000L,99999999999L});
//		if (len==11)	map.put(len, new Long[]{10000000000L,99999999999L});
//		if (len==12)	map.put(len, new Long[]{100000000000L,999999999999L});
		String mixLen = "1";
		String maxLen = "9";
		for(int i=0; i<len-1; i++){
			mixLen += "0";
			maxLen += "9";
		}
		map.put(len, new Long[]{Long.parseLong(mixLen),Long.parseLong(maxLen)});
		return map;
	}
	
	public static Integer randomInt(int upLimit, int downLimit) {
		return (int)(Math.random()*(upLimit-downLimit))+downLimit;
	}
	
	@SuppressWarnings("rawtypes")
	public static Integer randomInt(int len) {
		Map map = RandomUtil.baseInt(len);
		Integer[] baseInt = (Integer[]) map.get(len);
		return RandomUtil.randomInt(baseInt[0], baseInt[1]);
	}
	
	private static long randomLong(long min, long max) {
		return Math.round(Math.random()*(max-min)+min);
	}
	
	@SuppressWarnings("rawtypes")
	public static Long randomLong(int len) {
		Map map = RandomUtil.baseLong(len);
		Long[] baseLong = (Long[]) map.get(len);
		long temp = randomLong(baseLong[0], baseLong[1]);
		return temp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1000; i++) {
			System.out.println(randomLong(12).toString());
		}
	}

}
