package com.zlht.controller.btxjd.front;

import com.zlht.controller.base.BaseController;
import com.zlht.entity.Page;
import com.zlht.entity.system.User;
import com.zlht.service.btxjd.checklog.CheckLogManager;
import com.zlht.service.btxjd.creditapply.CreditApplyManager;
import com.zlht.service.btxjd.eleccontract.ElecContractManager;
import com.zlht.service.btxjd.eleclog.ElecLogManager;
import com.zlht.service.btxjd.order.OrderManager;
import com.zlht.service.btxjd.quota.QuotaManager;
import com.zlht.service.btxjd.shopuser.ShopUserManager;
import com.zlht.service.btxjd.systemquota.SystemQuotaManager;
import com.zlht.util.*;
import com.zlht.util.fadada.FddClientBase;
import com.zlht.util.fadada.util.RandomUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 说明：借贷申请
 * 创建人：ZLHT
 * 创建时间：2016-12-04
 */
@Controller
@RequestMapping(value="/front")
public class FrontController extends BaseController {
	
	String menuUrl = "creditapply/list.do"; //菜单地址(权限用)
	@Resource(name="creditapplyService")
	private CreditApplyManager creditapplyService;

	@Resource(name="shopuserService")
	private ShopUserManager shopuserService;

	@Resource(name="quotaService")
	private QuotaManager quotaService;

	@Resource(name="systemquotaService")
	private SystemQuotaManager systemquotaService;

	@Resource(name="checklogService")
	private CheckLogManager checklogService;

	@Resource(name="eleccontractService")
	private ElecContractManager eleccontractService;

	@Resource(name="eleclogService")
	private ElecLogManager eleclogService;

	@Resource(name="orderService")
	private OrderManager orderService;


	/**主页
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public ModelAndView index() throws Exception{
		logBefore(logger, "访问用户主页");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		mv.setViewName("front/index");
		mv.addObject("msg","success");
		return mv;
	}

	/**订单申请
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/order")
	public ModelAndView order() throws Exception{
		logBefore(logger, "访问订单申请");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		mv.setViewName("front/order");
		mv.addObject("msg","success");
		return mv;
	}

	/**主页
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/notify_url")
	public ModelAndView notify_url() throws Exception{
		logBefore(logger, "签署合同");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String ORDERSTATUS="2";
		try{
			if(pd.get("result_code").equals("3000")){
//				合同状态 1待用户签署 2待放款 3已放款 -1拒绝放款
				pd.put("ORDERSTATUS",ORDERSTATUS);
				// 		保存电子合同日志
				eleccontractService.updateStatus(pd);
			}else{
				ORDERSTATUS="0";;
			}
			PageData eleclog = new PageData();
			eleclog.put("USERID",pd.get("USERID"));
			eleclog.put("TYPERESULT",ORDERSTATUS);
			eleclog.put("REMARK",pd.get("result_desc"));
			eleclog.put("CREATETIME", System.currentTimeMillis());
			eleclog.put("ELECLOG_ID",this.get32UUID());
			eleclog.put("ELECCONTRACT_ID",pd.get("ELECCONTRACT_ID"));
			eleclogService.save(eleclog);
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}

		mv.setViewName("front/index");
		mv.addObject("msg","success");
		return mv;
	}

	/**申请保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增CreditApply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CREDITAPPLY_ID", this.get32UUID());	//主键
		try{
//			根据申请信息生成用户CA证书 customer_name, email, id_card, ident_type, mobile
			String caResult = FddClientBase.invokeSyncPersonAuto(pd.get("USERNAME").toString(), pd.get("USEREMAIL").toString(), pd.get("USERID").toString(), "0", pd.get("USERMOBILE").toString());
			JSONObject outobj = JSONObject.fromObject(caResult);
			String result = outobj.getString("result");
			if ("success".equals(result)) {
				String CUSTOMERID = outobj.getString("customer_id");
				pd.put("CUSTOMERID",CUSTOMERID);
			} else {
				logger.error("注册生成CA错误"+pd.toString());
				mv.addObject("exception", "注册生成CA错误"+pd.toString().replaceAll("\n", "<br/>"));
				mv.setViewName("error");
				return mv;
			}

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
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		mv.setViewName("front/index");
		mv.addObject("msg","success");
		return mv;
	}

	/*
	 *保存订单
	 */
	@RequestMapping(value="/saveOrder")
	public ModelAndView saveOrder() throws Exception{
		logBefore(logger, "新增Order");
		JSONObject outobj ;
 		ModelAndView mv = this.getModelAndView();
		String ELECCONTRACT_ID = this.get32UUID();//电子合同id
		PageData pd;
		pd = this.getPageData();
		pd.put("ORDER_ID", this.get32UUID());	//主键
		pd.put("ELECCONTRACT_ID", ELECCONTRACT_ID);

//		生成电子合同
		String transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
		String template_id = "testtest";
		String contract_id = System.currentTimeMillis() + RandomUtil.generateString(5);//电子合同id
		String doc_title = pd.getString("ORDERNAME")+" — 电子合同";
		String contractId = "zlht"+System.currentTimeMillis(); // 合同编号
		Map<String,String> map = new HashMap<>();
		map.put("fill_1","等额本息");
		map.put("fill_2","1");
		map.put("fill_3","2017-10-01");
		map.put("fill_1_2","等额本息");
		map.put("fill_2_2","1");
		map.put("fill_3_2","2017-10-01");
		String response = FddClientBase.invokeGenerateContract(contract_id, template_id, doc_title,"","", map);
		outobj = JSONObject.fromObject(response);
		String result = outobj.getString("result")+" "+outobj.getString("msg");

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
		result = outobj.getString("result")+" "+outobj.getString("msg");
//      保存电子合同日志
		PageData eleclog1 = new PageData();
		eleclog1.put("USERID","system");
		eleclog1.put("TYPERESULT",1);
		eleclog1.put("REMARK",result);
		eleclog1.put("CREATETIME", System.currentTimeMillis());
		eleclog1.put("ELECLOG_ID",this.get32UUID());
		eleclog1.put("ELECCONTRACT_ID",ELECCONTRACT_ID);
		eleclogService.save(eleclog1);
		orderService.save(pd);

		//		返回用户手动动签署地址
		String return_url = PropUtil.getValue("/conf", "return_url");
		transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
		sign_keyword=PropUtil.getValue("/conf", "customersign");
		notify_url = PropUtil.getValue("/conf", "domain")+"front/notify_url?ELECCONTRACT_ID="+ELECCONTRACT_ID;
		String get_url="";

//		根据订单号获得渠道id，再根据渠道id+userid获得customer_id
		PageData cpd = orderService.getCustomerId(pd);
		if(cpd != null){
			customer_id = cpd.getString("CUSTOMERID");
			//		生成电子合同地址
			get_url = FddClientBase.invokeExtSign(transaction_id, contract_id, notify_url,"" , customer_id,
					doc_title, notify_url, sign_keyword);
		}else{
			logger.error("错误：无法找到电子签名信息");
		}

		mv.addObject("msg","success");
		mv.addObject("get_url",get_url);
		mv.addObject("ORDER_ID",pd.getString("ORDER_ID"));
		mv.setViewName("front/order_save");
		return mv;
	}

