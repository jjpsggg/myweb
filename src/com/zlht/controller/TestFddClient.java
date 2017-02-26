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
package com.zlht.controller;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.zlht.util.fadada.FddClientBase;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


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
	public static void main(String[] args) throws SQLException {
		String response = "Welcome to www.fadada.com!";
		// 个人CA注册接口测试
//		response = FddClientBase.invokeSyncPersonAuto("测试", "test@fdd.com", "XXX", "0", "18988889999");
		Connection m_Connection = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost/fhadmin?user=root&password=123123");

//		2. 下面就是获取表的信息。
		DatabaseMetaData m_DBMetaData = (DatabaseMetaData) m_Connection.getMetaData();
		ResultSet tableRet = m_DBMetaData.getTables(null, "%","%",new String[]{"TABLE"});
//		其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。

//		3. 提取表的名字。
		while(tableRet.next()) System.out.println(tableRet.getString("TABLE_NAME"));

//		通过getString("TABLE_NAME")，就可以获取表的名字了。
//		从这里可以看出，前面通过getTables的接口的返回，JDBC是将其所有的结果，保存在一个类似table的内存结构中，而其中TABLE_NAME这个名字的字段就是每个表的名字。
//
//		4. 提取表内的字段的名字和类型
		String tablename = "tb_creditapply";
		String columnName;
		String columnType;
		ResultSet colRet = m_DBMetaData.getColumns(null,"%",tablename ,"%");

		StringBuilder stringBuilder = new StringBuilder("CREATE TABLE "+tablename +"(");
		while(colRet.next()) {
			columnName = colRet.getString("COLUMN_NAME");
			columnType = colRet.getString("TYPE_NAME");
//			int datasize = colRet.getInt("COLUMN_SIZE");
//			int digits = colRet.getInt("DECIMAL_DIGITS");
//			int nullable = colRet.getInt("NULLABLE");
			System.out.println(columnName+" "+columnType);
			stringBuilder.append(columnName +" "+ columnType +",");
//			System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+	nullable);
		}
		stringBuilder.append(")");

		System.out.println(stringBuilder.toString());
	}

	
}
