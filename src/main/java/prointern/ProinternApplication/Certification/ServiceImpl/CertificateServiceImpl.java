package prointern.ProinternApplication.Certification.ServiceImpl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import prointern.ProinternApplication.Certification.Model.Certificate;
import prointern.ProinternApplication.Certification.Model.Payment;
import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Model.TestStatus;
import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Repository.CertificateRepository;
import prointern.ProinternApplication.Certification.Repository.PaymentRepository;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Repository.TrainingRepository;
import prointern.ProinternApplication.Certification.Service.CertificateService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;

@Service
public class CertificateServiceImpl implements CertificateService {

	private final CertificateRepository certificateRepository;
	private final TrainingRepository trainingRepository;
	private final StudentRepository studentRepository;
	private final PaymentRepository paymentRepository;

	public CertificateServiceImpl(CertificateRepository certificateRepository, TrainingRepository trainingRepository,
			StudentRepository studentRepository, PaymentRepository paymentRepository) {
		this.certificateRepository = certificateRepository;
		this.trainingRepository = trainingRepository;
		this.studentRepository = studentRepository;
		this.paymentRepository = paymentRepository;
	}

	@Override
	public Certificate generateCertificate(Long trainingId, Long studentId, double score, String status,
			Long paymentId) {

		Training training = trainingRepository.findById(trainingId)
				.orElseThrow(() -> new DetailsNotFoundException("Training not found with id: " + trainingId));

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new DetailsNotFoundException("Student not found with id: " + studentId));

		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new DetailsNotFoundException("Payment not found with id: " + paymentId));

		if (!Boolean.TRUE.equals(training.getCompletionStatus())) {
			throw new DetailsNotFoundException("Cannot generate certificate: Training not completed yet");
		}

		// 🔹 Convert String → Enum safely
		TestStatus testStatus;
		try {
			testStatus = TestStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new DetailsNotFoundException("Invalid TestStatus: " + status);
		}

		Certificate certificate = new Certificate();
		certificate.setTraining(training);
		certificate.setStudent(student);
		certificate.setTrainer(training.getTrainer());
		certificate.setPayment(payment); // ✅ issueDate comes from Payment
		certificate.setCertificateId("CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		certificate.setScore(score);
		certificate.setTestStatus(testStatus);

		return certificateRepository.save(certificate);
	}

	@Override
	public Certificate getCertificateById(Long id) {
		return certificateRepository.findById(id)
				.orElseThrow(() -> new DetailsNotFoundException("Certificate not found with id: " + id));
	}

	@Override
	public List<Certificate> getAllCertificates() {
		return certificateRepository.findAll();
	}

	@Override
	public byte[] downloadCertificate(Long id) {
		Certificate cert = getCertificateById(id);

		if (cert.getStudent() == null || cert.getTraining() == null) {
			throw new DetailsNotFoundException("Student or Training info missing for certificate id " + id);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, baos);
			document.open();

			document.add(new Paragraph("CERTIFICATE OF APPRECIATION"));
			document.add(new Paragraph("This is to certify that"));
			document.add(new Paragraph(cert.getStudent().getName()));
			document.add(new Paragraph("Has successfully completed: " + cert.getTraining().getTitle()));
			document.add(new Paragraph("Training Description: " + cert.getTraining().getDescription()));
			document.add(new Paragraph("Score: " + cert.getScore()));
			document.add(new Paragraph("Status: " + cert.getTestStatus()));
			document.add(new Paragraph("Issued on: " + cert.getIssueDate()));

		} catch (Exception e) {
			throw new DetailsNotFoundException("Error generating PDF");
		} finally {
			document.close();
		}

		return baos.toByteArray();
	}

}
