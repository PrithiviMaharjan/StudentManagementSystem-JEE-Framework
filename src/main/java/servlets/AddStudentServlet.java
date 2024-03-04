package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writer = resp.getWriter();
		// Data Retrieve
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		
		// Database Connection & Query Execute
		try {
			// Driver Name
			Class.forName("com.mysql.jdbc.Driver");
			
			// Credentials
			String localhostUrl = "jdbc:mysql://localhost:3306/student_management";
			String localhostUsername = "root";
			String localhostPassword = "";
			
			// DB connection
			Connection connection = DriverManager.getConnection(localhostUrl, localhostUsername, localhostPassword);
			
			// Query -> Connection
			String registerStudentQuery = "INSERT INTO student_info (usrname, email) VALUES(?, ?)";
			PreparedStatement stmt = connection.prepareStatement(registerStudentQuery);
			stmt.setString(1, username);
			stmt.setString(2, email);
			
			// Run Query
			int result = stmt.executeUpdate();
			
			if(result>0) {
				// Success
				writer.append("Successful!");
			}else {
				// Failed
				writer.append("Unsuccessful!");
			}
			
		}catch(ClassNotFoundException | SQLException ex) {
			writer.append(ex.getMessage());
		}
		
	}

}