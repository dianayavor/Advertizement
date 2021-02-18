package repository;

import model.Advert;
import model.Heading;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static config.ConfigConnection.getConnection;
import static config.table.TableAdverts.*;
import static config.table.TableUsers.TABLE_NAME_USERS;

public class AdvertRepository implements CrudRepository<Advert>, AdvertRepositoryImpl {
    private final Logger logger = LogManager.getLogger(AdvertRepository.class);

    @Override
    public Advert save(Advert advert) {
        StringBuilder query = new StringBuilder();
        query.append("insert into ").append(TABLE_NAME_ADVERTS).append("(")
                .append(TABLE_FIELD_TITLE).append(", ")
                .append(TABLE_FIELD_DESCRIPTION).append(", ")
                .append(TABLE_FIELD_HEADING).append(", ")
                .append(TABLE_FIELD_DATE_OF_CREATION).append(", ")
                .append(TABLE_FIELD_DATE_OF_END).append(", ")
                .append(TABLE_FIELD_IS_ACTIVE).append(", ")
                .append(TABLE_FIELD_AUTHOR)
                .append(") values(?, ?, ?, ?, ?, ?, ?)");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, advert.getTitle());
            ps.setString(2, advert.getDescription());
            ps.setString(3, advert.getHeading().toString());
            ps.setDate(4, Date.valueOf(advert.getDateOfCreation()));
            ps.setDate(5, Date.valueOf(advert.getDateOfEnd()));
            ps.setBoolean(6, advert.getActive());
            ps.setLong(7, advert.getAuthor().getId());
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            while (result.next()) {
                advert.setId((long) result.getInt(1));
            }
            result.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return advert;
    }

    @Override
    public Advert update(Advert advert) {
        StringBuilder query = new StringBuilder();
        query.append("update ").append(TABLE_NAME_ADVERTS).append(" set ")
                .append(TABLE_FIELD_TITLE).append("=?, ")
                .append(TABLE_FIELD_DESCRIPTION).append("=?, ")
                .append(TABLE_FIELD_HEADING).append("=?, ")
                .append(TABLE_FIELD_IS_ACTIVE).append("=?, ")
                .append(" where id=?");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            ps.setString(1, advert.getTitle());
            ps.setString(2, advert.getDescription());
            ps.setString(3, advert.getHeading().toString());
            ps.setBoolean(4, advert.getActive());
            ps.setLong(5, advert.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
        return advert;
    }

    @Override
    public boolean delete(Long id) {
        StringBuilder query = new StringBuilder();
        query.append("delete from ").append(TABLE_NAME_ADVERTS).append(" where id=?");
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Advert findById(Long id) {
        StringBuilder query = new StringBuilder();
        query.append("select * from ").append(TABLE_NAME_ADVERTS).append(" where id=?");
        Advert advert = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                advert = getObject(result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return advert;
    }

    @Override
    public List<Advert> findAll() {
        StringBuilder query = new StringBuilder();
        List<Advert> adverts = new ArrayList<>();
        query.append("select * from ").append(TABLE_NAME_ADVERTS);
        try (ResultSet result = getConnection().createStatement().executeQuery(query.toString())) {
            while (result.next()) {
                adverts.add(getObject(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return adverts;
    }

    public Advert getObject(ResultSet result) {
        Advert advert = null;
        try {
            advert = new Advert();
            advert.setId((long) result.getInt("id"));
            advert.setTitle(result.getString("title"));
            advert.setDescription(result.getString("description"));
            advert.setHeading(Heading.valueOf(result.getString("heading")));
            advert.setActive(result.getBoolean("is_active"));
            advert.setDateOfCreation(new Date(result.getDate("date_of_creation").getTime()).toLocalDate());
            User user = new User();
            user.setId((long) result.getInt("author"));
            UserRepository userRepository = new UserRepository();
            user = userRepository.findById(user.getId());
            advert.setAuthor(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return advert;
    }

    public List<Advert> joinFindAllAdverts(Long userId) {
        StringBuilder query = new StringBuilder();
        query.append("select ")
                .append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_TITLE).append(", ")
                .append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_DESCRIPTION).append(", ")
                .append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_DATE_OF_CREATION).append(", ")
                .append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_DATE_OF_END).append(", ")
                .append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_HEADING).append(", ")
                .append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_IS_ACTIVE)
                .append(" from ").append(TABLE_NAME_ADVERTS)
                .append(" join ").append(TABLE_NAME_USERS)
                .append(" on ").append(TABLE_NAME_ADVERTS).append(".").append(TABLE_FIELD_AUTHOR).append("=").append(userId);
        return new ArrayList<>(execute(query.toString()));
    }

    public List<Advert> findAllByDateByAsc() {
        String query = "select * from adverts order by date_of_creation asc";
        return new ArrayList<>(execute(query));
    }

    public List<Advert> findAllByDateByDesc() {
        String query = "select * from adverts order by date_of_creation desc";
        return new ArrayList<>(execute(query));
    }

    public List<Advert> findAllByParams(Map<String, String> fieldValueMap) {
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

    public List<Advert> execute(String query) {
        List<Advert> adverts = new ArrayList<>();
        try (ResultSet result = getConnection().createStatement().executeQuery(query)) {
            while (result.next()) {
                adverts.add(getObject(result));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return adverts;
    }
}

