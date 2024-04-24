package com.abc.routeservices.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.abc.routeservices.entity.Route;
import com.abc.routeservices.service.RouteService;

@RestController
@RequestMapping("/route")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@GetMapping("/all")
	public List<Route> fetchAllRoutes() {
		List<Route> routes = routeService.getAllRoutes();
		return routes;
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Route> fetchRouteDetails(@PathVariable("id") int routeId){
        Route route = routeService.getRouteById(routeId);
        return new ResponseEntity<>(route,HttpStatus.OK);
	}
	
	@PostMapping("/save")
    public ResponseEntity<Route> addRoute(@RequestBody Route route){
		routeService.saveRoute(route);
		ResponseEntity<Route> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PutMapping("/update")
    public ResponseEntity<Route> updateRoute(@RequestBody Route route){
		routeService.saveRoute(route);
		ResponseEntity<Route> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable("id") int routeId){
		routeService.deleteRoute(routeId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
}
