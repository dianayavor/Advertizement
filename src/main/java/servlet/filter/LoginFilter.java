package servlet.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/loginFilter")
public class LoginFilter implements Filter {

    private FilterConfig config = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) {
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.equalsIgnoreCase("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (active){
            String username = request.getAttribute("username").toString();
            String password = request.getAttribute("password").toString();

            if(!username.contains("@") && password.length() > 15 && password.length() < 6) {
                request.setAttribute("error", "Wrong email or password");
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        config = null;
    }
}
