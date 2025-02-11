package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

@Controller
public class CommentController {
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/addComment")
	public String addCommentForm(Model model) {
		model.addAttribute("comment", new Comment());
		return "viewPost";
	}

	@PostMapping("/addComment")
	public String addPost(@ModelAttribute Comment comment,Post post) {
		// รับผู้ใช้ที่ล็อกอินอยู่
		Post currentpost = postService.getPostById(post.getId());
		
		// ตั้งค่าผู้ใช้ของโพสต์และบันทึกโพสต์
		comment.setPost(currentpost);
		commentService.saveComment(comment);
		return "redirect:/viewPost";
	}

}
