<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1a1a1a;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        h2 {
            text-align: center;
            margin-top: 50px;
            color: #f90;
            animation: lightSpeedInRight 1s ease-in-out;
        }
       
        ul {
            list-style-type: none;
            padding: 7px;
            margin: 0;
            border-radius: 10px;
           
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            background-color: #333; 
            border: 2px solid #ff9900;
            box-shadow: 0 0 10px rgba(255,153,0,0.5);
        }
        li {
            margin-right: 1rem;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        button {
            background-color: #f90;
            color: #1a1a1a; /* Change button text color to black */
            padding: 10px 10px;
            
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, color 0.3s ease, font-size 0.3s ease, font-weight 0.3s ease;
        }
        button a {
            text-decoration: none; 
            color: black;/* Remove underline */
            /* Change hyperlink color to black */
        }
        button a:hover {
            color: #ff9900;
            
            /* Change hyperlink color to black */
        }
        button:hover {
            background-color: #1a1a1a;
            
            font-size: 18px;
        font-weight: bold;
        }
        p {
            text-align: center;
            color: white;
            animation: blink 0.75s infinite alternate; /* Add blinking animation */
        }
        @keyframes blink {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }
        @keyframes lightSpeedInRight {
        from {
            opacity: 0;
            transform: translateX(100%);
        }
        to {
            opacity: 1;
            transform: translateX(0);
        }
       
    </style>
</head>
<body>

    <h2>Welcome to the Admin Dashboard</h2>
    <p>This is a secure area for administrators.</p>

    <ul>
        <li><button><a href="<%= request.getContextPath() %>/RegisterServlet">Add Customer</a></button></li>
        <li><button><a href="<%= request.getContextPath() %>/ModifyCustomerServlet">Modify Customer</a></button></li>
        <li><button><a href="<%= request.getContextPath() %>/ViewCustomerServlet">View Customer Details</a></button></li>
        <li><button><a href="<%= request.getContextPath() %>/DeleteCustomerServlet">Delete Customer</a></button></li>
    </ul>

</body>
</html>
