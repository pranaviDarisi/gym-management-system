<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background-color: white;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #f9f9f9;
            margin: 5px 0;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }
        a {
            display: block;
            text-align: center;
            margin: 20px auto;
            padding: 10px 15px;
            width: 200px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Profile Details</h1>

<c:if test="${not empty user}">
    <table>
        <tr>
            <th>ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>Username</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>Mobile</th>
            <td>${user.mobile}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${user.age}</td>
        </tr>
        <tr>
            <th>Weight</th>
            <td>${user.weight}</td>
        </tr>
        <tr>
            <th>Height</th>
            <td>${user.height}</td>
        </tr>
        <tr>
            <th>Enabled</th>
            <td>${user.enabled}</td>
        </tr>
        <tr>
            <th>Membership</th>
            <td>${user.membership}</td> <!-- Assuming 'membership' is a field in the Membership entity -->
        </tr>
        <tr>
            <th>Authorities</th>
            <td>
                <ul>
                    <c:forEach var="authority" items="${user.authorities}">
                        <li>${authority}</li> <!-- Assuming authority is a string or has a suitable toString() method -->
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </table>
</c:if>


        <a href="/admin/dashboard">Back to dashboard</a>



</body>
</html>
