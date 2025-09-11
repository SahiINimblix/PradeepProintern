package prointern.ProinternApplication.Certification.Model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ✅ keep as "id"

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    // Optional one-to-one back reference (not mandatory)
    @OneToOne(mappedBy = "payment")
    private Certificate certificate;

    public Payment() {}

    public Payment(Long id, LocalDate paymentDate, Double amount, Student student, Training training) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.student = student;
        this.training = training;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Training getTraining() { return training; }
    public void setTraining(Training training) { this.training = training; }

    public Certificate getCertificate() { return certificate; }
    public void setCertificate(Certificate certificate) { this.certificate = certificate; }
}
