package com.abc.routeservices.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.routeservices.entity.Route;
import com.abc.routeservices.exceptions.ResourceNotFoundException;
import com.abc.routeservices.repository.RouteRepository;

@SpringBootTest
public class RouteServiceTest {

	@InjectMocks
	private RouteServiceImpl routeService;
	
	@Mock
	private RouteRepository routeRepository;
	
	@Test
	public void testSaveRoute() {
		Route route = new Route();
		route.setRouteId(101);
		route.setSource("CityA");
		route.setDestination("CityB");
		route.setTicketPrice(800);
		
		when(routeRepository.save(route)).thenReturn(route);
		
		Route savedRoute = routeService.saveRoute(route);
		
		assertNotNull(savedRoute);
		assertEquals("CityA", savedRoute.getSource());
		assertEquals("CityB", savedRoute.getDestination());
	}
	
	@Test
	public void testGetRouteById() {
		Route route = new Route();
		route.setRouteId(101);
		route.setSource("CityA");
		route.setDestination("CityB");
		
		when(routeRepository.findById(101)).thenReturn(Optional.of(route));
		
		Route actualRoute = routeService.getRouteById(101);
		assertEquals("CityA", actualRoute.getSource());
		assertEquals("CityB", actualRoute.getDestination());
	}
	
	@Test
	public void testGetRouteByIdException() {
		when(routeRepository.findById(100)).thenThrow(new ResourceNotFoundException("Route not found with id : 100"));
		assertThrows(ResourceNotFoundException.class, () -> routeService.getRouteById(100));
	}
	
	@Test
	public void testGetAllRoutes() {
		Route route1 = new Route();
		route1.setRouteId(101);
		route1.setSource("CityA");
		route1.setDestination("CityB");
		route1.setTicketPrice(1600);
		
		Route route2 = new Route();
		route2.setRouteId(102);
		route2.setSource("CityC");
		route2.setDestination("CityD");
		route2.setTicketPrice(800);
		
		List<Route> routeList = new ArrayList<>();
		routeList.add(route1);
		routeList.add(route2);
		
		when(routeRepository.findAll()).thenReturn(routeList);
		
		List<Route> actualRouteList = routeService.getAllRoutes();
		
		assertEquals(2, actualRouteList.size());
	}
	
	@Test
	public void testUpdateRoute() {
		Route route = new Route();
		route.setRouteId(101);
		route.setSource("CityA");
		route.setDestination("CityB");
		route.setTicketPrice(800);
		
		when(routeRepository.findById(101)).thenReturn(Optional.of(route));
		when(routeRepository.save(route)).thenReturn(route);
		
		Route updatedRoute = routeService.updateRoute(route);
		
		assertNotNull(updatedRoute);
		assertEquals("CityA", updatedRoute.getSource());
		assertEquals("CityB", updatedRoute.getDestination());
	}
	
	@Test
	public void testUpdateRouteException() {
		Route route = new Route();
		route.setRouteId(101);
		route.setSource("CityA");
		route.setDestination("CityB");
		route.setTicketPrice(800);
		
		when(routeRepository.findById(100)).thenThrow(new ResourceNotFoundException("Route not found with id : 100"));
		
		assertThrows(ResourceNotFoundException.class, () -> routeService.updateRoute(route));
		
		verify(routeRepository, times(0)).save(route);
	}
	
	@Test
	public void testDeleteRoute() {
		Route route = new Route();
		route.setRouteId(101);
		route.setSource("CityA");
		route.setDestination("CityB");
		route.setTicketPrice(800);
		
		when(routeRepository.findById(101)).thenReturn(Optional.of(route));
		
		doNothing().when(routeRepository).delete(route);
		
		routeService.deleteRoute(101);
		
		verify(routeRepository, times(1)).findById(101);
		verify(routeRepository, times(1)).delete(route);
	}
	
	@Test
	public void testDeleteRouteWithException() {
		Route route = new Route();
		route.setRouteId(101);
		route.setSource("CityA");
		route.setDestination("CityB");
		route.setTicketPrice(800);
		
		when(routeRepository.findById(100)).thenThrow(new ResourceNotFoundException("Route not found with id : 100"));
		assertThrows(ResourceNotFoundException.class, () -> routeService.deleteRoute(100));
		
		verify(routeRepository, times(0)).delete(route);
	}
	
}
