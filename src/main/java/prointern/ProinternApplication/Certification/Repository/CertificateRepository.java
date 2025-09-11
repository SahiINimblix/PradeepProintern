package prointern.ProinternApplication.Certification.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.Certification.Model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByStudentId(Long studentId);
    List<Certificate> findByTrainingId(Long trainingId);
}
