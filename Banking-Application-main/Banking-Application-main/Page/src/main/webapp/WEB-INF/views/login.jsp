<!-- login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style>
      body {
       
        font-family: Arial, sans-serif;
            background-color: #1a1a1a;
            background-size: cover;
            background-position: center;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        input[type="submit"] {
            background-color: #ff0000;
            color: #1a1a1a;
            padding: 10px 15px;
            align-items: center;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            
            transition: background-color 0.3s ease;
        }
        form {
            background-color:#333;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: auto;
            color:#ff0000;
            box-shadow: 0 0 10px #b32f2f;
            border: 2px solid #ff0000;
        }
        h2{
            padding-top: 100px;
            text-align: center;
            margin-top: 25px;
            color: #ff0000;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            background-color: white;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; 
            color: #ff0000;/* Ensure padding and border are included in width */
        }
        .forgot-password,
        .change-password {
            justify-content: center;
            display: inline-block;
            text-align: center;
            margin-top: 5px;
            text-decoration: none;
            color: #ff0000;
        }

        .forgot-password:hover,
        .change-password:hover {
            text-decoration: underline;
            color: #1a1a1a;
        }
        .password-links {
            text-align: center;
        }
        input[type="submit"]:hover {
            background-color: #1a1a1a;
            color: #ff0000;
        }
        
</style>
</head>
<body>
<h2>Customer Login</h2>
    <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Login"><br>
        <div class="password-links">
        <a class="forgot-password" href="ForgotPassword.jsp">Forgot Password?</a><br>
        <a class="change-password" href="ChangePasswordServlet">Change Password</a><br>
        </div>
    </form>
</body>
</html>`