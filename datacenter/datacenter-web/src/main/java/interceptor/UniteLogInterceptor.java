package interceptor;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 请求日志拦截
 * Created by yhx on 2016/4/11.
 */
public class UniteLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(UniteLogInterceptor.class);

    //保证现成安全
    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("StopWatch-StartTime");
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        logger.info("==start controller=== "+request.getRequestURL().toString()+" ===start controller==");
        Enumeration<String> e = request.getParameterNames();
        StringBuffer sb = new StringBuffer("Parameter:[");
        if (e != null){
            while(e.hasMoreElements()){
                String param = e.nextElement();
                sb.append(param+":");
                String[] values = request.getParameterValues(param);
                if(values.length > 0){
                    if(values.length == 1){
                        sb.append(values[0]);
                    }else{
                        sb.append("[");
                        for (String s : values){
                            sb.append(s + ",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append("]");
                    }
                }
                sb.append("  ");
            }
        }
        sb.append("]");
        logger.info(sb.toString());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("==end controller=== "+request.getRequestURL().toString()+" ===end controller==");
        if (ex != null){
            logger.error("====error===="+ex.getMessage());
        }
        long endTime = System.currentTimeMillis();
        long startTime = startTimeThreadLocal.get();


        long useTime = endTime - startTime;

        if(useTime > 50L){
            logger.info(request.getRequestURI() + " consume time is " + useTime);
        }
    }
}
