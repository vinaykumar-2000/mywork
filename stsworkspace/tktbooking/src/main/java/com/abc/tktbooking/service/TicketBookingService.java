package com.abc.tktbooking.service;

import java.util.List;

import com.abc.tktbooking.entity.TicketBooking;
import com.abc.tktbooking.payload.TicketBookingPayload;

public interface TicketBookingService {
	
	TicketBooking createTicketBooking(TicketBookingPayload ticketBookingPayload);
	
	TicketBooking getTicketBookingDetails(int bookingId);
	
	List<TicketBooking> getAllTicketBookings();
	
	List<TicketBooking> getAllTicketBookingByUserId(int userId);
	
	List<TicketBooking> getAllTicketBookingByRouteId(int routeId);
	
	TicketBooking updateTicketBookingStatus(int bookingId, String status);
	
	void deleteTicketBooking(int bookingId);
}
