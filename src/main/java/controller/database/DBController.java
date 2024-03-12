package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.StudentModel;
import utils.StringUtils;

public class DBController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(StringUtils.DRIVER_NAME);
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	public int registerStudent(StudentModel student) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_STUDENT);

			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setDate(3, Date.valueOf(student.getDob()));
			stmt.setString(4, student.getGender());
			stmt.setString(5, student.getEmail());
			stmt.setString(6, student.getPhoneNumber());
			stmt.setString(7, student.getSubject());
			stmt.setString(8, student.getUsername());
			stmt.setString(9, student.getPassword());

			// Statement Run
			int result = stmt.executeUpdate();

			if (result > 0) {
				return 1;
			} else {
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public int getStudentLoginInfo(String username, String password) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
			st.setString(1, username);
			ResultSet result = st.executeQuery();

			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString(StringUtils.USER_NAME);
				String passwordDb = result.getString(StringUtils.PASSWORD);
				
				if (userDb.equals(username) && passwordDb.equals(password))
					return 1;
				else
					return 0;
			}else {
				return -1;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -2;
		}
	}

}
