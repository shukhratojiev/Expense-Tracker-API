package com.shukhrat.expensetrackerapi.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shukhrat.expensetrackerapi.entity.User;
import com.shukhrat.expensetrackerapi.entity.UserModel;
import com.shukhrat.expensetrackerapi.exception.ItemAlreadyExistsException;
import com.shukhrat.expensetrackerapi.exception.ResourceNotFoundException;
import com.shukhrat.expensetrackerapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(UserModel user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already registered");
		}
		
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public User read() throws ResourceNotFoundException {
		return userRepository.findById(getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
	}
	
	@Override
	public User update(User user) throws ResourceNotFoundException {
		
		User ouser = read();
		ouser.setName(user.getName() != null ? user.getName() : ouser.getName());
		ouser.setEmail(user.getEmail() != null ? user.getEmail() : ouser.getEmail());
		ouser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : ouser.getPassword());
		ouser.setAge(user.getAge() != null ? user.getAge() : ouser.getAge());

		return userRepository.save(ouser);
	}

	@Override
	public void delete() {
		User user = read();
		userRepository.delete(user);
	}

	@Override
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for " + email));
	}

}
