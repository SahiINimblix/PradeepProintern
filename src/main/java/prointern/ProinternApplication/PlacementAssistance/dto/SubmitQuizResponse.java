package prointern.ProinternApplication.PlacementAssistance.dto;

public class SubmitQuizResponse {
    private Long attemptId;
    private int score;
    private int total;

    public SubmitQuizResponse() {}
    public SubmitQuizResponse(Long attemptId, int score, int total) {
        this.attemptId = attemptId; this.score = score; this.total = total;
    }
    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
