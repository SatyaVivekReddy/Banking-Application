<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #1a1a1a;
        	background-size: cover;
        	background-position: center;
        }
        h2 {
        	animation: zoomInDown 0.5s ease-in-out;
            text-align: center;
            margin-top: 50px;
            color: #ff9900;
        }
        form {
            width: 300px;
            background-color: #333;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            border: 2px solid #ff9900;
            box-shadow: 0 0 10px rgba(255, 153, 0, 5);
            animation: zoomInDown 0.5s ease-in-out;
        }
        label {
            display: block;

            margin-bottom: 5px;
            color: #ff9900;
        }
        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            color:#ff9900;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #ff9900;
            color: #333;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button[type="submit"]:hover {
            background-color: #1a1a1a;
            color:#ff9900;
        }
        @keyframes zoomInDown {
            from {
                transform: scale(0) translateY(-100%);
                opacity: 0;
            }
            to {
                transform: scale(1) translateY(0);
                opacity: 1;
            }
            @keyframes zoomInDown {
            from {
                transform: scale(0) translateY(-100%);
                opacity: 0;
            }
            to {
                transform: scale(1) translateY(0);
                opacity: 1;
            }
    </style>
<body>

    <h2>Admin Login</h2>

    <%-- Display error message if login fails --%>

    <form action="<%= request.getContextPath() %>/AdminLoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <button type="submit">Login</button>
    </form>
</body>
</html>
