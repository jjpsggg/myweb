package com.zlht.controller.app;

import com.zlht.controller.base.BaseController;
import com.zlht.entity.Page;
import com.zlht.service.btxjd.credit.CreditManager;
import com.zlht.service.btxjd.creditapply.CreditApplyManager;
import com.zlht.service.btxjd.creditcheck.CreditCheckManager;
import com.zlht.service.btxjd.eleccontract.ElecContractManager;
import com.zlht.service.btxjd.eleclog.ElecLogManager;
import com.zlht.service.btxjd.instalment.InstalmentManager;
import com.zlht.service.btxjd.order.OrderManager;
import com.zlht.service.btxjd.quota.QuotaManager;
import com.zlht.service.btxjd.shopuser.ShopUserManager;
import com.zlht.service.system.appuser.AppuserManager;
import com.zlht.util.*;
import com.zlht.util.fadada.FddClientBase;
import com.zlht.util.fadada.util.RandomUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**@author GZ
 * 会员-接口类
 * 相关参数协议：
 * 00	请求失败
 * 01	请求成功
 * 02	返回空值
 * 03	请求协议参数不完整
 * 04  用户名或密码错误
 * 05  FKEY验证失败
 */
@Controller
@RequestMapping(value="/interface")
@SuppressWarnings("unchecked")
public class InterfacesController extends BaseController {

	@Resource(name="appuserService") //会员
	private AppuserManager appuserService;

	@Resource(name="creditcheckService")//征信
	private CreditCheckManager creditcheckService;

	@Resource(name="quotaService")//授信
	private QuotaManager quotaService;

	@Resource(name="creditapplyService")//审核
	private CreditApplyManager creditapplyService;

	@Resource(name="creditService")//还款
	private CreditManager creditService;

	@Resource(name="orderService")//支付
	private OrderManager orderService;

	@Resource(name="shopuserService")
	private ShopUserManager shopuserService;

	@Resource(name="eleccontractService")
	private ElecContractManager eleccontractService;

	@Resource(name="eleclogService")
	private ElecLogManager eleclogService;

	@Resource(name="instalmentService")
	private InstalmentManager instalmentService;



