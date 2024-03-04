package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Name: Prithivi Maharjan Email: prithivi.maharjan18@gmail.com
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/registerstudent" })
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter writer;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("<h1>Served at:</h1> ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		writer = resp.getWriter();
		// Data Retrieve
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		LocalDate dob = LocalDate.parse(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String subject = request.getParameter("subject");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			// Driver Setup for DB
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Configuration
			String localhostUrl = "jdbc:mysql://localhost:3306/student_management";
			String localhostUsername = "root";
			String localhostPassword = "";

			// Database Connect
			Connection connection = DriverManager.getConnection(localhostUrl, localhostUsername, localhostPassword);

			// Statement(Query) Prepare
			String query = "INSERT INTO student_info ("
					+ "first_name, last_name, birthday, gender, email, number, subject, user_name, password) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);

			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setDate(3, Date.valueOf(dob));
			stmt.setString(4, gender);
			stmt.setString(5, email);
			stmt.setString(6, phoneNumber);
			stmt.setString(7, subject);
			stmt.setString(8, username);
			stmt.setString(9, password);

			// Statement Run
			int result = stmt.executeUpdate();

			if (result > 0) {
				// Success
				writer.println("<h1>Your account is registered as</h1>");
				writer.println("<h3>Name: " + firstName + "</h3>");
				writer.println("<h3>Gender: " + gender + "</h3>");
			} else {
				// Failed
				writer.println("<h1>Sorry! Your data is not registered.</h1>");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			writer.println("Server Error! Please try again after 5 minutes!");
		}
	}

}