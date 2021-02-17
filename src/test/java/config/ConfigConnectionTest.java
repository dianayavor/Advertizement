package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static config.ConfigConnection.getConnection;
import static org.testng.Assert.assertNotNull;

public class ConfigConnectionTest {
    private final Logger logger = LogManager.getLogger(ConfigConnection.class);
    private ConfigConnection config = new ConfigConnection();

    @Test(priority = 2)
    public void testConnection() {
        assertNotNull(getConnection(), "Connection ok");
    }
}
