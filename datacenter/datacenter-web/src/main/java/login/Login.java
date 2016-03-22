package login;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhx on 2016/3/9.
 */
@Controller("/login")
public class Login {
    private static final Logger logger = Logger.getLogger(Login.class);

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        logger.info(" login controler start now!");
        ModelAndView mv = new ModelAndView();

        //添加模型数据 可以是任意的POJO对象
        mv.addObject("message","login");
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("login");
        return mv;
    }
}
