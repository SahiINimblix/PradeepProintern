package prointern.ProinternApplication.Certification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import prointern.ProinternApplication.Certification.Model.Trainer;
import prointern.ProinternApplication.Certification.Model.Training;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {


	Trainer findByEmail(String email);
	
	@Query(value="update trainer set training=?2 where id=?1",nativeQuery = true)
	void updateTraining(Long trainerId, Training training);

}
