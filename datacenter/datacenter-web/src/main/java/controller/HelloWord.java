package controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yhx on 2016/3/4.
 */
@Controller("/sayhello")
public class HelloWord {
    private static final Logger logger = Logger.getLogger(HelloWord.class);

    @RequestMapping("sayhello")
    public
    @ResponseBody
    void sayHello(HttpServletRequest request, HttpServletResponse response){
        logger.info("say hello");
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
        Gson gson = new Gson();
        PrintWriter out = null;

        try {
            response.setContentType("application/json;charset=utf-8");
            //response.setStatus(302);
//            response.sendRedirect("www.baidu.com");
            out = response.getWriter();
            bs.setCode(200);
            bs.setMsg(form);
            //在ie下打开页面会提示下载。下载一个json文件，但是chrom浏览器下会直接显示内容？？？？？
            String json = gson.toJson(bs);
            out.write(json);
            out.printf(json);
            out.flush();
        } catch (IOException e1) {
            logger.error("return out error info is " + e1);
//            throw new RuntimeException(e1.getMessage(), e1);
        } finally {
            if (null != out) {
                out.close();
                out = null;
            }
            logger.info("create form by string finished");
        }
    }
}
