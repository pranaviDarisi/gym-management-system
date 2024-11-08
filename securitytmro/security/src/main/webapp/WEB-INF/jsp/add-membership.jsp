<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Membership</title>
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

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: #fafafa;
            box-sizing: border-box;
        }

        input:focus, select:focus {
            border-color: #007bff;
            outline: none;
            background-color: #f4faff;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }

        button:hover {
            background-color: #0056b3;
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
        <h2>Add New Membership</h2>

        <form action="/admin/memberships/addmembership" method="post">
            <label for="membershipType">Membership Type:</label>
            <input type="text" name="membershipType" id="membershipType" required>

            <label for="fee">Fee:</label>
            <input type="number" name="fee" id="fee" required>

            <label for="status">Status:</label>
            <select name="status" id="status">
                <option value="true">Active</option>
                <option value="false">Inactive</option>
            </select>

            <button type="submit">Add Membership</button>
            <a href="/admin/memberships/getAllMems">show memberships</a>
        </form>

        <a class="back-link" href="/admin/dashboard">Go back to dashboard</a>
    </div>

</body>
</html>
