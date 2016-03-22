package interceptor;

import dao.bean.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.UserInfoService;

import javax.rmi.CORBA.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * Created by yhx on 2016/3/7.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Autowired
    UserInfoService userInfoService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("login interceptor now");
        if (checkLogIn(request, response, handler)) {
            return true;
        } else {
            //调转到登陆页面？？？
            logger.info("jump to login page");
            String loginUrl = "http://127.0.0.1/login";
            response.sendRedirect(loginUrl);
            return false;
        }
    }

    /**
     * cookie 里面的sessionId 与 服务器端的sessionId 匹配。
     * 并且服务器端的sessionid
     * 已经缓存用户登录名或者用户标识符 则用户已经登陆完成
     * <p/>
     * <p/>
     * 查看请求是否有cookei。如果没有则新建
     * 如果有则查看是否包含sessionid
     * 如果没有则验证登陆
     * 如果有则根据sessionId查看登陆信息
     * 如果存在登陆信息则认为登陆成功
     * 如果没有则返回登陆页面
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    private boolean checkLogIn(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("check log in now");
        String datacenterSessionId = null;
        Cookie dataCenterCookie = null;
        String serviceName = "datacenter";
//        Cookie cookie = new Cookie("loginName","admin");
//        response.addCookie(cookie);
        String loginUri = request.getRequestURI();
        StringBuffer loginUrl = request.getRequestURL();
        logger.info("loginUri is " + loginUri  + " loginUrl is " + loginUrl);

        String loginName = request.getParameter("loginName");
        String loginPasswork = request.getParameter("loginPassword");
        logger.info("login name is " + loginName + " login password is " + loginPasswork);
        if (request.getRequestURI().toLowerCase().equalsIgnoreCase("/hello") || request.getRequestURI().toLowerCase().equalsIgnoreCase("/login")) {
            return true;
        } else {
            //为了第二次访问welcome不失败也要验证一次session
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    logger.info("cookie is " + cookie.getName() + " " + cookie.getValue());
                    if (serviceName.equalsIgnoreCase(cookie.getName())) {
                        dataCenterCookie = cookie;
                        break;
                    }
                }
            }
            if (dataCenterCookie != null) {
                datacenterSessionId = dataCenterCookie.getValue();
                if (datacenterSessionId != null) {
                    HttpSession httpSession = request.getSession();
                    if (httpSession != null) {
                        //serviceId 可以是用户名或者用户标识
                        String serviceId = (String) httpSession.getAttribute(datacenterSessionId);
                        String sessionId = httpSession.getId();
                        if(sessionId != null){
                            logger.info("session id is " + serviceId);
                            //说明服务端session已经失效。但是cookie还在。需要清理cookie重新登陆
//                            for (int i= 0 ; i < cookies.length;i++) {
//                                logger.info("cookie is " + cookies[i].getName() + " " + cookies[i].getValue());
//                                if (serviceName.equalsIgnoreCase(cookies[i].getName())) {
//                                    logger.info("clear cookies id is " + cookies[i].getName() + " " + cookies[i].getValue());
////                                    cookies[i] = null;
//                                    return false;
////                                    break;
//                                }
//                            }
                        }
                        if (serviceId != null) {
                            //这里比较一下serviceId是否有效，查看serviceId 是否 是存在的用户？？？
                            //默认 存在就成功
                            logger.info("service id is " + serviceId);
                            return true;
                        }
                    };
                }
            }



            //验证登录名和密码
            logger.info("request url is " + request.getRequestURL());
            if (loginName == null || loginPasswork == null) {
                logger.info("login name is " + loginName + " login passwork is " + loginPasswork + " is null");
                return false;
            } else {
                //验证用户名和密码是否正确
                UserInfo userInfo = userInfoService.getUserInfoByLoginNamePassword(loginName,loginPasswork);
                if (userInfo != null) {
                    //用户名和密码正确
                    logger.info("check pass!!!");
                    //check pass 写入cookieId
                    UUID id = UUID.randomUUID();
                    Cookie[] cookies1 = request.getCookies();
                    String cookieName = "datacenter";
                    if (cookies1 != null) {
                        for (Cookie cookie : cookies1) {
                            if (cookie != null) {
                                String name = cookie.getName();
                                if (cookieName.equalsIgnoreCase(name)) {
                                    dataCenterCookie = cookie;
                                    break;
                                }
                            }
                        }
                    }
                    if (dataCenterCookie != null) {
                        //查看是否有sessionid 无论有没有都要重置sessionId
                        dataCenterCookie.setValue(id.toString());
                        response.addCookie(dataCenterCookie);
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute(id.toString(),"userName");
                        httpSession.setMaxInactiveInterval(30 * 60);
                    } else {
                        //新建cookie
                        Cookie cookie = new Cookie(serviceName, id.toString());
                        response.addCookie(cookie);
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute(id.toString(),"userName");
                        httpSession.setMaxInactiveInterval(30 * 60);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object handler, java.lang.Exception ex) throws java.lang.Exception {
        logger.info("login  4 interceptor after");
        return;
    }
}
