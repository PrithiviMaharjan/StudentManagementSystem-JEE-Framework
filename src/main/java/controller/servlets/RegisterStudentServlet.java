package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.StudentModel;

/**
 * Name: Prithivi Maharjan Email: prithivi.maharjan18@gmail.com
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/registerstudent" })
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
	
	public RegisterStudentServlet() {
		this.dbController = new DBController();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		LocalDate dob = LocalDate.parse(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String subject = request.getParameter("subject");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		StudentModel student = new StudentModel(
				firstName, lastName, dob, gender, email, phoneNumber, 
				subject, username, password);
		
		int result = dbController.registerStudent(student);
		
		if (result > 0) {
			resp.sendRedirect(request.getContextPath() + "/pages/login.html");
		} else {
			// Code will be written in later weeks
		}
	}
}