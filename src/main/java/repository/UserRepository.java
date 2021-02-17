package repository;

import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static config.ConfigConnection.getConnection;
import static config.table.TableUsers.*;

public class UserRepository implements UserRepositoryImpl, CrudRepository<User> {
    private final Logger logger = LogManager.getLogger(UserRepository.class);

    @Override
    public User save(User user) {
        StringBuilder query = new StringBuilder();
        query.append("insert into users( ")
                .append(TABLE_FIELD_FIRSTNAME).append(", ")
                .append(TABLE_FIELD_LASTNAME).append(", ")
                .append(TABLE_FIELD_EMAIL).append(", ")
                .append(TABLE_FIELD_DATE_OF_BIRTH).append(", ")
                .append(TABLE_FIELD_ROLE).append(", ")
                .append(TABLE_FIELD_IS_ACTIVE_ACCOUNT).append(", ")
                .append(TABLE_FIELD_PASSWORD)
                .append(") values( ?, ?, ?, ?, ?, ?, ?)");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
            ps.setString(5, user.getRole().toString());
            ps.setBoolean(6, user.isActiveAccount());
            ps.setString(7, user.getPassword());
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            while (result.next()) {
                user.setId((long) result.getInt(1));
            }
            result.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public User update(User user) {
        StringBuilder query = new StringBuilder();
        query.append("update ").append(TABLE_NAME_USERS).append(" set ")
                .append(TABLE_FIELD_FIRSTNAME).append("=?, ")
                .append(TABLE_FIELD_LASTNAME).append("=?, ")
                .append(TABLE_FIELD_EMAIL).append("=?, ")
                .append(TABLE_FIELD_ROLE).append("=?, ")
                .append(TABLE_FIELD_IS_ACTIVE_ACCOUNT).append("=?, ")
                .append(TABLE_FIELD_PASSWORD).append("=?, ")
                .append("where id=?");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole().toString());
            ps.setBoolean(5, user.isActiveAccount());
            ps.setString(5, user.getPassword());
            ps.setLong(6, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        StringBuilder query = new StringBuilder();
        query.append("delete from ").append(TABLE_NAME_USERS).append(" where id=?");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        StringBuilder query = new StringBuilder();
        query.append("select * from ").append(TABLE_NAME_USERS).append(" where id=?");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                user = getObject(result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("select * from ")
                .append(TABLE_NAME_USERS);
        try (ResultSet result = getConnection().createStatement().executeQuery(query.toString())) {
            while (result.next()) {
                users.add(getObject(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return users;
    }

    @Override
    public User getObject(ResultSet result) {
        User user = new User();
        try {
            user.setId((long) result.getInt(TABLE_FIELD_ID));
            user.setFirstName(result.getString(TABLE_FIELD_FIRSTNAME));
            user.setLastName(result.getString(TABLE_FIELD_LASTNAME));
            user.setEmail(result.getString(TABLE_FIELD_EMAIL));
            user.setDateOfBirth(new Date(result.getDate(TABLE_FIELD_DATE_OF_BIRTH).getTime()).toLocalDate());
            user.setRole(Role.valueOf(result.getString(TABLE_FIELD_ROLE)));
            user.setActiveAccount(result.getBoolean(TABLE_FIELD_IS_ACTIVE_ACCOUNT));
            user.setPassword(result.getString(TABLE_FIELD_PASSWORD));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        StringBuilder query = new StringBuilder();
        User user = new User();
        query.append("select * from ").append(TABLE_NAME_USERS)
                .append(" where ")
                .append(TABLE_FIELD_EMAIL).append("='")
                .append(login).append("' and ")
                .append(TABLE_FIELD_PASSWORD).append("='")
                .append(password).append("'");
        try (ResultSet result = getConnection().createStatement().executeQuery(query.toString())) {
            while (result.next()) {
                user = getObject(result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public User findByLogin(String login) {
        StringBuilder query = new StringBuilder();
        User user = new User();
        query.append("select * from ").append(TABLE_NAME_USERS)
                .append("where ")
                .append(TABLE_FIELD_EMAIL).append("=")
                .append(login);
        try (ResultSet result = getConnection().createStatement().executeQuery(query.toString())) {
            while (result.next()) {
                user = getObject(result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public List<User> findAllByParams(Map<String, String> fieldValueMap) {
        StringBuilder builder = new StringBuilder();
        if (!fieldValueMap.isEmpty()) {
            fieldValueMap.forEach((k, v) -> {
                if (builder.length() == 0) {
                    builder.append("where ");
                } else {
                    builder.append(" and ");
                }
                builder.append(" ").append(k).append(" like '").append(v).append("'");
            });
        }
        String query = "select * from users " + builder.toString();
        return new ArrayList<>(execute(query));
    }

    public List<User> execute(String query) {
        List<User> users = new ArrayList<>();
        try (ResultSet result = getConnection().createStatement().executeQuery(query)) {
            while (result.next()) {
                users.add(getObject(result));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return users;
    }
}
