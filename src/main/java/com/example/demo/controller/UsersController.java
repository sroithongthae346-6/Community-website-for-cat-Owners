package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UserService userService;

	// View Profile
	@GetMapping("/user/{id}")
	public String getViewProfile(@PathVariable Long id, Model model, Principal principal) {
		User myUser = userService.findByUsername(principal.getName());
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("myUser", myUser);
		return "viewUser";
	}

	// Edit User
	@GetMapping("/editUser/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	@PostMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Long id, @ModelAttribute User user, Principal principal, HttpSession session) {
	    User myUser = userService.findByUsername(principal.getName());
	    if (myUser.getId() == id) {  // Fixed the comparison
	        userService.updateUser(id, user);
	        // Update session with new user data
	        session.setAttribute("user", userService.getUserById(id)); // Update the session with the new user
	        return "redirect:/myProfile";
	    } else {
	        return "error/403"; // return forbidden if user is not authorized
	    }
	}

	@GetMapping("/test")
	public String getTest(@ModelAttribute Post post, Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "test";
	}

}
