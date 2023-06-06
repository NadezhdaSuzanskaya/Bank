package dao.interfaces;

import model.person.Department;

import java.sql.SQLException;

public interface IDaoDepartment extends IDao<Department, Department> {
    Department getDepartmentByAddress(String name) throws SQLException;
    Department updateDepartmentName(String name, int id) throws SQLException;
}
