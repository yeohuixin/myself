package dao;

import dao.bean.GoodsBean;

public interface GoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsBean record);

    int insertSelective(GoodsBean record);

    GoodsBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsBean record);

    int updateByPrimaryKey(GoodsBean record);
}