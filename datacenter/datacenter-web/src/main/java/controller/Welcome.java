package controller;

import login.Login;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhx on 2016/3/9.
 */
@Controller("/welcome")
public class Welcome {
    private static final Logger logger = Logger.getLogger(Login.class);

    @RequestMapping("welcome")
    public ModelAndView welcome(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        logger.info(" login controler start now!");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }
}
