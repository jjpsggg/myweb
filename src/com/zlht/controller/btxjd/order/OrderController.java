package com.zlht.controller.btxjd.order;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.mysql.jdbc.DatabaseMetaData;
import com.zlht.service.btxjd.credit.CreditManager;
import com.zlht.service.btxjd.eleccontract.ElecContractManager;
import com.zlht.service.btxjd.eleclog.ElecLogManager;
import com.zlht.service.btxjd.quota.QuotaManager;
import com.zlht.service.btxjd.shopuser.ShopUserManager;
import com.zlht.service.btxjd.systemquota.SystemQuotaManager;
import com.zlht.util.*;
import com.zlht.util.fadada.FddClientBase;
import com.zlht.util.fadada.util.RandomUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zlht.controller.base.BaseController;
import com.zlht.entity.Page;
import com.zlht.service.btxjd.order.OrderManager;

/* 
 * 说明：订单表（包括商城订单及现金贷放款）
 * 创建人：ZLHT
 * 创建时间：2016-12-04
 */
@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	
	private String menuUrl = "order/list.do"; //菜单地址(权限用)
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="eleccontractService")
	private ElecContractManager eleccontractService;
	@Resource(name="eleclogService")
	private ElecLogManager eleclogService;
	@Resource(name="systemquotaService")
	private SystemQuotaManager systemquotaService;
	@Resource(name="quotaService")
	private QuotaManager quotaService;
	@Resource(name="shopuserService")
	private ShopUserManager shopuserService;
	@Resource(name="creditService")
	private CreditManager creditService;

	/*
	 *保存订单
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Order");
		JSONObject outobj ;
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String ELECCONTRACT_ID = this.get32UUID();//电子合同id
		PageData pd = this.getPageData();
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

// 		保存电子合同日志
		PageData eleclog = new PageData();
		eleclog.put("USERID","system");
		eleclog.put("TYPERESULT",1);
		eleclog.put("REMARK",result);
		eleclog.put("CREATETIME", System.currentTimeMillis());
		eleclog.put("ELECLOG_ID",this.get32UUID());
		eleclog.put("ELECCONTRACT_ID",ELECCONTRACT_ID);
		eleclogService.save(eleclog);
//		企业端自动签署
		String customer_id = PropUtil.getValue("/conf", "customer_id");
		String sign_keyword = PropUtil.getValue("/conf", "appsign");
		String client_role = "1";
		String batch_id = "";
		String client_type = "";
		String notify_url = "";
		String httpOrgCreateTestRtn = FddClientBase.invokeExtSignAuto(transaction_id, customer_id, batch_id,
				client_type,client_role, contract_id, doc_title, sign_keyword, notify_url);
		outobj = JSONObject.fromObject(httpOrgCreateTestRtn);
		result = outobj.getString("result")+" "+outobj.getString("msg");
//		保存电子合同日志
		PageData eleclog1 = new PageData();
		eleclog1.put("USERID","system");
		eleclog1.put("TYPERESULT",2);
		eleclog1.put("REMARK",result);
		eleclog1.put("CREATETIME", System.currentTimeMillis());
		eleclog1.put("ELECLOG_ID",this.get32UUID());
		eleclog1.put("ELECCONTRACT_ID",ELECCONTRACT_ID);
		eleclogService.save(eleclog1);
		String msg = "success";
		try {
			orderService.save(pd);
		}catch (Exception ex){
			logger.error(ex.getMessage());
			msg = "error";
		}
		mv.addObject("msg",msg);
		mv.addObject("ORDER_ID",pd.getString("ORDER_ID"));
		mv.setViewName("btxjd/order/order_save");
		return mv;
	}

	/*
	 *去签订合同，返回签合同地址
	 */
	@RequestMapping(value="/confirmContract")
	public ModelAndView confirmContract() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"根据订单id返回电子合同签订地址");
		JSONObject outobj ;
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd;
		pd = this.getPageData();

//		返回用户手动动签署地址
		String return_url="http://localhost";
		String transaction_id = System.currentTimeMillis() + RandomUtils.nextInt(10)+"";
		String sign_keyword=PropUtil.getValue("/conf", "customersign");
		String customer_id = "6E7846938ED9A2DD";
		String doc_title = "";
		String notify_url = "";
		String contract_id = "";

//		根据订单号获得合同id及合同信息

//		根据订单号获得渠道id，再根据渠道id+userid获得customer_id

//		生成电子合同地址
		String get_url = FddClientBase.invokeExtSign(transaction_id, contract_id, return_url,"" , customer_id,
				doc_title, notify_url, sign_keyword);

		mv.addObject("msg","success");
		mv.addObject("get_url",get_url);
		mv.setViewName("btxjd/order/order_save");
		return mv;
	}

	
	/*删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Order");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd;
		pd = this.getPageData();
		orderService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/*修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Order");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		orderService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/*列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd =  this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = orderService.list(page);	//列出Order列表
		mv.setViewName("btxjd/order/order_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/*去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd =  this.getPageData();
		mv.setViewName("btxjd/order/order_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /*去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd =  this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		mv.setViewName("btxjd/order/order_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/*去放款页面
    * @param
    * @throws Exception
    */
	@RequestMapping(value="/goDeal")
	public ModelAndView goDeal()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd =  this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		PageData pageData = shopuserService.findByUserId(pd);
		List<PageData> quotaList = systemquotaService.listAll(pd);
