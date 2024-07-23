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
import java.sql.SQLException;

@WebServlet("/register/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        // Delete the customer by account number
        if (deleteCustomer(accountNumber)) {
            // Successful deletion, redirect to some success page or back to the admin dashboard
            response.sendRedirect(request.getContextPath() + "/AdminDashboardServlet");
        } else {
            // If deletion fails, redirect to an error page
            response.sendRedirect(request.getContextPath() + "/DeleteError.jsp");
        }
    }

    private boolean deleteCustomer(String accountNumber) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String deleteQuery = "DELETE FROM customer WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, accountNumber);
                int rowsAffected = preparedStatement.executeUpdate();
                // If any row is affected, deletion is successful
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurs
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/deletecustomer.jsp");
            dispatcher.forward(request, response);
 
    }

}
