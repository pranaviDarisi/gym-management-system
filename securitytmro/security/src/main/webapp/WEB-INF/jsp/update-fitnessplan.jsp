<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Fitness Plan</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            color: #333;
            font-size: 2.2em;
            margin-bottom: 20px;
        }

        form {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        button {
            background-color: #28a745;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #218838;
        }

        a {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #0056b3;
        }

        .container {
            text-align: center;
        }
    </style>
</head>
<body>

    <h1>Update Fitness Plan</h1>

    <form action="/admin/fitnessPlans/update/${fitnessPlan.id}" method="post">
        <input type="hidden" name="id" value="${fitnessPlan.id}">

        <label for="planName">Plan Name:</label>
        <input type="text" id="planName" name="planName" value="${fitnessPlan.planName}" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${fitnessPlan.description}</textarea>

        <button type="submit">Update Plan</button>
    </form>

    <div class="container">
        <a href="/admin/fitnessPlans">Back to Fitness Plans</a>
    </div>

</body>
</html>
