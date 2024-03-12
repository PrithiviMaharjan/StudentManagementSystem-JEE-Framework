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
import utils.StringUtils;

/**
 * Name: Prithivi Maharjan 
 * Email: prithivi.maharjan18@gmail.com
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
	
	public RegisterStudentServlet() {
		this.dbController = new DBController();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		LocalDate dob = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		String subject = request.getParameter(StringUtils.SUBJECT);
		String username = request.getParameter(StringUtils.USERNAME);
		String password = request.getParameter(StringUtils.PASSWORD);

		// Data Validation
		
		StudentModel student = new StudentModel(
				firstName, lastName, dob, gender, email, phoneNumber, 
				subject, username, password);
		
		int result = dbController.registerStudent(student);
		
		if (result == 1) {
			resp.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
		} else {
			// Code will be written in later weeks
		}
	}
}