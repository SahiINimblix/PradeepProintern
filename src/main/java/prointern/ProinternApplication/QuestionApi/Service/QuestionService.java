package prointern.ProinternApplication.QuestionApi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.PlacementAssistance.repo.QuestionRepository;
import prointern.ProinternApplication.QuestionApi.Model.Question;

@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository=questionRepository;
	}
	public List<Question> getRandomQuestions(String topic){
		return questionRepository.findRandomQuestionsByTopic(topic);
	
	}

}
