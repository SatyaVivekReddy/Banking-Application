<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Customer</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #1a1a1a;
            color: #fff;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #ff9900;
            animation: flip 1s ease-in-out;
        }

        form {
            max-width: 400px;
            width: 80%;
            margin: auto;
            padding: 20px;
            background-color: #333;
            border: 2px solid #ff9900;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(255, 153, 0, 0.5);
            box-sizing: border-box;
            animation: shake 0.5s ease-in-out;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #ff9900;
        }

        input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 2px solid #ff9900;
            border-radius: 5px;
            background-color: #444;
            color: #fff;
        }

        input[type="submit"] {
            background-color: #ff9900;
            color: #333;
            border: none;
            padding: 12px 20px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease-in-out;
        }

        input[type="submit"]:hover {
            background-color: #1a1a1a;
            color: #ff9900;
        }
        @keyframes shake {
        0%, 100% {
            transform: translateX(0);
        }
        10%, 30%, 50%, 70%, 90% {
            transform: translateX(-10px);
        }
        20%, 40%, 60%, 80% {
            transform: translateX(10px);
        }
    }
    @keyframes flip {
        from {
            transform: perspective(400px) rotateY(0);
        }
        to {
            transform: perspective(400px) rotateY(360deg);
        }
    }
    </style>
</head>
<body>

    <h2>Delete Customer</h2>

    <form action="${pageContext.request.contextPath}/DeleteCustomerServlet" method="post">
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required><br>

        <input type="submit" value="Delete">
    </form>

</body>
</html>
