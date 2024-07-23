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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signup.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        String address = request.getParameter("address");
        String emailId = request.getParameter("email_id");
        String account = request.getParameter("account");
        String balance = request.getParameter("balance");
        String dob = request.getParameter("dob");

        int accountNumber = generateUniqueAccountNumber();
        String password = generateRandomPassword();

        boolean registrationSuccess = registerUser(accountNumber, fullName, address, emailId, password, account, balance,dob);

        if (registrationSuccess) {
            // Set the account number and password as request attributes
            request.setAttribute("accountNumber", accountNumber);
            request.setAttribute("password", password);

            // Forward the request to another JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registrationSuccess.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.print("NOo");
        }
    }

    private int generateUniqueAccountNumber() {
        int newAccountNumber;
        do {
            newAccountNumber = 100000000 + new SecureRandom().nextInt(900000000);
        } while (isAccountNumberExists(newAccountNumber));

        return newAccountNumber;
    }

    private boolean isAccountNumberExists(int accountNumber) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String checkQuery = "SELECT COUNT(*) FROM customer  WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
                preparedStatement.setInt(1, accountNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);

        return Base64.getEncoder().encodeToString(randomBytes);
    }

    private boolean registerUser(int accountNumber, String fullName, String address, String emailId, String password,
            String account, String balance, String dob) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String insertQuery = "INSERT INTO customer (account_number, full_name, address, email_id, password, balance, account, dob) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setString(2, fullName);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, emailId);
                preparedStatement.setString(5, password);
                preparedStatement.setString(6, balance);
                preparedStatement.setString(7, account);
                preparedStatement.setString(8, dob);
                

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}