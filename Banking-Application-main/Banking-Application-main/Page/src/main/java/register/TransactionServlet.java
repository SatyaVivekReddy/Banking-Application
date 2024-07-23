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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/register/TransactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public TransactionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") != null
                && (boolean) request.getSession().getAttribute("loggedIn")) {
            List<Transaction> transactions = getLast10Transactions(request.getSession().getAttribute("accountNumber").toString());
            System.out.println("Number of transactions: " + transactions.size());

            // Set transactions in request attributes
            request.setAttribute("transactions", transactions);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/transaction.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("LoginServlet");
        }
    }
    private List<Transaction> getLast10Transactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String selectQuery = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_time DESC LIMIT 10";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, accountNumber);
                System.out.println("SQL Query: " + preparedStatement.toString()); // Add this line

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Transaction transaction = new Transaction();
                        transaction.setAccountNumber(resultSet.getString("account_number"));
                        transaction.setAmount(resultSet.getBigDecimal("amount"));
                        transaction.setTransactionType(resultSet.getString("transaction_type"));
                        transaction.setTransactionTime(resultSet.getString("transaction_time"));
                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    // Add doPost method if needed

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
