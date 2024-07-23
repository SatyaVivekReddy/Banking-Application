<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
    h1{
        text-align: center;
        color:#ff0000;
        animation: slide-in 2s ease; /* Apply the slide-in animation */
    }
    body{
        background-color:  #1a1a1a;
        background-size: cover;
        background-position: center;
      
    }
    @keyframes fade-in {
        from {
            opacity: 0; /* Start with opacity 0 */
        }
        to {
            opacity: 1; /* End with opacity 1 */
        }
    }
    form {
        text-emphasis-color: #ff0000;
        text-align: center;
        background-color: #333; 
        padding: 7px;
        border-radius: 10px;
        animation: fade-in 3s ease; 
        box-shadow: 0 0 10px #b32f2f;
        border: 2px solid #ff0000;
    }
    @keyframes slide-in {
        from {
            transform: translateX(100%); /* Start from outside the screen on the right */
        }
        to {
            transform: translateX(0); /* Move to the original position */
        }
    }
   
    input[type="submit"] {
        background-color:#ff0000;
        color: #lalala;
        padding: 15px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease, font-size 0.3s ease, font-weight 0.3s ease;
        margin-right: 50px;
        margin-left: 50px;
        margin-top: 10px;
        margin-bottom: 10px;
    }
    input[type="submit"]:hover {
        background-color: #1a1a1a;
        font-size: 18px;
        font-weight: bold;
        color: #ff0000;
    }
    h3{
        text-align: center;
        color: white;
        
    }
    @keyframes blink {
        0% {
            opacity: 0;
        }
        50% {
            opacity: 1;
        }
        100% {
            opacity: 0;
        }
    }

    
    .blinking {
        animation: blink 1s infinite; 
    }

 

    
</style>
</head>
<body style="background-image: url('/onlinebank.jpg'); background-size: cover; background-position: center;">


<%
    if (request.getSession().getAttribute("loggedIn") != null && (boolean) request.getSession().getAttribute("loggedIn")) {
%>
    <h1>Welcome to your Dashboard, <%= request.getSession().getAttribute("accountNumber") %>!</h1>

    <form method="post" action="<%= request.getContextPath() %>/CustomerServlet">
        <input type="submit" name="account" value="Account">
        <input type="submit" name="transactions" value="Transactions">
        <input type="submit" name="deposit" value="Deposit">
        <input type="submit" name="withdraw" value="Withdraw">
        <input type="submit" name="delete" value="Delete">
        <input type="submit" name="logout" value="Logout">
    </form>
    <!-- Add dashboard content here -->
<%
    } else {
%>
    <p>You are not logged in. Please log in to access the dashboard.</p>
<%
    }
%>
</body>
</html>
