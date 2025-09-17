package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Student;

public interface StudentService {

	    String createStudent(Student student);
	    Student getStudentById(Long id);
	    List<Student> getAllStudents();
	    String updateStudent(Long id, Student student);
	    String deleteStudent(Long id);
}
