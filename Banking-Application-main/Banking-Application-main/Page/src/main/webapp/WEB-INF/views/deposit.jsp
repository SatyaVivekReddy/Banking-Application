<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Deposit Money</title>
    <style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #1a1a1a;
        
        
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    h1 {
        color: #ff0000;
        text-align: center;
        padding-top: 150px;
    }

    form {
        background-color: #333;
        width: 300px;
        max-width: 400px;
        margin: auto;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px #b32f2f;
        border: 2px solid #ff0000;
        
    }

    label {
        display: block;
        margin-bottom: 8px;
        color: #ff0000;
    }

    input {
        width: 100%;
        padding: 8px;
        margin-bottom: 16px;
        box-sizing: border-box;
       
        border-radius: 4px;
    }

    input[type="submit"] {
        background-color: #ff0000;
        color: #1a1a1a;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #1a1a1a;
        color: #ff0000;
    }
</style>
</head>
<body>
    <h1>Deposit Money</h1>
    
    <form action="<%= request.getContextPath() %>/register/DS" method="post">
    <label for="amount">Enter Amount:</label>
    <input type="text" id="amount1" name="amount1" required>
    <br>
    <input type="submit" value="Deposit">
</form>
</body>
</html>
