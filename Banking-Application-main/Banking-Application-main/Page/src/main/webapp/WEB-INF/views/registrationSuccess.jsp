<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Success</title>
    <style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    
        align-items: center;
        justify-content: center;
        height: 100vh;
        background-color: #ecf0f1;
        color: #333;
    }

    h1 {
        text-align: center;
        color: #3498db;
        animation: blink 1s infinite alternate; 
        padding-top: 200px;
    }

    p {
        text-align: center;
        margin-top: 15px;
        padding: auto;
    }

    p:first-child {
        margin-top: 30px;
    }

    p span {
        font-weight: bold;
        color: #3498db;
    }

    @keyframes blink {
        from {
            opacity: 1;
        }
        to {
            opacity: 0.5;
        }
    }
</style>
</head>
<body>
    <h1>Registration Successful!</h1>
    <p>Your account number: <b>${accountNumber}</b></p>
    <p>Your password: <b>${password}</b></p>
</body>
</html>
