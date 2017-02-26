package com.zlht.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;

/** 接口参数校验
 * @author: GZ
 * 修改日期：2015/11/2
 */
public class AppUtil  {

	protected static Logger logger = Logger.getLogger(AppUtil.class);

	/**检查参数是否完整
	 * @param method
	 * @param pd
	 * @return
	 */
	public static boolean checkParam(String method, PageData pd){
		boolean result = false;

		int falseCount = 0;
		String[] paramArray = new String[20];
		String[] valueArray = new String[20];
		String[] tempArray  = new String[20];  //临时数组

		if(method=="registered"){// 注册
			paramArray = Const.APP_REGISTERED_PARAM_ARRAY;  //参数
			valueArray = Const.APP_REGISTERED_VALUE_ARRAY;  //参数名称

		}else if(method=="getAppuserByUsernmae"){//根据用户名获取会员信息
			paramArray = Const.APP_GETAPPUSER_PARAM_ARRAY;
			valueArray = Const.APP_GETAPPUSER_VALUE_ARRAY;
		}
		else if(method=="creditSearch"){//查询征信报告
			paramArray = Const.APP_CREDITSEARCH_PARAM_ARRAY;
			valueArray = Const.APP_CREDITSEARCH_VALUE_ARRAY;
		}
		else if(method=="creditContentSave"){//保存征信信息
			paramArray = Const.APP_CREDITSAVE_PARAM_ARRAY;
			valueArray = Const.APP_CREDITSAVE_VALUE_ARRAY;
		}
		else if(method=="creditApplySave"){//保存申请信息
			paramArray = Const.APP_CREDITAPPLYSAVE_PARAM_ARRAY;
			valueArray = Const.APP_CREDITAPPLYSAVE_VALUE_ARRAY;
		}
		else if(method=="creditQuotaSave"){//授信
			paramArray = Const.APP_CREDITQUOTA_PARAM_ARRAY;
			valueArray = Const.APP_CREDITQUOTA_VALUE_ARRAY;
		}
		else if(method=="creditApplyCheck"){//审核
			paramArray = Const.APP_CREDITAPPLYCHECK_PARAM_ARRAY;
			valueArray = Const.APP_CREDITAPPLYCHECK_VALUE_ARRAY;
		}
		else if(method=="quotaList"){//授信额度查询
			paramArray = Const.APP_QUOTALIST_PARAM_ARRAY;
			valueArray = Const.APP_QUOTALIST_VALUE_ARRAY;
		}
		else if(method=="rebackList"){//还款明细查询
			paramArray = Const.APP_REBACKLIST_PARAM_ARRAY;
			valueArray = Const.APP_REBACKLIST_VALUE_ARRAY;
		}
		else if(method=="confirmContract"){//签订合同
			paramArray = Const.APP_CONFIRMCONTRACT_PARAM_ARRAY;
			valueArray = Const.APP_CONFIRMCONTRACT_VALUE_ARRAY;
		}
		else if(method=="orderPay"){//签订合同
			paramArray = Const.APP_ORDERPAY_PARAM_ARRAY;
			valueArray = Const.APP_ORDERPAY_VALUE_ARRAY;
		}
		else if(method=="payList"){//签订合同
			paramArray = Const.APP_PAYLIST_PARAM_ARRAY;
			valueArray = Const.APP_PAYLIST_VALUE_ARRAY;
		}
		else if(method=="escrowPay"){//签订合同
			paramArray = Const.APP_ESCROWPAY_PARAM_ARRAY;
			valueArray = Const.APP_ESCROWPAY_VALUE_ARRAY;
		}
		else if(method=="creditPay"){//签订合同
			paramArray = Const.APP_CREDITPAY_PARAM_ARRAY;
			valueArray = Const.APP_CREDITPAY_VALUE_ARRAY;
		}
		else if(method=="creditStatusChange"){//还款状态修改
			paramArray = Const.APP_CREDITSTATUSCHANGE_PARAM_ARRAY;
			valueArray = Const.APP_CREDITSTATUSCHANGE_VALUE_ARRAY;
		}
		else if(method=="creditTermList"){//还款状态修改
			paramArray = Const.APP_CREDITTERMLIST_PARAM_ARRAY;
			valueArray = Const.APP_CREDITTERMLIST_VALUE_ARRAY;
		}
		else if(method=="orderList"){//还款状态修改
			paramArray = Const.APP_ORDERLIST_PARAM_ARRAY;
			valueArray = Const.APP_ORDERLIST_VALUE_ARRAY;
		}


		int size = paramArray.length;
		for(int i=0;i<size;i++){
			String param = paramArray[i];
			if(!pd.containsKey(param)){
				tempArray[falseCount] = valueArray[i]+"--"+param;
				falseCount += 1;
			}
		}
		if(falseCount>0){
			logger.error(method+"接口，请求协议中缺少 "+falseCount+"个 参数");
			for(int j=1;j<=falseCount;j++){
				logger.error("   第"+j+"个："+ tempArray[j-1]);
			}
		} else {
			result = true;
		}

		return result;
	}

