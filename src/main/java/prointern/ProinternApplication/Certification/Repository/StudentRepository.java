package prointern.ProinternApplication.Certification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.Certification.Model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

}
