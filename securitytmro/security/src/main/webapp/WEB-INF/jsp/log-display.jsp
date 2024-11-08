<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workout History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
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
            margin-bottom: 20px;
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
            display: inline-block;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            text-align: center;
        }

        a:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

    <div class="container">
        <h2>Your Workout History</h2>

        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Exercise</th>
                    <th>Duration (mins)</th>
                    <th>Calories Burned</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${workoutHistory}" var="workout">
                    <tr>
                        <td>${workout.date}</td>
                        <td>${workout.exerciseName}</td>
                        <td>${workout.duration}</td>
                        <td>${workout.caloriesBurn}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/user/dashboard">Back to Dashboard</a>
    </div>

</body>
</html>
