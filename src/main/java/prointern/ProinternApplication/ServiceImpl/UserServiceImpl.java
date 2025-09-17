package prointern.ProinternApplication.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Exception.UserAlreadyExistsException;
import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.Repository.UserRepository;
import prointern.ProinternApplication.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public User registerUser(User user) {
		// check if email already exists
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			throw new UserAlreadyExistsException("Email is already reigistered");
		}
		User user1 = userRepository.save(user);
		if(user1 ==null) throw new OperationFailedException("Unable to Register");
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new DetailsNotFoundException("Email Id is not registered.");
		else
			return user;
	}

	@Override
	public User forgotPasswordThroughEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null)
			return user;
		else
			throw new DetailsNotFoundException("Email Id is not registered.");
	}

	@Override
	public String resetPasswordByEmail(String email, String password) {
		User user = userRepository.findByEmail(email);
		// verification code
		if (user == null)
			throw new DetailsNotFoundException("Email address is not registered.");
		else {
			user.setPassword(password);
			userRepository.save(user);
			return "Password updated successfully";
		}

	}

	@Override
	public String loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new DetailsNotFoundException("Invalid Email address");
		else if (user.getStatus().equalsIgnoreCase("unverified"))
			throw new OperationFailedException("Unable to Login, user is not verified");
		else if (user.getPassword().equals(password)) {
			user.setStatus("VERIFIED");
			return "Login Successfully";
		} else
			throw new DetailsNotFoundException("Invalid Password");
	}

	@Override
	public void saveOTP(int otp, String email) {
		userRepository.saveOTP(otp, email);
	}

	@Override
	public void verificationUpdate(String email) {
		userRepository.verificationUpdate(email);

	}

	@Override
	public User getUserByOTP(int storedOtp) {
		return userRepository.getUserByOTP(storedOtp);
	}
}
