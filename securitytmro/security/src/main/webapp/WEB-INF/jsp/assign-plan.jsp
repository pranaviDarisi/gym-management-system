<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assign Fitness Plan</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            max-width: 500px;
            width: 100%;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-size: 14px;
            color: #555;
            display: block;
            margin-bottom: 8px;
        }

        select, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: #fafafa;
            box-sizing: border-box;
        }

        select:focus, button:focus {
            border-color: #007bff;
            outline: none;
            background-color: #f4faff;
        }

        button {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #218838;
        }

        .message {
            text-align: center;
            margin-bottom: 20px;
        }

        .success {
            color: green;
        }

        .error {
            color: red;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Assign Fitness Plan to User</h2>

        <!-- Success message -->
        <c:if test="${not empty successMessage}">
            <div class="message success">${successMessage}</div>
        </c:if>

        <!-- Error message -->
        <c:if test="${not empty errorMessage}">
            <div class="message error">${errorMessage}</div>
        </c:if>

        <form action="/admin/savePlan" method="post">
            <label for="userId">Select User:</label>
            <select name="userId" required>
                <c:forEach items="${clients}" var="user">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>

            <label for="fitnessPlanId">Select Fitness Plan:</label>
            <select name="fitnessPlanId" required>
                <c:forEach items="${fitnessPlans}" var="plan">
                    <option value="${plan.id}">${plan.planName}</option>
                </c:forEach>
            </select>

            <button type="submit">Assign Plan</button>
        </form>

        <a class="back-link" href="/admin/dashboard">Back to dashboard</a>
    </div>

</body>
</html>
