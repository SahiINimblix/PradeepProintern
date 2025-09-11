package prointern.ProinternApplication.Certification.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kiran.certification.certification.model.Student;
import com.kiran.certification.certification.service.StudentService;
import com.kiran.certification.certification.serviceimpl.StudentServiceimpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	public StudentController(StudentServiceimpl studentimpl) {
		super();
		this.studentService = studentimpl;
	}

	@PostMapping("/add")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){
		 Student saved = studentService.createStudent(student);
		return ResponseEntity.ok(saved);
	}


	@GetMapping("/{id}")
	@Validated
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@PutMapping("update/{id}")
	@Validated
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		return ResponseEntity.ok(studentService.updateStudent(id, student));
	}

	@DeleteMapping("/{id}")
	@Validated
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}





}
