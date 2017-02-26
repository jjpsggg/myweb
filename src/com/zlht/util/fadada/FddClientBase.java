/**
* 版权所有：深圳法大大网络科技有限公司
* Copyright 2015 fadada.com Inc.
* All right reserved. 
*====================================================
* 文件名称: FddClientBase.java
* 修订记录：
* No    日期				作者(操作:具体内容)
* 1.    Dec 18, 2015			Mocuishle(创建:创建文件)
*====================================================
* 类描述：(说明未实现或其它不应生成javadoc的内容)
* 
*/
package com.zlht.util.fadada;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zlht.util.fadada.common.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * <h3>概要:</h3> 
 *    法大大基础接口调用类
 * <br>
 * <h3>功能:</h3>
 * <ol>
 * 		<li>个人CA注册</li>
 * 		<li>上传文档</li>
 * 		<li>上传模板</li>
 * 		<li>生成合同</li>
 * 		<li>手动签署</li>
 * 		<li>自动签署</li>
 * 		<li>合同归档</li>
 * 		<li>下载合同</li>
 * 		<li>查看合同</li>
 * 		<li>修改用户信息</li>
 * </ol>
 * <h3>履历:</h3>
 * <ol>
 * 		<li>2016年10月31日[zhouxw] 新建</li>
 * </ol>
 */
public class FddClientBase{
	
