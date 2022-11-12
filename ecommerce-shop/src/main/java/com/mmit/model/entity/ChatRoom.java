package com.mmit.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.mmit.uidGenerator.MyRandomIDGenerator;

@Entity
@Table(
		uniqueConstraints = { @UniqueConstraint(columnNames = {"user1", "user2"})}
		)
public class ChatRoom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = MyRandomIDGenerator.generatorName)
	@GenericGenerator(name = MyRandomIDGenerator.generatorName, strategy = "com.mmit.uidGenerator.MyRandomIDGenerator")
	private String id;
	private String user1;
	private String user2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	
	public String getFirstLetterOfName()
	{
		if(this.user1 == null)
			return "";
		
		String firstLetterName = this.user1.substring(0, 1);
		return firstLetterName.toUpperCase();
	}
}
