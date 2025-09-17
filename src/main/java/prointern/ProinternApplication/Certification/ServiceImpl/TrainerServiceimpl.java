package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Trainer;
import prointern.ProinternApplication.Certification.Repository.TrainerRepository;
import prointern.ProinternApplication.Certification.Service.TrainerService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;

@Service
public class TrainerServiceimpl implements TrainerService {
	
	private final TrainerRepository trainerRepository;


    public TrainerServiceimpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public String createTrainer(Trainer trainer) {
    	Trainer trainerDetails = trainerRepository.save(trainer);
    	if(trainerDetails==null) throw new OperationFailedException("Unable to save trainer details.");
    	return "Trainer details saved successfully";
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new DetailsNotFoundException("Trainer not found with id: " + id));
    }

    @Override
    public List<Trainer> getAllTrainers() {
    	List<Trainer> listOfTrainers = trainerRepository.findAll();
    	if(listOfTrainers==null) throw new DetailsNotFoundException("No trainer records found in database");
    	return listOfTrainers;
    }

    @Override
    public String updateTrainer(Long id, Trainer updatedTrainer) {
        Trainer trainer = getTrainerById(id);
        if(trainer == null) throw new DetailsNotFoundException("Trainer not found with id "+id);
        trainer.setName(updatedTrainer.getName());
        trainer.setExpertise(updatedTrainer.getExpertise());
        Trainer trainer1 = trainerRepository.save(trainer);
        if(trainer1==null) throw new OperationFailedException("Unable to update");
        return "Trainer details updated successfully";
    }

    @Override
    public String deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(()-> new DetailsNotFoundException("Student details not found to delete"));
        trainerRepository.delete(trainer);
        if(trainerRepository.existsById(id))
        	return "Trainer Details deleted successfully";
        else 
        	throw new OperationFailedException("Unable to delete");
    }

}
