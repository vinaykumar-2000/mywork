package com.abc.tktbookingservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.tktbookingservices.entity.TicketBooking;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, Integer>{

}