package prointern.ProinternApplication.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.Service.EmailService;
import prointern.ProinternApplication.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private static int storedOtp;
	private String getMail;

	private static String tokenCreated;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public  String registerUser(@RequestBody User user) {
		try {
			User newUser = userService.registerUser(user);
			storedOtp = emailService.sendVerificationEmail(newUser.getEmail());
			userService.saveOTP(storedOtp,newUser.getEmail());
			System.out.println("Generated OTP: " + storedOtp);
//			String[] parts = newUser.getEmail().split("@");
//			String encryptMail = parts[0].substring(0, 3) + "XXX" +parts[0].substring(-2)+ "@" + parts[1];
			return "OTP sent to " + newUser.getEmail();
		} catch (Exception e) {
			throw new OperationFailedException("Unable to save : "+e.getMessage());
		}
	}

	@PostMapping("/verify")
	public String verify(@RequestParam("otp") int otp) {
//		userService.getUserByEmail(getMail);
		User user = userService.getUserByOTP(otp);
		if(user== null) throw new DetailsNotFoundException("Invalid OTP!");
		if (otp == user.getOtp()) {
			userService.verificationUpdate(user.getEmail());
			return "Verification successful!";
		} else {
			throw new DetailsNotFoundException("Invalid OTP!");
		}
	}

	@PostMapping("/forgot-password")
	public String forgotPasswordThroughEmail(@RequestParam("email") String email) {
		try {
			User user = userService.forgotPasswordThroughEmail(email);
			tokenCreated = UUID.randomUUID().toString();
			emailService.sendPasswordResetMail(email, tokenCreated);
			String[] parts = user.getEmail().split("@");
			String encryptMail = parts[0].substring(0, 3) + "XXX" + "@" + parts[1];

			getMail = email;
			System.out.println("Reset Link:\n http://localhost:8080/api/auth/reset-password?token="+tokenCreated);
			return "Password reset link sent to your registered email: " + encryptMail;
		} catch (Exception e) {
			System.err.print(e);
			throw new DetailsNotFoundException(e.getMessage());
		}
	}

	@PutMapping("/reset-password")
	public String updatePasswordByEmail(@RequestParam("token") String token,
			@RequestParam("password") String password) {
		if (token.equals(tokenCreated)) {
			return userService.resetPasswordByEmail(getMail, password);
		} else
			throw new DetailsNotFoundException("Invalid or expired token");
	}

	@GetMapping("/getByEmail")
	public User getUserByEmail(@RequestParam("email") String email) {
		return userService.getUserByEmail(email);
	}

	@GetMapping("/login")
	public String loginuser(@RequestParam("email") String email, @RequestParam("password") String password) {
		return userService.loginUser(email, password);
	}
}
