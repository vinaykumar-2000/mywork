package com.abc.tktbooking.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tbl")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	
	@Column(name = "first_name", length = 50)
	private String firstName;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Column(length = 3)
	private int age;
	
	@Column(length = 10)
	private String mobile;
	
	@Column(length = 50,unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TicketBooking> ticketBookings;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<TicketBooking> getTicketBookings() {
		return ticketBookings;
	}

	public void setTicketBookings(List<TicketBooking> ticketBookings) {
		this.ticketBookings = ticketBookings;
	}
	
	
}
