package controller;

import dao.bean.GoodsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhx on 2016/3/16.
 */
@Controller
@RequestMapping("/goodsmanagement")
public class GoodsManagement {
    private static final Logger logger = Logger.getLogger(GoodsManagement.class);

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/goodsmanagement")
    public
    @ResponseBody
    ModelAndView goodManagement(HttpServletRequest request, HttpServletResponse response){
        logger.info(" goodManagement start now!");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("platform/goodsmanagement");
        return mv;
    }
    void saveGoods(HttpServletRequest request, HttpServletResponse response){
        logger.info("save goods now");
        String goodsName = request.getParameter("name");
        String goodsCount = request.getParameter("count");

        logger.info("goods name is " + goodsName + " goods count is " + goodsCount);
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodsname(goodsName);
        goodsBean.setGoodscount(Long.valueOf(goodsCount));
        try{
            goodsService.save(goodsBean);
        }catch (Exception e){
            logger.info("error info is " + e);
        }
    }
}
