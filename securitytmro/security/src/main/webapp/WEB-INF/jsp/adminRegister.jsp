<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

h2 {
	text-align: center;
	color: #4CAF50;
}

form {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-top: 10px;
}

input[type="text"], input[type="password"], input[type="number"], select
	{
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

input[type="submit"] {
	margin-top: 20px;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: 100%;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

.error {
	color: red;
	text-align: center;
}
</style>
</head>
<body>

	<h2>User Registration</h2>

	<!-- Display error message if any -->
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>

	<form action="/admin/register/save" method="post">

		<label for="username">Username:</label> <input type="text"
			id="username" name="username" required><br> <label
			for="password">Password:</label> <input type="password" id="password"
			name="password" required><br> <label for="mobile">Mobile:</label>
		<input type="text" id="mobile" name="mobile" required pattern="\d{10}"
			title="Enter a valid 10-digit mobile number"><br> <label
			for="age">Age:</label> <input type="number" id="age" name="age"
			min="1" required><br> <label for="weight">Weight:</label>
		<input type="number" id="weight" name="weight" required><br>

		<label for="height">Height:</label> <input type="number" id="height"
			name="height" required><br>
			
			
    <label for="membershipType">Membership:</label>
    <select id="membershipType" name="membershipType" required>
        <!-- Default prompt option that cannot be selected -->
        <option value="" disabled selected>Select a membership</option>

        <c:choose>
            <c:when test="${not empty memberships}">
                <c:forEach var="membership" items="${memberships}">
                    <option value="${membership.membershipType}">${membership.membershipType}</option>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <option value="">No membership types available</option>
            </c:otherwise>
        </c:choose>
    </select><br>
		
		 <input type="submit" value="Register">
	</form>

</body>
</html>
