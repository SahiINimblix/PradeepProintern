package prointern.ProinternApplication.Service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private final SecureRandom random = new SecureRandom();
	
	public int sendVerificationEmail(String to) {
		int code = 1000 + random.nextInt(9000);
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setSubject("Email Verification");
	        mail.setText("Your OTP is: " + code);
			mail.setFrom("sonalisahoonpr91@gmail.com");
			System.out.println("Mail sent successfully to " + to);
			javaMailSender.send(mail);
		} catch (Exception e) {
//			return ResponseEntity.badRequest().body("Exception while sendEmail: "+e.getMessage());
			System.err.print("Exception while sendEmail:"+ e);
		}
		return code;
	}
	
	public String sendPasswordResetMail(String toEmail, String token) {
		String resetUrl = "http://localhost:8080/api/auth/reset-password?token="+token;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(toEmail);
			message.setSubject("Password Reset Request");
			message.setText("Click the link below to reset your password:\n" + resetUrl);
			message.setFrom("sonalisahoonpr91@gmail.com");
			System.out.println("Mail sent successfully to " + toEmail);
			javaMailSender.send(message);
		} catch (Exception e) {
//			return ResponseEntity.badRequest().body("Exception while sendEmail: "+e.getMessage());
			System.err.print("Exception while sendEmail:"+ e);
		}
		return resetUrl;
	}
}
