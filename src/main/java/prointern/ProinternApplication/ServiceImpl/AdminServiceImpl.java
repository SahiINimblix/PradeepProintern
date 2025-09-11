package prointern.ProinternApplication.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.Repository.UserRepository;
import prointern.ProinternApplication.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}
}
