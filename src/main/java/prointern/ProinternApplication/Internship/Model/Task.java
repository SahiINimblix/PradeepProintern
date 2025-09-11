package prointern.ProinternApplication.Internship.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // title should not be null
    private String title;

    @Column(length = 1000)  // description can be long
    private String description;

    private String expectedLanguage;

    private Integer timeLimitMinutes;

    // Constructors
    public Task() {}

    public Task(String title, String description, String expectedLanguage, Integer timeLimitMinutes) {
        this.title = title;
        this.description = description;
        this.expectedLanguage = expectedLanguage;
        this.timeLimitMinutes = timeLimitMinutes;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getExpectedLanguage() { return expectedLanguage; }
    public void setExpectedLanguage(String expectedLanguage) { this.expectedLanguage = expectedLanguage; }

    public Integer getTimeLimitMinutes() { return timeLimitMinutes; }
    public void setTimeLimitMinutes(Integer timeLimitMinutes) { this.timeLimitMinutes = timeLimitMinutes; }
}
