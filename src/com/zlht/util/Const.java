package com.zlht.util;

import org.springframework.context.ApplicationContext;
/**
 * 项目名称：
 * @author: GZ
 * 修改日期：2015/11/2
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	public static final String SESSION_USER = "sessionUser";			//session用的用户
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String sSESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String DEPARTMENT_IDS = "DEPARTMENT_IDS";		//当前用户拥有的最高部门权限集合
	public static final String DEPARTMENT_ID = "DEPARTMENT_ID";			//当前用户拥有的最高部门权限
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((interface)|(front)|(login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化

	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};

	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};

	//通过信贷申请id，查询征信报告接口_请求协议中的参数
	public static final String[] APP_CREDITSEARCH_PARAM_ARRAY = new String[]{"CREDITAPPLY_ID"};
	public static final String[] APP_CREDITSEARCH_VALUE_ARRAY = new String[]{"用户申请ID"};

	//保存征信信息
	public static final String[] APP_CREDITSAVE_PARAM_ARRAY = new String[]{"USERID","USERNAME","USERMOBILE","USERCITY","USERADDRESS","USERURGENTNAME","USERURGENTMOBILE","MARITALSTATUS","APPLYTYPE","USERIDNUM","USERIDPHOTO","USERIDOBVERSEPHOTO","SOCIETYNUM","JINGDONGNUM","TAOBAONUM"};
	public static final String[] APP_CREDITSAVE_VALUE_ARRAY = new String[]{"商城用户ID","用户姓名","用户手机号","用户城市","用户居住地址","紧急联系人姓名","紧急联系人手机号","婚姻状况","借贷类型 白条 现金贷","用户身份证号","身份证正面照地址","身份证反面照地址","社保账号","京东账号","淘宝账号"};

	//白条（现金贷）申请保存
	public static final String[] APP_CREDITAPPLYSAVE_PARAM_ARRAY = new String[]{"USERID","USERNAME","USERMOBILE","USERCITY","USERADDRESS","USERURGENTNAME","USERURGENTMOBILE","MARITALSTATUS","APPLYTYPE","USERIDNUM","USERIDPHOTO","USERIDOBVERSEPHOTO","SOCIETYNUM","JINGDONGNUM","TAOBAONUM","SOCIETYPWD","JINGDONGPWD","TAOBAOPWD","BANKNAME","BANKACCOUNT"};
	public static final String[] APP_CREDITAPPLYSAVE_VALUE_ARRAY = new String[]{"商城用户ID","用户姓名","用户手机号","用户城市","用户居住地址","紧急联系人姓名","紧急联系人手机号","婚姻状况","借贷类型 白条 现金贷","用户身份证号","身份证正面照地址","身份证反面照地址","社保账号","京东账号","淘宝账号","社保密码","京东密码","淘宝密码","银行名称","信用卡账号"};

	//保存授信信息
	public static final String[] APP_CREDITQUOTA_PARAM_ARRAY = new String[]{"CREDITAPPLY_ID","CREDITQUOTA"};
	public static final String[] APP_CREDITQUOTA_VALUE_ARRAY = new String[]{"用户信贷申请ID","系统中可选信用额度"};

	//审核
	public static final String[] APP_CREDITAPPLYCHECK_PARAM_ARRAY = new String[]{"CREDITAPPLY_ID","CHECKRESULT"};
	public static final String[] APP_CREDITAPPLYCHECK_VALUE_ARRAY = new String[]{"用户信贷申请ID","审核结果"};

	//授信额度查询
	public static final String[] APP_QUOTALIST_PARAM_ARRAY = new String[]{"QUOTATYPE"};
	public static final String[] APP_QUOTALIST_VALUE_ARRAY = new String[]{"额度类型"};

	//还款明细查询
	public static final String[] APP_REBACKLIST_PARAM_ARRAY = new String[]{"USERID","STARTTIME","ENDTIME","PAGESIZE","CURPAGE","ORDER_ID"};
	public static final String[] APP_REBACKLIST_VALUE_ARRAY = new String[]{"商城用户ID","查询开始日期","查询结束日期","分页数","当前页","订单ID"};

	//签订合同
	public static final String[] APP_CONFIRMCONTRACT_PARAM_ARRAY = new String[]{"USERID","ORDER_ID"};
	public static final String[] APP_CONFIRMCONTRACT_VALUE_ARRAY = new String[]{"商城用户ID","订单ID"};

	//支付
	public static final String[] APP_ORDERPAY_PARAM_ARRAY = new String[]{"ORDER_ID"};
	public static final String[] APP_ORDERPAY_VALUE_ARRAY = new String[]{"订单ID"};

	//支付查询
	public static final String[] APP_PAYLIST_PARAM_ARRAY = new String[]{"USERID","STARTTIME","ENDTIME","PAGESIZE","CURPAGE","ORDER_ID"};
	public static final String[] APP_PAYLIST_VALUE_ARRAY = new String[]{"商城用户ID","查询开始日期","查询结束日期","分页数","当前页","订单ID"};

	//代扣（参数未定）
	public static final String[] APP_ESCROWPAY_PARAM_ARRAY = new String[]{"USERID"};
	public static final String[] APP_ESCROWPAY_VALUE_ARRAY = new String[]{"商城用户ID"};

	//还款
	public static final String[] APP_CREDITPAY_PARAM_ARRAY = new String[]{"USERID","CREDIT_ID"};
	public static final String[] APP_CREDITPAY_VALUE_ARRAY = new String[]{"商城用户ID","贷款ID"};

	//还款状态修改
	public static final String[] APP_CREDITSTATUSCHANGE_PARAM_ARRAY = new String[]{"USERID","CREDIT_ID","STATUS"};
	public static final String[] APP_CREDITSTATUSCHANGE_VALUE_ARRAY = new String[]{"商城用户ID","贷款ID","状态：已支付 未支付"};

	//分期明细
	public static final String[] APP_CREDITTERMLIST_PARAM_ARRAY = new String[]{"USERID","STARTTIME","ENDTIME","PAGESIZE","CURPAGE","ORDER_ID"};
	public static final String[] APP_CREDITTERMLIST_VALUE_ARRAY = new String[]{"商城用户ID","查询开始日期","查询结束日期","分页数","当前页","订单ID"};

	//订单明细
	public static final String[] APP_ORDERLIST_PARAM_ARRAY = new String[]{"USERID","STARTTIME","ENDTIME","PAGESIZE","CURPAGE"};
	public static final String[] APP_ORDERLIST_VALUE_ARRAY = new String[]{"商城用户ID","查询开始日期","查询结束日期","分页数","当前页"};

}
