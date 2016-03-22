package filter;

import org.apache.log4j.Logger;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yhx on 2015/12/17.
 */
public class ChinaPayFilter extends CharacterEncodingFilter {
    private static final Logger logger = Logger.getLogger(ChinaPayFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);
        String authType = request.getAuthType();
        String contextPath = request.getContextPath();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            logger.info("cookies is " + cookie);
        }
        String id = request.getParameter("id");
        logger.error("id is " + id + " authType is " + authType + " contextPath is " + contextPath);

        filterChain.doFilter(request, response);
    }
}
