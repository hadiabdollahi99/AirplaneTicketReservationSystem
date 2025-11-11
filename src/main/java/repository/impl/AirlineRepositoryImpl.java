package repository.impl;

import jakarta.persistence.EntityManager;
import model.Airline;
import repository.AirlineRepository;
import util.EntityManagerProvider;

import java.util.Optional;

public class AirlineRepositoryImpl extends BaseRepositoryImpl<Long, Airline> implements AirlineRepository {
    @Override
    public Class<Airline> getEntityClass() {
        return Airline.class;
    }

    @Override
    public Airline validateAirline(String email, String password) {
        return EntityManagerProvider
                .getEntityManager()
                .createQuery("FROM Airline WHERE email = :email AND password = :password", Airline.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public Optional<Airline> findByEmail(String email) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        Airline airline = em.createQuery("select a from Airline a where a.email = :email", Airline.class)
                .setParameter("email",email).getSingleResult();
        return Optional.of(airline);
    }


}
