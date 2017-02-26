/**
* 版权所有：深圳法大大网络科技有限公司
* Copyright 2015 fadada.com Inc.
* All right reserved. 
*====================================================
* 文件名称: TestFddClient.java
* 修订记录：
* No    日期				作者(操作:具体内容)
* 1.    Dec 18, 2015			Mocuishle(创建:创建文件)
*====================================================
* 类描述：(说明未实现或其它不应生成javadoc的内容)
* 
*/
package com.zlht.util.fadada;

/**
 * <h3>概要:</h3> 
 *    FddClient的测试类
 * <br>
 * <h3>功能:</h3>
 * <ol>
 * 		<li>提供对FddClient方法的调用实例</li>
 * </ol>
 * <h3>履历:</h3>
 * <ol>
 * 		<li>2015年12月30日[zhouxw] 新建</li>
 * </ol>
 */
public class TestFddClient {

	/**
	 * <b>概要：</b>TODO 单元测试
	 * 按照“个人CA注册接口测试”的例子可自行添加测试代码，添加后运行main方法即可
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年11月9日 </br>
	 */
	public static void main(String[] args) {
		String response = "Welcome to www.fadada.com!";
		// 个人CA注册接口测试
		response = FddClientBase.invokeSyncPersonAuto("测试", "test@fdd.com", "XXX", "0", "18988889999");
		
		
		System.out.println(response);
	}

	
}
