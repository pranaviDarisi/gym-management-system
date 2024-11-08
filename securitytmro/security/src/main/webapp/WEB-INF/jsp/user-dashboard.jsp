<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .navbar {
            background-color: #343a40;
            padding: 10px 20px;
            color: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h1 {
            font-size: 24px;
        }

        .navbar a {
            color: #fff;
            text-decoration: none;
            font-size: 18px;
            margin-left: 15px;
        }

        .dashboard {
            max-width: 1200px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .dashboard h2 {
            text-align: center;
            margin-bottom: 40px;
        }

        .dashboard .cards {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
        }

        .card {
            background-color: #007bff;
            color: #fff;
            border-radius: 8px;
            padding: 20px;
            width: 250px;
            margin: 20px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            cursor: pointer;
            text-decoration: none;
        }

        .card:hover {
            background-color: #0056b3;
        }

        .logout {
            text-align: right;
            margin-top: 20px;
        }

        .logout a {
            background-color: #dc3545;
            color: #fff;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
        }

        .logout a:hover {
            background-color: #c82333;
        }

    </style>
</head>
<body>

    <div class="navbar">
        <h1>User Dashboard</h1>
        <div>
            <a href="/user/details/${userId}">Profile</a> <!-- Pass user ID to profile -->
        </div>
    </div>

    <div class="dashboard">
        <h2>Welcome, ${username}! (${role})</h2>
        <div class="cards">
            <!-- Log Workout -->
            <a href="/user/logWorkout?username=${username}" class="card">
                <h3>Log Workout</h3>
                <p>Add your daily workout details</p>
            </a>

            <!-- View Fitness Plans -->
            <a href="/user/userPlans?username=${username}" class="card">
                <h3>My Fitness Plans</h3>
                <p>View your assigned fitness plans</p>
            </a>

            <!-- Membership Info -->
            <a href="/user/membership?username=${username}" class="card">
                <h3>Membership Info</h3>
                <p>View your membership details</p>
            </a>
        </div>

        <!-- Optional Logout -->
        <div class="logout">
            <a href="/logout">Logout</a>
        </div>
    </div>

</body>
</html>
