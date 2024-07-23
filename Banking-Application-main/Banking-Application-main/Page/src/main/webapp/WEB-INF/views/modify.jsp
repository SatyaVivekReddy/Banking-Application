<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="register.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Customer Details</title>
<style>
        body {
            font-family: Arial, sans-serif;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-image: url('https://example.com/path-to-your-bank-image.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: #fff;
            background-color: #1a1a1a;
        }
    
        h2 {
            color:  #ff9900;
            text-align: center;
            margin-bottom: 25px;
            animation: flip 1s ease-in-out;
        }
    
        form {
            width: 80%;
            max-width: 700px;
            margin: auto;
            padding: 30px;
            background-color: #333;
            border: 2px solid #ff9900;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(255, 153, 0, 0.5);
            box-sizing: border-box;
        }
    
        label {
            display: block;
            margin-bottom: 8px;
            color:  #ff9900;
        }
    
        input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 2px solid #ff9900; /* Changed from 'brown' to 'black' */
            border-radius: 10px;
            background-color: #444;
            color: #fff;
        }
    
        input[type="submit"] {
            background-color: #ff9900;
            color: #333;
            border: none;
            padding: 12px 20px;
            cursor: pointer;
            border-radius: 10px;
        }
    
        input[type="submit"]:hover {
            background-color:  #1a1a1a;
            color: #ff9900;
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

    <h2>Modify Customer Details</h2>

    <form action="${pageContext.request.contextPath}/register/ModifyCustomerServlet" method="post">
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" value="${customer.accountNumber}"><br>

        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="${customer.fullName}" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${customer.address}" required><br>

        <label for="emailId">Email ID:</label>
        <input type="email" id="emailId" name="emailId" value="${customer.emailId}" required><br>

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="${customer.dob}" required><br>

        <input type="submit" value="Modify">
    </form>

</body>
</html>
