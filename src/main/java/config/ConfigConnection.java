package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static config.table.TableAdverts.createTableAdverts;
import static config.table.TableUsers.createTableUsers;

public class ConfigConnection {
    private final static Logger logger = LogManager.getLogger(ConfigConnection.class);
    private final static Properties properties = new Properties();
    private static Connection connection = null;

    public static Connection getConnection() {
        // loadProperties();
//        if (!loadProperties()) {
//            logger.error("Properties don't load");
//        }

        String localhost = "jdbc:postgresql://localhost:5432/advert_db";    //properties.getProperty("localhost");
        String username = "postgres";                                       //properties.getProperty("username");
        String password = "root";
        try {
            connection = DriverManager.getConnection(localhost, username, password);
            Class.forName("org.postgresql.Driver");
            createTableUsers();
            createTableAdverts();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

    private static boolean loadProperties() {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/db.properties"))) {
            if (input != null) {
                properties.load(input);
            }
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
