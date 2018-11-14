package maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite;

public interface DbContract {
    String DB_NAME = "db_groups_learners.sqlite";

    String GROUPS = "groups";
    interface Groups {
        String ID = "rowid";
        String NAME = "name";
    }

    String LEARNERS = "learners";
    interface Learners {
        String ID = "rowid";
        String NAME = "name";
        String PHONE = "phone";
        String GROUP_ID = "group_id";
    }
}
