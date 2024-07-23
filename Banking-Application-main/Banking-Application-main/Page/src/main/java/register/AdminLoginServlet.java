package register;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isValidLogin(username, password)) {
            // Admin credentials are valid, redirect to admin.jsp
        	response.sendRedirect(request.getContextPath() + "/AdminDashboardServlet");
        } else {
            // Invalid credentials, redirect to login page with an error message
            response.sendRedirect(request.getContextPath() + "/admin-login.jsp?error=true");
        }
    }

    private boolean isValidLogin(String accountNumber, String password) {
        // Implement your logic to check the login credentials in the database
    	String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    	String dbUser = "root";
    	String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String checkQuery = "SELECT COUNT(*) FROM admin WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
                preparedStatement.setString(1, accountNumber);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin.jsp");
            dispatcher.forward(request, response);
        
    }
}

