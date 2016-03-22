package interceptor;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhx on 2015/12/21.
 */
public class Request4Interceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(Request4Interceptor.class);
    /**
     * 我们的拦截器是单例，因此不管用户请求多少次都只有一个拦截器实现，即线程不安全，那我们应该怎么记录时间呢？
     * 解决方案是使用ThreadLocal，它是线程绑定的变量，
     * 提供线程局部变量（
     * 一个线程一个ThreadLocal，A线程的ThreadLocal只能看到A线程的ThreadLocal，不能看到B线程的ThreadLocal）
     */
    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("StopWatch-StartTime");
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request 4 interceptor now!!!");
        logger.info("request 4 interceptor now");
        long beginTime = System.currentTimeMillis();

        startTimeThreadLocal.set(beginTime);



        return true;
    }

    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object handler, java.lang.Exception ex) throws java.lang.Exception {

        logger.info("request 4 interceptor after");
        long endTime = System.currentTimeMillis();
        long startTime = startTimeThreadLocal.get();


        long useTime = endTime - startTime;

        if(useTime > 50L){
            logger.info(request.getRequestURI() + " consum time is " + useTime);
        }
        return;
    }


}
