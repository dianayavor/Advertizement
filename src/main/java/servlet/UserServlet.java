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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static servlet.ServletUtil.dispatcher;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(UserServlet.class);
    final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showUsers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = formatParamsGetUser(req);
        userService.save(user);
        showUsers(req, resp);
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

    private void showUsers(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_list.jsp");
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        dispatcher(req, resp, dispatcher);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = formatParamsGetUser(req);
        userService.update(user);
        showUsers(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
        Map<String, String[]> params = req.getParameterMap();

        long id = -1L;
        if (params.containsKey("id")) {
            id = Long.parseLong(removeBraces(Arrays.toString(params.get("id"))));
        }
        userService.delete(id);
        showUsers(req, resp);
    }

    private String removeBraces(String text) {
        return text.replace("[", "").replace("]", "");
    }
}
