package repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface CrudRepository<E extends Serializable> {

    E save(E e);

    E update(E e);

    boolean delete(Long id);

    E findById(Long id);

    List<E> findAll();

    E getObject(ResultSet result);

}
