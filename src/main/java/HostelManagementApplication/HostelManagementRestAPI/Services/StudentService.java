package HostelManagementApplication.HostelManagementRestAPI.Services;

import HostelManagementApplication.HostelManagementRestAPI.Controllers.StudentController;
import HostelManagementApplication.HostelManagementRestAPI.DAL.StudentDAL;
import HostelManagementApplication.HostelManagementRestAPI.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
   private final StudentDAL studentDAL;
    @Autowired
    public StudentService(StudentDAL studentDAL) {
        this.studentDAL = studentDAL;
    }
    public boolean InsertStudent(Student student){
        return studentDAL.insertStudentUsingStoredProcedure(student);
    }

    public Student getStudentById(Long studentId) {
        Student obj=studentDAL.getStudentById(studentId);
        return obj;
    }
    public List<Student> getAllStudents() {
        return studentDAL.getAllStudents();
    }
    public boolean deleteStudentById(Long studentId){
        return studentDAL.deleteStudentById(studentId);
    }
    public boolean updateStudent(Student student){
        return studentDAL.updateStudentDetails(student);
    }
}
