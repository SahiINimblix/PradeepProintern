package prointern.ProinternApplication.PlacementAssistance.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.PlacementAssistance.Service.AuthService;
import prointern.ProinternApplication.PlacementAssistance.Service.QuizService;
import prointern.ProinternApplication.PlacementAssistance.dto.AnswerDTO;
import prointern.ProinternApplication.PlacementAssistance.dto.QuestionDTO;
import prointern.ProinternApplication.PlacementAssistance.dto.StartQuizResponse;
import prointern.ProinternApplication.PlacementAssistance.dto.SubmitQuizRequest;
import prointern.ProinternApplication.PlacementAssistance.dto.SubmitQuizResponse;
import prointern.ProinternApplication.PlacementAssistance.entities.Attempt;
import prointern.ProinternApplication.PlacementAssistance.entities.User;
import prointern.ProinternApplication.PlacementAssistance.repo.AttemptRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final AuthService authService;
    private final QuizService quizService;
    private final AttemptRepository attemptRepository;

    private static final int QUESTIONS_PER_ATTEMPT = 25;

    public QuizController(AuthService authService, QuizService quizService, AttemptRepository attemptRepository) {
        this.authService = authService;
        this.quizService = quizService;
        this.attemptRepository = attemptRepository;
    }

    private Optional<User> getUserFromHeader(String authorization) {
        if (authorization == null) return Optional.empty();
        String token = authorization.replace("Bearer ", "").trim();
        return authService.findByToken(token);
    }

    @GetMapping("/start")
    public ResponseEntity<?> startQuiz(@RequestHeader(value = "Authorization", required = false) String authorization) {
        Optional<User> uOpt = getUserFromHeader(authorization);
        if (uOpt==null) return ResponseEntity.status(401).body("invalid token");
        Attempt attempt = quizService.startAttempt(uOpt.get(), QUESTIONS_PER_ATTEMPT);
        List<QuestionDTO> qs = quizService.questionsForAttempt(attempt);
        StartQuizResponse resp = new StartQuizResponse(attempt.getId(), qs);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestHeader(value = "Authorization", required = false) String authorization,
                                        @RequestBody SubmitQuizRequest req) {
        Optional<User> uOpt = getUserFromHeader(authorization);
        if (uOpt == null) return ResponseEntity.status(401).body("invalid token");

        if (req.getAttemptId() == null || req.getAnswers() == null) {
            return ResponseEntity.badRequest().body("attemptId and answers required");
        }

        Optional<Attempt> attOpt = attemptRepository.findById(req.getAttemptId());
        if (attOpt==null) return ResponseEntity.badRequest().body("attempt not found");
        Attempt attempt = attOpt.get();
        if (!attempt.getUser().getId().equals(uOpt.get().getId())) {
            return ResponseEntity.status(403).body("attempt does not belong to user");
        }
        if (attempt.getStatus() != null && attempt.getStatus().name().equals("COMPLETED")) {
            return ResponseEntity.badRequest().body("attempt already submitted");
        }

        Map<Long, Integer> map = req.getAnswers().stream()
                .filter(a -> a.getQuestionId() != null && a.getSelectedOption() != null)
                .collect(Collectors.toMap(AnswerDTO::getQuestionId, AnswerDTO::getSelectedOption));

        int score = quizService.submitAnswers(attempt, map);
        SubmitQuizResponse resp = new SubmitQuizResponse(attempt.getId(), score, attempt.getQuestionIds().size());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/attempt/{id}")
    public ResponseEntity<?> getAttempt(@RequestHeader(value = "Authorization", required = false) String authorization,
                                        @PathVariable Long id) {
        Optional<User> uOpt = getUserFromHeader(authorization);
        if (uOpt==null) return ResponseEntity.status(401).body("invalid token");

        Optional<Attempt> attOpt = attemptRepository.findById(id);
        if (attOpt==null) return ResponseEntity.badRequest().body("attempt not found");
        Attempt attempt = attOpt.get();
        if (!attempt.getUser().getId().equals(uOpt.get().getId())) {
            return ResponseEntity.status(403).body("attempt does not belong to user");
        }
        // build response
        Map<String, Object> out = new HashMap<>();
        out.put("attemptId", attempt.getId());
        out.put("score", attempt.getScore());
        out.put("total", attempt.getQuestionIds().size());
        out.put("status", attempt.getStatus());
        out.put("answers", attempt.getAnswers());
        out.put("questionIds", attempt.getQuestionIds());
        return ResponseEntity.ok(out);
    }
}
