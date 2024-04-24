package com.abc.tktbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.abc.tktbooking.entity.Route;
import com.abc.tktbooking.exceptions.ResourceNotFoundException;
import com.abc.tktbooking.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteRepository routeRepository;

	@Override
	public Route saveRoute(Route route) {
		routeRepository.save(route);
		return route;
	}

	@Override
	public Route getRouteById(int routeId) {
		Optional<Route> optionalRoute = routeRepository.findById(routeId);
		if(optionalRoute.isEmpty()) {
			throw new ResourceNotFoundException("Route not Found with this Id : "+routeId);
		}
		Route route = optionalRoute.get();
		return route;
	}

	@Override
	public List<Route> getAllRoutes() {
		List<Route> routes = routeRepository.findAll();
		return routes;
	}

	@Override
	public Route updateRoute(Route route) {
		Optional<Route> optionalRoute = routeRepository.findById(route.getRouteId());
		if(optionalRoute.isEmpty()) {
			throw new ResourceNotFoundException("Route not Found with this Id : "+route.getRouteId());
		}
		routeRepository.save(route);
		return route;
	}

	@Override
	public void deleteRoute(int routeId) {
		Optional<Route> optionalRoute = routeRepository.findById(routeId);
		if(optionalRoute.isEmpty()) {
			throw new ResourceNotFoundException("Route not Found with this Id : "+routeId);
		}
		Route route = optionalRoute.get();
		routeRepository.delete(route);
		
	}

}
