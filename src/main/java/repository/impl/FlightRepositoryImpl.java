package repository.impl;

import model.Flight;
import repository.FlightRepository;
import util.EntityManagerProvider;

import java.time.LocalDateTime;
import java.util.List;

public class FlightRepositoryImpl extends BaseRepositoryImpl<Long, Flight> implements FlightRepository {
    @Override
    public Class<Flight> getEntityClass() {
        return Flight.class;
    }

    @Override
    public List<Flight> getFlightsByRoute(String departureCity, String destinationCity) {
        return EntityManagerProvider
                .getEntityManager()
                .createQuery("FROM Flight WHERE departureCity = :departureCity " +
                        "AND destinationCity = :destinationCity " +
                        "AND departureTime > :now " +
                        "ORDER BY departureTime", Flight.class)
                .setParameter("departureCity", departureCity)
                .setParameter("destinationCity", destinationCity)
                .setParameter("now", LocalDateTime.now())
                .getResultList();
    }

    @Override
    public List<Flight> getFlightsByAirline(Long airlineId) {
        return EntityManagerProvider
                .getEntityManager()
                .createQuery("FROM Flight WHERE airline.id = :airlineId" +
                        " ORDER BY departureTime DESC", Flight.class)
                .setParameter("airlineId", airlineId)
                .getResultList();
    }

    @Override
    public List<Flight> searchFlightsWithSorting(String departureCity, String destinationCity,
                                                 String sortBy, String sortOrder) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM Flight WHERE departureCity = :departureCity ");
        hql.append("AND destinationCity = :destinationCity ");
        hql.append("AND departureTime > :now ");

        // Add sorting
        switch (sortBy) {
            case "price":
                hql.append("ORDER BY price ");
                break;
            case "airline":
                hql.append("ORDER BY airline.name ");
                break;
            case "departure":
                hql.append("ORDER BY departureTime ");
                break;
            case "duration":
                hql.append("ORDER BY (arrivalTime - departureTime) ");
                break;
            default:
                hql.append("ORDER BY departureTime ");
        }

        hql.append("ASC".equalsIgnoreCase(sortOrder) ? "ASC" : "DESC");

        return EntityManagerProvider
                .getEntityManager()
                .createQuery(hql.toString(), Flight.class)
                .setParameter("departureCity", departureCity)
                .setParameter("destinationCity", destinationCity)
                .setParameter("now", LocalDateTime.now())
                .getResultList();
    }
}
