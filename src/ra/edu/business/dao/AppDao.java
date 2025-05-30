package ra.edu.business.dao;

import java.util.List;

public interface AppDao<T> {
    List<T> findAll();
    boolean create(T t);
    boolean update(T t);
    boolean delete(T t);
}
