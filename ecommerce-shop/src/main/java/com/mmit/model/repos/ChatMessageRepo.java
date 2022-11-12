package com.mmit.model.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.model.entity.ChatMessage;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
	@Query(name = "Select * From messages Where chat_room_id = :chatID", nativeQuery = true)
	List<ChatMessage> findByChatRoomId(@Param("chatID") String chatID);
}
