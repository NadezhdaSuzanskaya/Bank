package mapper;

import model.enums.EmployeeJobTitle;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@MappedTypes(EmployeeJobTitle.class)
public class EmployeeJobTitleTypeHandler extends BaseTypeHandler<EmployeeJobTitle> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, EmployeeJobTitle employeeJobTitle, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, StringUtils.capitalize(employeeJobTitle.name().toLowerCase()));
    }
    @Override
    public EmployeeJobTitle getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String jobTitle = resultSet.getString(columnName);
        return EmployeeJobTitle.valueOf(jobTitle.toUpperCase(Locale.ROOT));
    }

    @Override
    public EmployeeJobTitle getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String jobTitle = resultSet.getString(columnIndex);
        return EmployeeJobTitle.valueOf(jobTitle.toUpperCase());
    }

    @Override
    public EmployeeJobTitle getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String jobTitle = callableStatement.getString(columnIndex);
        return EmployeeJobTitle.valueOf(jobTitle.toUpperCase());
    }
}
