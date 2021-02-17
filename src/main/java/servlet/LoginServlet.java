package servlet;

import model.User;
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String hashedPassword = hashPassword(password);

        if (checkPassword(password, hashedPassword)) {
            User user = userService.findByLogin(username);
            //todo send user to adverts page
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/adverts");
            dispatcher(req, resp, rd);
        } else {
            logger.error("error: Invalid login or password!");
            resp.getWriter().write("error: Invalid login or password!");
        }
    }

    @Override
    public void destroy() {
        servletContext = null;
    }
}
