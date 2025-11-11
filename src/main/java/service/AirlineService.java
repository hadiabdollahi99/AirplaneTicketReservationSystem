package service;

import model.Airline;


public interface AirlineService extends BaseService<Long, Airline> {
    Airline validateAirline(String email, String password);
    Airline findByEmail(String email);
}