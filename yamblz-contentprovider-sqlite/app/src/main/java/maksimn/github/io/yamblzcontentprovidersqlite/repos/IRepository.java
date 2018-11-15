package maksimn.github.io.yamblzcontentprovidersqlite.repos;

import java.util.List;

import maksimn.github.io.yamblzcontentprovidersqlite.repos.pojo.Group;

public interface IRepository {
    interface Action<T> {
        void run(T obj);
    }

    void getGroups(Action<List<Group>> action);

    void addGroup(final String groupName, Action<Long> result);
}
