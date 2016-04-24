package utils;

public class ResultCode {
	
	public static final int SESSION_ERROR = -2;		//Session过期
	public static final int LOGIN_FAILED = -1;		//用户名或密码错误
	
	public static final int ERROR = 0;				//业务逻辑请求出错
	public static final int SUCCESS = 1;			//业务逻辑请求成功
	
	public static final int NEEDUPDATE = 2;			//需要更新
	public static final int DONOTNEEDUPDATE = 3;	//不需要更新
	
}