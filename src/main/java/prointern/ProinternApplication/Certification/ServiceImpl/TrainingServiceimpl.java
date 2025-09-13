package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Repository.TrainingRepository;
import prointern.ProinternApplication.Certification.Service.TrainingService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;

@Service
public class TrainingServiceimpl implements TrainingService {

	private final TrainingRepository trainingrepository;

	public TrainingServiceimpl(TrainingRepository trainingRepository) {
		this.trainingrepository = trainingRepository;
	}

	@Override
	public Training assignTraining(Long studentId, Long trainerId, Training training) {

		Training train = trainingrepository.save(training);
		if (train == null)
			throw new DetailsNotFoundException("Unable to save training details");
		return train;
	}

	@Override
	public Training getTrainingById(Long id) {
		return trainingrepository.findById(id)
				.orElseThrow(() -> new DetailsNotFoundException("Training not found with id: " + id));
	}

	@Override
	public List<Training> getAllTrainings() {
		List<Training> listOfTraining = trainingrepository.findAll();
		if(listOfTraining == null) throw new DetailsNotFoundException("No training details found in database.");
		return listOfTraining;
	}

	@Override
	public Training updateCompletionStatus(Long trainingId, boolean status) {
		Training training = getTrainingById(trainingId);
		if(training == null) throw new DetailsNotFoundException("Unable to upadte the completion status.");
		training.setCompletionStatus(status);
		return trainingrepository.save(training);
	}

	@Override
	public String deleteTraining(Long id) {
		Training training = getTrainingById(id);
		if(training == null) throw new DetailsNotFoundException("Unable to delete the completion status.");
		trainingrepository.delete(training);
		return "Training details deleted successfully.";
	}

}
