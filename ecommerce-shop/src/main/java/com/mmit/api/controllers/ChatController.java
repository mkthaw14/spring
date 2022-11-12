package com.mmit.api.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmit.controller.requestData.ChatParticipantData;
import com.mmit.model.entity.ChatMessage;
import com.mmit.model.entity.ChatRoom;
import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.model.service.ChatMessageService;
import com.mmit.model.service.ChatRoomService;
import com.mmit.model.service.UserService;

@RestController
public class ChatController 
{
	@Autowired
	private ChatRoomService chatRoomService;
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	private static final String CUSTOMER_SUPPORT_CHANNEL_ID = "cs56gsab";
	private static final String SITE_OWNER_NAME = "Site Owner";
	
	@PostMapping("/chat/get-customer_support_operator_name")
	public String getCustomerSupportOperatorName()
	{
		return SITE_OWNER_NAME;
	}

	@PostMapping("/chat/get-chatId")
	public ChatParticipantData getChatId(Principal principal)
	{
		System.out.println(principal.getName());
		
		ChatRoom room = chatRoomService.findChatRoomByUserNames(principal.getName(), CUSTOMER_SUPPORT_CHANNEL_ID);

		if(room == null)
		{
			room = new ChatRoom();
			room.setUser1(principal.getName());
			room.setUser2(CUSTOMER_SUPPORT_CHANNEL_ID);	
			chatRoomService.save(room);
		}
		
		return new ChatParticipantData(principal.getName(), room.getId());
	}
	
	@PostMapping("/chat/load-offline-msg")
	public List<ChatMessage> loadOfflineMessages(String chatId)
	{
		List<ChatMessage> messages = chatMessageService.findByChatRoomId(chatId);
		
		System.out.println("loaded msg : " + messages);
		return messages;
	}
	
	@MessageMapping("/private-msg")
	public void sendMessageToSiteAdmin(@Payload ChatMessage msg, Principal principal) throws Exception
	{
		System.out.println("Message : " + msg);
		msg.setSenderName(principal.getName());
		
		if(msg.getContent().isEmpty() || msg.getContent().isBlank())
			throw new Exception("msg content is empty");
		
		chatMessageService.save(msg);
		simpMessagingTemplate.convertAndSendToUser(msg.getChatRoom().getId(), "customer-private-chat-room", msg);
	}
	
	@MessageMapping("/private-msg-to-customer")
	public void sendMessageToCustomer(@Payload ChatMessage message, Principal principal) throws Exception
	{
		message.setSenderName(SITE_OWNER_NAME);
		
		if(message.getContent().isEmpty() || message.getContent().isBlank())
			throw new Exception("msg content is empty");
		
		chatMessageService.save(message);
		simpMessagingTemplate.convertAndSendToUser(message.getChatRoom().getId(), "customer-private-chat-room", message);
	}
	
	
}
