package dao;

import dao.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);
    UserInfo selectByLoginname(@Param("loginName")String loginName);
    UserInfo selectByLoginnamePassword(@Param("loginName")String loginName,@Param("password")String password);
    UserInfo selectFirst(UserInfo record);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}