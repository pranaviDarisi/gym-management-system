<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
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
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
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
            background-color: #28a745;
            color: #fff;
            border-radius: 8px;
            padding: 20px;
            width: 250px;
            margin: 20px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .card:hover {
            background-color: #218838;
        }

        .card h3 {
            margin-bottom: 10px;
            font-size: 20px;
        }

        .card p {
            font-size: 14px;
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
        <h1>Admin Dashboard</h1>
        <div>
            <a  href="/admin/details/${userId}">Profile</a> 
        </div>
    </div>

    <div class="dashboard">
        <h2>Welcome to the Admin Dashboard</h2>
        <div class="cards">
            <!-- Manage Users -->
            <a href="/admin/getusers" class="card">
                <h3>View Users</h3>
                <p>Admin can only view users' data</p>
            </a>

            <!-- Manage Fitness Plans -->
            <a href="/admin/fitnessPlans/add" class="card">
                <h3>Manage Fitness Plans</h3>
                <p>View, Add, or Edit Plans</p>
            </a>

            <!-- Assign Fitness Plans to Users -->
            <a href="/admin/assignPlan" class="card">
                <h3>Assign Plans</h3>
                <p>Assign Fitness Plans to Users</p>
            </a>

            <!-- Manage Memberships -->
            <a href="/admin/memberships/showForm" class="card">
                <h3>Manage Memberships</h3>
                <p>View, Add, Delete, or Update</p>
            </a>
        </div>

        <div class="logout">
            <a href="/logout">Logout</a>
        </div>
    </div>

</body>
</html>
