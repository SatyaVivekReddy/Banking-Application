<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="register.AccountDetails" %>

<html>
<head>
    <title>Account Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1a1a1a;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #333;
            color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px #b32f2f;
        border: 2px solid #ff0000;
        }
        h1 {
            color: #ff0000;
            text-align: center;
            animation: slide-in 2s ease;
        }
        .detail-box {
            margin-bottom: 10px;
            border-radius: 5px;
            overflow: hidden;
            clear: both;
            animation: fade-in 3s ease; 
            box-shadow: 0 0 10px #b32f2f;
            border: 2px solid #ff0000;

        }
        .detail-label,
        .detail-value {
            padding: 10px;
            float: left;
        }
        .detail-label {
            font-weight: bold;
            color: #ff0000;
            background-color: #333;
        }
        .detail-value {
            color: #white;
            background-color: #white;
            width: auto; /* Adjust as needed */
        }
        @keyframes fade-in {
        from {
            opacity: 0; /* Start with opacity 0 */
        }
        to {
            opacity: 1; /* End with opacity 1 */
        }
    }
    @keyframes slide-in {
        from {
            transform: translateX(100%); /* Start from outside the screen on the right */
        }
        to {
            transform: translateX(0); /* Move to the original position */
        }
    }
    </style>
</head>
<body>

<div class="container">
    <h1>Your Account Details</h1>

    <%
        AccountDetails accountDetails = (AccountDetails) request.getAttribute("accountDetails");
        if (accountDetails != null) {
    %>
        <div class="detail-box">
            <div class="detail-label">Account Number:</div>
            <div class="detail-value"><%= accountDetails.getAccountNumber() %></div>
        </div>
        <div class="detail-box">
            <div class="detail-label">Account Holder Name:</div>
            <div class="detail-value"><%= accountDetails.getFullName() %></div>
        </div>
        <div class="detail-box">
    <div class="detail-label">Account Type:</div>
    <div class="detail-value"><%= accountDetails.getAccount() %></div>
</div>
<div class="detail-box">
    <div class="detail-label">Account Holder Address:</div>
    <div class="detail-value"><%= accountDetails.getAddress() %></div>
</div>
<div class="detail-box">
    <div class="detail-label">Account Holder EmailId:</div>
    <div class="detail-value"><%= accountDetails.getEmailId() %></div>
</div>
<div class="detail-box">
    <div class="detail-label">Account Holder Balance:</div>
    <div class="detail-value"><%= accountDetails.getBalance() %></div>
</div>
<div class="detail-box">
    <div class="detail-label">Account Holder Dob:</div>
    <div class="detail-value"><%= accountDetails.getDob() %></div>
</div>
    <%
        } else {
    %>
        <p>Account details not available.</p>
    <%
        }
    %>
</div>

</body>
</html>