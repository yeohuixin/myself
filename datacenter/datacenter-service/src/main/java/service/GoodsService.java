package service;

import dao.bean.GoodsBean;

import java.util.List;

/**
 * Created by yhx on 2016/3/17.
 */
public interface GoodsService {
    int save(GoodsBean goodsBean);
    List<GoodsBean> selectAll();
}
