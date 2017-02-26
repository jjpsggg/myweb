package com.zlht.controller.app.appuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zlht.service.btxjd.eleccontract.ElecContractManager;
import com.zlht.service.btxjd.eleccontract.impl.ElecContractService;
import com.zlht.service.btxjd.eleclog.ElecLogManager;
import com.zlht.service.btxjd.shopuser.ShopUserManager;
import com.zlht.util.Const;
import com.zlht.util.fadada.FddClientBase;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlht.controller.base.BaseController;
import com.zlht.entity.Page;
import com.zlht.service.btxjd.credit.CreditManager;
import com.zlht.service.btxjd.creditapply.CreditApplyManager;
import com.zlht.service.btxjd.creditcheck.CreditCheckManager;
import com.zlht.service.btxjd.order.OrderManager;
import com.zlht.service.btxjd.quota.QuotaManager;
import com.zlht.service.system.appuser.AppuserManager;
import com.zlht.util.AppUtil;
import com.zlht.util.PageData;
import com.zlht.util.Tools;


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
@RequestMapping(value="/appuser")
public class IntAppuserController extends BaseController {

	@Resource(name="appuserService") //會員
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
		try{
			if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("getAppuserByUsernmae", pd)){	//检查参数
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
		try{
			if(Tools.checkKey("CREDITAPPLY_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditSearch", pd)){	//检查参数
					pd = creditcheckService.findById(pd);
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
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		pd.put("CREDITAPPLY_ID", this.get32UUID());	//主键
		try{
			if(Tools.checkKey(Const.APP_CREDITSAVE_PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditContentSave", pd)){	//检查参数
					//保存征信报告，返回征信报告url

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
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd =  this.getPageData();
		String result = "00";
		pd.put("CREDITAPPLY_ID", this.get32UUID());	//主键
		try{
			if(Tools.checkKey(Const.APP_CREDITAPPLYSAVE_PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditContentSave", pd)){	//检查参数
					//			根据申请信息生成用户CA证书 customer_name, email, id_card, ident_type, mobile
					String caResult = FddClientBase.invokeSyncPersonAuto(pd.get("USERNAME").toString(), pd.get("USEREMAIL").toString(), pd.get("USERID").toString(), "0", pd.get("USERMOBILE").toString());
					JSONObject outobj = JSONObject.fromObject(caResult);
					String result1 = outobj.getString("result");
					if ("success".equals(result1)) {
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

//	/**申请信息列表
//	 * @return Object
//	 */
//	@RequestMapping(value="/creditApplyList")
//	@ResponseBody
//	public Object creditApplyList(){
//		logBefore(logger, "申请信息列表");
//		Map<String,Object> map = new HashMap<String,Object>();
//		PageData pd =  this.getPageData();
//		String result = "00";
//		ArrayList paramArray = new ArrayList();
//		ArrayList valueArray = new ArrayList();
//		try{
//			if(Tools.checkKey(Const.APP_CREDITAPPLYSAVE_PARAM_ARRAY[0], pd.getString("FKEY"))){	//检验请求key值是否合法
//				if(AppUtil.checkArrayParam("123",paramArray,valueArray, pd)){	//检查参数
//						result = "01";
//
//				}else {
//					result = "03";
//				}
//			}else{
//				result = "05";
//			}
//		}catch (Exception e){
//			logger.error(e.toString(), e);
//			map.put("msg",e.toString());
//		}finally{
//			map.put("result", result);
//			logAfter(logger);
//		}
//		return AppUtil.returnObject(new PageData(), map);
//	}



	/**给指定申请授信，将授信额度保存至用户信贷申请表中
	 * @return
	 */
	@RequestMapping(value="/creditQuotaSave")
	@ResponseBody
	public Object creditQuotaSave(){
		logBefore(logger, "保存授信信息");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd =  this.getPageData();
		String result = "00";

		try{
			if(Tools.checkKey("CREDITAPPLY_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditQuotaSave", pd)){	//检查参数
					quotaService.save(pd);
					//map.put("pd", pd);
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




	/**对用户信息、征信信息及授信额度进行审核
	 * @return
	 */
	@RequestMapping(value="/creditApplyCheck")
	@ResponseBody
	public Object creditApplyCheck(){
		logBefore(logger, "审核");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("CREDITAPPLY_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditApplyCheck", pd)){	//检查参数
					creditapplyService.edit(pd);
					//map.put("pd", pd);
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



	/**查询当前白条和现金贷可选授信额度
	 * @return
	 */
	@RequestMapping(value="/quotaList")
	@ResponseBody
	public Object quotaList(){
		logBefore(logger, "授信额度查询");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("QUOTATYPE", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("quotaList", pd)){	//检查参数
					Page page = new Page();
					page.setPd(pd);
					List<PageData> list = quotaService.list(page);
					map.put("pd", list);
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


	/**
	 * 还款明细
	 * @return
	 */
	@RequestMapping(value="/rebackList")
	@ResponseBody
	public Object rebackList(){
		logBefore(logger, "还款明细查询");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("rebackList", pd)){	//检查参数
					Page page = new Page();
					page.setPd(pd);
					List<PageData> list = creditService.list(page);
					map.put("pd", list);
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


	/**
	 * 用户申请提现或是商城购物提交订单后，用户签订合同，与电子合同接口对接
	 * @return
	 */
	@RequestMapping(value="/confirmContract")
	@ResponseBody
	public Object confirmContract(){
		logBefore(logger, "签订合同");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("confirmContract", pd)){	//检查参数
					//creditService.list(page);
					//map.put("pd", list);
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


	/**
	 * 给指定订单付款
	 * @return
	 */
	@RequestMapping(value="/orderPay")
	@ResponseBody
	public Object orderPay(){
		logBefore(logger, "支付");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("ORDER_ID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("orderPay", pd)){	//检查参数
					orderService.edit(pd);//尚未确定
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


	/**
	 * 查询支付明细
	 * @return
	 */
	@RequestMapping(value="/payList")
	@ResponseBody
	public Object payList(){
		logBefore(logger, "支付查询");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("payList", pd)){	//检查参数
					Page page = new Page();
					page.setPd(pd);
					orderService.list(page);//尚未确定
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



	/**
	 *代扣，第三方对接
	 * @return
	 */
	@RequestMapping(value="/escrowPay")
	@ResponseBody
	public Object escrowPay(){
		logBefore(logger, "代扣");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("escrowPay", pd)){	//检查参数
					orderService.edit(pd);//尚未确定
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


	/**
	 *还款指定期次贷款，与银行对接
	 * @return
	 */
	@RequestMapping(value="/creditPay")
	@ResponseBody
	public Object creditPay(){
		logBefore(logger, "还款");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditPay", pd)){	//检查参数
					creditService.edit(pd);
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


	/**
	 *还款状态修改
	 * @return
	 */
	@RequestMapping(value="/creditStatusChange")
	@ResponseBody
	public Object creditStatusChange(){
		logBefore(logger, "还款状态修改");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditStatusChange", pd)){	//检查参数
					creditService.edit(pd);
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


	/**
	 *查询分期明细
	 * @return
	 */
	@RequestMapping(value="/creditTermList")
	@ResponseBody
	public Object creditTermList(){
		logBefore(logger, "查询分期明细");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("creditTermList", pd)){	//检查参数
					Page page =new Page();
					page.setPd(pd);
					List<PageData> list = creditService.list(page);
					map.put("pd", list);
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


	/**
	 *查询订单明细
	 * @return
	 */
	@RequestMapping(value="/orderList")
	@ResponseBody
	public Object orderList(){
		logBefore(logger, "查询订单明细");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERID", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("orderList", pd)){	//检查参数
					Page page =new Page();
					page.setPd(pd);
					List<PageData> list = orderService.list(page);
					map.put("pd", list);
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

}
	
 