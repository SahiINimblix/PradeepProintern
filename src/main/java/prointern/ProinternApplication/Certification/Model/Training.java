package prointern.ProinternApplication.Certification.Model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required")
    private String title; // <-- This is your course name

    private String description;
    private boolean completionStatus;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToMany
    @JoinTable(
        name = "student_training", 
        joinColumns = @JoinColumn(name = "training_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificate> certificates;

    public Training() {}

    public Training(Long id, String title, String description, boolean completionStatus, Trainer trainer, List<Student> students) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completionStatus = completionStatus;
        this.trainer = trainer;
        this.students = students;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean getCompletionStatus() { return completionStatus; }
    public void setCompletionStatus(boolean completionStatus) { this.completionStatus = completionStatus; }

    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public List<Certificate> getCertificates() { return certificates; }
    public void setCertificates(List<Certificate> certificates) { this.certificates = certificates; }

    // ✅ Instead of a separate "courseName" field, reuse title
    public String getCourseName() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Training [id=" + id + ", title=" + title + ", description=" + description + ", completionStatus=" + completionStatus + "]";
    }
}
