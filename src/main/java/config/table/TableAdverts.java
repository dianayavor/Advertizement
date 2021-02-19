package config.table;

import static config.table.TableUtil.executeUpdate;

public class TableAdverts {
    public static final String TABLE_NAME_ADVERTS = "adverts";

    public static final String TABLE_FIELD_ID = "id";
    public static final String TABLE_FIELD_TITLE = "title";
    public static final String TABLE_FIELD_DESCRIPTION = "description";
    public static final String TABLE_FIELD_AUTHOR = "author";
    public static final String TABLE_FIELD_DATE_OF_CREATION = "date_of_creation";
    public static final String TABLE_FIELD_DATE_OF_END = "date_of_end";
    public static final String TABLE_FIELD_HEADING = "heading";
    public static final String TABLE_FIELD_IS_ACTIVE = "is_active";

    private TableAdverts() {
    }

    public static boolean createTableAdverts() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("create table if not exists ")
                .append(TABLE_NAME_ADVERTS).append(" ( ")
                .append(TABLE_FIELD_ID).append(" serial primary key, ")
                .append(TABLE_FIELD_TITLE).append(" text, ")
                .append(TABLE_FIELD_DESCRIPTION).append(" text, ")
                .append(TABLE_FIELD_AUTHOR).append(" int references users (id) on delete cascade on update cascade, ")
                .append(TABLE_FIELD_DATE_OF_CREATION).append(" date, ")
                .append(TABLE_FIELD_DATE_OF_END).append(" date, ")
                .append(TABLE_FIELD_HEADING).append(" varchar(20), ")
                .append(TABLE_FIELD_IS_ACTIVE).append(" boolean );");
        return executeUpdate(queryBuilder.toString());
    }

    public static boolean deleteTableAdverts() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("delete table if exists ")
                .append(TABLE_NAME_ADVERTS);
        return executeUpdate(queryBuilder.toString());
    }
}
