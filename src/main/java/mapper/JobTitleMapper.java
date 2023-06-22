package mapper;
import model.person.Department;
import model.person.JobTitle;
import org.apache.ibatis.annotations.Param;

public interface JobTitleMapper {
    JobTitle selectJobTitleById (int id);
    void insertIntoJobTitle(JobTitle jobTitle);
    void deleteFromJobTitleByID(int id);
   void updateJobTitleByID(@Param("name") String name, @Param("id") int id);
}
