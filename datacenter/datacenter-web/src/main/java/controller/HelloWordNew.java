package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by yhx on 2016/3/4.
 */
@Controller("/sayhellonew")
public class HelloWordNew {
    private static final Logger logger = Logger.getLogger(HelloWordNew.class);

    @RequestMapping("sayhellonew")
    public
//    @ResponseBody 这里不能使用这个修饰。
    ModelAndView sayHello(HttpServletRequest request, HttpServletResponse response){
        logger.info("say hello new");
        BaseResult bs = new BaseResult();
        String contect = request.getParameter("contect");
        logger.info("param is " + contect);
        String form = null;
        if(contect != null && contect.equalsIgnoreCase("hello!")){
            logger.info("param is hello");
            form = "hello!";
        }else
        {
            form = "nothing";
        }
//        Gson gson = new Gson();
        PrintWriter out = null;

        ModelAndView mv = new ModelAndView();
        //添加模型数据 可以是任意的POJO对象
        mv.addObject("message",form);
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("hellonew");
        return mv;
    }

}
