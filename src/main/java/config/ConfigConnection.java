package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlet.ServletUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigConnection {
    private final static Logger logger = LogManager.getLogger(ConfigConnection.class);

    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            properties.load(ServletUtil.servletContext.getResourceAsStream("/resources/db.properties"));
            String localhost = properties.getProperty("localhost");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(localhost, username, password);
        } catch (IOException | SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }
}
