package prointern.ProinternApplication.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.UserNotFoundException;
import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.Repository.UserRepository;
import prointern.ProinternApplication.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public User registerUser(User user) throws Exception {
		// check if email already exists
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			throw new DetailsNotFoundException("Email is already reigistered");
		}
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserNotFoundException("Email Id is not registered.");
		else
			return user;
	}

	@Override
	public User forgotPasswordThroughEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null)
			return user;
		else
			throw new UserNotFoundException("Email Id is not registered.");
	}

	@Override
	public String resetPasswordByEmail(String email, String password) {
		User user = userRepository.findByEmail(email);
		// verification code
		if (!user.equals(null)) {
			user.setPassword(password);
			userRepository.save(user);
			return "Password updated successfully";
		} else {
			throw new UserNotFoundException("Email address is not registered.");
		}
	}

	@Override
	public String loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserNotFoundException("Invalid Email address");
		else if (user.getStatus().equalsIgnoreCase("unverified"))
			throw new UserNotFoundException("Unable to Login, user is not verified");
		else if (user.getPassword().equals(password)) {
			user.setStatus("VERIFIED");
			return "Login Successfully";
		} else
			throw new UserNotFoundException("Invalid Password");
	}

	@Override
	public void saveOTP(int otp,String email) {
		userRepository.saveOTP(otp, email);		
	}
}
