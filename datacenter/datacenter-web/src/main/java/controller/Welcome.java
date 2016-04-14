package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhx on 2016/3/9.
 */
@Controller()
@RequestMapping("/welcome")
public class Welcome {
    private static final Logger logger = Logger.getLogger(Welcome.class);

    @RequestMapping(value = "/welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public
     ModelAndView welcome(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        logger.info(" login controler start now!");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome/welcome");
        return mv;
    }
}
