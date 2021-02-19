package config.table;

import static config.table.TableUtil.executeUpdate;

public class TableUsers {
    public static final String TABLE_NAME_USERS = "users";

    public static final String TABLE_FIELD_ID = "id";
    public static final String TABLE_FIELD_FIRSTNAME = "firstname";
    public static final String TABLE_FIELD_LASTNAME = "lastname";
    public static final String TABLE_FIELD_EMAIL = "email";
    public static final String TABLE_FIELD_DATE_OF_BIRTH = "date_of_birth";
    public static final String TABLE_FIELD_IS_ACTIVE_ACCOUNT = "is_active_account";
    public static final String TABLE_FIELD_ROLE = "role";
    public static final String TABLE_FIELD_PASSWORD = "password";

    private TableUsers() {}

    public static boolean createTableUsers() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("create table if not exists ")
                .append(TABLE_NAME_USERS).append("( ")
                .append(TABLE_FIELD_ID).append(" serial primary key, ")
                .append(TABLE_FIELD_FIRSTNAME).append(" text, ")
                .append(TABLE_FIELD_LASTNAME).append(" text, ")
                .append(TABLE_FIELD_EMAIL).append(" text, ")
                .append(TABLE_FIELD_DATE_OF_BIRTH).append(" date, ")
                .append(TABLE_FIELD_IS_ACTIVE_ACCOUNT).append(" boolean, ")
                .append(TABLE_FIELD_PASSWORD).append(" text, ")
                .append(TABLE_FIELD_ROLE).append(" varchar(20) );");
        return executeUpdate(queryBuilder.toString());
    }

    public static boolean deleteTableUsers() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("delete table if exists ")
                .append(TABLE_NAME_USERS)
                .append(" cascade");
        return executeUpdate(queryBuilder.toString());
    }
}
