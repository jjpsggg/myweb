package com.zlht.controller.btxjd.creditapply;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.zlht.entity.system.User;
import com.zlht.service.btxjd.checklog.CheckLogManager;
import com.zlht.service.btxjd.checklog.impl.CheckLogService;
import com.zlht.service.btxjd.quota.QuotaManager;
import com.zlht.service.btxjd.shopuser.ShopUserManager;
import com.zlht.service.btxjd.systemquota.SystemQuotaManager;
import com.zlht.util.*;
import com.zlht.util.fadada.FddClientBase;
import net.sf.json.JSONObject;
import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zlht.controller.base.BaseController;
import com.zlht.entity.Page;
import com.zlht.service.btxjd.creditapply.CreditApplyManager;

/** 
 * 说明：借贷申请
 * 创建人：ZLHT
 * 创建时间：2016-12-04
 */
@Controller
@RequestMapping(value="/creditapply")
public class CreditApplyController extends BaseController {
	
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
	

	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增CreditApply");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
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
		mv.setViewName("front/profile");
		mv.addObject("msg","success");
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
		pd.put("CHANNEL_ID","c0e83e478200420b8ae183b2e79ec841");
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
