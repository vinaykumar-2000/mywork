package com.abc.tktbooking.service;

import java.util.List;

import com.abc.tktbooking.entity.Route;

public interface RouteService {

	Route saveRoute(Route route);
	
	Route getRouteById(int routeId);
	
	List<Route> getAllRoutes();
	
	Route updateRoute(Route route);
	
	void deleteRoute(int routeId);
}
