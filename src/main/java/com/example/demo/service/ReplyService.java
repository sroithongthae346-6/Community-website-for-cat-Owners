package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reply;
import com.example.demo.repository.ReplyNotFoundException;
import com.example.demo.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	ReplyRepository replyRepository;
	
	public List<Reply> getReplies(){
		List<Reply> replies = (List<Reply>) replyRepository.findAll();
		return replies;
	}
	
	public Reply getReplyById(Long id) {
		return replyRepository.findById(id).orElseThrow(()->new ReplyNotFoundException(id));
	}
	
	public void saveReply(Reply r) {
		replyRepository.save(r);
	}
	
	public Reply addReply(Reply r) {
		replyRepository.save(r);
		return r;
	}
	
	public void deleteReply(Long id) {
		replyRepository.deleteById(id);
	}
	
	public Reply updateReply(Long id, Reply r) {
		Reply existingReply = replyRepository.findById(id).get();
		existingReply.setContent(r.getContent());
		return replyRepository.save(existingReply);
	}
}
