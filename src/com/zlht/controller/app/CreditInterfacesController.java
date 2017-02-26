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


/**@author GZ
 * 会员-接口类
 * 相关参数协议：
 * 00	请求失败
 * 01	请求成功
 * 02	返回空值
 * 03	请求协议参数不完整
 * 04  用户名或密码错误
 * 05  FKEY验证失败
 * 主要定义银行卡相关接口
 */
@Controller
@RequestMapping(value="/credit")
public class CreditInterfacesController extends BaseController {

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

	/**
	 *查询个人额度信息
	 * @return
	 */
	@RequestMapping(value="/orderList")
	@ResponseBody
	public Object orderList(){
		logBefore(logger, "查询个人额度信息");
		Map<String,Object> map = new HashMap<String,Object>();
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
					result = "01";
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


}
	
 