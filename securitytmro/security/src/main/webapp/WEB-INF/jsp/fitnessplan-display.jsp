<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Fitness Plans</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1, h2 {
            text-align: center;
            color: #333;
        }

        .container {
            max-width: 1000px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f8f8f8;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            color: #0056b3;
        }

        .actions a {
            margin-right: 10px;
        }

        .add-plan-btn, .back-btn {
            display: inline-block;
            margin: 20px 0;
            padding: 10px 15px;
            background-color: #28a745;
            color: white;
            border-radius: 5px;
            text-decoration: none;
        }

        .add-plan-btn:hover, .back-btn:hover {
            background-color: #218838;
        }

        .back-btn {
            background-color: #6c757d;
        }

        .back-btn:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Manage Fitness Plans</h1>

        <a href="/admin/fitnessPlans/add" class="add-plan-btn">Add New Fitness Plan</a>
        
        <h2>All Fitness Plans</h2>
        <table>
            <thead>
                <tr>
                    <th>Plan ID</th>
                    <th>Plan Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plan" items="${fitnessPlans}">
                    <tr>
                        <td>${plan.id}</td>
                        <td>${plan.planName}</td>
                        <td>${plan.description}</td>
                        <td class="actions">
                            <a href="/admin/fitnessPlans/update/${plan.id}">Update</a>
                            <a href="/admin/fitnessPlans/delete/${plan.id}" onclick="return confirm('Are you sure you want to delete this plan?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/admin/dashboard" class="back-btn">Go Back to Dashboard</a>
    </div>

</body>
</html>
