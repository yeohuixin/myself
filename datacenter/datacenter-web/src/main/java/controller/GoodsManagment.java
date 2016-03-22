package controller;

import dao.bean.GoodsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhx on 2016/3/16.
 */
@Controller("/goodsmanagment")
public class GoodsManagment {
    private static final Logger logger = Logger.getLogger(HelloWord.class);

    @Autowired
    GoodsService goodsService;

    @RequestMapping("goodsmanagment")
    public
    @ResponseBody
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