	/*
	 *去签订合同，返回签合同地址
	 */
	@RequestMapping(value="/confirmContract")
	public ModelAndView confirmContract() throws Exception{
		logBefore(logger, "根据订单id返回电子合同签订地址");
		ModelAndView mv = this.getModelAndView();
		PageData  pd = this.getPageData();
		PageData opd = orderService.findById(pd);
		//		根据订单号获得合同id及合同信息
		PageData epd = eleccontractService.findById(opd);
		//		根据订单号获得渠道id，再根据渠道id+userid获得customer_id
		PageData cpd = orderService.getCustomerId(opd);

//		返回用户手动动签署地址
		String return_url = PropUtil.getValue("/conf", "return_url");
		String transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
		String sign_keyword=PropUtil.getValue("/conf", "customersign");
		String notify_url = PropUtil.getValue("/conf", "domain")+"front/notify_url?ELECCONTRACT_ID="+opd.getString("ELECCONTRACT_ID");
		String customer_id = cpd.getString("CUSTOMERID");
		String doc_title = epd.getString("CONTRACTNAME");
		String contract_id = epd.getString("CONTRACT_ID");
		String ELECCONTRACT_ID = opd.getString("ELECCONTRACT_ID");
		String USERID = opd.getString("USERID");

//		生成电子合同地址
		String get_url = FddClientBase.invokeExtSign(transaction_id, contract_id, notify_url,"" , customer_id,
				doc_title, notify_url, sign_keyword);

		mv.addObject("msg","success");
		mv.addObject("get_url",get_url);
		mv.setViewName("front/confirm_save");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除CreditApply");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		creditapplyService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改CreditApply");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		Session session = Jurisdiction.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.get("APPLYSTATUS").equals("-1") || pd.get("APPLYSTATUS").equals("1")){
			pd.put("FIRSTCHECK",user.getNAME());
		}
		if(pd.get("APPLYSTATUS").equals("-2") || pd.get("APPLYSTATUS").equals("2")){
			pd.put("SECONDCHECK",user.getNAME());
		}
		pd.put("UPDATETIME",new Date());
		//更新审批状态
		creditapplyService.edit(pd);
//		写入日志信息
		PageData pdlog = new PageData();
		pdlog.put("CHECKLOG_ID", this.get32UUID());	//主键
		pdlog.put("USERID", user.getUSER_ID());
		pdlog.put("USERNAME", user.getNAME());
		pdlog.put("BUSSTYPE", "审核");
		pdlog.put("TYPERESULT", pd.get("APPLYSTATUS"));
		pdlog.put("REMARK", pd.get("REMARK"));
		pdlog.put("CREDITAPPLY_ID", pd.get("CREDITAPPLY_ID"));
		checklogService.save(pdlog);
