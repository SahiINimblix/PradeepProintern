package prointern.ProinternApplication.Certification.Controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Certification.Model.Certificate;
import prointern.ProinternApplication.Certification.Service.CertificateService;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    // Generate certificate for a student after payment & test
    @PostMapping("/generate/{trainingId}/student/{studentId}")
    public ResponseEntity<Certificate> generateCertificate(@PathVariable("trainingId") Long trainingId,
                                                           @PathVariable("studentId") Long studentId,
                                                           @RequestParam("score") double score,
                                                           @RequestParam("status") String status,
                                                           @RequestParam("paymentId") Long paymentId) {
        // Call with correct parameter order
        Certificate certificate = certificateService.generateCertificate(
                trainingId, studentId, score, status, paymentId
        );
        return ResponseEntity.ok(certificate);
    }


    // Get single certificate
    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable("id") Long id) {
        Certificate certificate = certificateService.getCertificateById(id);
        return ResponseEntity.ok(certificate);
    }

    // Download certificate as PDF (direct file download)
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable("id") Long id) {
        byte[] pdfBytes = certificateService.downloadCertificate(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificate_" + id + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    // Get Base64 version of certificate 
    @GetMapping("/{id}/download/base64")
    public ResponseEntity<String> downloadCertificateBase64(@PathVariable("id") Long id) {
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
