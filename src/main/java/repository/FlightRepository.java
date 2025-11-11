package repository;

import model.Flight;

import java.util.List;

public interface FlightRepository extends BaseRepository<Long, Flight> {
    List<Flight> getFlightsByRoute(String departureCity, String destinationCity);
    List<Flight> getFlightsByAirline(Long airlineId);
    List<Flight> searchFlightsWithSorting(String departureCity, String destinationCity,
                                          String sortBy, String sortOrder);
}
