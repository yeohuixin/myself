package service.impl;

import core.exception.MessageException;
import dao.GoodsMapper;
import dao.bean.GoodsBean;
import dao.bean.UserInfo;
import manager.GoodsManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserInfoService;
import service.GoodsService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yhx on 2016/3/17.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger logger = Logger.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsManager goodsManager;

    public int save(GoodsBean goodsBean) throws Exception {
        try{
            return goodsManager.save(goodsBean);
        }catch (MessageException e){
            throw e;
        }catch (RuntimeException e){
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<GoodsBean> selectAll(){
        return goodsManager.selectAll();
    }


}