	/**
	 * <b>概要：</b>TODO 个人CA注册
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月17日 </br>
	 * @param customer_name 名称
	 * @param email 邮箱
	 * @param id_card 身份证号码
	 * @param mobile 手机号码
	 * @return 接口处理结果
	 */
	public static String invokeSyncPersonAuto(String customer_name, String email, String id_card, String ident_type, String mobile) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			// Base64(SHA1(appid+md5(timestamp)+SHA1(appsecret)))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("customer_name", customer_name));
			params.add(new BasicNameValuePair("email", email));
			String id_mobile = FddEncryptTool.encrypt(id_card + "|" + mobile, FddConstantCommon.APP_SECRET.getValue());
			params.add(new BasicNameValuePair("id_mobile", id_mobile));
			params.add(new BasicNameValuePair("ident_type", ident_type));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_SYNCPERSON_AUTO.getValue(), params);
	}
	
	/**
	 * <b>概要：</b>TODO 上传文档
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月17日 </br>
	 * @param contract_id 合同编号
	 * @param doc_title 合同标题
	 * @param file 合同文件,与doc_url两个只传一个
	 * @param doc_url 合同文件url地址
	 * @param docType 合同类型：.doc .docx .pdf
	 * @return 接口处理结果
	 */
	public static String invokeUploadDocs(String contract_id,String doc_title,File file,String doc_url,String docType){
		String result = "";
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			//Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+  contract_id )))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()+contract_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			
			HttpClient httpclient = new SSLClient();
	        HttpPost httppost = new HttpPost(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_UPLOADDOCS.getValue());  
	        MultipartEntity reqEntity = new MultipartEntity();  //对请求的表单域进行填充  
	        if(file!=null){
	        	FileBody fileBody = new FileBody(file); //创建待处理的文件  
	        	reqEntity.addPart("file", fileBody);  
	        }
	        if(doc_url!=null){
	        	doc_url= doc_url.replaceAll(" ", "%20");
	        	reqEntity.addPart("doc_url", new StringBody(doc_url));
	        }
			reqEntity.addPart("contract_id", new StringBody(contract_id));
			reqEntity.addPart("doc_title", new StringBody(URLEncoder.encode(doc_title, FddClientUtil.charset)));
			reqEntity.addPart("doc_type", new StringBody(docType));

	        reqEntity.addPart("app_id", new StringBody(FddConstantCommon.API_ID.getValue()));  //创建待处理的表单域内容文本  
	        reqEntity.addPart("v", new StringBody(FddConstantCommon.V.getValue()));  
	        reqEntity.addPart("timestamp", new StringBody(timeStamp));  
	        reqEntity.addPart("msg_digest", new StringBody(msgDigest));
	        httppost.setEntity(reqEntity);  
	        HttpResponse response = httpclient.execute(httppost);
	        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
	            HttpEntity entity = response.getEntity();  
	            //显示内容  
	            if (entity != null) {  
	            	result = EntityUtils.toString(entity);
	            }  
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>概要：</b>TODO 上传合同模板
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年3月3日 </br>
	 * @param template_id 模板ID
	 * @param file 模板文件
	 * @param doc_url 模板URL
	 * @return
	 */
	public static String invokeUploadTemplate(String template_id,File file,String doc_url){
		String result = "";
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			//Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+  template_id )))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()+template_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			
			HttpClient httpclient = new DefaultHttpClient();  
	        HttpPost httppost = new HttpPost(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_UPLOADTEMP.getValue());  
	        FileBody fileBody = new FileBody(file); //创建待处理的文件  
	        MultipartEntity reqEntity = new MultipartEntity();  //对请求的表单域进行填充  
	        reqEntity.addPart("file", fileBody);  
	        reqEntity.addPart("doc_url", new StringBody(doc_url));
	        reqEntity.addPart("app_id", new StringBody(FddConstantCommon.API_ID.getValue()));  //创建待处理的表单域内容文本  
	        reqEntity.addPart("v", new StringBody(FddConstantCommon.V.getValue()));  
	        reqEntity.addPart("template_id", new StringBody(template_id));  
	        reqEntity.addPart("timestamp", new StringBody(timeStamp));  
	        reqEntity.addPart("msg_digest", new StringBody(msgDigest));
//	        reqEntity.addPart("doc_type", new StringBody(docType)); 
	        httppost.setEntity(reqEntity);  
	        HttpResponse response = httpclient.execute(httppost);
	        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
	            HttpEntity entity = response.getEntity();  
	            //显示内容  
	            if (entity != null) {  
	            	result = EntityUtils.toString(entity);
	            }  
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>概要：</b>TODO 生成合同
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年3月7日 </br>
	 * @param contract_id 合同ID
	 * @param template_id 模板ID
	 * @param doc_title 合同标题
	 * @param map 参数键值对
	 * @return 
	 */
	public static String invokeGenerateContract(String contract_id,String template_id,String doc_title,String font_size,String font_type, Map<String,String> map){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
	        String parameter_map = JsonUtil.mapToJson(map);
			//Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+ template_id + contract_id ) +parameter_map))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()+template_id+contract_id)+parameter_map);
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
			params.add(new BasicNameValuePair("doc_title", doc_title));
			params.add(new BasicNameValuePair("template_id", template_id));
			params.add(new BasicNameValuePair("contract_id", URLDecoder.decode(contract_id, FddClientUtil.charset)));
			params.add(new BasicNameValuePair("parameter_map", parameter_map));
			params.add(new BasicNameValuePair("font_size", font_size));
			params.add(new BasicNameValuePair("font_type", font_type));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_GENERATE_CONTRACT.getValue(), params);
	}
	
	/**
	 * <b>概要：</b>TODO 手动签章
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月30日 </br>
	 * @param transaction_id 交易号，长度小于等于32位
	 * @param contract_id 合同编号
	 * @param return_url 跳转地址
	 * @param client_type 客户类型：1-个人，2-企业
	 * @param customer_id 客户编号
	 * @param doc_title 文档标题
	 * @param notify_url 异步通知地址
	 * @param sign_keyword 签章关键字
	 * @return 返回拼接好的地址，请重定向到该地址
	 */
	public static String invokeExtSign(String transaction_id,String contract_id,String return_url,String client_type,String customer_id,String doc_title,String notify_url,String sign_keyword){
		String timeStamp = FddClientUtil.getTimeStamp();
		StringBuffer sb= new StringBuffer(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_EXTSIGN.getValue());
		try {
			String msgDigest = "";
			//Base64(SHA1(app_id+md5(transaction_id+timestamp)+SHA1(app_secret+ customer_id +doc_url)))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(transaction_id+timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()+customer_id));
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
	 * <b>概要：</b>TODO 自动签署
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月30日 </br>
	 * @param transaction_id 交易号
	 * @param customer_id 客户编号
	 * @param batch_id 批次号（交易号）
	 * @param client_type 客户类型：1-个人，2-企业
	 * @param client_role 客户角色
	 * @param contract_id 合同编号
	 * @param doc_title 文档标题
	 * @param sign_keyword 签章关键字
	 * @param notify_url 异步通知地址
	 * @return 接口处理结果
	 */
	public static String invokeExtSignAuto(String transaction_id,String customer_id,String batch_id,String client_type,String client_role,String contract_id,String doc_title,String sign_keyword,String notify_url){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			//Base64(SHA1(app_id+md5(transaction_id+timestamp) + SHA1(app_secret+ customer_id +doc_url)))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(transaction_id+timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()+customer_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
			params.add(new BasicNameValuePair("transaction_id", transaction_id)); 
			params.add(new BasicNameValuePair("batch_id", batch_id));
			params.add(new BasicNameValuePair("contract_id", contract_id));
			params.add(new BasicNameValuePair("client_type", client_type));
			params.add(new BasicNameValuePair("client_role", client_role));
			params.add(new BasicNameValuePair("customer_id", customer_id));
			params.add(new BasicNameValuePair("doc_title", doc_title));
			params.add(new BasicNameValuePair("sign_keyword", sign_keyword));
			params.add(new BasicNameValuePair("notify_url", notify_url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_EXTSIGN_AUTO.getValue(), params);
	}
	
	
	/**
	 * <b>概要：</b>TODO 合同归档
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月18日 </br>
	 * @param contract_id 合同编号
	 * @return 接口处理结果
	 */
	public static String invokeContractFilling(String contract_id){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			//Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+  contract_id )))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()+contract_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
			params.add(new BasicNameValuePair("contract_id", contract_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_CONTRACTFILING.getValue(), params);
	}
	
	/**
	 * <b>概要：</b>TODO 已签章文档下载接口
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年7月4日 </br>
	 * @param contract_id 合同编号
	 */
	public static void invokeDownloadPdf(String contract_id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			// Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+ contract_id)))
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("contract_id", contract_id));

			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + contract_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes())).trim();
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path = "D:/" + contract_id + ".pdf"; // 地址自定义，文件名自定义，后缀固定pdf
		FddClientUtil.doPostDownload(path, FddConstantCommon.API_URL.getValue() + FddConstantCommon.URL_DOWNLOADCONTRACT, params);
	}
	
	/**
	 * <b>概要：</b>TODO 获取合同下载地址
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年10月31日 </br>
	 * @param contract_id 合同编号
	 */
	public static String getDownloadPdfURL(String contract_id) {
		StringBuffer sb = new StringBuffer();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			// Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+ contract_id)))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + contract_id));
			String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes())).trim();
			sb.append(FddConstantCommon.API_URL.getValue());
			sb.append(FddConstantCommon.URL_DOWNLOADCONTRACT);
			sb.append("?app_id=").append(FddConstantCommon.URL_DOWNLOADCONTRACT);
			sb.append("&contract_id=").append(contract_id);
			sb.append("&v=").append(FddConstantCommon.V.getValue());
			sb.append("&timestamp=").append(timeStamp);
			sb.append("&msg_digest=").append(msgDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * <b>概要：</b>TODO 查看合同
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2016年11月2日 </br>
	 * @param contract_id 合同编号
	 * @return
	 */
	public static String invokeViewContract(String contract_id) {
		StringBuffer sb = new StringBuffer();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			// Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret+contract_id )))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue() + contract_id));
			String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			sb.append(FddConstantCommon.API_URL.getValue()).append(FddConstantCommon.URL_VIEWCONTRACT.getValue());
			sb.append("?app_id=").append(FddConstantCommon.API_ID.getValue());
			sb.append("&contract_id=").append(contract_id);
			sb.append("&v=").append(FddConstantCommon.V.getValue());
			sb.append("&timestamp=").append(timeStamp);
			sb.append("&msg_digest=").append(msgDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * <b>概要：</b>TODO 修改用户信息
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月23日 </br>
	 * @param email 邮箱
	 * @param mobile 手机号码11位
	 * @param customer_id 客户编号
	 * @return 接口处理结果
	 */
	public static String invokeInfoChange(String email,String mobile,String customer_id){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = FddClientUtil.getTimeStamp();
			String msgDigest = "";
			//Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret )))
			String sha1 = FddEncryptTool.sha1(FddConstantCommon.API_ID.getValue()+FddEncryptTool.md5Digest(timeStamp)+FddEncryptTool.sha1(FddConstantCommon.APP_SECRET.getValue()));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
			params.add(new BasicNameValuePair("app_id", FddConstantCommon.API_ID.getValue()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", FddConstantCommon.V.getValue()));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("mobile", mobile));
			params.add(new BasicNameValuePair("customer_id", customer_id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FddClientUtil.doPost(FddConstantCommon.API_URL.getValue()+FddConstantCommon.URL_INFOCHANGE.getValue(), params);
	}
	
}
