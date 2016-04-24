package controller;

import Utils.JsonUtils;
import com.google.gson.reflect.TypeToken;
import dao.bean.GoodsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.GoodsService;
import utils.Result;
import utils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.Date;

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

    @RequestMapping("/addGoods")
    public
    @ResponseBody
    String addGoods(HttpServletRequest request, HttpServletResponse response){
        logger.info("add goods now");
        String goodsName = request.getParameter("goodsName");
        String goodsCount = request.getParameter("goodsCount");
        String goodsUnitPrice = request.getParameter("goodsUnitPrice");

        logger.info("goods name is " + goodsName + " goods count is " + goodsCount);
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodsname(goodsName);
        goodsBean.setGoodscount(Long.valueOf(goodsCount));
        goodsBean.setCreatetime(new Date());
        try{
            goodsService.save(goodsBean);
        }catch (Exception e){
            logger.info("error info is " + e);
        }
        Result<String> result = new Result<String>();
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("success");
        result.setTimestamp(new Date());
        Type type = new TypeToken<Result<String>>(){}.getType();

        return JsonUtils.toJson(result, type);
    }


}
