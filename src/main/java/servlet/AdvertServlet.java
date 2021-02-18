package servlet;

import model.Advert;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AdvertService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static servlet.ServletUtil.*;

@WebServlet(urlPatterns = {"/adverts", "/my-adverts"})
public class AdvertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdvertService advertService = new AdvertService();
    private final UserService userService = new UserService();

    private final Logger logger = LogManager.getLogger(AdvertServlet.class);
    private User user = new User();

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        userService.findAll();
        advertService.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAdverts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAdverts(req, resp);
    }

    private void showAdverts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadUser(req);
        String action = req.getServletPath();
        switch (action) {
            case "/adverts":
                showAllAdverts(req, resp);
                break;
            case "/my-adverts":
                showMyAdverts(req, resp);
                break;
        }
    }

    private void showAllAdverts(HttpServletRequest req, HttpServletResponse resp) {
        List<Advert> adverts = advertService.findAll();
        req.setAttribute("adverts", adverts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("advert_list.jsp");
        dispatcher(req, resp, dispatcher);
    }

    private void showMyAdverts(HttpServletRequest req, HttpServletResponse resp) {
        List<Advert> adverts = userService.findAllByUserId(user.getId());
        req.setAttribute("adverts", adverts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("advert_list.jsp");
        dispatcher(req, resp, dispatcher);
    }

    @Override
    public void destroy() {
        servletContext = null;
    }

    private boolean loadUser(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        String username = "";
        if (params.containsKey("username")) {
            username = removeBraces(Arrays.toString(params.get("username")));
        }

        if (user.getId() == null) {
            user = userService.findByLogin(username);
        }
        req.setAttribute("user", user);

        return user != null;
    }
}
