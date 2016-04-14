package controller.login;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yhx on 2016/3/9.
 */
@Controller
@RequestMapping("/platform")
public class Login {
    private static final Logger logger = Logger.getLogger(Login.class);
    @RequestMapping(value = "/toLogin", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView("platform/login");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody ModelAndView toLogout(HttpServletRequest request){
        LoginContext.remove();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView("platform/login");
        return modelAndView;
    }

}
