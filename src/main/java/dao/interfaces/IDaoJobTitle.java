package dao.interfaces;

import model.person.Department;
import model.person.JobTitle;

import java.sql.SQLException;

public interface IDaoJobTitle extends IDao <JobTitle,JobTitle> {
    JobTitle getJobTitleByName(String name) throws SQLException;
}
