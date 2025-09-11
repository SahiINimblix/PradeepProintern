package prointern.ProinternApplication.Internship.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String duration;
    private Integer lessonsCount;
    private Double price;
    private Integer completedLessons;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("course")
    private List<Lesson> lessons;
    
    // Constructors
    public Course() {}
    
    public Course(String title, String duration, Integer lessonsCount, Double price, Integer completedLessons) {
        this.title = title;
        this.duration = duration;
        this.lessonsCount = lessonsCount;
        this.price = price;
        this.completedLessons = completedLessons;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    
    public Integer getLessonsCount() { return lessonsCount; }
    public void setLessonsCount(Integer lessonsCount) { this.lessonsCount = lessonsCount; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Integer getCompletedLessons() { return completedLessons; }
    public void setCompletedLessons(Integer completedLessons) { this.completedLessons = completedLessons; }
    
    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }
}