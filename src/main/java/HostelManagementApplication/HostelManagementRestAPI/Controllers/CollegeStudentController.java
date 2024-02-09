package HostelManagementApplication.HostelManagementRestAPI.Controllers;

import HostelManagementApplication.HostelManagementRestAPI.Models.CollegeStudent;
import HostelManagementApplication.HostelManagementRestAPI.Models.Student;
import HostelManagementApplication.HostelManagementRestAPI.Services.CollegeStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/CollegeStudents")
public class CollegeStudentController {
    private final CollegeStudentService collegeStudentService;
 @Autowired
    public CollegeStudentController(CollegeStudentService collegeStudentService) {
        this.collegeStudentService = collegeStudentService;
    }
    @GetMapping("/{collegeName}")
    public ResponseEntity<Object> GetStudentsByCollegeName(@PathVariable("collegeName") String collegeName)
    {
        CollegeStudent studentDetails = collegeStudentService.GetStudentsByCollegeName(collegeName);
        if(studentDetails != null)
        {
            return ResponseEntity.ok(studentDetails);
        }
        else {
            // Create a custom error response when student is not found
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Student not found");
            errorResponse.put("Warning", "Please provide the Registered Student ID witha college");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        }

    }
}
