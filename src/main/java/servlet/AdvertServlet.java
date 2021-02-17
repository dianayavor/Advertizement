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

@WebServlet("/adverts")
public class AdvertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger logger = LogManager.getLogger(AdvertServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        showAdverts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAdverts(req, resp);
    }

    private void showAdverts(HttpServletRequest req, HttpServletResponse resp) {
        AdvertService service = new AdvertService();
        List<Advert> adverts = service.findAll();
        req.setAttribute("adverts", adverts);

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("advert_list.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}
