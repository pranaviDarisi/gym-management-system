<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User Details</title>
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
        form {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="email"], input[type="number"], select {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        input[readonly] {
            background-color: #f9f9f9; /* Optional: lighter background for readonly */
            border: 1px solid #ddd; /* Maintain border style */
        }
    </style>
</head>
<body>
<h1>Edit Profile Details</h1>

<c:if test="${not empty user}">
    <form action="/user/details/update/${user.id}" method="post">
        <input type="hidden" name="id" value="${user.id}"> <!-- Hidden field for user ID -->

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" required>

        <label for="mobile">Mobile:</label>
        <input type="text" id="mobile" name="mobile" value="${user.mobile}" required>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}" required>

        <label for="weight">Weight:</label>
        <input type="number" id="weight" name="weight" value="${user.weight}" required>

        <label for="height">Height:</label>
        <input type="number" id="height" name="height" value="${user.height}" required>

        <label for="enabled">Enabled:</label>
        <select id="enabled" name="enabled">
            <option value="true" <c:if test="${user.enabled}">selected</c:if>>Yes</option>
        </select>

        <label for="membership">Membership:</label>
        <input type="text" id="membership" name="membership" value="${membership}" readonly="readonly"> <!-- Read-only field -->

        <input type="submit" value="Update User">
    </form>
</c:if>

<a href="${pageContext.request.contextPath}/user/dashboard">Back to User Dashboard</a>

</body>
</html>
