package service;

import model.Airline;


public interface AirlineService extends BaseService<Long, Airline> {
    Airline findByEmail(String email);
}