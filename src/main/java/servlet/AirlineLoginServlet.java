package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Airline;
import repository.AirlineRepository;
import repository.impl.AirlineRepositoryImpl;
import service.AirlineService;
import service.impl.AirlineServiceImpl;
import util.PasswordUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/airline-login")
public class AirlineLoginServlet extends HttpServlet {
    private AirlineService airlineService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        AirlineRepository airlineRepository = new AirlineRepositoryImpl();
        airlineService = new AirlineServiceImpl(airlineRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("airline-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Airline airline = airlineService.findByEmail(email);

        if (PasswordUtil.verifyPassword(password, airline.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("airline", airline);
            response.sendRedirect(request.getContextPath() + "/airline-dashboard");
            return;
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h1 style=\"color:orange\">This airline cannot find!</h1>");
    }
}