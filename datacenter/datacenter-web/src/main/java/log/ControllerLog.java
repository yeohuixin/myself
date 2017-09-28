package log;

import org.aspectj.lang.JoinPoint;

/**
 * Created by D7070 on 2017/9/27.
 */
public class ControllerLog {

    public  void logOut(JoinPoint joinPoint, Object retValue){
        Object object[] = joinPoint.getArgs(); // 获取被切函数 的参数

        if(object != null && object.length > 1) {
            System.out.println("input is " + object[0].toString());
            System.out.println("out put is " + retValue.toString());
        }
    }
}
