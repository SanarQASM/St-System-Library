package application;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class enterEmailController {
	private static Stage tempStage;

	@FXML
	private AnchorPane changePassword;

	@FXML
	private TextField email;

	@FXML
	private Label errorEmail;

	public void setStage(Stage stage) {
		tempStage = stage;
	}


	@FXML
	void backToregister(ActionEvent event) {
		accountController aC = new accountController();
		aC.showStage();
		tempStage.close();
	}

	@FXML
	void findEmail(ActionEvent event) throws IOException {
		 if (email.getText().length()>=45) {
			errorEmail.setText("Too Long!!!");
			email.setStyle("-fx-border-color: red;");
		}
	     else if (!(email.getText().isEmpty())) {
			String emailText = email.getText().trim();

			if (isGmailAddress(emailText) && !(emailText.contains(" "))) {
				emailSender eSender = new emailSender();
				DatabaseConnection databaseCon = new DatabaseConnection();
				if (!(databaseCon.checkEmailInDatabase(emailText))) {
					int result =eSender.startSendMail(emailText);
					if(result ==-1) {
						notificationsClass nC=new notificationsClass();
						nC.showNotificaitonCheckYourEmail();
						errorEmail.setText("");
						email.setStyle("-fx-border-color: #0077b6;");
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/enterCode.fxml"));
						enterCodeController eCC = new enterCodeController();
						Parent root = loader.load();
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						scene.getStylesheets().add(getClass().getResource("/fxmlFile/application.css").toExternalForm());
						stage.setScene(scene);
						stage.setResizable(false);
						stage.setTitle("Enter Code");
						eCC.setStage(stage);
						eCC.setEmail(emailText);
						Image image =new Image(getClass().getResource("/image/gmail.png").toString());
						stage.getIcons().add(image);
						tempStage.hide();
						stage.show();
						stage.setOnCloseRequest(event1 -> {
							event1.consume();
							Main m = new Main();
							m.logout(stage);
						});
					}
					else if(result==1) {
					    notificationsClass nC=new notificationsClass();
					    nC.showNotificaitonNointernet();
					}
					else{
						notificationsClass nC=new notificationsClass();
						nC.showNotificaitonSomethingWrong();
						errorEmail.setText("Not Found");
						email.setStyle("-fx-border-color: red;");
					}
				} else {
					errorEmail.setText("This gmail is takin!!!");
					email.setStyle("-fx-border-color: red;");
				}
			} else {
				errorEmail.setText("Not Found!");
				email.setStyle("-fx-border-color: red;");
			}
		}
		else {
			errorEmail.setText("Empty!");
			email.setStyle("-fx-border-color: red;");
		}
	}

	public void showStage() {
		tempStage.show();
	}

	public static boolean isGmailAddress(String email) {
		String gmailPattern = "^[a-zA-Z0-9_.]+@gmail\\.com$";
		Pattern pattern = Pattern.compile(gmailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
