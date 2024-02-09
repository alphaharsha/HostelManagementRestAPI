package HostelManagementApplication.HostelManagementRestAPI.Services;

import HostelManagementApplication.HostelManagementRestAPI.DAL.CollegeStudentDAL;
import HostelManagementApplication.HostelManagementRestAPI.Models.CollegeStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeStudentService {
    private final CollegeStudentDAL collegeStudentDAL;
    @Autowired
    public CollegeStudentService(CollegeStudentDAL collegeStudentDAL) {
        this.collegeStudentDAL = collegeStudentDAL;
    }



    public CollegeStudent GetStudentsByCollegeName(String collegeName){
     return collegeStudentDAL.GetStudentsByCollegeName(collegeName);
    }
}
