package controller;

import Utils.AbstractJsonUtils;
import com.google.gson.reflect.TypeToken;
import dao.bean.GoodsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.GoodsService;
import utils.Result;
import utils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        try{
            request.setCharacterEncoding("UTF-8");
        }catch (Exception e){
            logger.error("requst error info is " + e);
        }
        String goodsName = request.getParameter("goodsName");
        String goodsCount = request.getParameter("goodsCount");
        String goodsUnitPrice = request.getParameter("goodsUnitPrice");

        logger.info("goods name is " + goodsName + " goods count is " + goodsCount);
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodsname(goodsName);
        goodsBean.setGoodscount(Long.valueOf(goodsCount));
        goodsBean.setGoodsunitprice(Double.valueOf(goodsUnitPrice));
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

        return AbstractJsonUtils.toJson(result, type);
    }

    /**
     * 增加了produces 解决了返回前端乱码的问题
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectGoods", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
//    @RequestMapping(value = "/selectGoods")
    public
    @ResponseBody
    String selectGoods(HttpServletRequest request, HttpServletResponse response){
        logger.info("select goods now");
//        try{
//            request.setCharacterEncoding("UTF-8");
//        }catch (Exception e){
//            logger.error("requst error info is " + e);
//        }
        List<GoodsBean> goodsBeanList = new ArrayList<GoodsBean>();

        try{
            goodsBeanList = goodsService.selectAll();
        }catch (Exception e){
            logger.info("error info is " + e);
        }
        Result<List<GoodsBean>> result = new Result<List<GoodsBean>>();
        result.setCode(ResultCode.SUCCESS);

        result.setMsg("success");
        result.setTimestamp(new Date());
        result.setData(goodsBeanList);
        Type type = new TypeToken<Result<List<GoodsBean>>>(){}.getType();
        String coding = null;
//        coding = response.getCharacterEncoding();
//        String type1 = response.getContentType();
//        logger.info("coding is " + coding + " type is " + type1);
//        response.setCharacterEncoding("utf-8");
        coding = response.getCharacterEncoding();
        logger.info("coding after is " + coding);
//        response.setContentType("text/html;charset=utf-8");
        String ifo = AbstractJsonUtils.toJson(result, type);
        logger.info("info is " + ifo);
        /**
         * 如果没有增加produce 需要直接使用response的writer写入不会有乱码，不能直接返回json字符串
         */
//        try {
//            response.getWriter().print(ifo);
//        }catch (Exception e){
//
//        }
        return ifo;
    }
}
