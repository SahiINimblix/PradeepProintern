package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Model.Trainer;
import prointern.ProinternApplication.Certification.Model.Trainer;
import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Repository.TrainerRepository;
import prointern.ProinternApplication.Certification.Repository.TrainingRepository;
import prointern.ProinternApplication.Certification.Service.TrainingService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;

@Service
public class TrainingServiceimpl implements TrainingService {

	private final TrainingRepository trainingRepository;
	private final StudentRepository studentRepo;
	private final TrainerRepository trainerRepo;

	public TrainingServiceimpl(TrainingRepository trainingRepository,StudentRepository studentRepo,TrainerRepository trainerRepo) {
		this.trainingRepository = trainingRepository;
		this.studentRepo = studentRepo;
		this.trainerRepo = trainerRepo;
	}

	@Override
	public String assignTraining(Long studentId, Long trainerId, Training training) {
//		Student stud = studentRepo.findById(studentId).orElseThrow(()->new DetailsNotFoundException("Student not found with id "+studentId));
//		studentRepo.updateTraining(studentId,training);
//		Trainer trainer = trainerRepo.findById(trainerId).orElseThrow(()->new DetailsNotFoundException("Trainer not found with id "+trainerId));
//		trainerRepo.updateTraining(trainerId,training);
		Training train = trainingRepository.save(training);
		
		if (train == null)
			throw new OperationFailedException("Unable to save training details");
		return "Training details saved successfully";
	}

	@Override
	public Training getTrainingById(Long id) {
		return trainingRepository.findById(id)
				.orElseThrow(() -> new DetailsNotFoundException("Training not found with id: " + id));
	}

	@Override
	public List<Training> getAllTrainings() {
		List<Training> listOfTraining = trainingRepository.findAll();
		if(listOfTraining == null) throw new DetailsNotFoundException("No training details found in database.");
		return listOfTraining;
	}

	@Override
	public String updateCompletionStatus(Long trainingId, boolean status) {
		Training training = trainingRepository.findById(trainingId).orElseThrow(()->new DetailsNotFoundException("Training details not found"));
		training.setCompletionStatus(status);
		Training training1 = trainingRepository.save(training);
		if(training1 == null) throw new OperationFailedException("Unable to save the status");
		return "Training details updated successfully";
	}

	@Override
	public String deleteTraining(Long id) {
		Training training = trainingRepository.findById(id).orElseThrow(()->new DetailsNotFoundException("Training details not found"));
		trainingRepository.delete(training);
		if(trainingRepository.existsById(id)) throw new OperationFailedException("Unable to delete");
		return "Training details deleted successfully.";
	}

}
