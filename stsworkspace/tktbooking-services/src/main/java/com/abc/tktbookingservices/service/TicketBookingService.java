package com.abc.tktbookingservices.service;

import java.util.List;

import com.abc.tktbookingservices.entity.TicketBooking;
import com.abc.tktbookingservices.model.TicketBookingResponse;
import com.abc.tktbookingservices.payload.TicketBookingPayload;

public interface TicketBookingService {
	
	TicketBooking createTicketBooking(TicketBookingPayload ticketBookingPayload);
	
	TicketBooking saveTicketBooking(TicketBooking ticketBooking);
	
	TicketBookingResponse getTicketBookingDetails(int bookingId);
	
	List<TicketBookingResponse> getAllTicketBookings();
	
	
}
