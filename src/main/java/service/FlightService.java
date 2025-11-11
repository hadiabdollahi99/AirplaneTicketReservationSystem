package service;

import model.Flight;
import model.User;

import java.util.List;

public interface FlightService extends BaseService<Long, Flight> {
    List<Flight> getFlightsByRoute(String departureCity, String destinationCity);
    List<Flight> getFlightsByAirline(Long airlineId);
    List<Flight> searchFlightsWithSorting(String departureCity, String destinationCity,
                                          String sortBy, String sortOrder);
}
