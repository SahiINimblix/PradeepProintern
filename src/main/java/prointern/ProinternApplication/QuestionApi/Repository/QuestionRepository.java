package prointern.ProinternApplication.QuestionApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import prointern.ProinternApplication.QuestionApi.Model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	@Query(value= "Select * from question where topic = ?1 ORDER BY RAND() LIMIT 20", nativeQuery = true)
	List<Question> findRandomQuestionsByTopic(String topic);

}
