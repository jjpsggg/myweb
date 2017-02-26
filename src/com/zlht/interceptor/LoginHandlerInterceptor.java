package com.zlht.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zlht.util.PropUtil;
import com.zlht.util.fadada.model.UserInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.zlht.entity.system.User;
import com.zlht.util.Const;
import com.zlht.util.Jurisdiction;
/**
 * 
* 类名称：登录过滤，权限验证
* 类描述： 
* @author GZ
* 作者单位： 
* 联系方式：
* 创建时间：2015年11月2日
* @version 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			if(PropUtil.getValue("/conf", "debug").equals("true")){//debug模式，用户信息写死
				UserInfo userInfo = new UserInfo();
				userInfo.setCustomerName("章张璋");
				userInfo.setEmail("21140@qq.com");
				userInfo.setIdCard("21140219791108466X");
				userInfo.setMobile("13811354259");
				HttpSession session = request.getSession();
				session.setAttribute("customer", userInfo);
			}else{
//			判断是否已经登录，且通过了实名认证，是则保存实名认证信息，否则返回登录页面
//			response.sendRedirect("login");
			}

			return true;
		}else{
			User user = (User)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
			if(user!=null){
				path = path.substring(1, path.length());
				boolean b = Jurisdiction.hasJurisdiction(path); //访问权限校验
				if(!b){
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			}else{
				//登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;		
			}
		}
	}
	
}
