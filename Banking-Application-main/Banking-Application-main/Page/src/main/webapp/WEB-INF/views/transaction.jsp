<%@ page import="java.util.List" %>
<%@ page import="register.Transaction" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transaction History</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #1a1a1a;
        margin: 0;
        padding: 0;
        text-align: center;
    }
    h2 {
        text-align: center;
        margin-top: 20px;
        color: #ff0000;
        animation: slide-in 2s ease;
    }
    table {
        width: 80%;
        border-collapse: collapse;
        
        border-radius: 10px;
        margin: auto;
        animation: slide-out 1s ease;
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
    th, td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
        transition: background-color 0.3s;
    }
    th {
        background-color: #ff0000;
        color: #fff;
    }
    tr{
        background-color: white;
        color: black;
    }
   
    tr:hover td {
        background-color: #333;
        color:white;
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
   
    @keyframes slide-out {
        from {
            transform: translateY(500%);
        }
        to {
            transform: translateY(0);
        }
    }
    p{
        color: white
    }
    .no-transactions {
        text-align: center;
        padding: 20px;
        font-size: 18px;
        color: #777;
    }
    input[type="submit"] {
        background-color:#ff0000;
        color: white;
        border-radius: 5px;
        padding: 10px;
        
    }
    input[type="submit"]:hover {
        background-color: white;
        color: black;
        font-size: 14px;
        font-weight: bold;
    }
</style>
</head>

<body>

<h2>Transaction History</h2>

<% 
List<Transaction> transactionList = (List<Transaction>) request.getAttribute("transactions");
System.out.println("Transaction list size in JSP: " + (transactionList != null ? transactionList.size() : "null"));
if (transactionList != null && !transactionList.isEmpty()) {
%>
    <table border="1">
        <thead>
            <tr>
                <th>Account Number</th>
                <th>Amount</th>
                <th>Transaction Type</th>
                <th>Transaction Time</th>
            </tr>
        </thead>
        <tbody>
            <% for (Transaction transaction : transactionList) { %>
                <tr>
                    <td><%= transaction.getAccountNumber() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getTransactionTime() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } else { %>
    <p>No transactions found.</p>
<% } %>

<br>
<form action="<%= request.getContextPath() %>/DownloadPdfServlet" method="get">
    <input type="submit" value="Print in PDF">
</form>

</body>
</html>
