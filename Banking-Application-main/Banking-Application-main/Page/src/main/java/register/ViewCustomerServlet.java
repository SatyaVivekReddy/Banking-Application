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

@WebServlet("/register/ViewCustomerDetailsServlet")
public class ViewCustomerServlet extends HttpServlet {
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
        List<Customer> customers = getCustomerDetails();

        // Set the customer details in the request attribute
        request.setAttribute("customers", customers);

        // Forward the request to the JSP page to display customer details
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/viewcustomer.jsp");
        dispatcher.forward(request, response);
    }

    private List<Customer> getCustomerDetails() {
        List<Customer> customers = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost:3306/customer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPassword = "Chaithu@9515";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
        	String query = "SELECT account_number, full_name, address, email_id, dob FROM customer";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setAccountNumber(resultSet.getString("account_number"));
                    customer.setFullName(resultSet.getString("full_name"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setEmailId(resultSet.getString("email_id"));
                    customer.setDob(resultSet.getString("dob"));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	       

	}

}
