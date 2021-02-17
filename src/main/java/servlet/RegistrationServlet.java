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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("email"), LocalDate.now(), true, Role.USER, req.getParameter("password"));
        String action = req.getServletPath();
        if ("/registration".equals(action)) {
            userService.save(user);
            RequestDispatcher rd = req.getRequestDispatcher("user_list.jsp");
            try {
                rd.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user_registration.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //logger.error(e.getMessage());
        }
    }
}
