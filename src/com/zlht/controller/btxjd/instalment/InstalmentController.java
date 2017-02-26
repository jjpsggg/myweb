package com.zlht.controller.btxjd.instalment;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zlht.controller.base.BaseController;
import com.zlht.entity.Page;
import com.zlht.util.AppUtil;
import com.zlht.util.ObjectExcelView;
import com.zlht.util.PageData;
import com.zlht.util.Jurisdiction;
import com.zlht.util.Tools;
import com.zlht.service.btxjd.instalment.InstalmentManager;

/** 
 * 说明：分期费率管理
 * 创建人：ZLHT
 * 创建时间：2016-12-11
 */
@Controller
@RequestMapping(value="/instalment")
public class InstalmentController extends BaseController {
	
	String menuUrl = "instalment/list.do"; //菜单地址(权限用)
	@Resource(name="instalmentService")
	private InstalmentManager instalmentService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public void save(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Instalment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){out.close();} //校验权限
//		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("INSTALMENT_ID", this.get32UUID());	//主键
		instalmentService.save(pd);
//		mv.addObject("msg","success");
//		mv.addObject("pd",pd);
//		mv.setViewName("/instalment/list.do");
//		return mv;
		out.write("success");
		out.close();
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Instalment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		instalmentService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Instalment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		instalmentService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Instalment");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		page.setShowCount(100);
		List<PageData>	varList = instalmentService.list(page);	//列出Instalment列表
		List<PageData>  listTerm = instalmentService.listTerm(pd);//列出当前渠道产品下可用放款机构的期次
		if(pd.get("showonly")!=null && pd.get("showonly").equals("1"))
			mv.setViewName("btxjd/instalment/instalment_list_1");
		else
			mv.setViewName("btxjd/instalment/instalment_list");
		mv.addObject("varList", varList);
		mv.addObject("listTerm", listTerm);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}

	/**列表
	 * @throws Exception
	 */
	@RequestMapping(value="/listMax")
	@ResponseBody
	public Object listMax() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Instalment");
		Map<String,PageData> map = new HashMap<String,PageData>();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pageData = instalmentService.findByPd(pd);//列出当前渠道产品下可用放款机构的期次
		map.put("result",pageData);
		return AppUtil.returnObject(new PageData(), map);
	}

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list_o")
	public ModelAndView list_o(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Instalment");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		page.setShowCount(100);
		List<PageData>	varList = instalmentService.list(page);	//列出Instalment列表
		if(pd.get("showonly")!=null && pd.get("showonly").equals("1"))
			mv.setViewName("btxjd/instalment/instalment_list_1");
		else
			mv.setViewName("btxjd/instalment/instalment_o_list");
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
		mv.setViewName("btxjd/instalment/instalment_edit");
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
		pd = instalmentService.findById(pd);	//根据ID读取
		mv.setViewName("btxjd/instalment/instalment_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Instalment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			instalmentService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Instalment到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("分期类型ID");	//1
		titles.add("分期类型名称");	//2
		titles.add("分期期次");	//3
		titles.add("分期费率");	//4
		titles.add("手续费");	//5
		titles.add("逾期罚息费率");	//6
		titles.add("逾期手续费");	//7
		dataMap.put("titles", titles);
		List<PageData> varOList = instalmentService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TYPEID"));	    //1
			vpd.put("var2", varOList.get(i).getString("TYPENAME"));	    //2
			vpd.put("var3", varOList.get(i).get("ITEM").toString());	//3
			vpd.put("var4", varOList.get(i).get("FEERATE").toString());	//4
			vpd.put("var5", varOList.get(i).get("COMMISSION").toString());	//5
			vpd.put("var6", varOList.get(i).get("OVERDUEFEERATE").toString());	//6
			vpd.put("var7", varOList.get(i).get("OVERDUECOMMISSION").toString());	//7
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
