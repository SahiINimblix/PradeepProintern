package prointern.ProinternApplication.Certification.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Service.TrainingService;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

	private final TrainingService trainingService;

	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@PostMapping("/assign/{studentId}/{trainerId}")
	@Validated
	public String assignTraining(@PathVariable("studentId") Long studentId,@PathVariable("trainerId") Long trainerId , @RequestBody Training training) {
		return trainingService.assignTraining(studentId, trainerId, training);
	}

	@GetMapping("/{id}")
	@Validated
	public ResponseEntity<Training> getTrainingById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(trainingService.getTrainingById(id));
	}

	@GetMapping
	public ResponseEntity<List<Training>> getAllTrainings() {
		return ResponseEntity.ok(trainingService.getAllTrainings());
	}

	@PutMapping("/{id}/completion")
	@Validated
	public String updateCompletionStatus(@PathVariable("id") Long id,@RequestParam("completionStatus") String completionStatus) {
		return trainingService.updateCompletionStatus(id, Boolean.parseBoolean(completionStatus));
	}

	@DeleteMapping("/{id}")
	@Validated
	public String deleteTraining(@PathVariable("id") Long id) {
		return trainingService.deleteTraining(id);
//		return ResponseEntity.noContent().build();
	}
}