	/**根据用户名获取会员信息
	 * @return object
	 */
	@RequestMapping(value="/getAppuserByUm")
	@ResponseBody
	public Object getAppuserByUsernmae(){
		logBefore(logger, "根据用户名获取会员信息");
		HashMap map = new HashMap();
		PageData pd = this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERNAME"};
		String[] VALUE_ARRAY = new String[]{"用户名"};
		try{
			if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("getAppuserByUsernmae",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					pd = appuserService.findByUsername(pd);
					map.put("pd", pd);
					result = (null == pd) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}

	/**返回合同地址
	 * @return object
	 */
	@RequestMapping(value="/getContractUrl")
	@ResponseBody
	public Object getContractUrl(){
		logBefore(logger, "通过合同id获得查看合同地址");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		try{
			String url = FddClientBase.invokeViewContract(pd.getString("contract_id"));
			map.put("url",url);
			result = "01";
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}

	/**根据返回信息更新订单状态
	 */
	@RequestMapping(value="/notify_url")
	@ResponseBody
	public void notify_url(){
		logBefore(logger, "根据签合同返回信息更新合同状态");
		PageData pd = this.getPageData();
		String ORDERSTATUS="2";
		try{
			if(pd.get("result_code").equals("300")){
//				合同状态 1待用户签署 2待放款 3已放款 -1拒绝放款
				pd.put("ORDERSTATUS",ORDERSTATUS);
				// 		保存电子合同日志
				eleccontractService.updateStatus(pd);
			}else{
				ORDERSTATUS="0";
			}
			PageData eleclog = new PageData();
			eleclog.put("USERID",pd.get("USERID"));
			eleclog.put("TYPERESULT",ORDERSTATUS);
			eleclog.put("REMARK",pd.get("result_code"));
			eleclog.put("CREATETIME", System.currentTimeMillis());
			eleclog.put("ELECLOG_ID",this.get32UUID());
			eleclog.put("ELECCONTRACT_ID",pd.get("ELECCONTRACT_ID"));
			eleclogService.save(eleclog);
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
	}


	/**通过信贷申请id，查询征信报告
	 * @return object
	 */
	@RequestMapping(value="/creditSearch")
	@ResponseBody
	public Object creditSearch(){
		logBefore(logger, "通过信贷申请id，查询征信报告");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"CREDITAPPLY_ID"};
		String[] VALUE_ARRAY = new String[]{"用户申请ID"};
		try{
			if(Tools.checkKey("CREDITAPPLY_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditSearch",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					pd = creditapplyService.findById(pd);
					map.put("pd", pd);
					result = (null == pd) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**保存征信信息
	 * @return Object
	 */
	@RequestMapping(value="/creditContentSave")
	@ResponseBody
	public Object creditContentSave(){
		logBefore(logger, "保存征信信息");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		pd.put("CREDITAPPLY_ID", this.get32UUID());	//主键
		//保存征信信息
		String[] PARAM_ARRAY = new String[]{"USERID","USERNAME","USERMOBILE","USERCITY","USERADDRESS","USERURGENTNAME","USERURGENTMOBILE","MARITALSTATUS","APPLYTYPE","USERIDNUM","USERIDPHOTO","USERIDOBVERSEPHOTO","SOCIETYNUM","JINGDONGNUM","TAOBAONUM"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID","用户姓名","用户手机号","用户城市","用户居住地址","紧急联系人姓名","紧急联系人手机号","婚姻状况","借贷类型 白条 现金贷","用户身份证号","身份证正面照地址","身份证反面照地址","社保账号","京东账号","淘宝账号"};
		try{
			if(Tools.checkKey(Const.APP_CREDITSAVE_PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditContentSave",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					//保存征信报告，返回征信报告url
					creditcheckService.save(pd);
					result = "02";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}

	/**保存申请信息
	 * @return Object
	 */
	@RequestMapping(value="/creditApplySave")
	@ResponseBody
	public Object creditApplySave(){
		logBefore(logger, "保存申请信息");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","USERNAME","USERMOBILE","USEREMAIL","CHANNEL_ID","USERCITY","USERADDRESS","USERURGENTNAME","USERURGENTMOBILE","MARITALSTATUS","APPLYTYPE","USERIDNUM","USERIDPHOTO","USERIDOBVERSEPHOTO","SOCIETYNUM","JINGDONGNUM","TAOBAONUM","SOCIETYPWD","JINGDONGPWD","TAOBAOPWD","BANKNAME","BANKACCOUNT"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID(身份证号)","用户姓名","用户手机号","用户邮箱","渠道id","用户城市","用户居住地址","紧急联系人姓名","紧急联系人手机号","婚姻状况","借贷类型","用户身份证号","身份证正面照地址","身份证反面照地址","社保账号","京东账号","淘宝账号","社保密码","京东密码","淘宝密码","银行名称","信用卡账号"};
		try{
			if(Tools.checkKey(PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditApplySave",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					//			根据申请信息生成用户CA证书 customer_name, email, id_card, ident_type, mobile
					String caResult = FddClientBase.invokeSyncPersonAuto(pd.get("USERNAME").toString(), pd.get("USEREMAIL").toString(), pd.get("USERID").toString(), "0", pd.get("USERMOBILE").toString());
					JSONObject outobj = JSONObject.fromObject(caResult);
					String result1 = outobj.getString("result");
					if ("success".equals(result1)) {
						pd.put("CREDITAPPLY_ID", this.get32UUID());	//主键
						String CUSTOMERID = outobj.getString("customer_id");
						pd.put("CUSTOMERID",CUSTOMERID);

						//			保存征信信息，待接口完善补充
						//			String CREDITURL = GETCREDITURL
						//			pd.put("CREDITURL",CREDITURL);

						//保存申请信息，包括基本信息，CA证书，征信链接地址
						creditapplyService.save(pd);
						//初始化用户表，如果身份证号存在，则写入不成功，可能是不同渠道或是不同产品的申请
						PageData pageData = shopuserService.findByUserId(pd);
						if(pageData == null){
							pd.put("SHOPUSER_ID", this.get32UUID());	//主键
							shopuserService.save(pd);
						}
						result = "01";
					} else {
						logger.error("注册生成CA错误"+outobj.getString("msg").replaceAll("\n", "<br/>"));
						map.put("msg","注册生成CA错误"+outobj.getString("msg").replaceAll("\n", "<br/>"));
					}
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("msg",e.toString());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}

	/**申请信息列表
	 * @return Object
	 */
	@RequestMapping(value="/creditApplyList")
	@ResponseBody
	public Object creditApplyList(){
		logBefore(logger, "申请信息列表");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","CHANNEL_IDS"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID(身份证号)","渠道IDs"};
		boolean param = true;
		if(pd.getString(PARAM_ARRAY[0]) == null || pd.getString(PARAM_ARRAY[0]).equals("")){
			result = "03";
			param =false;
		}
		if(pd.getString(PARAM_ARRAY[1]) == null || pd.getString(PARAM_ARRAY[1]).equals("")){
			result = "03";
			param =false;
		}
		try{
			if(Tools.checkKey(PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditApplyList",PARAM_ARRAY,VALUE_ARRAY, pd) && param){	//检查参数,参数个数检验，非空校验
					Page page = getListResult(pd);
					List<PageData>	varList = creditapplyService.list(page);
					map.put("page", page);
					map.put("pd", varList);
					result = (varList.size()<1) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("msg",e.toString());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}



	/**给指定申请授信，将授信额度保存至用户信贷申请表中
	 */
	@RequestMapping(value="/creditQuotaSave")
	@ResponseBody
	public Object creditQuotaSave(){
		logBefore(logger, "保存授信信息");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"CREDITAPPLY_ID","CREDITQUOTA"};
		String[] VALUE_ARRAY = new String[]{"用户信贷申请ID","系统中可选信用额度"};
		try{
			if(Tools.checkKey("CREDITAPPLY_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditQuotaSave",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					quotaService.save(pd);
					result =  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}



	/**对用户信息、征信信息及授信额度进行审核
	 */
	@RequestMapping(value="/creditApplyCheck")
	@ResponseBody
	public Object creditApplyCheck(){
		logBefore(logger, "审核");
		Map<String,Object> map = new HashMap();
		PageData pd = this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"CREDITAPPLY_ID","CHECKRESULT"};
		String[] VALUE_ARRAY = new String[]{"用户信贷申请ID","审核结果"};
		try{
			if(Tools.checkKey("CREDITAPPLY_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditApplyCheck",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					creditapplyService.edit(pd);
					result = "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}



	/**查询当前白条和现金贷可选授信额度
	 */
	@RequestMapping(value="/quotaList")
	@ResponseBody
	public Object quotaList(){
		logBefore(logger, "授信额度查询");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","CHANNEL_IDS"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID(身份证号)","渠道IDs"};
		boolean param = true;
		if(pd.getString(PARAM_ARRAY[0]) == null || pd.getString(PARAM_ARRAY[0]).equals("")){
			result = "03";
			param =false;
		}
//		if(pd.getString(PARAM_ARRAY[1]) == null || pd.getString(PARAM_ARRAY[1]).equals("")){
//			result = "03";
//			param =false;
//		}
		try{
			if(Tools.checkKey(PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditApplyList",PARAM_ARRAY,VALUE_ARRAY, pd) && param){	//检查参数,参数个数检验，非空校验
					pd.put("CHANNEL_IDS",","+pd.getString("CHANNEL_IDS"));
					PageData varOList = shopuserService.findByUserId(pd);
					map.put("pd", varOList);
					result = (varOList == null) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("msg",e.toString());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 * 还款明细
	 */
	@RequestMapping(value="/rebackList")
	@ResponseBody
	public Object rebackList(){
		logBefore(logger, "还款明细查询");
		Map<String,Object> map = new HashMap();
		PageData pd  = this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","ORDER_ID"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID","订单ID"};
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("rebackList",PARAM_ARRAY, VALUE_ARRAY,pd)){	//检查参数
					Page page = new Page();
					page.setPd(pd);
					List<PageData> list = creditService.list(page);
					map.put("pd", list);
					result =  (list.size() < 1) ?  "02" : "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 * 用户申请提现或是商城购物提交订单后，用户签订合同，与电子合同接口对接
	 */
	@RequestMapping(value="/confirmContract")
	@ResponseBody
	public Object confirmContract(){
		logBefore(logger, "签订合同");
		Map<String,Object> map = new HashMap();
		PageData pd = this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","ORDER_ID"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID","订单ID"};
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("confirmContract",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					PageData opd = orderService.findById(pd);
					//		根据订单号获得合同id及合同信息
					PageData epd = eleccontractService.findById(opd);
					//		根据订单号获得渠道id，再根据渠道id+userid获得customer_id
					PageData cpd = orderService.getCustomerId(opd);

//		返回用户手动动签署地址
					String return_url = PropUtil.getValue("/conf", "return_url");
					if(!Objects.equals(pd.getString("RETURN_URL"), ""))
						return_url = pd.getString("RETURN_URL");
					String transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
					String sign_keyword=PropUtil.getValue("/conf", "customersign");
					String notify_url = PropUtil.getValue("/conf", "domain")+"front/notify_url?ELECCONTRACT_ID="+opd.getString("ELECCONTRACT_ID");
					String customer_id = cpd.getString("CUSTOMERID");
					String doc_title = epd.getString("CONTRACTNAME");
					String contract_id = epd.getString("CONTRACT_ID");

//		生成电子合同地址
					String get_url = FddClientBase.invokeExtSign(transaction_id, contract_id, return_url,"" , customer_id,
							doc_title, notify_url, sign_keyword);
					result =  "01";
					PageData out = new PageData();
					out.put("get_url",get_url);
					map.put("pd",out);
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("pd",e.getMessage());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 * 生成白条订单
	 */
	@RequestMapping(value="/orderPay")
	@ResponseBody
	public Object orderPay(){
		logBefore(logger, "生成白条订单");
		Map map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","ORDERID","ORDERNAME","ORDERVALUE","ORDERTIME","ITEM","FEERATE","COMMISSION","OVERDUEFEERATE","OVERDUECOMMISSION","CHANNEL_ID","PRODUCTTYPE"};
		String[] VALUE_ARRAY = new String[]{"用户身份证号","订单ID","订单名称","订单金额","订单时间","分期数","分期费率","手续费","逾期费率","逾期手续费","渠道id","订单类型 1白条  2现金贷"};
		boolean param = true;
		if(pd.getString(PARAM_ARRAY[0]) == null || pd.getString(PARAM_ARRAY[0]).equals("")){
			result = "03";
			param =false;
		}
		try{
			if(Tools.checkKey(PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("instalmentList",PARAM_ARRAY,VALUE_ARRAY, pd) && param){	//检查参数,参数个数检验，非空校验
					JSONObject outobj ;
					PageData pdrs = new PageData();
					String ELECCONTRACT_ID = this.get32UUID();
					pd.put("ORDER_ID", this.get32UUID());	//主键
					pd.put("ELECCONTRACT_ID", ELECCONTRACT_ID);//电子合同id
//		生成电子合同
					String transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
					String template_id = "testtest";
					String contract_id = System.currentTimeMillis() + RandomUtil.generateString(5);//电子合同id
					String doc_title = pd.getString("ORDERNAME")+" — 电子合同";
					String contractId = "zlht"+System.currentTimeMillis(); // 合同编号
					Map  mapcontract = new HashMap<>();
					mapcontract.put("fill_1","等额本息");
					mapcontract.put("fill_2","1");
					mapcontract.put("fill_3","2017-10-01");
					mapcontract.put("fill_1_2","等额本息");
					mapcontract.put("fill_2_2","1");
					mapcontract.put("fill_3_2","2017-10-01");
					FddClientBase.invokeGenerateContract(contract_id, template_id, doc_title,"","", mapcontract);
//					outobj = JSONObject.fromObject(response);
//					String rs = outobj.getString("result")+" "+outobj.getString("msg");
//		保存电子合同
					PageData elecpg = new PageData();
					elecpg.put("CONTRACTID",contractId);
					elecpg.put("CONTRACTNAME",doc_title);
					elecpg.put("ORDERSTATUS","1");
					elecpg.put("CONTRACT_ID",contract_id);
					elecpg.put("SHOPUSER_ID",pd.getString("SHOPUSER_ID"));
					elecpg.put("ELECCONTRACT_ID",ELECCONTRACT_ID);
					eleccontractService.save(elecpg);

//		企业端自动签署
					String customer_id = PropUtil.getValue("/conf", "customer_id");
					String sign_keyword = PropUtil.getValue("/conf", "appsign");
					String client_role = "1";
					String batch_id = "";
					String client_type = "";
					String notify_url = PropUtil.getValue("/conf", "domain")+"front/notify_url";
					String httpOrgCreateTestRtn = FddClientBase.invokeExtSignAuto(transaction_id, customer_id, batch_id,
							client_type,client_role, contract_id, doc_title, sign_keyword, notify_url);
					outobj = JSONObject.fromObject(httpOrgCreateTestRtn);
					String rs = outobj.getString("result")+" "+outobj.getString("msg");
//      保存电子合同日志，保存订单
					PageData eleclog1 = new PageData();
					eleclog1.put("USERID","system");
					eleclog1.put("TYPERESULT",1);
					eleclog1.put("REMARK",rs);
					eleclog1.put("CREATETIME", System.currentTimeMillis());
					eleclog1.put("ELECLOG_ID",this.get32UUID());
					eleclog1.put("ELECCONTRACT_ID",ELECCONTRACT_ID);
					eleclogService.save(eleclog1);
					orderService.save(pd);

					//增加冻结额度
					PageData pds = new PageData();
					pds.put("USERID",pd.get("USERID"));
					pds.put("PRODUCTTYPE",pd.get("PRODUCTTYPE"));
					if(pd.get("PRODUCTTYPE").equals("1")){
						pds.put("BTUSERDQUOTA",0);
						pds.put("BTDISABLEQUOTA",Double.valueOf(pd.getString("ORDERVALUE")));
					}
					else{
						pds.put("XJDUSERDQUOTA",0);
						pds.put("XJDDISABLEQUOTA",Double.valueOf(pd.getString("ORDERVALUE")));
					}

					shopuserService.editusedquota(pds);

					result = "01";
					//		返回用户手动动签署地址
					String return_url = PropUtil.getValue("/conf", "return_url");
					if(!Objects.equals(pd.getString("RETURN_URL"), ""))
						return_url = pd.getString("RETURN_URL");
					transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
					sign_keyword=PropUtil.getValue("/conf", "customersign");
					notify_url = PropUtil.getValue("/conf", "domain")+"front/notify_url?ELECCONTRACT_ID="+ELECCONTRACT_ID;
					String get_url="";

					//		根据订单号获得渠道id，再根据渠道id+userid获得customer_id
					PageData cpd = orderService.getCustomerId(pd);
					if(cpd != null){
						customer_id = cpd.getString("CUSTOMERID");
						//		生成电子合同地址
						get_url = FddClientBase.invokeExtSign(transaction_id, contract_id, return_url,"" , customer_id,
								doc_title, notify_url, sign_keyword);
					}else{
						logger.error("错误：无法找到电子签名信息");
						result = "00";
					}
					pdrs.put("get_url",get_url);
					map.put("pd",pdrs);
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("msg",e.toString());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 * 查询支付明细
	 */
	@RequestMapping(value="/payList")
	@ResponseBody
	public Object payList(){
		logBefore(logger, "支付查询");
		Map<String,Object> map = new HashMap();
		PageData pd = this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","STARTTIME","ENDTIME","PAGESIZE","CURPAGE","ORDER_ID"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID","查询开始日期","查询结束日期","分页数","当前页","订单ID"};
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("payList",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					Page page = new Page();
					page.setPd(pd);
					orderService.list(page);//尚未确定
					result = "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}



	/**
	 *代扣，第三方对接
	 */
	@RequestMapping(value="/escrowPay")
	@ResponseBody
	public Object escrowPay(){
		logBefore(logger, "代扣");
		Map<String,Object> map = new HashMap();
		PageData pd  = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("escrowPay", pd)){	//检查参数
					orderService.edit(pd);//尚未确定
					result =  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 *还款指定期次贷款，与银行对接
	 */
	@RequestMapping(value="/creditPay")
	@ResponseBody
	public Object creditPay(){
		logBefore(logger, "还款");
		Map<String,Object> map = new HashMap();
		PageData pd = this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","CREDIT_ID"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID","贷款ID"};
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditPay",PARAM_ARRAY,VALUE_ARRAY, pd)){	//检查参数
					creditService.edit(pd);
					result =  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 *还款状态修改
	 */
	@RequestMapping(value="/creditStatusChange")
	@ResponseBody
	public Object creditStatusChange(){
		logBefore(logger, "还款状态修改");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","CREDIT_ID","STATUS"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID","贷款ID","状态：已支付 未支付"};

		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditStatusChange",PARAM_ARRAY, VALUE_ARRAY,pd)){	//检查参数
					creditService.edit(pd);
					result =  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 *查询分期明细
	 */
	@RequestMapping(value="/creditTermList")
	@ResponseBody
	public Object creditTermList(){
		logBefore(logger, "查询分期明细");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"ORDER_ID"};
		String[] VALUE_ARRAY = new String[]{"订单ID"};
		boolean param = true;
		if(pd.getString(PARAM_ARRAY[0]) == null || pd.getString(PARAM_ARRAY[0]).equals("")){
			result = "03";
			param =false;
		}
		try{
			if(Tools.checkKey("ORDER_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("creditTermList",PARAM_ARRAY,VALUE_ARRAY, pd) && param){	//检查参数
					Page page = getListResult(pd);
					List<PageData> list = creditService.list(page);
					map.put("pd", list);
					result =  (list.size() < 1) ?  "02" : "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 *查询渠道分期
	 */
	@RequestMapping(value="/instalmentList")
	@ResponseBody
	public Object instalmentList(){
		logBefore(logger, "查询渠道分期");
		Map map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"CHANNEL_ID"};
		String[] VALUE_ARRAY = new String[]{"渠道ID"};
		boolean param = true;
		if(pd.getString(PARAM_ARRAY[0]) == null || pd.getString(PARAM_ARRAY[0]).equals("")){
			result = "03";
			param =false;
		}
		try{
			if(Tools.checkKey(PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("instalmentList",PARAM_ARRAY,VALUE_ARRAY, pd) && param){	//检查参数,参数个数检验，非空校验
					List<PageData>	varList = instalmentService.listAll(pd);
					map.put("pd", varList);
					result = (varList.size()<1) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("msg",e.toString());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}


	/**
	 *查询订单明细
	 */
	@RequestMapping(value="/orderList")
	@ResponseBody
	public Object orderList(){
		logBefore(logger, "查询订单明细");
		Map<String,Object> map = new HashMap();
		PageData pd =  this.getPageData();
		String result = "00";
		String[] PARAM_ARRAY = new String[]{"USERID","CHANNEL_IDS"};
		String[] VALUE_ARRAY = new String[]{"商城用户ID(身份证号)","渠道IDs"};
		boolean param = true;
		if(pd.getString(PARAM_ARRAY[0]) == null || pd.getString(PARAM_ARRAY[0]).equals("")){
			result = "03";
			param =false;
		}
		if(pd.getString(PARAM_ARRAY[1]) == null || pd.getString(PARAM_ARRAY[1]).equals("")){
			result = "03";
			param =false;
		}
		try{
			if(Tools.checkKey(PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkArrayParam("orderList",PARAM_ARRAY,VALUE_ARRAY, pd) && param){	//检查参数,参数个数检验，非空校验
					pd.put("CHANNEL_IDS",","+pd.getString("CHANNEL_IDS"));
					Page page = new Page();
					page.setPd(pd);
					List<PageData>	varList = orderService.list(page);
					map.put("page", page);
					map.put("pd", varList);
					result = (varList.size()<1) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
			map.put("msg",e.toString());
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}

//	设置页面参数
	Page getListResult(PageData pd){
		pd.put("CHANNEL_IDS",","+pd.getString("CHANNEL_IDS"));
		Page page = new Page();
		page.setPd(pd);
		return page;
	}

}
	
 