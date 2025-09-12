package prointern.ProinternApplication.PlacementAssistance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import prointern.ProinternApplication.PlacementAssistance.entities.PlacementQuestion;

public interface PlacmentQuestionRepository extends JpaRepository<PlacementQuestion, Long> {
	@Query(value= "Select * from question where topic = ?1 ORDER BY RAND() LIMIT 20", nativeQuery = true)
	List<PlacementQuestion> findRandomQuestionsByTopic(String topic);
}
