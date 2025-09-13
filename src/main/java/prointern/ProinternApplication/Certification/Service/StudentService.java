package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Student;

public interface StudentService {

	    Student createStudent(Student student);
	    Student getStudentById(Long id);
	    List<Student> getAllStudents();
	    Student updateStudent(Long id, Student student);
	    String deleteStudent(Long id);
}
