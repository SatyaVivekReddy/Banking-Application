<%@ page import="java.util.List" %>
<%@ page import="register.Customer" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Customer Details</title>
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
        animation: fadeInUp 1s ease-in-out;
    }

    table {
        width: 80%;
        margin: auto;
        border-collapse: collapse;
        box-shadow: 0 0 10px rgba(255, 153, 0, 0.5);
        animation: fadeIn 1s ease-in-out;
    }

    th, td {
        border: 1px solid #ff9900;
        padding: 12px;
        text-align: left;
        transition: background-color 0.3s ease-in-out;
    }

    th {
        background-color: #333;
        color: #ff9900;
        animation: fadeInLeft 1s ease-in-out;
    }
    tr{
    transition: background-color 0.3s ease-in-out;
    }
    

    tr:hover {
        background-color: #333;
        animation: fadeIn 0.5s ease-in-out;
    }
     @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeInLeft {
            from {
                opacity: 0;
                transform: translateX(-50px);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }
</style>

</head>
<body>

<h2>Customer Details</h2>

<table border="1">
    <thead>
        <tr>
            <th>Account Number</th>
            <th>Full Name</th>
            <th>Address</th>
            <th>Email ID</th>
            <th>Date of Birth</th>
        </tr>
    </thead>
    <tbody>
        <% List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
           for (Customer customer : customerList) { %>
            <tr>
                <td><%= customer.getAccountNumber() %></td>
                <td><%= customer.getFullName() %></td>
                <td><%= customer.getAddress() %></td>
                <td><%= customer.getEmailId() %></td>
                <td><%= customer.getDob() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
