package prointern.ProinternApplication.Certification.Model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String certificateId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestStatus testStatus;   // PASSED / FAILED / NOT_TAKEN

    @Min(0)
    @Max(100)
    private Double score;   // 0–100 %

    @OneToOne(optional = false)
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    private Payment payment;   // ✅ FK to Payment.id

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    public Certificate() {}

    public Certificate(Long id, String certificateId, TestStatus testStatus, Double score,
                       Payment payment, Student student, Training training, Trainer trainer) {
        this.id = id;
        this.certificateId = certificateId;
        this.testStatus = testStatus;
        this.score = score;
        this.payment = payment;
        this.student = student;
        this.training = training;
        this.trainer = trainer;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCertificateId() { return certificateId; }
    public void setCertificateId(String certificateId) { this.certificateId = certificateId; }

    public TestStatus getTestStatus() { return testStatus; }
    public void setTestStatus(TestStatus testStatus) { this.testStatus = testStatus; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Training getTraining() { return training; }
    public void setTraining(Training training) { this.training = training; }

    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }

    // --- Derived field ---
    @Transient
    public LocalDate getIssueDate() {
        return payment != null ? payment.getPaymentDate() : null;
    }

    @Override
    public String toString() {
        return "Certificate [id=" + id +
                ", certificateId=" + certificateId +
                ", testStatus=" + testStatus +
                ", score=" + score +
                ", issueDate=" + getIssueDate() + "]";
    }
}
