package com.mmit.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.model.entity.ChatRoom;


public interface ChatRoomRepo extends JpaRepository<ChatRoom, String> {
	
	@Query(name = "Select * From chat_room Where user1 = :u1 And user2 = :u2", nativeQuery = true)
	ChatRoom findByUser1AndUser2(@Param("u1") String u1, @Param("u2") String u2);

	@Query(name = "Select * From chat_room Where (user1 = :u1 And user2 = :u2) Or (user2 = :u3 And user1 = :u4)", nativeQuery = true)
	ChatRoom findByUser1AndUser2OrUser2AndUser1(@Param("u1") String u1, @Param("u2") String u2, @Param("u3") String u3, @Param("u4") String u4);
}
