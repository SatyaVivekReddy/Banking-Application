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

@WebServlet("/register/AccountServlet")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") != null && (boolean) request.getSession().getAttribute("loggedIn")) {
            // Retrieve account details from the database using the account number
            String accountNumber = (String) request.getSession().getAttribute("accountNumber");

            // Implement logic to fetch account details from the database based on the accountNumber
            AccountDetails accountDetails = getAccountDetailsFromDatabase(accountNumber);

            // Set the accountDetails as an attribute to be accessed in the JSP
            request.setAttribute("accountDetails", accountDetails);

            // Forward to the account.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/account.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to the login page if not logged in
            response.sendRedirect("LoginServlet");
        }
    }

    private AccountDetails getAccountDetailsFromDatabase(String accountNumber) {
        // Implement logic to fetch account details from the database
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM customer WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, accountNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String fullName = resultSet.getString("full_name");
                        String address = resultSet.getString("address");
                        String emailId = resultSet.getString("email_id");
                        String account = resultSet.getString("account");
                        String balance = resultSet.getString("balance");
                        String dob = resultSet.getString("dob");

                        return new AccountDetails(accountNumber, fullName, address, emailId, account, balance, dob);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle post requests if needed
        doGet(request, response);
    }
}
