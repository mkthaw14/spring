package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.ChatRoom;
import com.mmit.model.repos.ChatRoomRepo;

@Service
public class ChatRoomService {

	@Autowired
	ChatRoomRepo repo;
	
	public void save(ChatRoom chatRoom)
	{
		repo.save(chatRoom);
	}
	
	public ChatRoom findChatRoomByTwoUserNames(String senderName, String receiverName) {
		ChatRoom room = repo.findByUser1AndUser2(senderName, receiverName);
		
		if(room != null) 
			return room;
		
		//If the first query doesn't work then swaps the parameter position order
		room = repo.findByUser1AndUser2(receiverName, senderName);
		return room;
	}
	
	public ChatRoom findChatRoomByUserNames(String user1, String user2) {
		ChatRoom room = repo.findByUser1AndUser2OrUser2AndUser1(user1, user2, user1, user2);
		return room;
	}

	public ChatRoom findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public List<ChatRoom> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}
