package config.table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TableUtil<E> {
    private final static Logger logger = LogManager.getLogger(TableUtil.class);

    public static boolean executeUpdate(String query) {
        /*try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }*/
        return true;
    }
}
