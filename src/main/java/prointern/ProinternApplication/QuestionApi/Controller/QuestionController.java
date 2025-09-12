package prointern.ProinternApplication.QuestionApi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.QuestionApi.Model.Question;
import prointern.ProinternApplication.QuestionApi.Service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
	private final QuestionService questionService;
	
	public QuestionController(QuestionService questionService) {
		this.questionService=questionService;
	}
	@GetMapping("/{topic}")
	public List<Question> getQuestions(@PathVariable("topic") String topic) {
		return questionService.getRandomQuestions(topic);
	}
	
}