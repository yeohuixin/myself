package Utils;

import dao.bean.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yhx on 2016/4/11.
 */
public class SessionUtils {

    public static UserInfo getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        return user;
    }
}