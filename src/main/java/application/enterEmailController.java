package application;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class enterEmailController {
	private static Stage tempStage;

	@FXML
	private AnchorPane enterEmailAnchor;

	@FXML
	private TextField email;

	@FXML
	private ImageView emailIcon;

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
	void findEmail(ActionEvent event) {

				if (email.getText().length()>=99) {
					errorEmail.setText("Too Long!!!");
					email.setStyle("-fx-border-color: red;");
				}
				else if (!(email.getText().isEmpty())) {
					       String emailText = email.getText().trim();

							if (isGmailAddress(emailText) && !(emailText.contains(" "))) {
								DatabaseConnection databaseCon = new DatabaseConnection();
								if (!(databaseCon.checkEmailInDatabase(emailText))) {
									final int[] result = {0};
									Task<Void> task = new Task<>() {
										@Override
										protected Void call(){
											emailSender eSender = new emailSender();
											result[0] =eSender.startSendMail(emailText);
											return null;
										}
									};
									new Thread(task).start();
									task.setOnSucceeded(_ -> {
										enterEmailAnchor.setDisable(false);
										Image image=new Image(Objects.requireNonNull(getClass().getResource(
												"/image/emailMain.png")).toString());
										emailIcon.setImage(image);
										if(result[0] ==-1) {
											notificationsClass nC=new notificationsClass();
											nC.showNotificaitonCheckYourEmail();
											errorEmail.setText("");
											email.setStyle("-fx-border-color: #0077b6;");
											FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/enterCode.fxml"));
											enterCodeController eCC = new enterCodeController();
                                            Parent root = null;
                                            try {
                                                root = loader.load();
                                            } catch (IOException e) {
												nC.showNotificaitonSomethingWrong();
                                            }
                                            Scene scene = new Scene(root);
											Stage stage = new Stage();
											scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
											stage.setScene(scene);
											stage.setResizable(false);
											stage.setTitle("Enter Code");
											eCC.setStage(stage);
											eCC.setEmail(emailText);
											image =new Image(Objects.requireNonNull(getClass().getResource("/image/gmail.png")).toString());
											stage.getIcons().add(image);
											tempStage.hide();
											stage.show();
											stage.setOnCloseRequest(event1 -> {
												event1.consume();
												Main m = new Main();
												m.logout(stage);
											});
										}
										else if(result[0] ==1) {
											notificationsClass nC=new notificationsClass();
											nC.showNotificaitonNointernet();
										}
										else{
											notificationsClass nC=new notificationsClass();
											nC.showNotificaitonSomethingWrong();
											errorEmail.setText("Not Found");
											email.setStyle("-fx-border-color: red;");
										}
									});
									Platform.runLater(() -> {
										Image image=new Image(Objects.requireNonNull(getClass().getResource(
												"/image/changeToLoading.gif")).toString());
										emailIcon.setImage(image);
										enterEmailAnchor.setDisable(true);
									});
								}
								else {
									errorEmail.setText("This gmail is takin!!!");
									email.setStyle("-fx-border-color: red;");
								}
							}
							else {
						errorEmail.setText("Not Found!");
						email.setStyle("-fx-border-color: red;");
					}
				}
				else{
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
