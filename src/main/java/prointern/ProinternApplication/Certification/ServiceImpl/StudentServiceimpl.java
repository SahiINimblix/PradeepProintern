package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Service.StudentService;

@Service
public class StudentServiceimpl implements StudentService {

	@Autowired
	private final StudentRepository studentRepository;
	
	
	 public StudentServiceimpl(StudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	    }

	    @Override
	    public Student createStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    @Override
	    public Student getStudentById(Long id) {
	        return studentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
	    }

	    @Override
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    @Override
	    public Student updateStudent(Long id, Student updatedStudent) {
	        Student student = getStudentById(id);
	        student.setName(updatedStudent.getName());
	        student.setEmail(updatedStudent.getEmail());
	        return studentRepository.save(student);
	    }

	    @Override
	    public void deleteStudent(Long id) {
	        Student student = getStudentById(id);
	        studentRepository.delete(student);
	    }
}
