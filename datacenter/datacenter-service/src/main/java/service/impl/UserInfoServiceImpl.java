package service.impl;

import dao.UserInfoMapper;
import dao.bean.SysFuncResource;
import dao.bean.UserInfo;
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
    @Resource
    UserInfoMapper userInfoMapper;

    public int save(UserInfo userInfo){
        logger.info("account save start now!!!");
        int count = userInfoMapper.insert(userInfo);
        return count;
    }

    public UserInfo getUserInfoByLoginNamePassword(String loginName,String password){
        UserInfo userInfo = userInfoMapper.selectByLoginnamePassword(loginName, password);
        return userInfo;
    }

    public UserInfo getUserInfoByLoginName(String loginName){
        UserInfo userInfo = userInfoMapper.selectByLoginname(loginName);
        return userInfo;
    }

//    public List<SysFuncResource> getAllResourceByUserId(String userId) {
//        return null;
//    }

}
