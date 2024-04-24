package com.abc.tktbookingservices.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.tktbookingservices.model.Route;


@FeignClient(name="ROUTE-SERVICES")
public interface RouteServiceConsumer {
	
	@GetMapping("/route/{id}")
	Route getRouteDetails(@PathVariable("id") int routeId);
	
}
