package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Trainer;

public interface TrainerService {

	String createTrainer(Trainer trainer);

	Trainer getTrainerById(Long id);

	List<Trainer> getAllTrainers();

	String updateTrainer(Long id, Trainer trainer);

	String deleteTrainer(Long id);
}
