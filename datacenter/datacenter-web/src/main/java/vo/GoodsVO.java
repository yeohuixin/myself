package vo;

import java.util.Date;

/**
 * Created by yhx on 2016/6/6.
 */
public class GoodsVO {
    private String id;

    private Date updatetime;

    private Date createtime;

    private Long goodscount;

    private String goodsname;

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

}
