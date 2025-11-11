package service.impl;

import model.Flight;
import repository.FlightRepository;
import service.FlightService;


import java.util.List;

public class FlightServiceImpl extends BaseServiceImpl<Long, Flight, FlightRepository> implements FlightService {
    public FlightServiceImpl(FlightRepository flightRepository) {
        super(flightRepository);
    }


    @Override
    public List<Flight> getFlightsByRoute(String departureCity, String destinationCity) {
        return baseRepository.getFlightsByRoute(departureCity,destinationCity);
    }

    @Override
    public List<Flight> getFlightsByAirline(Long airlineId) {
        return baseRepository.getFlightsByAirline(airlineId);
    }

    @Override
    public List<Flight> searchFlightsWithSorting(String departureCity, String destinationCity, String sortBy, String sortOrder) {
        return baseRepository.searchFlightsWithSorting(departureCity,destinationCity,sortBy,sortOrder);
    }
}