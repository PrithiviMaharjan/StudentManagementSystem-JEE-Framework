package utils;

public class StringUtils {
	
	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/student_management";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	// End: DB Connection
	
	// Start: Queries
	public static final String REGISTER_STUDENT_QUERY = "INSERT INTO student_info ("
			+ "first_name, last_name, birthday, gender, email, number, subject, user_name, password) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String GET_LOGIN_STUDENT_INFO = "SELECT * FROM student_info WHERE user_name = ?";
	// End: Queries
	
	// Start: Validation Messages
	
	// End: Validation Messages
	
}
