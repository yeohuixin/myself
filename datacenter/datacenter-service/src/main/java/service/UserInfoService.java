package service;

import dao.bean.UserInfo;

/**
 * Created by yhx on 2016/3/18.
 */
public interface UserInfoService {
    int save(UserInfo userInfo);

    UserInfo getUserInfoByLoginNamePassword(String loginName,String password);
    UserInfo getUserInfoByLoginName(String loginName);
}
