package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Repository.TrainingRepository;
import prointern.ProinternApplication.Certification.Service.TrainingService;

@Service
public class TrainingServiceimpl implements TrainingService {
	
	private final TrainingRepository trainingrepository;

	 public TrainingServiceimpl(TrainingRepository trainingRepository) {
	        this.trainingrepository = trainingRepository;
	    }

	    @Override
	    public Training assignTraining(Long studentId, Long trainerId, Training training) {
	       
	        return trainingrepository.save(training);
	    }

	    @Override
	    public Training getTrainingById(Long id) {
	        return trainingrepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Training not found with id: " + id));
	    }

	    @Override
	    public List<Training> getAllTrainings() {
	        return trainingrepository.findAll();
	    }

	    @Override
	    public Training updateCompletionStatus(Long trainingId, boolean status) {
	        Training training = getTrainingById(trainingId);
	        training.setCompletionStatus(status);
	        return trainingrepository.save(training);
	    }

	    @Override
	    public void deleteTraining(Long id) {
	        Training training = getTrainingById(id);
	        trainingrepository.delete(training);
	    }

}
