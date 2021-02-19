package servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.ServletUtil.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LogManager.getLogger(LoginServlet.class);
    private UserService userService = new UserService();
    private final String subject = "token";

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/user_sign_in.jsp");
        dispatcher(req, resp, rd);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getAttribute("username").toString();
        String password = req.getAttribute("password").toString();
        String hashedPassword = hashPassword(password);
        if (username.contains("@") || password.length() > 15 || password.length() < 5 || !checkPassword(password, hashedPassword)) {
            String token = createToken(subject);
            req.setAttribute("token", token);
        }
    }

    @Override
    public void destroy() {
        servletContext = null;
    }
}
