package service.impl;

import jakarta.persistence.NoResultException;
import model.Airline;
import repository.AirlineRepository;
import service.AirlineService;

import java.util.Optional;

public class AirlineServiceImpl extends BaseServiceImpl<Long, Airline, AirlineRepository> implements AirlineService {
    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        super(airlineRepository);
    }


    @Override
    public Airline findByEmail(String email) {
        Optional<Airline> airlineOptional = baseRepository.findByEmail(email);
        if (airlineOptional.isPresent())
            return airlineOptional.get();
        throw new NoResultException("This airline cannot find!");
    }
}