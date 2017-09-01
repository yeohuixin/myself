package controller;

import Utils.SessionUtils;
import controller.login.LoginContext;
import dao.bean.SysFuncResource;
import dao.bean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserInfoService;
import Utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhx on 2016/4/11.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ModelAndView login(HttpServletRequest request, @ModelAttribute UserInfo user, Errors errors) {
        ModelAndView modelAndView = new ModelAndView("platform/login");
        HttpSession session = request.getSession(false);
        String id = session.getId();
        UserInfo user1 = SessionUtils.getCurrentUser(request);
        List<String> resourceNameList = (List<String>) session.getAttribute("resourceNameList");
        if(user1 != null){//已登录
            modelAndView.setViewName("platform/index");
            modelAndView.addObject("userName", user1.getLoginName());
            return modelAndView;
        }else {
            if (StringUtils.isBlank(user.getLoginName())) {
                return modelAndView;
            }
            if (StringUtils.isBlank(user.getPassword())) {
                return modelAndView;
            }
            final UserInfo userInDb = userInfoService.getUserInfoByLoginName(user.getLoginName());
            if (userInDb == null) {
                modelAndView.addObject("errorMsg", "登录名不存在");
                errors.rejectValue("loginName", null, "登录名不存在");
                return modelAndView;
            }
            if(userInDb.getStatus() == 0) {
                modelAndView.addObject("errorMsg", "账户被禁用错误");
                errors.reject(null, "账户被禁用错误");
                return modelAndView;
            }
            if (!user.getPassword().equals(userInDb.getPassword())) {
                modelAndView.addObject("errorMsg", "密码错误");
                errors.rejectValue("password", null, "密码错误");
                return modelAndView;
            }
//            if(resourceNameList == null){
//                //取登录用户所有的权限集合
//                List<SysFuncResource> resourceList = userInfoService.getAllResourceByUserId(userInDb.getUserId());
//                resourceNameList = new ArrayList<String>();
//                for(SysFuncResource resource : resourceList){
//                    resourceNameList.add(resource.getName());
//                }
//                session.setAttribute("resourceNameList", resourceNameList);
//            }
            LoginContext loginContext = new LoginContext(userInDb.getUserId(), user.getLoginName(), userInDb.getLoginName());
            LoginContext.setLoginContext(loginContext);
            session.setAttribute("user", userInDb);
            modelAndView.addObject("userName", userInDb.getLoginName());
        }
        modelAndView.setViewName("platform/index");
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}