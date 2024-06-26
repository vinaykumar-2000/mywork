package com.abc.tktbooking.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.tktbooking.entity.Route;
import com.abc.tktbooking.entity.TicketBooking;
import com.abc.tktbooking.entity.User;
import com.abc.tktbooking.exceptions.ResourceNotFoundException;
import com.abc.tktbooking.payload.TicketBookingPayload;
import com.abc.tktbooking.repository.TicketBookingRepository;

@Service
public class TicketBookingServiceImpl implements TicketBookingService{
	
	@Autowired
	private TicketBookingRepository ticketBookingRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RouteService routeService;

	@Override
	public TicketBooking getTicketBookingDetails(int bookingId) {
		Optional<TicketBooking> optionalTicketBooking = ticketBookingRepository.findById(bookingId);
		if(optionalTicketBooking.isEmpty()) {
			throw new ResourceNotFoundException("Ticket booking not found with Id : " + bookingId);
		}
		TicketBooking ticketBooking = optionalTicketBooking.get();
		return ticketBooking;
	}

	@Override
	public List<TicketBooking> getAllTicketBookings() {
		List<TicketBooking> ticketBookings = ticketBookingRepository.findAll();
		return ticketBookings;
	}

	@Override
	public List<TicketBooking> getAllTicketBookingByUserId(int userId) {
		User user = userService.getUserById(userId);
		List<TicketBooking> userBookings = user.getTicketBookings();
		return userBookings;
	}

	@Override
	public List<TicketBooking> getAllTicketBookingByRouteId(int routeId) {
		Route route = routeService.getRouteById(routeId);
		List<TicketBooking> routeBookings = route.getTicketBookings();
		return routeBookings;
	}

	@Override
	public TicketBooking updateTicketBookingStatus(int bookingId, String status) {
		TicketBooking ticketBooking = getTicketBookingDetails(bookingId);
		ticketBooking.setBookingStatus(status);
		return ticketBookingRepository.save(ticketBooking);
	}

	@Override
	public void deleteTicketBooking(int bookingId) {
		TicketBooking ticketBooking = getTicketBookingDetails(bookingId);
		ticketBooking.setBookingStatus("Cancelled");
		ticketBookingRepository.save(ticketBooking);
		
	}

	@Override
	public TicketBooking createTicketBooking(TicketBookingPayload ticketBookingPayload) {
		User user = userService.getUserById(ticketBookingPayload.getUserId());
		Route route = routeService.getRouteById(ticketBookingPayload.getRouteId());
		
		double bookingAmount = calculateBookingAmount(route.getTicketPrice(), user, ticketBookingPayload.getNoOfPassengers());
		
		TicketBooking ticketBooking = new TicketBooking();
		ticketBooking.setUser(user);
		ticketBooking.setRoute(route);
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
	 
}


