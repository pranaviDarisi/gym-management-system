<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .button-container {
            text-align: center;
        }
        .button {
            padding: 15px 25px;
            margin: 10px;
            font-size: 18px;
            color: white;
            background-color: #007bff;
            text-decoration: none; /* Remove underline from anchor */
            border-radius: 5px;
            display: inline-block; /* Allow padding and margin to apply */
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="button-container">
        <h1>Welcome!</h1>
         <a class="button" href="/admin/register">add Admin</a>
        <a class="button" href="/admin/dashboard">Admin</a>
        <a class="button" href="/user/dashboard">user</a>
    </div>
</body>
</html>
