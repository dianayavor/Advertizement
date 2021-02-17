package servlet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LogManager.getLogger(LoginServlet.class);
    final UserService userService = new UserService();

    private final static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final static SecretKey secretKey = MacProvider.generateKey(signatureAlgorithm);
    private final static TemporalAmount timeOfTokenValidity = Duration.ofMinutes(30L);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_sign_in.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = Role.USER.toString();

        String flag = req.getAttribute("flagValidate").toString();

        if (flag == null) {
            String token = createToken(password, role);

            req.setAttribute("tokenJKT", token);

            ServletContext context = getServletContext().getContext("/adverts");
            RequestDispatcher rd = context.getRequestDispatcher("/adverts");
            try {
                rd.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else if (flag.equals("Y")) {
            try {
                String token = req.getAttribute("tokenJKT").toString();

                Jws<Claims> validate = parseToken(token);

                if (validate != null) {
                    UserService userService = new UserService();
                    if (userService.findByLoginAndPassword(username, password).getId() != null) {
                        ServletContext context = getServletContext().getContext("/adverts");
                        RequestDispatcher rd = context.getRequestDispatcher("/adverts");
                        rd.forward(req, resp);
                    }
                }
            } catch (IOException | ServletException e) {
                logger.error(e.getMessage());
            }
        }
    }


    private String createToken(final String subject, final String role) {
        final Instant now = Instant.now();
        final Date expiryDate = Date.from(now.plus(timeOfTokenValidity));
        return Jwts.builder()
                .setSubject(subject)
                .claim("role", role)
                .setExpiration(expiryDate)
                .setIssuedAt(Date.from(now))
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }

    private Jws<Claims> parseToken(final String compactToken) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(compactToken);
    }
}
