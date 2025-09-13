package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Trainer;
import prointern.ProinternApplication.Certification.Repository.TrainerRepository;
import prointern.ProinternApplication.Certification.Service.TrainerService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;

@Service
public class TrainerServiceimpl implements TrainerService {
	
	private final TrainerRepository trainerRepository;


    public TrainerServiceimpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
    	Trainer trainerDetails = trainerRepository.save(trainer);
    	if(trainerDetails==null) throw new DetailsNotFoundException("Unable to save trainer details.");
    	return trainerDetails;
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new DetailsNotFoundException("Trainer not found with id: " + id));
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        Trainer trainer = getTrainerById(id);
        if(trainer == null) throw new DetailsNotFoundException("Trainer not found with id "+id);
        trainer.setName(updatedTrainer.getName());
        trainer.setExpertise(updatedTrainer.getExpertise());
        return trainerRepository.save(trainer);
    }

    @Override
    public String deleteTrainer(Long id) {
        Trainer trainer = getTrainerById(id);
        if(trainer==null) throw new DetailsNotFoundException("Unable to delete");
        trainerRepository.delete(trainer);
        return "Trainer Details deleted successfully";
    }

}
