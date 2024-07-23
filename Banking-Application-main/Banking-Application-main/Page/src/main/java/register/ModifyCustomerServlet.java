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

@WebServlet("/register/ModifyCustomerServlet")
public class ModifyCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the customer details based on the account number
        String accountNumber = request.getParameter("accountNumber");
        Customer customer = getCustomerDetails(accountNumber);

        // Set the customer details in the request attribute
        request.setAttribute("customer", customer);

        // Forward the request to the JSP page for modification
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/modify.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get modified customer details from the form
        String accountNumber = request.getParameter("accountNumber");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String emailId = request.getParameter("emailId");
        String dob = request.getParameter("dob");

        // Update customer details in the database
        updateCustomerDetails(accountNumber, fullName, address, emailId, dob);

        // Redirect to the view customer details page after modification
        response.sendRedirect(request.getContextPath() + "/register/ViewCustomerDetailsServlet");
    }

    private Customer getCustomerDetails(String accountNumber) {
        Customer customer = null;

        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT account_number, full_name, address, email_id, dob FROM customer WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, accountNumber);
                var resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setAccountNumber(resultSet.getString("account_number"));
                    customer.setFullName(resultSet.getString("full_name"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setEmailId(resultSet.getString("email_id"));
                    customer.setDob(resultSet.getString("dob"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    private void updateCustomerDetails(String accountNumber, String fullName, String address, String emailId, String dob) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String updateQuery = "UPDATE customer SET full_name = ?, address = ?, email_id = ?, dob = ? WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, emailId);
                preparedStatement.setString(4, dob);
                preparedStatement.setString(5, accountNumber);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
