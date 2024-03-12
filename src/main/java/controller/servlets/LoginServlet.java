package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.LoginModel;

/**
 * Name: Prithivi Maharjan Email: prithivi.maharjan18@gmail.com
 */
@WebServlet(urlPatterns = "/login", asyncSupported = true)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	public LoginServlet() {
		this.dbController = new DBController();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		LoginModel loginModel = new LoginModel(userName, password);
		int loginResult = dbController.getStudentLoginInfo(loginModel);

		if (loginResult == 1) {
		    response.sendRedirect(request.getContextPath() + "/pages/welcome.html");
		} else {
			// Code will be written in later weeks
		}
	}
}
