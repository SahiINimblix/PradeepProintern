package prointern.ProinternApplication.Certification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Model.Training;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);
    
    @Query(value="update student set trainings=?2 where id=?1",nativeQuery = true)
	void updateTraining(Long id,Training training);

}
