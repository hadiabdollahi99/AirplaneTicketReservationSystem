package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Flight;
import repository.FlightRepository;
import repository.impl.FlightRepositoryImpl;
import service.FlightService;
import service.impl.FlightServiceImpl;

import java.io.IOException;
import java.util.List;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private FlightService flightService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        FlightRepository flightRepository = new FlightRepositoryImpl();
        flightService = new FlightServiceImpl(flightRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights = flightService.findAll();
        req.setAttribute("flight", flights);
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departureCity = request.getParameter("departureCity");
        String destinationCity = request.getParameter("destinationCity");
        String sortBy = request.getParameter("sortBy");
        String sortOrder = request.getParameter("sortOrder");

        if (departureCity == null || destinationCity == null ||
                departureCity.isEmpty() || destinationCity.isEmpty()) {
            List<Flight> flights = flightService.findAll();
            String message = "Please select both departure and destination cities!";
            request.setAttribute("flight", flights);
            request.setAttribute("message" , message);
            request.getRequestDispatcher("search.jsp").forward(request, response);
            return;
        }

        List<Flight> flights;
        if (sortBy != null && !sortBy.isEmpty()) {
            flights = flightService.searchFlightsWithSorting(departureCity, destinationCity, sortBy, sortOrder);
        } else {
            flights = flightService.getFlightsByRoute(departureCity, destinationCity);
        }

        request.setAttribute("flight", flights);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
