package filter;

import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by yhx on 2015/12/21.
 */
public class Filter4Datacenter implements FilterChain {
    private static final Logger logger = Logger.getLogger(Filter4Datacenter.class);

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        logger.error("do filter now!!!");
    }
}
