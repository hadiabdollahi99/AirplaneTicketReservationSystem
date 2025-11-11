<%@ page import="model.Flight" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 11/9/2025
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Flight Search - Airline Reservation System</title>
    <link rel="stylesheet" href="css/search.css">
    <%
        List<Flight> flights = (List<Flight>) request.getAttribute("flight");
        String errorMessage = (String) request.getAttribute("message");
    %>
</head>
<body>
    <div class='container'>
            <div class='header'>
                    <h1>✈️ Airline Ticket Reservation System</h1>
                    <p>Find the best flights for your journey</p>
                </div>

            <div class='search-box'>
                    <form method='post' class='search-form'>
                            <div class='form-group'>
                                    <label for='departureCity'>From</label>
                                    <input type='text' id='departureCity' name='departureCity' placeholder='Departure city' required>
                                </div>
                            <div class='form-group'>
                                    <label for='destinationCity'>To</label>
                                    <input type='text' id='destinationCity' name='destinationCity' placeholder='Destination city' required>
                                </div>
                            <div class='form-group'>
                                    <label for='sortBy'>Sort By</label>
                                    <select id='sortBy' name='sortBy'>
                                            <option value=''>Default</option>
                                            <option value='price'>Price</option>
                                            <option value='airline'>Airline</option>
                                            <option value='departure'>Departure Time</option>
                                            <option value='duration'>Duration</option>
                                        </select>
                                </div>
                            <div class='form-group'>
                                    <label for='sortOrder'>Order</label>
                                    <select id='sortOrder' name='sortOrder'>
                                            <option value='ASC'>Ascending</option>
                                            <option value='DESC'>Descending</option>
                                        </select>
                                </div>
                            <button type='submit' class='btn'>Search Flights</button>
                        </form>
                </div>

        <% if (errorMessage != null) { %>

        <div class='error'><%=errorMessage%></div>
        <% } %>



        <% if (flights != null) { %>
            <div class='flight-results'>
                    <h2 style='margin-bottom: 20px; color: #333;'>Available Flights</h2>
<% if (flights.isEmpty()) { %>
                    <div class='no-flights'>
                            <h3>No flights found for this route</h3>
                            <p>Please try different cities or check back later.</p>
                        </div>
                <% } else {
                    for (Flight flight : flights) { %>
                    <div class='flight-card'>
                            <div class='flight-header'>
                                    <div class='airline-info'>
                                            <%=flight.getAirline().getName()%>
                                            <span class='flight-number'>(<%=flight.getFlightNumber()%>)</span>
                                        </div>
                                </div>
                            <div class='flight-details'>
                                    <div class='time-info'>
                                            <div class='time'><%=flight.getFormattedDepartureTime()%></div>
                                            <div class='city'><%=flight.getDepartureCity()%></div>
                                        </div>
                                    <div class='duration'>
                                            <%=flight.getFormattedDuration()%>
                                        </div>
                                    <div class='time-info'>
                                            <div class='time'><%=flight.getFormattedArrivalTime()%></div>
                                            <div class='city'><%=flight.getDestinationCity()%></div>
                                        </div>
                                    <div class='price-info'>
                                            <div class='price'>$<%=flight.getPrice()%></div>
                                            <div class='seats'><%=flight.getAvailableSeats()%> seats available</div>
                                            <div class='aircraft'><%=flight.getAircraftType()%></div>
                                        </div>
                                </div>
                        </div>
                <%
                        }
                    }
                %>
                </div>
        <%
            }
        %>
            <div class='admin-link'>
                    <a href='airline-login'>Airline Company Login</a>
                </div>
        </div>
</body>
</html>