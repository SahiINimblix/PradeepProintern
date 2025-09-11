package prointern.ProinternApplication.Certification.Controller;

import com.kiran.certification.certification.model.Certificate;
import com.kiran.certification.certification.service.TestStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-status")
public class TestStatusController {

    private final TestStatusService testStatusService;

    public TestStatusController(TestStatusService testStatusService) {
        this.testStatusService = testStatusService;
    }

    // Record test result for a student in a training
    @PostMapping("/training/{trainingId}/student/{studentId}")
    public ResponseEntity<Certificate> recordTestResult(@PathVariable Long trainingId,
                                                        @PathVariable Long studentId,
                                                        @RequestParam double score,
                                                        @RequestParam String status,
                                                        @RequestParam Long paymentId) {
        Certificate certificate = testStatusService.recordTestResult(trainingId, studentId, score, status, paymentId);
        return ResponseEntity.ok(certificate);
    }

    //  Get test results (certificates) of a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Certificate>> getTestResultsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(testStatusService.getTestResultsByStudent(studentId));
    }

    // Get all test results for a training
    @GetMapping("/training/{trainingId}")
    public ResponseEntity<List<Certificate>> getTestResultsByTraining(@PathVariable Long trainingId) {
        return ResponseEntity.ok(testStatusService.getTestResultsByTraining(trainingId));
    }
}
