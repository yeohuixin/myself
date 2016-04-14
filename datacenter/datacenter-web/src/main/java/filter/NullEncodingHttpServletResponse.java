package filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by yhx on 2016/4/4.
 */
public class NullEncodingHttpServletResponse extends HttpServletResponseWrapper {
    /**
     * Creates a new response.
     * @param response
     * original response
     */
    public NullEncodingHttpServletResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return url;
    }

    @Override
    public String encodeRedirectURL(String url) {
        return url;
    }

    @Override
    public String encodeUrl(String url) {
        return url;
    }

    @Override
    public String encodeURL(String url) {
        return url;
    }
}

