package manager;

import dao.bean.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by D7070 on 2017/10/16.
 */
public interface UserInfoManager {
    int save(UserInfo userInfo);
    UserInfo getFirst(UserInfo userInfo);
    UserInfo getUserInfoByLoginNamePassword(String loginName, String password);
    UserInfo getUserInfoByLoginName(String loginName);
}
