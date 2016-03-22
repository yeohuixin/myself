import dao.bean.GoodsBean;
import dao.bean.UserInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.GoodsService;

/**
 * Created by yhx on 2016/3/18.
 */
import dao.bean.GoodsBean;
import service.UserInfoService;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class Test {

    @Autowired
    GoodsService goodsService;
    @Autowired
    UserInfoService userInfoService;

    @org.junit.Test
    public void test1(){
        UUID id = UUID.randomUUID();
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setId(id.toString());
        goodsBean.setGoodscount(3L);
        goodsBean.setGoodsname("app");
        goodsService.save(goodsBean);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id.toString());
        userInfo.setLoginName("admin");
        userInfo.setPassword("123456");
        userInfo.setCreateTime(new Date());
        userInfoService.save(userInfo);
    }

    @org.junit.Test
    public void test2(){
        UserInfo userInfo = userInfoService.getUserInfoByLoginName("admin");
        if(userInfo != null){
            System.out.println("ok");
        }
    }
    @org.junit.Test
    public void test3(){
        UserInfo userInfo = userInfoService.getUserInfoByLoginNamePassword("admin","123456");
        if(userInfo != null){
            System.out.println("ok");
        }
    }
}
