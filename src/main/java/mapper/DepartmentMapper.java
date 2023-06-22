package mapper;
import model.person.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    Department selectDepartmentById (int id);
    void insertIntoDepartment(Department dep);
    void deleteFromDepartmentByID(int id);
   void updateDepartmentByID(@Param("name") String name, @Param("id") int id);
}
