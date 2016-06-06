package dao.bean;

import java.util.Date;

/**
 * Created by yhx on 2016/3/17.
 */
public class GoodsBean {
    private String id;

    private Date updatetime;

    private Date createtime;

    private Long goodscount;

    private String goodsname;

    private Double goodsunitprice;

    public Double getGoodsunitprice() {
        return goodsunitprice;
    }

    public void setGoodsunitprice(Double goodsunitprice) {
        this.goodsunitprice = goodsunitprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(Long goodscount) {
        this.goodscount = goodscount;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "id='" + id + '\'' +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                ", goodscount=" + goodscount +
                ", goodsname='" + goodsname + '\'' +
                ", goodsunitprice=" + goodsunitprice +
                '}';
    }
}