	/**检查参数是否完整
	 * @param paramArray
	 * @param pd
	 * @return
	 */
	public static boolean checkArrayParam(String method,String[] paramArray,String[] valueArray, PageData pd){
		boolean result = false;

		int falseCount = 0;
		String[] tempArray  = new String[30];  //临时数组

		int size = paramArray.length;
		for(int i=0;i<size;i++){
			String param = paramArray[i];
			if(!pd.containsKey(param)){
				tempArray[falseCount] = valueArray[i]+"--"+param;
				falseCount += 1;
			}
		}
		if(falseCount>0){
			logger.error(method+"接口，请求协议中缺少 "+falseCount+"个 参数");
			for(int j=1;j<=falseCount;j++){
				logger.error("   第"+j+"个："+ tempArray[j-1]);
			}
		} else {
			result = true;
		}

		return result;
	}

	/**
	 * 设置分页的参数
	 * @param pd
	 * @return
	 */
	public static PageData setPageParam(PageData pd){
		String page_now_str = pd.get("page_now").toString();
		int pageNowInt = Integer.parseInt(page_now_str)-1;
		String page_size_str = pd.get("page_size").toString(); //每页显示记录数
		int pageSizeInt = Integer.parseInt(page_size_str);
		String page_now = pageNowInt+"";
		String page_start = (pageNowInt*pageSizeInt)+"";
		pd.put("page_now", page_now);
		pd.put("page_start", page_start);
		return pd;
	}

	/**设置list中的distance
	 * @param list
	 * @param pd
	 * @return
	 */
	public static List<PageData>  setListDistance(List<PageData> list, PageData pd){
		List<PageData> listReturn = new ArrayList<PageData>();
		String user_longitude = "";
		String user_latitude = "";
		try{
			user_longitude = pd.get("user_longitude").toString(); //"117.11811";
			user_latitude  = pd.get("user_latitude").toString();  //"36.68484";
		} catch(Exception e){
			logger.error("缺失参数--user_longitude和user_longitude");
			logger.error("lost param：user_longitude and user_longitude");
		}
		PageData pdTemp = new PageData();
		int size = list.size();
		for(int i=0;i<size;i++){
			pdTemp = list.get(i);
			String longitude = pdTemp.get("longitude").toString();
			String latitude = pdTemp.get("latitude").toString();
			String distance = MapDistance.getDistance(
					user_longitude,	user_latitude,
					longitude,		latitude
			);
			pdTemp.put("distance", distance);
			pdTemp.put("size", distance.length());
			listReturn.add(pdTemp);
		}
		return listReturn;
	}

	/**
	 * @param pd
	 * @param map
	 * @return
	 */
	public static Object returnObject(PageData pd, Map map){
		if(pd.containsKey("callback")){
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}else{
			return map;
		}
	}
}
