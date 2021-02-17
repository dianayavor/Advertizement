package servlet;

import model.Advert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AdvertService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static servlet.ServletUtil.dispatcher;
import static servlet.ServletUtil.servletContext;

@WebServlet("/adverts")
public class AdvertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdvertService service = new AdvertService();

    private final Logger logger = LogManager.getLogger(AdvertServlet.class);

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showAdverts(req, resp);
    }

    private void showAdverts(HttpServletRequest req, HttpServletResponse resp) {
        List<Advert> adverts = service.findAll();
        req.setAttribute("adverts", adverts);

        RequestDispatcher dispatcher = req.getRequestDispatcher("advert_list.jsp");
        dispatcher(req, resp, dispatcher);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAdverts(req, resp);
    }

    @Override
    public void destroy() {
        servletContext = null;
    }
}
