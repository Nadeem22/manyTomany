package com.demo.manyTomany.manyTomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.manyTomany.manyTomany.model.Role;
import com.demo.manyTomany.manyTomany.model.RoleRepository;
import com.demo.manyTomany.manyTomany.model.User;
import com.demo.manyTomany.manyTomany.model.UserRepository;

@Controller
public class MController {
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private RoleRepository roleRepository;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/users")
	public String showUserList(Model model) {
	List<User> listUsers=	userRepository.findAll();
		model.addAttribute("listUsers",listUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String createNewUserForm(Model model) {
		List<Role> listRoles=roleRepository.findAll();
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("user", new User());
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		System.out.println("Selected User Roles" +user.getRoles());
		userRepository.save(user);
		return "redirect:/users";
	}
}
