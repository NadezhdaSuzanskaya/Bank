package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IDao <T, D>{
    void create (T t) throws SQLException;
    void remove ( int id) throws SQLException;
    List<T> getAllElements() throws SQLException;

}
