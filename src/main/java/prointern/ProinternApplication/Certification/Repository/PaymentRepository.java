package prointern.ProinternApplication.Certification.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.Certification.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentId(Long studentId);
    List<Payment> findByTrainingId(Long trainingId);
}
