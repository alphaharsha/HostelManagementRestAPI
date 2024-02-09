package HostelManagementApplication.HostelManagementRestAPI.DAL;

import HostelManagementApplication.HostelManagementRestAPI.Models.CollegeStudent;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CollegeStudentDAL {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CollegeStudentDAL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Transactional
    public CollegeStudent GetStudentsByCollegeName(String collegeName)
    {
        CollegeStudent collegeStudent = new CollegeStudent();
        try {
            String sql = "CALL GetStudentsByCollegeName(?)";
            return jdbcTemplate.queryForObject(
                    sql,
                    new Object[] { collegeName },
                    (resultSet, rowNum) ->
                    {
                        collegeStudent.setStudentId(resultSet.getLong("StudentID"));
                        collegeStudent.setName(resultSet.getString("Name"));
                        collegeStudent.setAddress(resultSet.getString("Address"));
                        collegeStudent.setDob(resultSet.getDate("DOB"));
                        collegeStudent.setEmailAddress(resultSet.getString("EmailAddress"));
                        collegeStudent.setGender(resultSet.getString("Gender"));
                        collegeStudent.setPhoneNumber(resultSet.getString("PhoneNum"));
                        return collegeStudent;
                    } );
        }
        catch (Exception e)
        {
            return null;
        }

    }
}
