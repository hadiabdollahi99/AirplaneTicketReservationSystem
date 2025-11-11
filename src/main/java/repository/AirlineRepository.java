package repository;

import model.Airline;

import java.util.Optional;

public interface AirlineRepository extends BaseRepository<Long, Airline>{
    Airline validateAirline(String email, String password);
    Optional<Airline> findByEmail(String email);
}
