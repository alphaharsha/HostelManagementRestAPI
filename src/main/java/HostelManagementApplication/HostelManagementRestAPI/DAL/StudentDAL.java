package HostelManagementApplication.HostelManagementRestAPI.DAL;

import HostelManagementApplication.HostelManagementRestAPI.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAL {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public boolean insertStudentUsingStoredProcedure(Student student) {
        boolean result = false;
        try
        {
            String sql = "CALL spInsertStudentTable(?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, student.getAddress(), student.getDob(), student.getEmailAddress(),
                    student.getGender(), student.getName(), student.getPhoneNumber(), student.getRoomNum());
            result = true;
        }
        catch (Exception e)
        {
            System.out.println("This is a message printed to the console. I got exception insertStudentUsingStoredProcedure");
            result = false;
        }
        return result;
    }
    public Student getStudentById(Long studentId)
    {
        Student student = new Student();
        try {
        String sql = "CALL spGetStudentById(?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[] { studentId },
                (resultSet, rowNum) ->
                {
                    student.setStudentId(resultSet.getLong("StudentID"));
                    student.setName(resultSet.getString("Name"));
                    student.setAddress(resultSet.getString("Address"));
                    student.setDob(resultSet.getDate("DOB"));
                    student.setEmailAddress(resultSet.getString("EmailAddress"));
                    student.setGender(resultSet.getString("Gender"));
                    student.setPhoneNumber(resultSet.getString("PhoneNum"));
                    student.setRoomNumber(resultSet.getLong("RoomNumber"));
                    return student;
                } );
            }
        catch (Exception e)
        {
            return null;
        }

    }
    public List<Student> getAllStudents() {
        try {
            String sql = "CALL spGetAllStudents()";
            return jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> {
                        Student student = new Student();
                        student.setStudentId(resultSet.getLong("StudentID"));
                        student.setName(resultSet.getString("Name"));
                        student.setAddress(resultSet.getString("Address"));
                        student.setDob(resultSet.getDate("DOB"));
                        student.setEmailAddress(resultSet.getString("EmailAddress"));
                        student.setGender(resultSet.getString("Gender"));
                        student.setPhoneNumber(resultSet.getString("PhoneNum"));
                        student.setRoomNumber(resultSet.getLong("RoomNumber"));
                        return student;
                    }
            );
        }catch (Exception e){
            return null;
        }
    }
    public boolean deleteStudentById(Long studentId) {
        try {
            String sql = "CALL spDeleteStudentById(?)";

            int rowsAffected = jdbcTemplate.update(sql, studentId);


            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false; // Return false to indicate failure
        }
    }

    public boolean updateStudentDetails(Student student) {
        boolean result = false;
        try
        {
            String sql = "CALL spUpdateStudent(?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, student.getStudentId(), student.getName(), student.getAddress(), student.getDob(), student.getEmailAddress(), student.getGender(), student.getPhoneNumber(), student.getRoomNum());
            result = true;
        }
        catch (Exception e)
        {
            System.out.println("This is a message printed to the console. I got exception insertStudentUsingStoredProcedure");
            result = false;
        }
        return result;
    }
}
