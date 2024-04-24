package com.abc.tktbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.tktbooking.entity.TicketBooking;
import com.abc.tktbooking.payload.TicketBookingPayload;
import com.abc.tktbooking.service.TicketBookingService;

@RestController
@RequestMapping("/ticketBooking")
public class TicketBookingController {

	@Autowired
    private TicketBookingService ticketBookingService;
	
	@GetMapping("/all")
	public List<TicketBooking> fecthAllDetails(){
		List<TicketBooking> ticketBookibgs = ticketBookingService.getAllTicketBookings();
		return ticketBookibgs;
	}

    @PostMapping("/save")
    public ResponseEntity<TicketBooking> bookTicket(@RequestBody TicketBookingPayload ticketBookingPayload) {
        TicketBooking bookedTicket = ticketBookingService.createTicketBooking(ticketBookingPayload);
        return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<TicketBooking> getTicketBookingDetails(@PathVariable int bookingId) {
        TicketBooking ticketBooking = ticketBookingService.getTicketBookingDetails(bookingId);
        return new ResponseEntity<>(ticketBooking, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketBooking>> getAllTicketBookingByUserId(@PathVariable int userId) {
        List<TicketBooking> userBookings = ticketBookingService.getAllTicketBookingByUserId(userId);
        return new ResponseEntity<>(userBookings, HttpStatus.OK);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<TicketBooking>> getAllTicketBookingByRouteId(@PathVariable int routeId) {
        List<TicketBooking> routeBookings = ticketBookingService.getAllTicketBookingByRouteId(routeId);
        return new ResponseEntity<>(routeBookings, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TicketBooking> updateTicketBooking(@PathVariable("id") int bookingId, @RequestParam("status") String status)  {
        TicketBooking updatedBooking = ticketBookingService.updateTicketBookingStatus(bookingId, status);
        return new ResponseEntity<>(updatedBooking,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicketBooking(@PathVariable("id") int bookingId) {
        ticketBookingService.deleteTicketBooking(bookingId);
        return new ResponseEntity<>("Order Canceled Successfully",HttpStatus.OK);
    }
    
}
