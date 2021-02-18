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
import java.util.List;
import java.util.Map;

import static servlet.ServletUtil.*;

@WebServlet(urlPatterns = {"/users"/*, "/users/delete", "/users/edit"*/})
public class UserServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(UserServlet.class);
    private UserService userService = new UserService();

    @Override
    public void init() {
        servletContext = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showUsers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> params = req.getParameterMap();
        long id = -1L;
        if (params.containsKey("id")) {
            id = Long.parseLong(removeBraces(Arrays.toString(params.get("id"))));
        }

        String action = req.getServletPath();

        switch (action) {
            case "users/edit":
                updateUser(id, req, resp);
                break;
            case "users/delete":
                deleteUser(id, resp, req);
                break;
            case "users/registration":
                User user = formatParamsGetUser(req);
                userService.save(user);
                showUsers(req, resp);
                break;
            default:
                showUsers(req, resp);
        }
    }

    private User formatParamsGetUser(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        User user = new User();

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

    private boolean showUsers(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_list.jsp");
        dispatcher(req, resp, dispatcher);
        return true;
    }

    private boolean updateUser(long id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = userService.findById(id);
        req.setAttribute("user", user);
        //resp.sendRedirect("/users");
        //RequestDispatcher dispatcher = req.getRequestDispatcher("user_edit.jsp");
        //dispatcher(req, resp, dispatcher);
        return true;
    }

    private boolean deleteUser(long id, HttpServletResponse resp, HttpServletRequest req) {
        userService.delete(id);
        //showUsers(req, resp);
        return true;
    }
}
