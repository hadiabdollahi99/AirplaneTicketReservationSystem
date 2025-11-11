package servlet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Airline;
import model.Flight;
import repository.FlightRepository;
import repository.impl.FlightRepositoryImpl;
import service.FlightService;
import service.impl.FlightServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/airline-dashboard")
public class AirlineDashboardServlet extends HttpServlet {
    private FlightService flightService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        FlightRepository flightRepository = new FlightRepositoryImpl();
        flightService = new FlightServiceImpl(flightRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Airline airline = (Airline) session.getAttribute("airline");
        List<Flight> flights = flightService.getFlightsByAirline(airline.getId());

        request.setAttribute("flight",flights);
        request.setAttribute("airline",airline);
        request.getRequestDispatcher("airline-dashboard.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Airline airline = (Airline) session.getAttribute("airline");
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String flightNumber = request.getParameter("flightNumber");
            String departureCity = request.getParameter("departureCity");
            String destinationCity = request.getParameter("destinationCity");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"), formatter);
            LocalDateTime arrivalTime = LocalDateTime.parse(request.getParameter("arrivalTime"), formatter);

            Double price = Double.parseDouble(request.getParameter("price"));
            Integer totalSeats = Integer.parseInt(request.getParameter("totalSeats"));
            String aircraftType = request.getParameter("aircraftType");

            Flight flight = Flight.builder().airline(airline).flightNumber(flightNumber)
                    .departureCity(departureCity).destinationCity(destinationCity)
                    .departureTime(departureTime).arrivalTime(arrivalTime).price(price)
                    .totalSeats(totalSeats).aircraftType(aircraftType).build();

            flightService.saveOrUpdate(flight);

            List<Flight> flights = flightService.getFlightsByAirline(airline.getId());
            String message = "Flight added successfully!";

            request.setAttribute("airline",airline);
            request.setAttribute("flight",flights);
            request.setAttribute("message",message);
            request.getRequestDispatcher("airline-dashboard.jsp").forward(request,response);

        } else if ("logout".equals(action)) {
            response.sendRedirect(request.getContextPath()+"/logout");
        }
    }
}