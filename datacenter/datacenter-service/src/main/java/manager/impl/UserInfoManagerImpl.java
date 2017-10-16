package manager.impl;

import dao.UserInfoMapper;
import dao.bean.UserInfo;
import manager.GoodsManager;
import manager.UserInfoManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.impl.UserInfoServiceImpl;

import javax.annotation.Resource;

/**
 * Created by D7070 on 2017/10/16.
 */
@Component
public class UserInfoManagerImpl implements UserInfoManager {
    private static final Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
    @Autowired
    UserInfoMapper userInfoMapper;

    public int save(UserInfo userInfo){
        logger.info("account save start now!!!");
        int count = userInfoMapper.insert(userInfo);
        return count;
    }

    public UserInfo getFirst(UserInfo userInfo){
        return userInfoMapper.selectFirst(userInfo);
    }

    public UserInfo getUserInfoByLoginNamePassword(String loginName,String password){
        UserInfo userInfo = userInfoMapper.selectByLoginnamePassword(loginName, password);
        return userInfo;
    }

    public UserInfo getUserInfoByLoginName(String loginName){
        UserInfo userInfo = userInfoMapper.selectByLoginname(loginName);
        return userInfo;
    }
}
