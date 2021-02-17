package servlet;

import model.Role;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(UserServlet.class);
    final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showUsers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getServletPath();

        switch (action) {
            case "/user_registration.jsp":
                signUp(req, resp);
                break;
            default:
                showUsers(req, resp);
                break;
        }
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String firstname = req.getParameter("inputFirstName");
            String lastname = req.getParameter("inputLastName");
            String email = req.getParameter("inputEmailName");
            LocalDate dob = LocalDate.parse(req.getParameter("inputDOB"));
            Role role = Role.USER;

            //User user = new User(firstname, lastname, email, dob, true, role);
            //userService.save(user);
            resp.sendRedirect("/users");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    void showUsers(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_list.jsp");
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}
