package dao.interfaces;

import java.sql.SQLException;

public interface IDao <T, D>{
    D create (T t) throws SQLException;
    void remove ( int id) throws SQLException;
}
