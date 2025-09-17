package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Training;

public interface TrainingService {

	String assignTraining(Long studentId, Long trainerId, Training training);
    Training getTrainingById(Long id);
    List<Training> getAllTrainings();
    String updateCompletionStatus(Long trainingId, boolean status);
    String deleteTraining(Long id);
}
