package prointern.ProinternApplication.Certification.Controller;

import com.kiran.certification.certification.model.Payment;
import com.kiran.certification.certification.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
