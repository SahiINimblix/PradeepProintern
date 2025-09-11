package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Trainer;

public interface TrainerService {

	 Trainer createTrainer(Trainer trainer);
	    Trainer getTrainerById(Long id);
	    List<Trainer> getAllTrainers();
	    Trainer updateTrainer(Long id, Trainer trainer);
	    void deleteTrainer(Long id);
}
