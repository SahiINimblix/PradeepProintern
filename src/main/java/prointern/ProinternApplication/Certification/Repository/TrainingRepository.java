package prointern.ProinternApplication.Certification.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.Certification.Model.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {


	// Find all trainings by student
	List<Training> findByStudents_Id(Long studentId);

	// Find all trainings by trainer
	List<Training> findByTrainerId(Long trainerId);

	// Find trainings by completion status
	List<Training> findByCompletionStatus(boolean status);
}
