<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <% String login = (String) session.getAttribute("user-login");
        Boolean isAdult = (Boolean) session.getAttribute("adult");
        Boolean isSecurePassword = (Boolean) session.getAttribute("secure"); %>

        <% if (login == null || login.equals("")) { %>
            <form action="/login" method="POST">
                Login: <input type="text" name="login"><br>
                Password: <input type="password" name="password"><br>
                Age: <input type="number" min="1" max="140" name="age"><br>
                <input type="submit"/>
                <% if (isAdult != null && !isAdult ){ %>
                <p style="color: red; font-size: 20px">Field "Age" is incorrect! Login only +18!</p>
                <% } %>
            </form>
        <% } else { %>
            <h1>You are logged in as: <%= login %>
            </h1>
            <br>Click this link to <a href="/login?a=exit">logout</a>
        <% if (!isSecurePassword) { %>
            <p style="color: orange; font-size: 20px">Your password is not secure. Please change it as soon as possible!</p>
        <% } } %>

    </body>
</html>