package prointern.ProinternApplication.Certification.Service;

import java.util.List;

import prointern.ProinternApplication.Certification.Model.Payment;

public interface PaymentService {
    Payment makePayment(Long studentId, Long trainingId, double amount);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByStudent(Long studentId);
    List<Payment> getPaymentsByTraining(Long trainingId);
    Payment getPaymentById(Long paymentId);
}
