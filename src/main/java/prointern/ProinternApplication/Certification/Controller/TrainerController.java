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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import prointern.ProinternApplication.Certification.Model.Trainer;
import prointern.ProinternApplication.Certification.Service.TrainerService;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

	private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public String createTrainer(@Valid @RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @GetMapping("/{id}")
    @Validated
    public ResponseEntity<Trainer> getTrainerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @PutMapping("/{id}")
    @Validated
    public String updateTrainer(@PathVariable("id") Long id, @RequestBody Trainer trainer) {
        return trainerService.updateTrainer(id, trainer);
    }

    @DeleteMapping("/{id}")
    @Validated
    public String deleteTrainer(@PathVariable("id") Long id) {
        return trainerService.deleteTrainer(id);
//        return ResponseEntity.noContent().build();
    }
}
