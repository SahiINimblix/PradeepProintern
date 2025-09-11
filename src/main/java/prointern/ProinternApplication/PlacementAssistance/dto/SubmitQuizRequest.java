package prointern.ProinternApplication.PlacementAssistance.dto;

import java.util.List;

public class SubmitQuizRequest {
    private Long attemptId;
    private List<AnswerDTO> answers;

    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }
    public List<AnswerDTO> getAnswers() { return answers; }
    public void setAnswers(List<AnswerDTO> answers) { this.answers = answers; }
}
