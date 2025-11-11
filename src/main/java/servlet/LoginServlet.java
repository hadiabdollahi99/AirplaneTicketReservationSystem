//package servlet;
//
//import jakarta.persistence.NoResultException;
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import model.User;
//import repository.UserRepository;
//import repository.impl.UserRepositoryImpl;
//import service.UserService;
//import service.impl.UserServiceImpl;
//import util.PasswordUtil;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//    private UserService userService;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        UserRepository userRepository = new UserRepositoryImpl();
//        userService = new UserServiceImpl(userRepository);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect(req.getContextPath()+"/login.jsp");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        User user;
//        try {
//            user = userService.findByUserName(username);
//        }catch (NoResultException e){
//            writer.println("<h1 style=\"color:red\">This username and password cannot find!</h1>");
//            return;
//        }
//
//        if (PasswordUtil.verifyPassword(password,user.getPassword())){
//            if (user.getRole() == Role.CUSTOMER) {
//                HttpSession session = req.getSession();
//                session.setAttribute("user",user);
//                resp.sendRedirect(req.getContextPath()+"/search-flights");
//                return;
//            }
//            HttpSession session = req.getSession();
//            session.setAttribute("user",user);
//            resp.sendRedirect(req.getContextPath()+"/airline-dashboard");
//            return;
//        }
//
//        writer.println("<h1 style=\"color:orange\">This username or password cannot find!</h1>");
//    }
//}