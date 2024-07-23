<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Customer</title>
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
            animation: zoomInRight 1s ease-in-out;
        }

    form {
        width: 80%;
        max-width: 400px;
        margin: auto;
        padding: 20px;
        background-color: #333;
        border: 2px solid #ff9900;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(255, 153, 0, 0.5);
        box-sizing: border-box;
        animation: fadeIn 1s ease-in-out;
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
        border: 1px solid #ddd;
        border-radius: 5px;
        transition: background-color 0.3s ease-in-out;
    }

    input[type="file"] {
        width: 100%;
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
    @keyframes zoomInRight {
            from {
                opacity: 0;
                transform: translateX(100%);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
</style>
</head>
<body>
<h2>Add Customer</h2>
<form method="post" action="<%= request.getContextPath() %>/RegisterServlet">
        <label for="full_name">Full Name:</label>
        <input type="text" id="full_name" name="full_name" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>

        <label for="email_id">Email ID:</label>
        <input type="email" id="email_id" name="email_id" required><br>
        
        <label for="account">Account Type:</label>
        <input type="text" id="account" name="account" required><br>

        <label for="balance">Balance:</label>
        <input type="text" id="balance" name="balance" required><br>

        <label for="dob">Date of Birth:</label>
        <input type="text" id="dob" name="dob" placeholder="YYYY-MM-DD" required><br>

        <label for="id_proof">ID Proof:</label>
        <input type="file" id="id_proof" name="id_proof" accept="image/*"><br>

        <input type="submit" value="Register">
    </form>


</body>
</html>