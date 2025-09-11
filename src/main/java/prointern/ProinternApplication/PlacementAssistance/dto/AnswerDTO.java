package prointern.ProinternApplication.PlacementAssistance.dto;

public class AnswerDTO {
    private Long questionId;
    private Integer selectedOption; // 0-3

    public AnswerDTO() {}
    public AnswerDTO(Long questionId, Integer selectedOption) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
    public Integer getSelectedOption() { return selectedOption; }
    public void setSelectedOption(Integer selectedOption) { this.selectedOption = selectedOption; }
}
