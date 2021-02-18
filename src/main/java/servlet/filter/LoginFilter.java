package servlet.filter;

import javax.servlet.*;
import java.io.IOException;

import static servlet.ServletUtil.checkPassword;
import static servlet.ServletUtil.hashPassword;

//@WebFilter(urlPatterns = "/login")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getAttribute("username").toString();
        String password = request.getAttribute("password").toString();
        String hashedPassword = hashPassword(password);

        if (!username.contains("@") || password.length() > 15 || password.length() < 6 || !checkPassword(password, hashedPassword)) {
            request.setAttribute("error", "Wrong email or password");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
