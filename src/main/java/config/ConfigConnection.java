package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static config.table.TableAdverts.createTableAdverts;
import static config.table.TableUsers.createTableUsers;
import static servlet.ServletUtil.servletContext;

public class ConfigConnection {
    private final static Logger logger = LogManager.getLogger(ConfigConnection.class);

    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            DriverManager.registerDriver(new Driver());
            properties.load(servletContext.getResourceAsStream("/resources/db.properties"));
            String localhost = properties.getProperty("localhost");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(localhost, username, password);
            createTableUsers();
            createTableAdverts();

        } catch (IOException | SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }
}
