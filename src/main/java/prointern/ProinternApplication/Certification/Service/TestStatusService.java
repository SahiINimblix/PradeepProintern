package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Certificate;

public interface TestStatusService {
    String recordTestResult(Long trainingId, Long studentId, double score, String status, Long paymentId);
    List<Certificate> getTestResultsByStudent(Long studentId);
    List<Certificate> getTestResultsByTraining(Long trainingId);
}
