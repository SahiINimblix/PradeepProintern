package prointern.ProinternApplication.QuestionApi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.QuestionApi.Repository.QuestionRepository;
import prointern.ProinternApplication.QuestionApi.Model.quizQuestions;

@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository=questionRepository;
	}
	public List<quizQuestions> getRandomQuestions(String topic){
		return questionRepository.findRandomQuestionsByTopic(topic);
	
	}

}
