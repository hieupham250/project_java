package ra.edu.business.service.enrollment;

import ra.edu.business.dao.enrollment.EnrollmentDao;
import ra.edu.business.dao.enrollment.EnrollmentDaoImp;
import ra.edu.business.model.RegisteredStudentInfo;

import java.util.List;

public class EnrollmentServiceImp implements EnrollmentService {
    private EnrollmentDao enrollmentDao;

    public EnrollmentServiceImp() {
        enrollmentDao = new EnrollmentDaoImp();
    }

    @Override
    public List<RegisteredStudentInfo> getRegisteredStudentsByCourse(int courseId, int page, int pageSize, int[] totalRecordsOut) {
        return enrollmentDao.getRegisteredStudentsByCourse(courseId, page, pageSize, totalRecordsOut);
    }

    @Override
    public boolean approveOrDenyRegistration(int studentId, int courseId, String newStatus) {
        return enrollmentDao.approveOrDenyRegistration(studentId, courseId, newStatus);
    }

    @Override
    public boolean deleteEnrollment(int studentId, int courseId) {
        return enrollmentDao.deleteEnrollment(studentId, courseId);
    }
}
