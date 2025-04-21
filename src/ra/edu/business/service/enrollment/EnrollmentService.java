package ra.edu.business.service.enrollment;

import ra.edu.business.model.RegisteredStudentInfo;

import java.util.List;

public interface EnrollmentService {
    List<RegisteredStudentInfo> getRegisteredStudentsByCourse(int courseId, int page, int pageSize, int[] totalRecordsOut);
    boolean approveOrDenyRegistration(int studentId, int courseId, String newStatus);
    boolean deleteEnrollment(int studentId, int courseId);
}
