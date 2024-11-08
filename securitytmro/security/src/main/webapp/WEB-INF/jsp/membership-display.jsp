<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Memberships</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin-bottom: 20px;
        }

        table th, table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        a {
            color: #007bff;
            text-decoration: none;
            padding: 10px;
            border-radius: 5px;
            background-color: #e9ecef;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #d1d1d1;
        }

        a.delete {
            color: white;
            background-color: #dc3545;
            padding: 8px 12px;
        }

        a.update {
            color: white;
            background-color: #28a745;
            padding: 8px 12px;
        }

        a.delete:hover, a.update:hover {
            opacity: 0.8;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .back-link:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <h2>Manage Memberships</h2>

    <h3>Existing Memberships</h3>
    <table>
        <tr>
            <th>Membership Type</th>
            <th>Fee</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${memberships}" var="membership">
            <tr>
                <td>${membership.membershipType}</td>
                <td>${membership.fee}</td>
                <td>${membership.status ? 'Active' : 'Inactive'}</td>
                <td class="actions">
                    <a href="/admin/memberships/delete/${membership.id}" class="delete"
                        onclick="return confirm('Are you sure you want to delete this membership?');">
                        Delete
                    </a>
                    <a href="/admin/memberships/edit/${membership.id}" class="update">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="/admin/dashboard" class="back-link">Go back to dashboard</a>
     <a href="/admin/memberships/showForm" class="back-link">Go back</a>

</body>
</html>
