package prointern.ProinternApplication.Internship.Model;

public class ExecutionRequest {
    private String code;
    private String language;
    private Long taskId;
    
    // Constructors
    public ExecutionRequest() {}
    
    public ExecutionRequest(String code, String language, Long taskId) {
        this.code = code;
        this.language = language;
        this.taskId = taskId;
    }
    
    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
}