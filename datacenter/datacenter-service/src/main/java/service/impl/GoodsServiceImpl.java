package service.impl;

import dao.GoodsMapper;
import dao.bean.GoodsBean;
import dao.bean.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserInfoService;
import service.GoodsService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yhx on 2016/3/17.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger logger = Logger.getLogger(GoodsServiceImpl.class);

    @Autowired
    private UserInfoService accountService;
    @Resource
    private GoodsMapper goodsMapper;

    public int save(GoodsBean goodsBean) {
        logger.info("goods Mapper start now!!");
        String id = goodsBean.getId();
        if(id == null){
            id = UUID.randomUUID().toString();
        }
        goodsBean.setId(id);
        int count = goodsMapper.insert(goodsBean);
        return count;
    }
}
