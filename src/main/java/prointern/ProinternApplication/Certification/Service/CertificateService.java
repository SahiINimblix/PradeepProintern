package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Certificate;

public interface CertificateService {
    Certificate generateCertificate(Long trainingId, Long studentId, double score, String status, Long paymentId);
    Certificate getCertificateById(Long id);
    List<Certificate> getAllCertificates();
    byte[] downloadCertificate(Long id); // for PDF
}
