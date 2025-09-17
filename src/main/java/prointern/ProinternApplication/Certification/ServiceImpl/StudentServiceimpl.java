package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Service.StudentService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Exception.UserAlreadyExistsException;

@Service
public class StudentServiceimpl implements StudentService {

	@Autowired
	private final StudentRepository studentRepository;

	public StudentServiceimpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public String createStudent(Student student) {
		Student isStudent = studentRepository.findByEmail(student.getEmail());
		if(isStudent!=null) throw new UserAlreadyExistsException("Email id is already registered");
		Student stud = studentRepository.save(student);
		if (student == null)
			throw new OperationFailedException("Error in saving details");
		return "Student details saved successfully";
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new DetailsNotFoundException("Student not found with id: " + id));
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> listStudent = studentRepository.findAll();
		if (listStudent.isEmpty())
			throw new DetailsNotFoundException("No students found in database");
		return listStudent;
	}

	@Override
	public String updateStudent(Long id, Student updatedStudent) {
		Student student = getStudentById(id);
		if (student == null)
			throw new DetailsNotFoundException("Student not found to update");
		student.setName(updatedStudent.getName());
		student.setEmail(updatedStudent.getEmail());
		Student stud = studentRepository.save(student);
		if (stud == null)
			throw new OperationFailedException("Unable to update");
		return "Student details updated successfully";
	}

	@Override
	public String deleteStudent(Long id) {
		//Step 1: Check if student exists
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new DetailsNotFoundException("Student not found with id: " + id));
		
		//Step 2: Attempt to delete
		studentRepository.delete(student);
		
		//Step 3: Verify delete
		if (studentRepository.existsById(id)) {
			throw new OperationFailedException("Unable to delete");
		}
		return "Student deleted successfully";

	}
}
