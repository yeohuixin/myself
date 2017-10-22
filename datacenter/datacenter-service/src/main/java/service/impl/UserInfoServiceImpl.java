package service.impl;

import dao.UserInfoMapper;
import dao.bean.SysFuncResource;
import dao.bean.UserInfo;
import manager.UserInfoManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserInfoService;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by yhx on 2016/3/18.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

    @Autowired
    UserInfoManager userInfoManager;

    public int save(UserInfo userInfo) {
        return userInfoManager.save(userInfo);
    }

    public UserInfo getUserInfoByLoginNamePassword(String loginName, String password) {
        return userInfoManager.getUserInfoByLoginNamePassword(loginName, password);
    }

    public UserInfo getUserInfoByLoginName(String loginName) {
        return userInfoManager.getUserInfoByLoginName(loginName);
    }
}
