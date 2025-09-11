package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Training;

public interface TrainingService {

	Training assignTraining(Long studentId, Long trainerId, Training training);
    Training getTrainingById(Long id);
    List<Training> getAllTrainings();
    Training updateCompletionStatus(Long trainingId, boolean status);
    void deleteTraining(Long id);
}
