package com.abc.tktbookingservices.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.tktbookingservices.entity.TicketBooking;
import com.abc.tktbookingservices.exceptions.ResourceNotFoundException;
import com.abc.tktbookingservices.model.Route;
import com.abc.tktbookingservices.model.TicketBookingResponse;
import com.abc.tktbookingservices.model.User;
import com.abc.tktbookingservices.payload.TicketBookingPayload;
import com.abc.tktbookingservices.repository.TicketBookingRepository;

@Service
public class TicketBookingServiceImpl implements TicketBookingService{
	
	@Autowired
	private TicketBookingRepository ticketBookingRepository;
	
	@Autowired
	private UserServiceConsumer userService;
	
	@Autowired
	private RouteServiceConsumer routeService;

	@Override
	public TicketBookingResponse getTicketBookingDetails(int bookingId) {
		Optional<TicketBooking> optionalTicketBooking = ticketBookingRepository.findById(bookingId);
		if(optionalTicketBooking.isEmpty()) {
			throw new ResourceNotFoundException("Ticket booking not found with Id : " + bookingId);
		}
		TicketBooking ticketBooking = optionalTicketBooking.get();
		
		TicketBookingResponse ticketBookingResponse = new TicketBookingResponse();
		ticketBookingResponse.setBookingId(ticketBooking.getBookingId());
		ticketBookingResponse.setBookingAmount(ticketBooking.getBookingAmount());
		ticketBookingResponse.setBookingStatus(ticketBooking.getBookingStatus());
		ticketBookingResponse.setDateOfBooking(ticketBooking.getDateOfBooking());
		ticketBookingResponse.setDateOfJourney(ticketBooking.getDateOfJourney());
		ticketBookingResponse.setNoOfPassengers(ticketBooking.getNoOfPassengers());
		
		User user = userService.getUserDetails(ticketBooking.getUserId());
        ticketBookingResponse.setUser(user);

        Route route = routeService.getRouteDetails(ticketBooking.getRouteId());
        ticketBookingResponse.setRoute(route);


	    return ticketBookingResponse;
	}

	@Override
	public List<TicketBookingResponse> getAllTicketBookings() {
		List<TicketBooking> ticketBookings = ticketBookingRepository.findAll();
		List<TicketBookingResponse> ticketBookingResponses = new ArrayList<>();
		for(TicketBooking ticketBooking : ticketBookings) {
			TicketBookingResponse ticketBookingResponse = new TicketBookingResponse();
			ticketBookingResponse.setBookingId(ticketBooking.getBookingId());
			ticketBookingResponse.setBookingAmount(ticketBooking.getBookingAmount());
			ticketBookingResponse.setBookingStatus(ticketBooking.getBookingStatus());
			ticketBookingResponse.setDateOfBooking(ticketBooking.getDateOfBooking());
			ticketBookingResponse.setDateOfJourney(ticketBooking.getDateOfJourney());
			ticketBookingResponse.setNoOfPassengers(ticketBooking.getNoOfPassengers());
			
			 User user = userService.getUserDetails(ticketBooking.getUserId());
	            ticketBookingResponse.setUser(user);

	            Route route = routeService.getRouteDetails(ticketBooking.getRouteId());
	            ticketBookingResponse.setRoute(route);
			
	            ticketBookingResponses.add(ticketBookingResponse);
			
			
		}
		return ticketBookingResponses;
	}

	

	@Override
	public TicketBooking createTicketBooking(TicketBookingPayload ticketBookingPayload) {
		User user = userService.getUserDetails(ticketBookingPayload.getUserId());
		Route route = routeService.getRouteDetails(ticketBookingPayload.getRouteId());
		
		double bookingAmount = calculateBookingAmount(route.getTicketPrice(), user, ticketBookingPayload.getNoOfPassengers());
		
		TicketBooking ticketBooking = new TicketBooking();
		ticketBooking.setUserId(ticketBookingPayload.getUserId());
		ticketBooking.setRouteId(ticketBookingPayload.getRouteId());
		ticketBooking.setBookingStatus("Pending");
		ticketBooking.setNoOfPassengers(ticketBookingPayload.getNoOfPassengers());
		ticketBooking.setBookingAmount(bookingAmount);
		ticketBooking.setDateOfJourney(ticketBookingPayload.getDateOfJourney());
		ticketBooking.setDateOfBooking(java.time.LocalDate.now());
		
		return ticketBookingRepository.save(ticketBooking);
	}
	
	 private double calculateBookingAmount(double ticketPrice, User user, int noOfPassengers) {
		 
		 return ticketPrice * noOfPassengers;
	 }

	@Override
	public TicketBooking saveTicketBooking(TicketBooking ticketBooking) {
		ticketBookingRepository.save(ticketBooking);
		return ticketBooking;
	}
	 
}

