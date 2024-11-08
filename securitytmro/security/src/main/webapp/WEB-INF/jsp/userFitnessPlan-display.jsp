<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Your Fitness Plans</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        ul {
            list-style-type: none;
            padding: 0;
            max-width: 600px;
            margin: 0 auto;
        }
        li {
            background-color: #fff;
            margin: 10px 0;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .no-plans-message {
            text-align: center;
            color: red;
            font-size: 18px;
            margin-top: 20px;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #4CAF50;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Your Fitness Plans</h1>

    <c:choose>
        <c:when test="${not empty fitnessPlans}">
            <ul>
                <c:forEach var="plan" items="${fitnessPlans}">
                    <li>
                        <div>
                            <strong>${plan.planName}</strong><br>
                            <span>${plan.description}</span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <div class="no-plans-message">
                ${noPlansMessage}
            </div>
        </c:otherwise>
    </c:choose>

    <a href="/user/dashboard">Back to Dashboard</a>
</body>
</html>
