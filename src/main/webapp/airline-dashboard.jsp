<%@ page import="model.Flight" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Airline" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 11/11/2025
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <title>Airline Dashboard</title>
        <link rel="stylesheet" href="css/airline-dashboard.css">
        <%
            List<Flight> flights = (List<Flight>) request.getAttribute("flight");
            Airline airline = (Airline) request.getAttribute("airline");
            String message = (String) request.getAttribute("message");
        %>
</head>
<body>
<div class='header'>
    <div class='container'>
        <div class='header-content'>
            <h1>✈️ Airline Dashboard - <%=airline.getName()%></h1>
            <div class='user-info'>
                <span>Welcome, <%=airline.getName()%></span>
                <form method='post' style='display: inline;'>
                    <input type='hidden' name='action' value='logout'>
                    <button type='submit' class='btn'>Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class='container'>
    <div class='main-content'>

        <% if (message != null) { %>
        <div class='card message success'><%=message%></div>
        <% } %>

        <div class='card'>
            <h2>Add New Flight</h2>
            <form method='post'>
                <input type='hidden' name='action' value='add'>
                <div class='form-grid'>
                    <div class='form-group'>
                        <label for='flightNumber'>Flight Number</label>
                        <input type='text' id='flightNumber' name='flightNumber' required>
                    </div>
                    <div class='form-group'>
                        <label for='departureCity'>Departure City</label>
                        <input type='text' id='departureCity' name='departureCity' required>
                    </div>
                    <div class='form-group'>
                        <label for='destinationCity'>Destination City</label>
                        <input type='text' id='destinationCity' name='destinationCity' required>
                    </div>
                    <div class='form-group'>
                        <label for='departureTime'>Departure Time</label>
                        <input type='datetime-local' id='departureTime' name='departureTime' required>
                    </div>
                    <div class='form-group'>
                        <label for='arrivalTime'>Arrival Time</label>
                        <input type='datetime-local' id='arrivalTime' name='arrivalTime' required>
                    </div>
                    <div class='form-group'>
                        <label for='price'>Price ($)</label>
                        <input type='number' id='price' name='price' step='0.01' min='0' required>
                    </div>
                    <div class='form-group'>
                        <label for='totalSeats'>Total Seats</label>
                        <input type='number' id='totalSeats' name='totalSeats' min='1' required>
                    </div>
                    <div class='form-group'>
                        <label for='aircraftType'>Aircraft Type</label>
                        <input type='text' id='aircraftType' name='aircraftType' required>
                    </div>
                </div>
                <button type='submit' class='btn btn-primary' style='margin-top: 20px;'>Add Flight</button>
            </form>
        </div>

        <div class='card'>
            <h2>Your Flights</h2>
            <table class='flight-table'>
                <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Route</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Price</th>
                    <th>Seats</th>
                    <th>Aircraft</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (flights != null && !flights.isEmpty()) {
                        for (Flight flight : flights) {
                %>

                <tr>
                    <td><%=flight.getFlightNumber()%></td>
                    <td><%=flight.getDepartureCity()%> → <%=flight.getDestinationCity()%></td>
                    <td><%=flight.getFormattedDepartureTime()%></td>
                    <td><%=flight.getFormattedArrivalTime()%></td>
                    <td>$<%=flight.getPrice()%></td>
                    <td><%=flight.getAvailableSeats()%>/<%=flight.getTotalSeats()%></td>
                    <td><%=flight.getAircraftType()%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan='7' style='text-align: center; color: #666;'>No flights added yet.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>