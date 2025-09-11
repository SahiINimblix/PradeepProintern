package prointern.ProinternApplication.Certification.Model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String expertise;

    @Email
    private String email;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings;

    public Trainer() {}

    public Trainer(Long id, String name, String expertise, String email, List<Training> trainings) {
        this.id = id;
        this.name = name;
        this.expertise = expertise;
        this.email = email;
        this.trainings = trainings;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Training> getTrainings() { return trainings; }
    public void setTrainings(List<Training> trainings) { this.trainings = trainings; }

    @Override
    public String toString() {
        return "Trainer [id=" + id + ", name=" + name + ", expertise=" + expertise + ", email=" + email + "]";
    }
}
