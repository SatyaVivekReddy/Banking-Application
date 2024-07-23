<!DOCTYPE html>
<html>
<head>
    <title>Role Selection</title>
</head>
<style>
input{
width:500px;
}
</style>
<body>

    <h2>Welcome to the Role Selection Page</h2>

    <form method="post" action="<%= request.getContextPath() %>/RoleSelectionServlet">
    <input type="submit" name="adminButton" value="Admin">
    <input type="submit" name="customerButton" value="Customer">
</form>


</body>
</html>
