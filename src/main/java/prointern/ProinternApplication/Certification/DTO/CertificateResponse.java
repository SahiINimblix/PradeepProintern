package prointern.ProinternApplication.Certification.DTO;

public class CertificateResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private String base64Data;

    public CertificateResponse(Long id, String fileName, String fileType, String base64Data) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.base64Data = base64Data;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getBase64Data() { return base64Data; }
    public void setBase64Data(String base64Data) { this.base64Data = base64Data; }
}
