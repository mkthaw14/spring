package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.ChatMessage;
import com.mmit.model.repos.ChatMessageRepo;

@Service
public class ChatMessageService {

	@Autowired
	ChatMessageRepo repo;

	public List<ChatMessage> findByChatRoomId(String id) {
		return repo.findByChatRoomId(id);
	}

	public void save(ChatMessage msg) {
		repo.save(msg);
	}
	
	
}
