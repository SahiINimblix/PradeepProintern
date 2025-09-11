package prointern.ProinternApplication.Certification.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Certification.Model.Payment;
import prointern.ProinternApplication.Certification.Service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Make a new payment for a student in a training
    @PostMapping("/student/{studentId}/training/{trainingId}")
    public ResponseEntity<Payment> makePayment(@PathVariable Long studentId,
                                               @PathVariable Long trainingId,
                                               @RequestParam double amount) {
        Payment payment = paymentService.makePayment(studentId, trainingId, amount);
        return ResponseEntity.ok(payment);
    }

    //  Get all payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    //  Get payments made by a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Payment>> getPaymentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(paymentService.getPaymentsByStudent(studentId));
    }

    // Get payments for a specific training
    @GetMapping("/training/{trainingId}")
    public ResponseEntity<List<Payment>> getPaymentsByTraining(@PathVariable Long trainingId) {
        return ResponseEntity.ok(paymentService.getPaymentsByTraining(trainingId));
    }

    //  Get a single payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }
}
