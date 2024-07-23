package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ChangePassword.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve account number, old password, and new password from the form
        String accountNumber = request.getParameter("accountNumber");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        // Implement logic to validate old password and change the password
        boolean passwordChanged = validateAndChangePassword(accountNumber, oldPassword, newPassword);

        // Redirect back to the login page after changing the password
        if (passwordChanged) {
            request.setAttribute("successMessage", "Password changed successfully.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to change password. Please check your credentials.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }

    // Helper method to validate old password and change the password in the database
    private boolean validateAndChangePassword(String accountNumber, String oldPassword, String newPassword) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Check if the provided old password is correct
            if (isOldPasswordCorrect(accountNumber, oldPassword, connection)) {
                // Update the password in the database
                return updatePassword(accountNumber, newPassword, connection);
            } else {
                return false; // Old password validation failed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to check if the provided old password is correct
    private boolean isOldPasswordCorrect(String accountNumber, String oldPassword, Connection connection) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM customer WHERE account_number = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, oldPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    // Helper method to update the password in the database
    private boolean updatePassword(String accountNumber, String newPassword, Connection connection) throws SQLException {
        String updateQuery = "UPDATE customer SET password = ? WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, accountNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}