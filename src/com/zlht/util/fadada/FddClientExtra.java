/**
* 版权所有：深圳法大大网络科技有限公司
* Copyright 2015 fadada.com Inc.
* All right reserved. 
*====================================================
* 文件名称: FddClient.java
* 修订记录：
* No    日期				作者(操作:具体内容)
* 1.    Dec 18, 2015			Mocuishle(创建:创建文件)
*====================================================
* 类描述：(说明未实现或其它不应生成javadoc的内容)
* 
*/
package com.zlht.util.fadada;



import com.zlht.util.fadada.common.FddClientUtil;
import com.zlht.util.fadada.common.FddConstantCommon;
import com.zlht.util.fadada.common.FddEncryptTool;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class FddClientExtra {

	/**
	 * <b>概要：</b>TODO 手动签模式带有效期
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年10月31日 </br>
	 * @param transaction_id 交易号
	 * @param contract_id 合同编号
	 * @param return_url 签章完成后页面跳转地址
	 * @param client_type 客户类型 1 个人 2 企业
	 * @param customer_id 客户编号
	 * @param doc_title 文档标题
	 * @param notify_url 签章结果接收地址（异步通知）
	 * @param sign_keyword 签章关键字
	 * @param validity 单位为分钟的有效时间，格式为大于0，小于10080（七天对应的分钟数）的整数
	 * @param quantity 有效次数格式为大于0的整数
	 * @return
	 */
	public static String invokeExtSignValidation(String transaction_id, String contract_id, String return_url, String client_type, String customer_id, String doc_title, String notify_url, String sign_keyword, String validity, String quantity) {
		String timeStamp = FddClientUtil.getTimeStamp();
		StringBuffer sb = new StringBuffer(FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_EXTSIGN_VALIDATION.getValue());
		try {
			String msgDigest = "";
			// Base64(SHA1(app_id+md5(transaction_id+timestamp)+SHA1(app_secret+ customer_id +doc_url)))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(transaction_id + timeStamp + validity + quantity) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + customer_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

			sb.append("?timestamp=").append(timeStamp);
			sb.append("&app_id=").append(FddConstantCommon.API_ID.getValue());
			sb.append("&v=").append(FddConstantCommon.V.getValue());
			sb.append("&msg_digest=").append(msgDigest);
			sb.append("&transaction_id=").append(transaction_id);
			sb.append("&contract_id=").append(contract_id);
			sb.append("&client_type=").append(client_type);
			sb.append("&customer_id=").append(customer_id);
			sb.append("&doc_title=").append(URLEncoder.encode(doc_title, FddClientUtil.charset));
			sb.append("&sign_keyword=").append(URLEncoder.encode(sign_keyword, FddClientUtil.charset));
			sb.append("&validity=").append(validity);
			sb.append("&quantity=").append(quantity);
			sb.append("&return_url=").append(URLEncoder.encode(return_url, FddClientUtil.charset));
			sb.append("&notify_url=").append(URLEncoder.encode(notify_url, FddClientUtil.charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * <b>概要：</b>TODO 文档批量签署接口
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年11月2日 </br>
	 * @param batch_id
	 * @param sign_data
	 * @param client_type
	 * @param client_role
	 * @param customer_id
	 * @param outh_customer_id
	 * @param batch_title
	 * @param return_url
	 * @param notify_url
	 * @return
	 */
	public static String invokeExtBatchSign(String batch_id,String sign_data,String client_type,String client_role,String customer_id,String outh_customer_id,String batch_title,String return_url,String notify_url) {
		StringBuffer sb = new StringBuffer();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			// SHA1(app_id+md5(batch_id+timestamp)+S HA1(app_secret+ customer_id+ outh_customer_id ))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(batch_id+timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + customer_id+outh_customer_id));
			String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			
			sb.append(FddConstantCommon.API_URL.getValue()).append(FddConstantCommon.URL_EXTBATCHSIGN.getValue());
			sb.append("?app_id=").append(FddConstantCommon.API_ID.getValue());
			sb.append("&batch_id=").append(batch_id);
			sb.append("&sign_data=").append(sign_data);
			sb.append("&client_type=").append(client_type);
			sb.append("&client_role=").append(client_role);
			sb.append("&customer_id=").append(customer_id);
			sb.append("&outh_customer_id=").append(outh_customer_id);
			sb.append("&batch_title=").append(URLEncoder.encode(batch_title, "UTF-8"));
			sb.append("&return_url=").append(URLEncoder.encode(return_url, "UTF-8"));
			sb.append("&notify_url=").append(URLEncoder.encode(notify_url, "UTF-8"));

			sb.append("&v=").append(FddConstantCommon.V.getValue());
			sb.append("&timestamp=").append(timeStamp);
			sb.append("&msg_digest=").append(msgDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * <b>概要：</b> TODO 文档批量签署接口(手工全自动)
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年11月2日 </br>
	 * @param batch_id 
	 * @param sign_data
	 * @param client_type
	 * @param client_role
	 * @param customer_id
	 * @param outh_customer_id
	 * @param batch_title
	 * @param return_url
	 * @param notify_url
	 * @return
	 */
	public static String invokeGotoBatchSemiautoSignPage(String batch_id,String sign_data,String client_type,String client_role,String customer_id,String outh_customer_id,String batch_title,String return_url,String notify_url) {
		
		StringBuffer sb = new StringBuffer();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			// SHA1(app_id+md5(batch_id+timestamp)+S HA1(app_secret+ customer_id+ outh_customer_id ))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(batch_id+timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + customer_id+outh_customer_id));
			String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			
			sb.append(FddConstantCommon.API_URL.getValue()).append(FddConstantCommon.URL_GOTO_BATCH_SEMIAUTO_SIGNPAGE.getValue());
			sb.append("?app_id=").append(FddConstantCommon.API_ID.getValue());
			sb.append("&batch_id=").append(batch_id);
			sb.append("&sign_data=").append(sign_data);
			sb.append("&client_type=").append(client_type);
			sb.append("&client_role=").append(client_role);
			sb.append("&customer_id=").append(customer_id);
			sb.append("&outh_customer_id=").append(outh_customer_id);
			sb.append("&batch_title=").append(URLEncoder.encode(batch_title, "UTF-8"));
			sb.append("&return_url=").append(URLEncoder.encode(return_url, "UTF-8"));
			sb.append("&notify_url=").append(URLEncoder.encode(notify_url, "UTF-8"));

			sb.append("&v=").append(FddConstantCommon.V.getValue());
			sb.append("&timestamp=").append(timeStamp);
			sb.append("&msg_digest=").append(msgDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * <b>概要：</b> TODO 文档批量签署接口(全自动)
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年11月3日 </br>
	 * @param batch_id 批次号
	 * @param sign_data 签章数据
	 * @param batch_title 批量签标题
	 * @param return_url 跳转URL
	 * @param notify_url 签章结果通知URL
	 * @return
	 */
	public static String invokeExtBatchSignAuto(String batch_id,String sign_data,String batch_title,String return_url,String notify_url) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			params.add(new BasicNameValuePair("batch_id", batch_id));
			params.add(new BasicNameValuePair("sign_data", sign_data));
			params.add(new BasicNameValuePair("batch_title", URLEncoder.encode(batch_title, "UTF-8")));
			params.add(new BasicNameValuePair("return_url", URLEncoder.encode(return_url, "UTF-8")));
			params.add(new BasicNameValuePair("notify_url", notify_url));

			// SHA1(app_id+md5(batch_id+timestamp)+S HA1(app_secret+ sign_data ))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(batch_id+timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + sign_data));
			String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes())).trim();
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_EXTBATCHSIGN_AUTO.getValue(), params);
	}
	
	/**
	 * <b>概要：</b>TODO 客户签署状态查询接口
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年10月31日 </br>
	 * @param contract_id 合同编号
	 * @param customer_id 客户编号
	 * @return
	 */
	public static String invokeQuerySignStatus(String contract_id, String customer_id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			// Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+ contract_id+customer_id)))
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("contract_id", contract_id));
			params.add(new BasicNameValuePair("customer_id", customer_id));

			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + contract_id + customer_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes())).trim();
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_QUERY_SIGNSTATUS.getValue(), params);
	}


	/**
	 * <b>概要：</b>TODO 文档临时查看下载地址接口（带有效期和次数）
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年10月31日 </br>
	 * @param contract_id 合同编号
	 * @param validity 有效期（分钟）	大于0的整数
	 * @param quantity 有效次数	大于0的整数
	 * @return
	 */
	public static String invokeGetUrl(String contract_id, String validity, String quantity) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp + validity + quantity) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + contract_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("contract_id", contract_id));
			params.add(new BasicNameValuePair("validity", validity));
			params.add(new BasicNameValuePair("quantity", quantity));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_GETURL.getValue(), params);
	}
	

	/**
	 * <b>概要：</b>TODO 客户信息查询接口
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年10月31日 </br>
	 * @param customer_id 客户编号
	 * @return
	 */
	public static String invokeQueryUserInfo(String customer_id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			// Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+ contract_id+customer_id)))
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("customer_id", customer_id));

			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + customer_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes())).trim();
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_QUERY_USERINFO.getValue(), params);
	}
	
}
