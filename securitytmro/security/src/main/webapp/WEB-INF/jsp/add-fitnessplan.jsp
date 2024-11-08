<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Fitness Plan</title>
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

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            font-size: 14px;
            color: #555;
            display: block;
            margin-bottom: 8px;
        }

        select, textarea, input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: #fafafa;
            box-sizing: border-box;
        }

        select:focus, textarea:focus, input:focus {
            border-color: #007bff;
            outline: none;
            background-color: #f4faff;
        }

        textarea {
            height: 100px;
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
        <h1>Add New Fitness Plan</h1>

        <form action="/admin/fitnessPlans/addPlan" method="post">
            <label for="planName">Plan Name:</label>
            <select id="planName" name="planName" required>
                <option value="">--Select Plan--</option>
                <option value="Beginner Fitness Plan">Beginner Fitness Plan</option>
                <option value="Weight Loss Plan">Weight Loss Plan</option>
                <option value="Muscle Gain Plan">Muscle Gain Plan</option>
                <option value="Cardio Boost Plan">Cardio Boost Plan</option>
                <option value="Strength Building Plan">Strength Building Plan</option>
                <option value="Flexibility & Mobility Plan">Flexibility & Mobility Plan</option>
                <option value="High-Intensity Plan">High-Intensity Plan</option>
                <option value="Balanced Fitness Plan">Balanced Fitness Plan</option>
            </select>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <button type="submit">Add Plan</button>
        </form>

        <a class="back-link" href="/admin/dashboard">Back to Dashboard</a>
    </div>
</body>
</html>
