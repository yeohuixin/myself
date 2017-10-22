package manager.impl;

import core.exception.MessageException;
import dao.GoodsMapper;
import dao.bean.GoodsBean;
import manager.GoodsManager;
import manager.UserInfoManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserInfoService;
import service.impl.GoodsServiceImpl;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by D7070 on 2017/10/16.
 */
@Component
public class GoodsManagerImpl implements GoodsManager {
    private static final Logger logger = Logger.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;

    public int save(GoodsBean goodsBean) {
        logger.info("goods Mapper start now!!");
        String id = goodsBean.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        goodsBean.setId(id);
        int count = goodsMapper.insert(goodsBean);
        if (count == 1 && false) {
            //不回滚
            throw new MessageException();
            //回滚
//            throw new RuntimeException("error");
        }
        return count;
    }

    public List<GoodsBean> selectAll() {
        logger.info("select all start");
        List<GoodsBean> goodsBeens = goodsMapper.selectAll();
        if (goodsBeens != null && goodsBeens.size() > 1) {
            logger.info("all info is " + goodsBeens.get(0));
        }
        return goodsBeens;
    }
}
