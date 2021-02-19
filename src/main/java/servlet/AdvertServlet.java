package servlet;

import model.Advert;
import model.Heading;
import model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.AdvertService;
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

@WebServlet(urlPatterns = {"/adverts", "/my-adverts", "/adverts/delete", "/adverts/edit", "/adverts/edited", "/adverts/add"})
public class AdvertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(AdvertServlet.class);
    private final AdvertService advertService = new AdvertService();
    private final UserService userService = new UserService();

    private User user = new User();
    private Advert advert = new Advert();
    private String token = "";

    @Override
    public void init() {
        servletContext = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showAdverts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> params = req.getParameterMap();
        if (params.containsKey("advertId")) {
            advert.setId(Long.parseLong(removeBraces(Arrays.toString(params.get("advertId")))));
        }
        if (params.containsKey("userId")) {
            user.setId(Long.parseLong(removeBraces(Arrays.toString(params.get("userId")))));
        }

        String action = req.getServletPath();

        try {
            switch (action) {
                case "/adverts/delete":
                    deleteAdvert(req, resp);
                    break;
                case "/adverts/edit":
                    updateAdvert(req, resp);
                    break;
                case "/adverts/edited":
                    updatedAdvert(req, resp);
                    break;
                case "/adverts/add":
                    addAdvert(req, resp);
                    break;
                default:
                    showAdverts(req, resp);
                    break;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void showAdverts(HttpServletRequest req, HttpServletResponse resp) {
        loadUser(req);
        String action = req.getServletPath();
        switch (action) {
            case "/adverts":
                showAdverts(req, resp, advertService.findAll());
                break;
            case "/my-adverts":
                showAdverts(req, resp, userService.findAllByUserId(user.getId()));
                break;
        }
    }

    private void showAdverts(HttpServletRequest req, HttpServletResponse resp, List<Advert> adverts) {
        req.setAttribute("adverts", adverts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("advert_list.jsp");
        dispatcher(req, resp, dispatcher);
    }

    private void loadUser(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        String username = "";
        if (params.containsKey("username")) {
            username = removeBraces(Arrays.toString(params.get("username")));
        }
        if (params.containsKey("token")) {
            token = removeBraces(Arrays.toString(params.get("username")));
        }

        if (user.getId() == null) {
            user = userService.findByLogin(username);
        }
        req.setAttribute("user", user);
    }

    private void loadAdvert(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();
        if (params.containsKey("id")) {
            advert.setId(Long.parseLong(removeBraces(Arrays.toString(params.get("id")))));
        }
        advert = advertService.findById(advert.getId());
        req.setAttribute("advert", advert);
    }

    private void updateAdvert(HttpServletRequest req, HttpServletResponse resp) {
        loadAdvert(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/advert_edit.jsp");
        dispatcher(req, resp, dispatcher);
    }

    private void updatedAdvert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        advertService.update(formatParamsGetAdvert(req));
        redirectToAdverts(req, resp);
    }

    private void deleteAdvert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        advertService.delete(advert.getId());
        redirectToAdverts(req, resp);
    }

    private void redirectToAdverts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("adverts", advertService.findAll());
        resp.sendRedirect("/webappadw/adverts");
    }

    private void addAdvert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        advertService.save(formatParamsGetAdvert(req));
        List<Advert> adverts = advertService.findAll();
        req.setAttribute("adverts", adverts);
        resp.sendRedirect("/webappadw/adverts");
    }

    private Advert formatParamsGetAdvert(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        if (params.containsKey("advertId")) {
            advert.setId(Long.parseLong(removeBraces(Arrays.toString(params.get("advertId")))));
        }
        if (params.containsKey("title")) {
            advert.setTitle(removeBraces(Arrays.toString(params.get("title"))));
        }
        if (params.containsKey("description")) {
            advert.setDescription(removeBraces(Arrays.toString(params.get("description"))));
        }
        if (params.containsKey("heading")) {
            //advert.setHeading(Heading.valueOf(removeBraces(Arrays.toString(params.get("email")))));
        }
        if (params.containsKey("isActive")) {
            advert.setActive(Boolean.parseBoolean(removeBraces(Arrays.toString(params.get("isActive")))));
        }
        advert.setHeading(Heading.ANIMAL);
        advert.setDateOfCreation(LocalDate.now());
        advert.setDateOfEnd(LocalDate.now());
        advert.setAuthor(userService.findById(user.getId()));
        advert.setActive(true);
        return advert;
    }

    @Override
    public void destroy() {
        servletContext = null;
    }
}