//		根据订单id列出所有可选放款机构
		List<PageData> opd = orderService.listAllOrg(pd);	//根据ID读取
		mv.setViewName("btxjd/order/deal_edit");
		mv.addObject("quota", pageData);
		mv.addObject("maxquota",quotaList.get(0));
		mv.addObject("listorg",opd);
		mv.addObject("pd", pd);
		return mv;
	}

	/*放款
    * @param
    * @throws Exception
    */
	@RequestMapping(value="/savedeal")
	public ModelAndView deal()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd =  this.getPageData();
		try {
			if(pd.getString("ORDERSTATUS").equals("3")){//放款
				int ii = Double.valueOf(pd.getString("INSTALMENT")).intValue();
				//放款，调用第三方接口，转移资金
				//更新放款机构
				orderService.edit(pd);
				//生成账单
				for(int i=0;i<ii;i++){
					Double perValue = new BigDecimal(Double.valueOf(pd.getString("ORDERVALUE"))/ii).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() ;//每期本金
					Double perIns = Double.valueOf(pd.getString("ORDERVALUE")) * Double.valueOf(pd.getString("INSTALMENTRATE"));//分期期费
					Double insFee = Double.valueOf(pd.getString("INSTALMENTRATE"));//手续费
					pd.put("INSTALMENTORDER",i+1);
					if(i==0)
						pd.put("INSTALMENTFEE",perValue+perIns+insFee+(Double.valueOf(pd.getString("ORDERVALUE"))-perValue*ii));
					else
						pd.put("INSTALMENTFEE",perValue+perIns);
					pd.put("STARTTIME",new Date(DateUtil.getDayBeginTimestamp()+i*(30*60*60*24*1000L)));
					pd.put("ENDTIME",new Date(DateUtil.getDayBeginTimestamp()+(i+1)*(30*60*60*24*1000L)));
					pd.put("CREDIT_ID",this.get32UUID());
					creditService.save(pd);
				}
			}
			//				保存日志
			pd.put("TYPERESULT",pd.get("ORDERSTATUS"));
			pd.put("CREATETIME", System.currentTimeMillis());
			pd.put("ELECLOG_ID",this.get32UUID());
			pd.put("REMARK",pd.get("REMARK"));
			eleclogService.save(pd);
//			合同归档
			FddClientBase.invokeContractFilling(pd.getString("CONTRACT_ID"));
//			更新合同状态
			eleccontractService.updateStatus(pd);
//			保存放款机构
			orderService.edit(pd);
//			更新个人额度，冻结额度转已用额度
			//增加冻结额度
			PageData pds = new PageData();
			pds.put("USERID",pd.get("USERID"));
			pds.put("PRODUCTTYPE",pd.get("PRODUCTTYPE"));
			if(pd.get("PRODUCTTYPE").equals("1")){
				pds.put("BTUSEDQUOTA",Double.valueOf(pd.getString("ORDERVALUE")));
				pds.put("BTDISABLEQUOTA",-Double.valueOf(pd.getString("ORDERVALUE")));
			}
			else{
				pds.put("XJDUSEDQUOTA",Double.valueOf(pd.getString("ORDERVALUE")));
				pds.put("XJDDISABLEQUOTA",-Double.valueOf(pd.getString("ORDERVALUE")));
			}
			shopuserService.editusedquota(pds);
		}catch (Exception ex){
			logger.error(ex.getMessage());
		}
		pd = orderService.findById(pd);	//根据ID读取
		mv.setViewName("save_result");
		mv.addObject("msg", "success");
		mv.addObject("pd", pd);
		return mv;
	}

	/*批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Order");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = this.getPageData();
		Map<String,Object> map = new HashMap<>();

		List<PageData> pdList = new ArrayList<>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			orderService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /*导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Order到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv ;
		PageData pd =  this.getPageData();
		Map<String,Object> dataMap = new HashMap<>();
		List<String> titles = new ArrayList<>();
		titles.add("订单ID");	//1
		titles.add("订单名称");	//2
		titles.add("订单金额");	//3
		titles.add("订单状态");	//4
		titles.add("订单时间");	//5
		titles.add("创建时间");	//6
		titles.add("分期数");	//7
		titles.add("分期费率");	//8
		dataMap.put("titles", titles);
		List<PageData> varOList = orderService.listAll(pd);
		List<PageData> varList = new ArrayList<>();
		for (PageData aVarOList : varOList) {
			PageData vpd = new PageData();
			vpd.put("var1", aVarOList.getString("ORDERID"));        //1
			vpd.put("var2", aVarOList.getString("ORDERNAME"));        //2
			vpd.put("var3", aVarOList.get("ORDERVALUE").toString());    //3
			vpd.put("var4", aVarOList.get("ORDERSTATUS").toString());    //4
			vpd.put("var5", aVarOList.getString("ORDERTIME"));        //5
			vpd.put("var6", aVarOList.getString("CREATETIME"));        //6
			vpd.put("var7", aVarOList.get("INSTALMENT").toString());    //7
			vpd.put("var8", aVarOList.get("INSTALMENTRATE").toString());    //8
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
