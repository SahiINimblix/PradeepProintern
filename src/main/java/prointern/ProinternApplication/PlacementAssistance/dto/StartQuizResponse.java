package prointern.ProinternApplication.PlacementAssistance.dto;

import java.util.List;

public class StartQuizResponse {
    private Long attemptId;
    private List<QuestionDTO> questions;

    public StartQuizResponse() {}

    public StartQuizResponse(Long attemptId, List<QuestionDTO> questions) {
        this.attemptId = attemptId;
        this.questions = questions;
    }

    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }
}
