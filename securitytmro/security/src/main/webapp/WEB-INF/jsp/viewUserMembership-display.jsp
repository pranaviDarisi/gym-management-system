<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Membership</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #007bff;
            border-bottom: 2px solid #007bff;
            padding-bottom: 5px;
            margin-bottom: 10px;
        }

        .error-msg {
            color: red;
            font-weight: bold;
            text-align: center;
            margin: 20px 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .back-link:hover {
            background-color: #0056b3;
        }

        .membership-details {
            margin-bottom: 20px;
        }

        .empty-msg {
            text-align: center;
            font-weight: bold;
            color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Membership Details</h1>

        <!-- Error message display -->
        <c:if test="${not empty errorMessage}">
            <p class="error-msg">${errorMessage}</p>
        </c:if>

        <c:if test="${empty errorMessage}">
            <!-- Display no membership message -->
            <c:if test="${not empty noMembershipMessage}">
                <p class="error-msg">${noMembershipMessage}</p>
            </c:if>

            <!-- Display current membership if it exists -->
            <c:if test="${not empty currentMembership}">
                <div class="membership-details">
                    <h2>Current Membership</h2>
                    <p><strong>Membership Type:</strong> ${currentMembership.membershipType}</p>
                    <p><strong>Membership Fee:</strong> $${currentMembership.fee}</p>
                    <p><strong>Status:</strong> ${currentMembership.status ? 'Inactive' : 'Active'}</p>
                </div>
            </c:if>

            <h2>Available Memberships</h2>
            <c:if test="${empty memberships}">
                <p class="empty-msg">No available memberships at the moment.</p>
            </c:if>

            <c:if test="${not empty memberships}">
                <table>
                    <thead>
                        <tr>
                            <th>Membership Type</th>
                            <th>Fee</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="membership" items="${memberships}">
                            <tr>
                                <td>${membership.membershipType}</td>
                                <td>$${membership.fee}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/user/updateMembership" method="post" onsubmit="return confirm('Are you sure you want to update this membership?');">
                                        <input type="hidden" name="membershipId" value="${membership.id}" />
                                        <input type="hidden" name="username" value="${currentUser}" />
                                        <button type="submit">Change Membership</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>

        <a class="back-link" href="${pageContext.request.contextPath}/user/dashboard">Back to Dashboard</a>
    </div>
</body>
</html>
