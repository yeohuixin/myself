package interceptor;

import Utils.JsonUtils;
import Utils.SessionUtils;
import Utils.StringUtils;
import com.google.gson.reflect.TypeToken;
import controller.login.LoginContext;
import dao.bean.SysFuncResource;
import dao.bean.UserInfo;
import http.CommonHttpClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import service.UserInfoService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yhx on 2016/4/11.
 */
public class CheckPrivilegeInterceptor  implements HandlerInterceptor {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CheckPrivilegeInterceptor.class);
    @Resource
    private UserInfoService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取信息
        UserInfo user = SessionUtils.getCurrentUser(request);
        String url = request.getRequestURI();

        if(url.contains("/platform/logout")){
            //如果是登出，直接放行
            return true;
        }
        // 如果未登录
        if (user == null) {
            if (url.contains("/platform/toLogin") || url.contains("/platform/login") || url.equals("/") || url.equals("")) {//为了兼容IndexController的index方法
                // 如果是去登录，就放行
                return true;
            } else {
                // 如果不是去登录，就转到登录页面
                response.sendRedirect("/platform/toLogin");
                return false;
            }
        }
        // 如果已登录，就判断权限 然后存LoginContext
        else {
            setSessionValue(request, user);
        }
        return true;
    }

    private void setSessionValue(HttpServletRequest request, UserInfo user) {
        LoginContext loginContext = new LoginContext();
        loginContext.setUserId(user.getUserId());
        loginContext.setLoginName(user.getLoginName());
        loginContext.setNickName(user.getLoginName());
        LoginContext.setLoginContext(loginContext);

        try {
            HttpSession session = request.getSession();
//            List<String> resourceNameList = (List<String>) session.getAttribute("resourceNameList");
//            if(resourceNameList == null){
//                //取登录用户所有的权限集合
//                List<SysFuncResource> resourceList = userService.getAllResourceByUserId(user.getUserId());
//                resourceNameList = new ArrayList<String>();
//                for(SysFuncResource resource : resourceList){
//                    resourceNameList.add(resource.getName());
//                }
//                session.setAttribute("resourceNameList", resourceNameList);
//            }
        } catch (Exception e) {
        }
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
