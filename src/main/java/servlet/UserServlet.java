package servlet;

import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import static servlet.ServletUtil.*;

@WebServlet(urlPatterns = {"/users", "/users/delete", "/users/edit", "/users/edited"})
public class UserServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(UserServlet.class);
    private final UserService userService = new UserService();

    @Override
    public void init() {
        servletContext = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showUsers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> params = req.getParameterMap();
        long id = -1L;
        if (params.containsKey("id")) {
            id = Long.parseLong(removeBraces(Arrays.toString(params.get("id"))));
        }

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/users/edit":
                    updateUser(id, req, resp);
                    break;
                case "/users/delete":
                    deleteUser(id, resp, req);
                    break;
                case "/registration":
                    saveUser(req, resp);
                    break;
                case "/users/edited":
                    updatedUser(req, resp);
                    break;
                default:
                    showUsers(req, resp);
                    break;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private User formatParamsGetUser(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        User user = new User();

        if (params.containsKey("id")) {
            user.setId(Long.parseLong(removeBraces(Arrays.toString(params.get("id")))));
        }
        if (params.containsKey("firstname")) {
            user.setFirstName(removeBraces(Arrays.toString(params.get("firstname"))));
        }
        if (params.containsKey("lastname")) {
            user.setLastName(removeBraces(Arrays.toString(params.get("lastname"))));
        }
        if (params.containsKey("email")) {
            user.setEmail(removeBraces(Arrays.toString(params.get("email"))));
        }
        if (params.containsKey("dob")) {
            //user.setDateOfBirth(Arrays.toString(entry.getValue()));todo work with time
            user.setDateOfBirth(LocalDate.now());
        }
        if (params.containsKey("password")) {
            user.setPassword(removeBraces(Arrays.toString(params.get("password"))));
        }
        user.setActiveAccount(true);
        user.setRole(Role.USER);
        return user;
    }

    private void showUsers(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("users", userService.findAll());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user_list.jsp");
        dispatcher(req, resp, dispatcher);
    }

    private void updateUser(long id, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("user", userService.findById(id));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user_edit.jsp");
        dispatcher(req, resp, dispatcher);
    }

    private void deleteUser(long id, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        userService.delete(id);
        req.setAttribute("users", userService.findAll());
        resp.sendRedirect("/webappadw/users");
    }

    private void updatedUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.update(formatParamsGetUser(req));
        resp.sendRedirect("/webappadw/users");
    }

    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.save(formatParamsGetUser(req));
        resp.sendRedirect("/users");
    }
}
