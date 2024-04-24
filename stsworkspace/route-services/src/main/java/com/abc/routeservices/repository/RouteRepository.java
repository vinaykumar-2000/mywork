package com.abc.routeservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.routeservices.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Integer>{

}
