<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        .roles {
            margin-top: 20px;
            text-align: left; /* Aligns the list to the left for better readability */
        }
        .roles h2 {
            color: #007bff; /* Change the color of the roles heading */
        }
        ul {
            list-style-type: none; /* Removes bullet points */
            padding: 0;
        }
        li {
            padding: 8px;
            border-bottom: 1px solid #ddd; /* Adds a separator line between roles */
        }
        li:last-child {
            border-bottom: none; /* Removes the last line */
        }
        .btn {
            display: inline-block;
            margin: 10px 5px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, ${username}!</h1>
        <div class="roles">
            <h2>Your Roles:</h2>
            <ul>
                <c:forEach var="role" items="${roles}">
                    <li>${role.authority}</li>
                </c:forEach>
            </ul>
        </div>
        <a href="/logout" class="btn">Logout</a>
        <a href="/delete" class="btn">Delete Account</a>
        <a href="/updatePassword" class="btn">Update Password</a>
    </div>
</body>
</html>
