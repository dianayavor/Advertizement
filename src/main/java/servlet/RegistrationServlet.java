package servlet;

import model.Role;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static servlet.ServletUtil.*;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("passwordRepeat");

        if (password.equals(repeatPassword)) {
            String hashedPassword = hashPassword(password);
            User user = new User(req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("email"), LocalDate.now(), true, Role.USER, hashedPassword);
            userService.save(user);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
            dispatcher(req, resp, dispatcher);
        } else {
            resp.getWriter().write("error: Passwords are different!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_registration.jsp");
        dispatcher(req, resp, dispatcher);
    }

    @Override
    public void destroy() {
        servletContext = null;
    }
}
