package prointern.ProinternApplication.PlacementAssistance.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.PlacementAssistance.dto.QuestionDTO;
import prointern.ProinternApplication.PlacementAssistance.entities.Attempt;
import prointern.ProinternApplication.PlacementAssistance.entities.AttemptStatus;
import prointern.ProinternApplication.PlacementAssistance.entities.PlacementQuestion;
import prointern.ProinternApplication.PlacementAssistance.repo.AttemptRepository;
import prointern.ProinternApplication.PlacementAssistance.repo.PlacmentQuestionRepository;
import prointern.ProinternApplication.PlacementAssistance.entities.PlacementQuestion;

@Service
public class QuizService {
    private final PlacmentQuestionRepository placmentQuestionRepository;
    private final AttemptRepository attemptRepository;

    public QuizService(PlacmentQuestionRepository placmentQuestionRepository, AttemptRepository attemptRepository) {
        this.placmentQuestionRepository = placmentQuestionRepository;
        this.attemptRepository = attemptRepository;
    }

    public Attempt startAttempt(User user, int numQuestions) {
        List<PlacementQuestion> all = placmentQuestionRepository.findAll();
        Collections.shuffle(all);
        List<PlacementQuestion> chosen = all.stream().limit(numQuestions).collect(Collectors.toList());
        Attempt attempt = new Attempt();
        attempt.setUser(user);
        List<Long> qIds = chosen.stream().map(PlacementQuestion::getId).collect(Collectors.toList());
        attempt.setQuestionIds(qIds);
        attempt.setScore(null);
        attempt.setStatus(AttemptStatus.IN_PROGRESS);
        attempt.setStartedAt(LocalDateTime.now());
        attempt = attemptRepository.save(attempt);
        return attempt;
    }

    public List<QuestionDTO> questionsForAttempt(Attempt attempt) {
        List<QuestionDTO> PlacementQuestion = attempt.getQuestionIds().stream()
                .map(id -> placmentQuestionRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(q -> new QuestionDTO(q.getId(), q.getText(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()))
                .collect(Collectors.toList());
        return PlacementQuestion;
    }

    public int submitAnswers(Attempt attempt, Map<Long, Integer> answersMap) {
        int score = 0;
        for (Long qid : attempt.getQuestionIds()) {
            Optional<PlacementQuestion> qopt = placmentQuestionRepository.findById(qid);
            if (qopt ==null) continue;
            PlacementQuestion q = qopt.get();
            Integer selected = answersMap.get(qid);
            if (selected != null && selected == q.getCorrectOptionIndex()) {
                score++;
            }
        }
        attempt.setAnswers(answersMap);
        attempt.setScore(score);
        attempt.setStatus(AttemptStatus.COMPLETED);
        attempt.setCompletedAt(LocalDateTime.now());
        attemptRepository.save(attempt);
        return score;
    }

    public Optional<Attempt> findAttempt(Long attemptId) {
        return attemptRepository.findById(attemptId);
    }
}