//		增加可用额度
		if(pd.get("APPLYSTATUS").equals("2")){
			PageData pdquota = new PageData();
			pdquota.put("PRODUCTTYPE", pd.get("PRODUCTTYPE"));
			pdquota.put("USERID", pd.get("USERID"));
			pdquota.put("APPLYQUOTA", pd.get("APPLYQUOTA"));
			shopuserService.editquota(pdquota);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表CreditApply");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = creditapplyService.list(page);	//列出CreditApply列表
		mv.setViewName("btxjd/creditapply/creditapply_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list1")
	public ModelAndView list1(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表CreditApply");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = creditapplyService.list(page);	//列出CreditApply列表
		mv.setViewName("btxjd/creditapply/creditapply_list1");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("btxjd/creditapply/creditapply_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}

	/**去申请页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goApply")
	public ModelAndView goApply()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//添加商城实名认证信息，未认证则返回指定页面
		pd.put("USERID","21140219791108461X");
		pd.put("USERNAME","高志");
		pd.put("USERMOBILE","13811354259");
		pd.put("USEREMAIL","gaochongzhi@163.com");
		pd.put("USERIDPHOTO","/uploadFiles/uploadImgs/001.jpg");
		pd.put("USERIDOBVERSEPHOTO","/uploadFiles/uploadImgs/001.jpg");
		//添加渠道信息
		pd.put("CHANNEL_ID","a2675ec3733c40d29e083279dc50476b");
		pd.put("CHANNELNAME","暖猫商城");
		pd.put("PRODUCTNAME","暖猫商城白条");

		mv.setViewName("front/apply");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String rerunUrl = pd.get("URL").toString();
		pd = creditapplyService.findById(pd);	//根据ID读取
		List<PageData> varOList = quotaService.listAll(pd);
		List<PageData> quotaList = systemquotaService.listAll(pd);
		mv.setViewName("btxjd/creditapply/"+rerunUrl);
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("quotalist", varOList);
		mv.addObject("maxquota",quotaList.get(0));
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除CreditApply");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			creditapplyService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出CreditApply到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商城用户ID");	//1
		titles.add("用户姓名");	//2
		titles.add("用户手机号");	//3
		titles.add("用户城市");	//4
		titles.add("用户居住地址");	//5
		titles.add("紧急联系人姓名");	//6
		titles.add("紧急联系人手机号");	//7
		titles.add("婚姻状况");	//8
		titles.add("借贷类型   白条  现金贷");	//9
		titles.add("借贷额度");	//10
		titles.add("借贷流程状态1申请2授信3审核4放款");	//11
		titles.add("申请时间");	//12
		titles.add("用户身份证号");	//13
		titles.add("身份证正面照地址");	//14
		titles.add("身份证反面照地址");	//15
		titles.add("社保账号");	//16
		titles.add("京东账号");	//17
		titles.add("淘宝账号");	//18
		titles.add("流程处理时间");	//19
		dataMap.put("titles", titles);
		List<PageData> varOList = creditapplyService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("USERID"));	    //1
			vpd.put("var2", varOList.get(i).getString("USERNAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("USERMOBILE"));	    //3
			vpd.put("var4", varOList.get(i).getString("USERCITY"));	    //4
			vpd.put("var5", varOList.get(i).getString("USERADDRESS"));	    //5
			vpd.put("var6", varOList.get(i).getString("USERURGENTNAME"));	    //6
			vpd.put("var7", varOList.get(i).getString("USERURGENTMOBILE"));	    //7
			vpd.put("var8", varOList.get(i).getString("MARITALSTATUS"));	    //8
			vpd.put("var9", varOList.get(i).getString("APPLYTYPE"));	    //9
			vpd.put("var10", varOList.get(i).get("APPLYQUOTA").toString());	//10
			vpd.put("var11", varOList.get(i).get("APPLYSTATUS").toString());	//11
			vpd.put("var12", varOList.get(i).getString("CREATEDATETIME"));	    //12
			vpd.put("var13", varOList.get(i).getString("USERIDNUM"));	    //13
			vpd.put("var14", varOList.get(i).getString("USERIDPHOTO"));	    //14
			vpd.put("var15", varOList.get(i).getString("USERIDOBVERSEPHOTO"));	    //15
			vpd.put("var16", varOList.get(i).getString("SOCIETYNUM"));	    //16
			vpd.put("var17", varOList.get(i).getString("JINGDONGNUM"));	    //17
			vpd.put("var18", varOList.get(i).getString("TAOBAONUM"));	    //18
			vpd.put("var19", varOList.get(i).getString("UPDATETIME"));	    //19
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
