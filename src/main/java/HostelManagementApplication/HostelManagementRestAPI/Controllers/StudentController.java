package HostelManagementApplication.HostelManagementRestAPI.Controllers;

import HostelManagementApplication.HostelManagementRestAPI.Models.Student;
import HostelManagementApplication.HostelManagementRestAPI.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/Students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/RegisterStudent")
    public ResponseEntity<String> insertStudent(@RequestBody Student student)
    {
        boolean result = false;
        try {
            result = studentService.InsertStudent(student);
            if(result)
            {
                return ResponseEntity.status(HttpStatus.CREATED).body("Student record inserted successfully.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student record Insertion Failed.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inserting student record: " + e.getMessage());
        }
    }
    @GetMapping("/{studentId}")
    public  ResponseEntity<Object> GetStudentById(@PathVariable("studentId") Long studentId)
    {
        Student studentDetails = studentService.getStudentById(studentId);
        if(studentDetails != null)
        {
            return ResponseEntity.ok(studentDetails);
        }
        else {
            // Create a custom error response when student is not found
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Student not found");
            errorResponse.put("Warning", "Please provide the Registered Student ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        }

    }
    @GetMapping("/getAllStudents") // Updated URL pattern
    public ResponseEntity<Object> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        } else {
            // Create a custom error response when no students are found
            Map<String,Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "No students found");
            errorResponse.put("Warning", "Please  Register Student ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("studentId") Long studentId) {
        boolean deleted = studentService.deleteStudentById(studentId);

        if (deleted) {
            return ResponseEntity.ok("Student with ID " + studentId + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + studentId + " not found.");
        }
    }
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student){
        boolean result = false;
        try {
            result = studentService.updateStudent(student);
            if(result)
            {
                return ResponseEntity.status(HttpStatus.CREATED).body("Student record updated successfully.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student record updation Failed.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating student record: " + e.getMessage());
        }
    }
}


