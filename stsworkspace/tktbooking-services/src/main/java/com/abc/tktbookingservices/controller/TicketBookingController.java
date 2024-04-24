package com.abc.tktbookingservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.tktbookingservices.entity.TicketBooking;
import com.abc.tktbookingservices.model.TicketBookingResponse;
import com.abc.tktbookingservices.payload.TicketBookingPayload;
import com.abc.tktbookingservices.service.TicketBookingService;

@RestController
@RequestMapping("/ticketBooking")
public class TicketBookingController {

	@Autowired
    private TicketBookingService ticketBookingService;
	
	@GetMapping("/all")
	public List<TicketBookingResponse> fecthAllDetails(){
		List<TicketBookingResponse> ticketBookings = ticketBookingService.getAllTicketBookings();
		return ticketBookings;
	}

    @PostMapping("/save")
    public ResponseEntity<TicketBooking> bookTicket(@RequestBody TicketBookingPayload ticketBookingPayload) {
    	TicketBooking savedTicketBooking = ticketBookingService.createTicketBooking(ticketBookingPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicketBooking);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<TicketBookingResponse> getTicketBookingDetails(@PathVariable int bookingId) {
        TicketBookingResponse ticketBooking = ticketBookingService.getTicketBookingDetails(bookingId);
        return new ResponseEntity<>(ticketBooking, HttpStatus.OK);
    }
    
}
