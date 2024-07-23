package register;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register/DeposittServlet")
public class DeposittServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeposittServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") != null
                && (boolean) request.getSession().getAttribute("loggedIn")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/deposit.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("LoginServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        String amountParam = request.getParameter("amount");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Amount Param: " + amountParam);

        if (amountParam != null) {
            try {
                BigDecimal amount = new BigDecimal(amountParam);
                updateBalance(accountNumber, amount);
                insertTransaction(accountNumber, amount, "Deposit");
                response.sendRedirect("TransactionSuccess.jsp"); // Redirect to a success page
            } catch (NumberFormatException e) {
                // Handle invalid input (non-numeric value)
                e.printStackTrace(); // Log the exception or handle it appropriately
                response.sendRedirect("DepositError.jsp"); // Redirect to an error page
            }
        } else {
            // Handle null amount parameter
            response.sendRedirect("DepositError.jsp"); // Redirect to an error page
        }
    }
    


    private void updateBalance(String accountNumber, BigDecimal amount) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String updateQuery = "UPDATE customer SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setBigDecimal(1, amount);
                preparedStatement.setString(2, accountNumber);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTransaction(String accountNumber, BigDecimal amount, String transactionType) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String insertQuery = "INSERT INTO transactions (account_number, amount, transaction_type, transaction_time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, accountNumber);
                preparedStatement.setBigDecimal(2, amount);
                preparedStatement.setString(3, transactionType);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
