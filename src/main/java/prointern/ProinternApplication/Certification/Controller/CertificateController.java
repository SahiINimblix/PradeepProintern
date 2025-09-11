package prointern.ProinternApplication.Certification.Controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kiran.certification.certification.model.Certificate;
import com.kiran.certification.certification.service.CertificateService;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    // Generate certificate for a student after payment & test
    @PostMapping("/generate/{trainingId}/student/{studentId}")
    public ResponseEntity<Certificate> generateCertificate(@PathVariable Long trainingId,
                                                           @PathVariable Long studentId,
                                                           @RequestParam double score,
                                                           @RequestParam String status,
                                                           @RequestParam Long paymentId) {
        // Call with correct parameter order
        Certificate certificate = certificateService.generateCertificate(
                trainingId, studentId, score, status, paymentId
        );
        return ResponseEntity.ok(certificate);
    }


    // Get single certificate
    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long id) {
        Certificate certificate = certificateService.getCertificateById(id);
        return ResponseEntity.ok(certificate);
    }

    // Download certificate as PDF (direct file download)
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long id) {
        byte[] pdfBytes = certificateService.downloadCertificate(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificate_" + id + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    // Get Base64 version of certificate 
    @GetMapping("/{id}/download/base64")
    public ResponseEntity<String> downloadCertificateBase64(@PathVariable Long id) {
        byte[] pdfBytes = certificateService.downloadCertificate(id);
        String base64 = Base64.getEncoder().encodeToString(pdfBytes);
        return ResponseEntity.ok(base64);
    }


    // Get all certificates
    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }
}
