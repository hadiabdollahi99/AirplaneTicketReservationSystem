package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Airline;
import model.User;
import repository.AirlineRepository;
import repository.UserRepository;
import repository.impl.AirlineRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.AirlineService;
import service.UserService;
import service.impl.AirlineServiceImpl;
import service.impl.UserServiceImpl;
import util.PasswordUtil;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;
    private AirlineService airlineService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserRepository userRepository = new UserRepositoryImpl();
        AirlineRepository airlineRepository = new AirlineRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
        airlineService = new AirlineServiceImpl(airlineRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        password = PasswordUtil.hashPassword(password);
        airlineService.saveOrUpdate(Airline.builder().name(username).password(password).email(email).build());
//        userService.saveOrUpdate(User.builder().username(username).email(email).password(password).role(Role.CUSTOMER).build());
        resp.sendRedirect(req.getContextPath() + "/search");
    }
}
