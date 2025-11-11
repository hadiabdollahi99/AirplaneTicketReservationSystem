<%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 11/9/2025
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Airline Login</title>
    <link rel="stylesheet" href="css/airline-login.css">
    <%
        String errorMessage = (String) request.getAttribute("message");
    %>
</head>
<body>
    <div class='login-container'>
            <div class='login-header'>
                    <h1>✈️ Airline Login</h1>
                    <p>Access your airline dashboard</p>
                </div>

        <% if (errorMessage != null) { %>
        <div class='error'><%=errorMessage%></div>
        <% } %>
            <form method='post'>
                    <div class='form-group'>
                            <label for='email'>Email</label>
                            <input type='email' id='email' name='email' required>
                        </div>
                    <div class='form-group'>
                            <label for='password'>Password</label>
                            <input type='password' id='password' name='password' required>
                        </div>
                    <button type='submit' class='btn'>Login</button>
                </form>
            <div class='back-link'>
                    <a href='search'>← Back to Flight Search</a>
                </div>
        </div>
</body>
</html>