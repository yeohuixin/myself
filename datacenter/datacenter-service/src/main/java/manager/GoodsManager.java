package manager;

import dao.bean.GoodsBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by D7070 on 2017/10/16.
 */
public interface GoodsManager {
    int save(GoodsBean goodsBean) throws Exception;
    List<GoodsBean> selectAll();
}
