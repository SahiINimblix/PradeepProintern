package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Service.StudentService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;

@Service
public class StudentServiceimpl implements StudentService {

	@Autowired
	private final StudentRepository studentRepository;

	public StudentServiceimpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student createStudent(Student student) {
		Student stud = studentRepository.save(student);
		if (student == null)
			throw new DetailsNotFoundException("Error in saving details");
		return stud;
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new DetailsNotFoundException("Student not found with id: " + id));
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> listStudent = studentRepository.findAll();
		if (listStudent == null)
			throw new DetailsNotFoundException("No students found in database");
		return listStudent;
	}

	@Override
	public Student updateStudent(Long id, Student updatedStudent) {
		Student student = getStudentById(id);
		if (student == null)
			throw new DetailsNotFoundException("Student not found to update");
		student.setName(updatedStudent.getName());
		student.setEmail(updatedStudent.getEmail());
		return studentRepository.save(student);
	}

	@Override
	public String deleteStudent(Long id) {
		Student student = getStudentById(id);
		if (student == null)
			throw new DetailsNotFoundException("Student not found to delete");
		studentRepository.delete(student);
		return "Student deleted successfully";
	}
}
