package prointern.ProinternApplication.Certification.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Certification.Model.Payment;
import prointern.ProinternApplication.Certification.Model.Student;
import prointern.ProinternApplication.Certification.Model.Training;
import prointern.ProinternApplication.Certification.Repository.PaymentRepository;
import prointern.ProinternApplication.Certification.Repository.StudentRepository;
import prointern.ProinternApplication.Certification.Repository.TrainingRepository;
import prointern.ProinternApplication.Certification.Service.PaymentService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final StudentRepository studentRepository;
	private final TrainingRepository trainingRepository;

	public PaymentServiceImpl(PaymentRepository paymentRepository, StudentRepository studentRepository,
			TrainingRepository trainingRepository) {
		this.paymentRepository = paymentRepository;
		this.studentRepository = studentRepository;
		this.trainingRepository = trainingRepository;
	}

	@Override
	public String makePayment(Long studentId, Long trainingId, double amount) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new DetailsNotFoundException("Student not found"));
		Training training = trainingRepository.findById(trainingId)
				.orElseThrow(() -> new DetailsNotFoundException("Training not found"));

		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setPaymentDate(LocalDate.now());
		payment.setStudent(student);
		payment.setTraining(training);

		Payment payment1 = paymentRepository.save(payment);
		if (payment1 == null)
			throw new OperationFailedException("Unable to save payment");
		return "Payment saved successfully";
	}

	@Override
	public List<Payment> getAllPayments() {
		List<Payment> listOfPayments = paymentRepository.findAll();
		if (listOfPayments.isEmpty())
			throw new DetailsNotFoundException("No payments found in database");
		return listOfPayments;
	}

	@Override
	public List<Payment> getPaymentsByStudent(Long studentId) {
		List<Payment> listOfPayment = paymentRepository.findByStudentId(studentId);
		if (listOfPayment.isEmpty())
			throw new DetailsNotFoundException("No payments found in database");
		return listOfPayment;
	}

	@Override
	public List<Payment> getPaymentsByTraining(Long trainingId) {
		List<Payment> listOfPayment = paymentRepository.findByTrainingId(trainingId);
		if (listOfPayment.isEmpty())
			throw new DetailsNotFoundException("No payments found in database");
		return listOfPayment;
	}

	@Override
	public Payment getPaymentById(Long paymentId) {
		return paymentRepository.findById(paymentId)
				.orElseThrow(() -> new DetailsNotFoundException("Payment not found with id: "+paymentId));
	}
}
