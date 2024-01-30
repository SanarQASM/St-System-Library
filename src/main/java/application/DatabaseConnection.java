package application;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	private boolean haveQuestion;
	private boolean haveAnswer;

	public boolean getHaveQuestion() {
		return haveQuestion;
	}

	public boolean getHaveAnswer() {
		return haveAnswer;
	}

	private Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/booksystem", "root", "Tanakam18&");
	}

	private void closeConnection() throws ClassNotFoundException, SQLException {
		createConnection().close();
	}

	public boolean checkUsername(String username) {
		boolean result = false;
		try {
			String query = "SELECT username FROM register WHERE username=?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, username);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String usernameIndatabase = rs.getString("username");
				if (usernameIndatabase.equals(username)) {
					result = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check username 1223344");
		}
		return result;
	}

	public boolean checkPasswordThrougUsername(String username,String password) {
		boolean result = false;
		try {
			String query = "SELECT password FROM register WHERE username=?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, username);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String passwordIndatabase = rs.getString("password");
				if (passwordIndatabase.equals(password)) {
					result = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check password");
		}
		return result;
	}
	public boolean checkPasswordThrougEmail(String email,String password) {
		boolean result = false;
		try {
			String query = "SELECT password FROM register WHERE email=?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, email);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String passwordIndatabase = rs.getString("password");
				if (passwordIndatabase.equals(password)) {
					result = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check password");
		}
		return result;
	}
	public void setAllInformationIndatabase(String username, String password, String email, int question_index,String answer) {
		try {
			String query = "INSERT INTO register(username,password,email,question_index,answer) VALUES (?,?,?,?,?);";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, username);
			prmt.setString(2, password);
			prmt.setString(3, email);
			prmt.setInt(4, question_index);
			prmt.setString(5,answer);
			prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya set all information");
		}
	}

	public void checkAnswerAndQuestion(String username, int indexQuestion, String forgetAnswer) {
		try {
			String query = "SELECT question_index, answer FROM register WHERE username=?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, username);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				int question_index = rs.getInt("question_index");
				if (question_index == indexQuestion) {
					haveQuestion = true;
				}
				String answer = rs.getString("answer");
				if (answer.equals(forgetAnswer)) {
					haveAnswer = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya check question");
		}
	}

	public boolean checkToSameOldPass(String username, String newPassword) {
		boolean samePassword = false;
		try {
			String query = "SELECT password FROM register WHERE username = ?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, username);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String oldPassword = rs.getString("password");
				if (oldPassword.equals(newPassword)) {
					samePassword = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check same password");
		}
		return samePassword;
	}

	public void setNewToOldPassword(String username, String newPassword) {
		try {
			String query = "UPDATE register SET password = ? WHERE username = ?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, newPassword);
			prmt.setString(2, username);
			prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la set New to Old password");
		}
	}

	public boolean checkToSameUsername(String newUsername) {
		boolean result = false;
		try {
			String query = "SELECT username FROM register WHERE username =?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, newUsername);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String oldUsername = rs.getString("username");
				if (newUsername.equals(oldUsername)) {
					result = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check new username");
		}
		return result;
	}

	public void setOldWithNewUsername(String oldUsername, String newUsername) {
		try {
			String query = "UPDATE register SET username = ? WHERE username = ?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, newUsername);
			prmt.setString(2, oldUsername);
			prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
			System.out.println("errort haya la set new username");
		}
	}

	public boolean checkEmailInDatabase(String emailText) {
		boolean result = false;
		try {
			String query = "SELECT email FROM register WHERE email=?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, emailText);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String emailIndatabase = rs.getString("email");
				if (emailText.equals(emailIndatabase)) {
					result = true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check email");
		}
		return result;
	}

	public String getUsernameByEmail(String email) {
		String result="";
		try {
			String query = "SELECT username FROM register WHERE email=?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, email);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("username");
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check email");
			result ="";
		}
		return result;
	}

	public boolean checkToSameOldPassWithEmail(String email, String password) {
		boolean result =false;
		try {
			String query = "SELECT password FROM register WHERE email = ?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, email);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				String passwordIndatabase = rs.getString("password");
				if (passwordIndatabase.equals(password)) {
					result=true;
				}
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la check same old password with email");
			result =false;
		}
		return result;
	}

	public void setNewToOldPasswordWithEmail(String email, String password) {
		try {
			String query = "UPDATE register SET password = ? WHERE email = ?;";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, password);
			prmt.setString(2, email);
			prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("errort haya la set same old password with email");
		}
	}

	public void setNewEmailToOldThroughUsername(String username, String oldEmail) {
		try {
			String query = "UPDATE register SET email = ? WHERE username = ?";

            PreparedStatement prmt = createConnection().prepareStatement(query);
                prmt.setString(1, oldEmail);
                prmt.setString(2, username);
            prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
			System.out.println("errort haya la set same new email with username");
		}
		
	}

	public void addBookInformationByUsername(String username, String bookName, String tempUrl, String description,
			String language, String numberOfPage, String publisher, String reward, String year, File file) {
		
	}

}
