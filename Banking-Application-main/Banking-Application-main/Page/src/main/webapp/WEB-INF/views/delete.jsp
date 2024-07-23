<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Account</title>
</head>
<body>

    <h2>Delete Account</h2>

    <form action="<%= request.getContextPath() %>/register/DeleteServlet" method="post">
        <p>Are you sure you want to close your account?</p>
        <button type="submit">Yes, Close Account</button>
    </form>

</body>
</html>
