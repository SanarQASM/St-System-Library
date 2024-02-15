package application;

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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
		}
		return result;
	}

	public boolean checkPasswordThrougUsername(String username, String password) {
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
		}
		return result;
	}

	public boolean checkPasswordThrougEmail(String email, String password) {
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
		}
		return result;
	}

	public void setAllInformationIndatabase(String username, String password, String email, int question_index, String answer) {
		try {
			String query = "INSERT INTO register(username,password,email,question_index,answer) VALUES (?,?,?,?,?);";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, username);
			prmt.setString(2, password);
			prmt.setString(3, email);
			prmt.setInt(4, question_index);
			prmt.setString(5, answer);
			prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
		}
		return result;
	}

	public String getUsernameByEmail(String email) {
		String result = "";
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
			result = "";
		}
		return result;
	}

	public boolean checkToSameOldPassWithEmail(String email, String password) {
		boolean result = false;
		try {
			String query = "SELECT password FROM register WHERE email = ?;";
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

			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
			result = false;
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
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
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
		}

	}

	public void addBookInformationByUsername(String tempUsername, String bookTitle, String description, String language,
											 int numberOfPage, String publisher, String tempReward, int year,
											 int editionNumber, String nameOfTranslator,
											 String imageName, String fileName, String imageHTTP,
											 String fileHTTP, int tempIndex, String timeAndDate, boolean isReviewed, String prefixFile, String prefixImage) {
		try {
			String query = "SELECT id FROM register WHERE username = ?";
			PreparedStatement prmt = createConnection().prepareStatement(query);
			prmt.setString(1, tempUsername);
			ResultSet rs = prmt.executeQuery();
			int user_id = 0;
			while (rs.next()) {
				user_id = rs.getInt("ID");
			}
			String insertBookQuery = "INSERT INTO user_book(user_id, book_title, description, language, " +
					"number_of_page, publisher, temp_reward, year, edition_number, " +
					"name_of_translator, image_name, file_name, image_http, file_http, " +
					"temp_index, time_and_date,is_reviewed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			prmt = createConnection().prepareStatement(insertBookQuery);
			prmt.setInt(1, user_id);
			prmt.setString(2, bookTitle);
			prmt.setString(3, description);
			prmt.setString(4, language);
			prmt.setInt(5, numberOfPage);
			prmt.setString(6, publisher);
			prmt.setString(7, tempReward);
			prmt.setInt(8, year);
			prmt.setInt(9, editionNumber);
			prmt.setString(10, nameOfTranslator);
			prmt.setString(11, imageName);
			prmt.setString(12, fileName);
			prmt.setString(13, imageHTTP);
			prmt.setString(14, fileHTTP);
			prmt.setInt(15, tempIndex);
			prmt.setString(16, timeAndDate);
			prmt.setBoolean(17, isReviewed);
			prmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			firebase fb = new firebase();
			fb.deleteFile(STR."\{prefixFile}/\{fileName}");
			fb.deleteFile(STR."\{prefixImage}/\{imageName}");
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonEnterCorrectInromation();
		}
	}

	public void insertIntoContactUs(String email, String message, String fileOrImageName, String fileOrImageHTTP, boolean isAnswer, String prefixName) {
		try {
			String insertBookQuery = "INSERT INTO contact_us(email, descriptions,imageOrFileName,imageOrFilePath,isAnswer) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prmt = createConnection().prepareStatement(insertBookQuery);
			prmt.setString(1, email);
			prmt.setString(2, message);
			prmt.setString(3, fileOrImageName);
			prmt.setString(4, fileOrImageHTTP);
			prmt.setBoolean(5, isAnswer);
			prmt.executeUpdate();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			firebase fb = new firebase();
			fb.deleteFile(STR."\{prefixName}/\{fileOrImageName}");
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonEnterCorrectInromation();
		}
	}

	public int getNumberOfBook(String username) {
		int result=0;
		try {
			String queryGetId = "SELECT ID FROM register WHERE username = ?";
			PreparedStatement prmt = createConnection().prepareStatement(queryGetId);
			prmt.setString(1, username);
			ResultSet resultSet = prmt.executeQuery();
			int userId = -1; // Default value or handle if not found
			if (resultSet.next()) {
				userId = resultSet.getInt("ID");
			}
			String query = "SELECT COUNT(*) as book_count FROM user_book WHERE user_id = ?;";

			prmt = createConnection().prepareStatement(query);
			prmt.setInt(1,userId);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("book_count");
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
			result = -1;
		}
		return result;
	}

	public int getNumberOfReviewedBooks(String username) {
		int result=0;
		try {
			String queryGetId = "SELECT ID FROM register WHERE username = ?";
			PreparedStatement prmt = createConnection().prepareStatement(queryGetId);
			prmt.setString(1, username);
			ResultSet resultSet = prmt.executeQuery();
			int userId = -1; // Default value or handle if not found
			if (resultSet.next()) {
				userId = resultSet.getInt("ID");
			}
			String query = "SELECT COUNT(*) as book_reviewed FROM user_book WHERE is_reviewed=1 AND user_id=?;";
			prmt = createConnection().prepareStatement(query);
			prmt.setInt(1,userId);
			ResultSet rs = prmt.executeQuery();
			while (rs.next()) {
				result= rs.getInt("book_reviewed");
			}
			rs.close();
			prmt.close();
			closeConnection();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			notificationsClass nC = new notificationsClass();
			nC.showNotificaitonSomethingWrong();
			result = -1;
		}
		return result;
	}
}