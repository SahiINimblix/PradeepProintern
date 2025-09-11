package prointern.ProinternApplication.PlacementAssistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.PlacementAssistance.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
