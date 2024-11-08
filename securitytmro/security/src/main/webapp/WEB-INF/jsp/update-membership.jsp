<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Membership</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #4CAF50;
        }
        .form-container {
            max-width: 400px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
            color: #333;
        }
        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #4CAF50;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h2>Edit Membership</h2>

<div class="form-container">
    <form action="/admin/memberships/update/${membership.id}" method="post">
        <input type="hidden" name="id" value="${membership.id}">

        <label for="membershipType">Membership Type:</label>
        <input type="text" name="membershipType" value="${membership.membershipType}" required>

        <label for="fee">Fee:</label>
        <input type="number" name="fee" value="${membership.fee}" required>

        <label for="status">Status:</label>
        <select name="status">
            <option value="true" ${membership.status ? 'selected' : ''}>Active</option>
            <option value="false" ${!membership.status ? 'selected' : ''}>Inactive</option>
        </select>

        <button type="submit">Update Membership</button>
    </form>

    <a href="/admin/memberships/getAllMems" class="back-link">Back to Memberships</a>
</div>

</body>
</html>
