package log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by yhx on 2016/4/11.
 */
public class LogKit {
    public static long Default_Slow_Time = 500;

    private static Logger debugLog = Logger.getLogger("debug");
    private static Logger infoLog = Logger.getLogger("info");
    private static Logger warnLog = Logger.getLogger("warn");
    private static Logger errorLog = Logger.getLogger("error");

    static{
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                LogManager.shutdown();
            }
        });
    }

    public static boolean isDebugEnabled() {
        return debugLog.isDebugEnabled();
    }

    public static void debug(Object msg) {
        debugLog.debug(msg);
    }
    public static void info(Object msg) {
        infoLog.info(msg);
    }
    public static void warn(Object msg) {
        warnLog.warn(msg);
    }
    public static void warn(Object msg, Throwable e) {
        warnLog.warn(msg, e);
    }
    public static void error(Object msg) {
        errorLog.error(msg);
    }
    public static void error(Object msg, Throwable e) {
        errorLog.error(msg, e);
    }

}
