package prointern.ProinternApplication.Certification.Model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Student name is required")
    private String name;

    @Email
    @NotNull(message = "Email is required")
    @Column(unique = true)
    private String email;

    // Owning side is Training, so here we use mappedBy
    @ManyToMany(mappedBy = "students")
    private List<Training> trainings;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificate> certificates;

    public Student() {}

    public Student(Long id, String name, String email, List<Training> trainings, List<Certificate> certificates) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.trainings = trainings;
        this.certificates = certificates;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Training> getTrainings() { return trainings; }
    public void setTrainings(List<Training> trainings) { this.trainings = trainings; }

    public List<Certificate> getCertificates() { return certificates; }
    public void setCertificates(List<Certificate> certificates) { this.certificates = certificates; }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
