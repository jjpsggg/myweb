/**
* 版权所有：深圳法大大网络科技有限公司
* Copyright 2015 fadada.com Inc.
* All right reserved. 
*====================================================
* 文件名称: FddConstantCommon.java
* 修订记录：
* No    日期				作者(操作:具体内容)
* 1.    Dec 18, 2015			Mocuishle(创建:创建文件)
*====================================================
* 类描述：(说明未实现或其它不应生成javadoc的内容)
* 
*/
package com.zlht.util.fadada.common;

import com.zlht.util.PropUtil;

/**
 * <h3>概要:</h3> 
 *    法大大服务配置类
 * <br>
 * <h3>功能:</h3>
 * <ol>
 * 		<li>通过getValue()获取实际值</li>
 * </ol>
 * <h3>履历:</h3>
 * <ol>
 * 		<li>2015年12月17日[zhouxw] 新建</li>
 * </ol>
 */
public enum FddConstantCommon {

	/*测试环境地址：	https://testapi.fadada.com:8443/api/
	app_id：	400221
	app_secret：	dbvRqt4TYARjdakKcbHGzIOp
	测试客户编号：	607B3931211594A6*/

	/**
	 * apiId
	 * TODO 请替换成贵平台的专属app_id
	 */
	API_ID(PropUtil.getValue("/conf", "app_id")),

	/**
	 * 密钥
	 * TODO 请替换成贵平台的专属secret
	 */
	APP_SECRET(PropUtil.getValue("/conf", "app_secret")),

	/**
	 * 版本
	 */
	V(PropUtil.getValue("/conf", "v")),
	
	/**
	 * 地址
	 */
	API_URL(PropUtil.getValue("/conf", "api_url")),

	// ========================基础接口 start======================
	/** 个人CA注册 */
	URL_SYNCPERSON_AUTO("syncPerson_auto.api"),
	
	/** 上传文档 */
	URL_UPLOADDOCS("uploaddocs.api"),

	/** 上传模板 */
	URL_UPLOADTEMP("uploadtemplate.api"),

	/** 生成合同 */
	URL_GENERATE_CONTRACT("generate_contract.api"),

	/** 手动签署 */
	URL_EXTSIGN("extsign.api"),

	/** 自动签署 */
	URL_EXTSIGN_AUTO("extsign_auto.api"),
	
	/** 合同归档 */
	URL_CONTRACTFILING("contractFiling.api"), 
	
	/** 下载合同 */
	URL_DOWNLOADCONTRACT("downLoadContract.api"),
	
	/** 查看合同 */
	URL_VIEWCONTRACT("viewContract.api"),

	/** 修改用户信息 */
	URL_INFOCHANGE("infochange.api"),

	// ========================基础接口 end========================
	

	// ========================扩展接口 start======================

	/** 手动签模式，带有效期 */
	URL_EXTSIGN_VALIDATION("extsign_validation.api"),
	
	/** 批量签署接口 */
	URL_BATCHSIGN("extBatchSign.api"),
	
	/** 文档批量签署接口(手工全自动) */
	URL_BATCH_SEMIAUTOSIGN("gotoBatchSemiautoSignPage.api"),
	
	/** 文档批量签署接口(全自动) */
	URL_BATCHSIGN_AUTO("extBatchSignAuto.api"),
	
	/** 查询签章状态 */
	URL_QUERY_SIGNSTATUS("query_signstatus.api"),
	
	/** 文档临时查看下载地址接口（带有效期和次数） */
	URL_GETURL("geturl.api"),

	/** 查询签章状态 */
	URL_QUERY_USERINFO("query_userinfo.action"),

	/** 文档批量签署接口 */
	URL_EXTBATCHSIGN("extBatchSign.api"),
	
	/** 文档批量签署接口(手工全自动) */
	URL_GOTO_BATCH_SEMIAUTO_SIGNPAGE("gotoBatchSemiautoSignPage.api"),
	
	/** 文档批量签署接口(全自动) */
	URL_EXTBATCHSIGN_AUTO("extBatchSignAuto.api"),
	// ========================扩展接口 end========================
	
	
	
	;
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 概要：FddConstantCommon类的构造函数
	 */
	FddConstantCommon(){};
	/**
	 * 概要：FddConstantCommon类的构造函数
	 * @param val
	 */
	FddConstantCommon(String val){
		this.value = val;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
