package prointern.ProinternApplication.Certification.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Certificate;
import prointern.ProinternApplication.Certification.Model.Payment;
import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Model.TestStatus;
import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Repository.CertificateRepository;
import prointern.ProinternApplication.Certification.Repository.PaymentRepository;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Repository.TrainingRepository;
import prointern.ProinternApplication.Certification.Service.TestStatusService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;

@Service
public class TestStatusServiceImpl implements TestStatusService {

    private final CertificateRepository certificateRepository;
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final TrainingRepository trainingRepository;

    public TestStatusServiceImpl(CertificateRepository certificateRepository,
                                 PaymentRepository paymentRepository,
                                 StudentRepository studentRepository,
                                 TrainingRepository trainingRepository) {
        this.certificateRepository = certificateRepository;
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public String recordTestResult(Long trainingId, Long studentId, double score, String status, Long paymentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new DetailsNotFoundException("Student not found with id: " + studentId));

        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new DetailsNotFoundException("Training not found with id: " + trainingId));

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new DetailsNotFoundException("Payment not found with id: " + paymentId));

        // 🔹 Convert string to Enum
        TestStatus testStatus;
        try {
            testStatus = TestStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DetailsNotFoundException("Invalid TestStatus: " + status);
        }

        Certificate certificate = new Certificate();
        certificate.setStudent(student);
        certificate.setTraining(training);
        certificate.setTrainer(training.getTrainer());
        certificate.setPayment(payment); // Payment determines issueDate
        certificate.setCertificateId("CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        certificate.setScore(score);
        certificate.setTestStatus(testStatus);

        Certificate certificate1 = certificateRepository.save(certificate);
        if(certificate1==null) throw new OperationFailedException("Error in saving details");
        return "Certificate saved successfully";
    }

    @Override
    public List<Certificate> getTestResultsByStudent(Long studentId) {
    	List<Certificate> certificate = certificateRepository.findByStudentId(studentId);
    	if(certificate==null) throw new DetailsNotFoundException("Certificate of student with id "+studentId+" is not found");
    	return certificate;
    }

    @Override
    public List<Certificate> getTestResultsByTraining(Long trainingId) {
        List<Certificate> certificate = certificateRepository.findByTrainingId(trainingId);
    	if(certificate==null) throw new DetailsNotFoundException("Certificate of training with id "+trainingId+" is not found");
    	return certificate;
    }
}
