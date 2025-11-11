package repository;

import model.Airline;

import java.util.Optional;

public interface AirlineRepository extends BaseRepository<Long, Airline>{
    Optional<Airline> findByEmail(String email);
}
