package application;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class accountController implements Initializable {
	private boolean userNameBool;
	private boolean passWord;
	private boolean question;
	private boolean answer;
	private boolean usernameSi;
	private boolean passwordSi;
	private boolean notHaveAccout = true;
	private boolean questionFormat;
	private boolean answerForget;
	private boolean usernameInFormatFound;
	private boolean questionIndatabase;
	private boolean answerIndatabse;
	private static Stage tempStage;
	private static accountController aC;
	private final Preferences pref = Preferences.userRoot().node("Rememberme");

	@FXML
	private Button alreadyHaveAccountButton;

	@FXML
	private AnchorPane anchorForm;

	@FXML
	private Button createNewAccountButton;

	@FXML
	private TextField forgetAnswer;

	@FXML
	private Label forgetAnswerError;

	@FXML
	private ImageView forgetIcon;

	@FXML
	private ComboBox<String> forgetPassCombo;

	@FXML
	private AnchorPane forgetPasswordForm;

	@FXML
	private AnchorPane forgetPasswordWayForm;

	@FXML
	private Label forgetQuestionError;

	@FXML
	private TextField forgetUsername;

	@FXML
	private Label forgetUsernameError;

	@FXML
	private ImageView logInIcon;

	@FXML
	private AnchorPane loginForm;

	@FXML
	private ImageView logoSystem;

	@FXML
	private CheckBox rememberMe;

	@FXML
	private ImageView shapeImage;

	@FXML
	private ImageView showPass;

	@FXML
	private ImageView showPass1;

	@FXML
	private Label si_errorPassword;

	@FXML
	private Label si_errorUserName;

	@FXML
	private PasswordField si_passwordPass;

	@FXML
	private TextField si_passwordText;

	@FXML
	private TextField si_username;

	@FXML
	private StackPane stackPane;

	@FXML
	private TextField su_answer;

	@FXML
	private Label su_errorAnswer;

	@FXML
	private Label su_errorQuestion;

	@FXML
	private Label su_errorUserName;

	@FXML
	private Label su_errorUserPass;

	@FXML
	private PasswordField su_passwordPass;

	@FXML
	private TextField su_passwordText;

	@FXML
	private ComboBox<String> su_question;

	@FXML
	private TextField su_username;

	public void setStage(Stage stage) {
		tempStage = stage;
	}
	public void setController(accountController aC) {
		accountController.aC=aC;
	}

	@FXML
	void showPassAction(MouseEvent event) {
		if (showPass.getImage().getUrl().equals(Objects.requireNonNull(getClass().getResource("/image/showPass.png")).toString())) {
			si_passwordPass.setVisible(false);
		    si_passwordText.setVisible(true);
		    si_passwordText.setText(si_passwordPass.getText());
		    Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/hidePass.png")).toString());
		    showPass.setImage(image);
		} else {
			si_passwordPass.setVisible(true);
			si_passwordText.setVisible(false);
			si_passwordPass.setText(si_passwordText.getText());
			Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/showPass.png")).toString());
			showPass.setImage(image);
		}
	}

	@FXML
	void showPassAction1(MouseEvent event) {
		if (showPass1.getImage().getUrl().equals(Objects.requireNonNull(getClass().getResource("/image/showPass.png")).toString())) {
			su_passwordPass.setVisible(false);
			su_passwordText.setVisible(true);
			su_passwordText.setText(su_passwordPass.getText());
			Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/hidePass.png")).toString());
			showPass1.setImage(image);
		} else {
			su_passwordPass.setVisible(true);
			su_passwordText.setVisible(false);
			su_passwordPass.setText(su_passwordText.getText());
			Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/showPass.png")).toString());
			showPass1.setImage(image);
		}
	}

	@FXML
	void createNewAccount(ActionEvent event) {
		TranslateTransition slider = new TranslateTransition();
		slider.setNode(anchorForm);
		slider.setToX(300);
		slider.setDuration(Duration.seconds(0.5));
		alreadyHaveAccountButton.setVisible(true);
		createNewAccountButton.setVisible(false);
		TranslateTransition slider1 = new TranslateTransition();
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), shapeImage);
		rotateTransition.setByAngle(360);
		slider1.setNode(shapeImage);
		slider1.setToX(220);
		slider1.setToY(120);
		slider1.setDuration(Duration.seconds(1));
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), logoSystem);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		ParallelTransition parallelTransition = new ParallelTransition(slider1, rotateTransition, slider,
				scaleTransition);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		parallelTransition.play();
	}

	@FXML
	void alreadyHaveAccount(ActionEvent event) {
		TranslateTransition slider = new TranslateTransition();
		slider.setNode(anchorForm);
		slider.setToX(0);
		slider.setDuration(Duration.seconds(0.5));
		alreadyHaveAccountButton.setVisible(false);
		createNewAccountButton.setVisible(true);
		TranslateTransition slider1 = new TranslateTransition();
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), shapeImage);
		rotateTransition.setByAngle(-360);
		slider1.setNode(shapeImage);
		slider1.setToX(0);
		slider1.setToY(0);
		slider1.setDuration(Duration.seconds(1));
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), logoSystem);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		ParallelTransition parallelTransition = new ParallelTransition(slider1, rotateTransition, slider,
				scaleTransition);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		parallelTransition.play();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	        String usr;
	        usr = pref.get("si_username", null);
	        si_username.setText(usr);
	        String pss;
	        pss = pref.get("si_passwordPass", null);
	        si_passwordPass.setText(pss);
	        si_passwordText.setText(pss);
		String[] questionArray = { "What was the first car you owned?", "Who was your first teacher?",
				"Where was your first job?", "What was the first album you owned",
				"In which city were you first kissed?", "What is the name of your favorite food?" };

		su_question.getItems().addAll(questionArray);
		forgetPassCombo.getItems().addAll(questionArray);
	}


    
	@FXML
	void si_logIn(ActionEvent event){
		Task<Void> task = new Task<>() {
			@Override
			protected Void call(){
                si_username.setText(si_username.getText().trim());
                checked(rememberMe.isSelected());
                if(usernameOrEmail()){
                    checkUsernameSi();
                }
                else {
                    checkEmailIndatabase();
                }
                checkPasswordSi();
                if (!(si_passwordPass.isVisible())) {
                    si_passwordPass.setText(si_passwordText.getText());
                }
				return null;
			}
		};
		new Thread(task).start();
		task.setOnSucceeded(_ -> {
				if (usernameSi && passwordSi) {
					try {
						stackPane.setDisable(false);
						Image image=new Image(Objects.requireNonNull(getClass().getResource(
								"/image/login.png")).toString());
						logInIcon.setImage(image);
						successfullyLoggedIn();
					} catch (IOException e) {
						notificationsClass nC = new notificationsClass();
						nC.showNotificaitonSomethingWrong();
					}
				}
		});
		Platform.runLater(() -> {
			Image image=new Image(Objects.requireNonNull(getClass().getResource(
					"/image/changeToLoading.gif")).toString());
			logInIcon.setImage(image);
			stackPane.setDisable(true);
		});
	}

	public final void checked(boolean remember){
        if(remember){
            saveemailpass(si_username.getText(), si_passwordPass.getText());
        }
    }
	
	 public void saveemailpass(String Email, String Pass){
	        if(!(Email == null || Pass == null)) {
                pref.put("si_username", Email);
                pref.put("si_passwordPass", Pass);
	            pref.put("si_passwordText", Pass);
	        }
	    }

	private void successfullyLoggedIn() throws IOException {
		if (usernameOrEmail()) {
			new mainController(si_username.getText());
		}else {
			DatabaseConnection databaseCon=new DatabaseConnection();
			new mainController(databaseCon.getUsernameByEmail(si_username.getText()));
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/MainSceneBookShop.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("System Book St Library");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/mainImageBookSystem.png")).toString());
		mainController mC=new mainController();
		tempStage.close();
		mC.setStage(stage);
		stage.getIcons().add(image);
        stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest(event -> {
			event.consume();
			Main main = new Main();
			main.logout(stage);
		});
		si_username.setText("");
		si_passwordText.setText("");
		si_passwordPass.setText("");
	}

	private boolean usernameOrEmail() {
        return (!si_username.getText().endsWith("@gmail.com"));
	}

	private void checkEmailIndatabase() {
		if (si_username.getText().isEmpty()) {
			si_errorUserName.setText("Empty!");
		} else {
			DatabaseConnection databaseCon=new DatabaseConnection();
			if (!(databaseCon.checkEmailInDatabase(si_username.getText()))) {
				si_errorUserName.setText("not found!");
			} else {
				si_errorUserName.setText("");
				si_username.setStyle("-fx-border-color: #0077b6;");
				usernameSi = true;
			}
		}
	}
	private void checkUsernameSi(){
		if (si_username.getText().isEmpty()) {
			si_errorUserName.setText("Empty!");
		}else if (si_username.getText().length()>=15) {
			si_errorUserName.setText("Too Long!!!");
			si_username.setStyle("-fx-border-color: red;");
		}
		else {
			DatabaseConnection databaseCon=new DatabaseConnection ();
			if (!(databaseCon.checkUsername(si_username.getText()))) {
				si_errorUserName.setText("not found!");
			} else {
				si_errorUserName.setText("");
				si_username.setStyle("-fx-border-color: #0077b6;");
				usernameSi = true;
			}
		}
	}


	private void checkPasswordSi(){
		if (!(si_passwordPass.isVisible())) {
			si_passwordPass.setText(si_passwordText.getText());
		}
		if (si_passwordPass.getText().isEmpty()) {
			si_errorPassword.setText("Empty!");
		} 
		else if(si_passwordPass.getText().length()>=20) {
			si_errorPassword.setText("Too Long!!!");
			si_errorPassword.setStyle("-fx-border-color: red;");
		}
		else {
			DatabaseConnection databaseCon = new DatabaseConnection();
			if (usernameOrEmail()) {
					if (!(databaseCon.checkPasswordThrougUsername(si_username.getText(),si_passwordPass.getText()))) {
					si_errorPassword.setText("not found!");
				    }
					else {
					si_errorPassword.setText("");
					si_passwordPass.setStyle("-fx-border-color: #0077b6;");
					si_passwordText.setStyle("-fx-border-color: #0077b6;");
					passwordSi = true;
				}
			}
			else {
				    if(!(databaseCon.checkPasswordThrougEmail(si_username.getText(),si_passwordPass.getText()))) {
				    	si_errorPassword.setText("Not found!");
				    }
				    else {
						si_errorPassword.setText("");
						si_passwordPass.setStyle("-fx-border-color: #0077b6;");
						si_passwordText.setStyle("-fx-border-color: #0077b6;");
						passwordSi = true;
				}
			}
		}
	}

	@FXML
	void su_signUp(ActionEvent event){
				DatabaseConnection databaseCon = new DatabaseConnection();
				checkUsername();
				checkPassword();
				checkComboBox();
				checkAnswer();
				notHaveAccout = true;
				if (databaseCon.checkUsername(su_username.getText())) {
					su_errorUserName.setText("Username Is Takin!!!");
					notHaveAccout = false;
				}
			if (userNameBool && passWord && answer && question && notHaveAccout) {
				notificationsClass nC=new notificationsClass();
				nC.showNotificationEnterEmail();
				try {
					openSu_email();
				} catch (IOException e) {
					nC.showNotificaitonSomethingWrong();
				}
			}
	}

	private void openSu_email() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/enterEmail.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Enter Email");
		enterEmailController eEC = new enterEmailController();
		eEC.setStage(stage);
		tempStage.hide();
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/gmail.png")).toString());
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main m = new Main();
			m.logout(stage);
		});
	}

	public void registerDone() {
		enterCodeController eCC = new enterCodeController();
		DatabaseConnection databaseCon = new DatabaseConnection();
		databaseCon.setAllInformationIndatabase(su_username.getText(),su_passwordPass.getText(),eCC.getTempEmail(), findIndexQuestoin(), su_answer.getText());
		TranslateTransition slider = new TranslateTransition();
		slider.setNode(anchorForm);
		slider.setToX(0);
		slider.setDuration(Duration.seconds(0.5));
		TranslateTransition slider1 = new TranslateTransition();
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), shapeImage);
		rotateTransition.setByAngle(-360);
		slider1.setNode(shapeImage);
		slider1.setToX(0);
		slider1.setToY(0);
		slider1.setDuration(Duration.seconds(1));
		alreadyHaveAccountButton.setVisible(false);
		createNewAccountButton.setVisible(true);
		su_username.setText("");
		su_passwordPass.setText("");
		su_passwordText.setText("");
		su_answer.setText("");
		ParallelTransition parallelTransition = new ParallelTransition(slider1, rotateTransition, slider);
		parallelTransition.play();
	}

	private int findIndexQuestoin() {
		int selectedIndex = su_question.getSelectionModel().getSelectedIndex();
		return selectedIndex + 1;
	}

	private void checkAnswer() {
		if (su_answer.getText().isEmpty()) {
			su_errorAnswer.setText("Answer!");
			su_answer.setStyle("-fx-border-color: red;");
		}else if (su_answer.getText().length()>=15) {
			su_errorAnswer.setText("Too Long!!!");
			su_answer.setStyle("-fx-border-color: red;");
		}
		else {
			su_errorAnswer.setText("");
			answer = true;
		}
	}

	private void checkComboBox() {
		if (su_question.getValue() == null) {
			su_errorQuestion.setText("choose!");
		} else {
			su_errorQuestion.setText("");
			question = true;
		}
	}

	private void checkPassword() {
		if (!(su_passwordPass.isVisible())) {
			su_passwordPass.setText(su_passwordText.getText());
		}
		if (!(su_passwordPass.getText().isEmpty())) {
			String userName = su_passwordPass.getText();
			su_passwordPass.setText(userName);
			boolean strong = checkPassToHaveDigit(userName);
			if (su_passwordPass.getText().length()>=20) {
				su_errorUserPass.setText("Too Long!!!");
				su_passwordText.setStyle("-fx-border-color: red;");
				su_passwordPass.setStyle("-fx-border-color: red;");
			}else if (strong) {
				su_errorUserPass.setText("");
				su_passwordPass.setStyle("-fx-border-color: #0077b6;");
				su_passwordText.setStyle("-fx-border-color: #0077b6;");
				passWord = true;
			}
			else {
				su_errorUserPass.setText("letter & Digit!");
			}
		} else {
			su_errorUserPass.setText("Empty!");
			su_passwordPass.setStyle("-fx-border-color: red;");
			su_passwordText.setStyle("-fx-border-color: red;");
		}
	}

	public boolean checkPassToHaveDigit(String username) {
		int countDigit = 0;
		int countLetter = 0;
		for (int i = 0; i < username.length(); i++) {
			char digit = username.charAt(i);
			if (Character.isLetter(digit)) {
				countLetter++;
			}
			if (Character.isDigit(digit)) {
				countDigit++;
			}
		}
        return !(countDigit == 0) && !(countLetter == 0);
	}

	private void checkUsername() {
		if (!(su_username.getText().isEmpty())) {
			String userName = su_username.getText().trim();
			su_username.setText(userName);
			boolean strong = checkUserToHaveDigit(userName);
			if (su_username.getText().length()>=15) {
				su_errorUserName.setText("Too long!!!");
				su_username.setStyle("-fx-border-color: red;");
			} else if (strong) {
				su_errorUserName.setText("");
				su_username.setStyle("-fx-border-color:#0077b6;");
				userNameBool = true;
			}
			else {
				su_errorUserName.setText("letter & Digit!");
			}
		} else {
			su_errorUserName.setText("Empty!");
			su_username.setStyle("-fx-border-color: red;");
		}
	}

	private boolean checkUserToHaveDigit(String username) {
		int countDigit = 0;
		int countLetter = 0;
		for (int i = 0; i < username.length(); i++) {
			char digit = username.charAt(i);
			if (Character.isLetter(digit)) {
				countLetter++;
			}
			if (Character.isDigit(digit)) {
				countDigit++;
			}
		}
        return !(countDigit == 0) && !(countLetter == 0);

	}

	@FXML
	void forgetPassword(ActionEvent event) {
		forgetPasswordWayForm.setVisible(true);
		loginForm.setVisible(false);
		si_errorUserName.setText("");
		si_passwordPass.setText("");
		si_passwordText.setText("");

	}

	@FXML
	void proceed(ActionEvent event) throws IOException {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws IOException {
				forgetUsername.setText(forgetUsername.getText().trim());
				if(forgetUsername.getText().length()>=20) {
					forgetUsernameError.setText("Too Long!!!");
					forgetUsername.setStyle("-fx-border-color: red;");
				}
				forgetAnswer.setText(forgetAnswer.getText().trim());
				if(forgetAnswer.getText().length()>=20) {
					forgetAnswerError.setText("Too Long!!!");
					forgetAnswer.setStyle("-fx-border-color: red;");
				}
				checkUsernameToEmpty();
				return null;
			}
		};
		new Thread(task).start();
		task.setOnSucceeded(_ -> {
			if (questionIndatabase && answerIndatabse) {
				forgetPasswordForm.setVisible(false);
                try {
					stackPane.setDisable(false);
					Image image=new Image(Objects.requireNonNull(getClass().getResource(
							"/image/forgot-password.png")).toString());
					forgetIcon.setImage(image);
                    openChangePasswordController(forgetUsername.getText());
                } catch (IOException e) {
                    notificationsClass nC=new notificationsClass();
					nC.showNotificaitonSomethingWrong();
                }
            }
		});
		Platform.runLater(() -> {
			Image image=new Image(Objects.requireNonNull(getClass().getResource(
					"/image/changeToLoading.gif")).toString());
			forgetIcon.setImage(image);
			stackPane.setDisable(true);
		});

	}
    public void openChangePasswordController(String wayToChange) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changePassword.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Password");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/reset-password.png")).toString());
		changePasswordController cPC=new changePasswordController();
		tempStage.hide();
		cPC.setTempWayToChange(wayToChange);
		cPC.setStage(stage);
		cPC.setAccountController(accountController.aC);
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event -> {
			event.consume();
			Main main = new Main();
			main.logout(stage);
		});
    }
	private void checkUsernameToEmpty() throws IOException {
		if (forgetUsername.getText().isEmpty()) {
			forgetUsernameError.setText("Empty!");
			forgetUsername.setStyle("-fx-border-color: red;");
		} else if(enterEmailController.isGmailAddress(forgetUsername.getText())){
			checkForgetEmailIndatabase();
		}else{
			checkUsernameIndatabase();
		}
	}

	private void checkForgetEmailIndatabase() {
			DatabaseConnection databaseCon = new DatabaseConnection();
			if (databaseCon.checkEmailInDatabase(forgetUsername.getText())) {
				usernameInFormatFound = true;
				forgetUsernameError.setText("");
				checkQuestionFormat();
				checkAnswerFormat();
				if (answerForget && questionFormat) {
					databaseCon.checkAnswerAndQuestion(databaseCon.getUsernameByEmail(forgetUsername.getText()), findIndexQuestoinFormat(),
							forgetAnswer.getText());
					if (databaseCon.getHaveQuestion()) {
						forgetQuestionError.setText("");
						questionIndatabase = true;
					} else {
						forgetQuestionError.setText("Not Found!");
					}
					if (databaseCon.getHaveAnswer()) {
						forgetAnswerError.setText("");
						forgetAnswer.setStyle("-fx-border-color: #0077b6;");
						answerIndatabse = true;
					} else {
						forgetAnswerError.setText("Not Found!");
					}
				}
			}
			if (!usernameInFormatFound) {
				forgetUsernameError.setText("Not Found!");
			}
	}

	private void checkUsernameIndatabase(){
		DatabaseConnection databaseCon = new DatabaseConnection();
		if (databaseCon.checkUsername(forgetUsername.getText())) {
			usernameInFormatFound = true;
			forgetUsernameError.setText("");
			checkQuestionFormat();
			checkAnswerFormat();
			if (answerForget && questionFormat) {
				databaseCon.checkAnswerAndQuestion(forgetUsername.getText(), findIndexQuestoinFormat(),
						forgetAnswer.getText());
				if (databaseCon.getHaveQuestion()) {
					forgetQuestionError.setText("");
					questionIndatabase = true;
				} else {
					forgetQuestionError.setText("Not Found!");
				}
				if (databaseCon.getHaveAnswer()) {
					forgetAnswerError.setText("");
					forgetAnswer.setStyle("-fx-border-color: #0077b6;");
					answerIndatabse = true;
				} else {
					forgetAnswerError.setText("Not Found!");
				}
			}
		}
		if (!usernameInFormatFound) {
			forgetUsernameError.setText("Not Found!");
		}
	}

	private void checkAnswerFormat() {
		if (forgetAnswer.getText().isEmpty()) {
			forgetAnswerError.setText("Answer!");
		}else if (forgetAnswer.getText().length()>=15) {
			forgetAnswerError.setText("Too Long!!!");
			forgetAnswer.setStyle("-fx-border-color: red;");
		} 
		else {
			forgetAnswerError.setText("");
			forgetAnswer.setStyle("-fx-border-color: #0077b6;");
			answerForget = true;
		}
	}
	private void checkQuestionFormat() {
		if (forgetPassCombo.getValue() == null) {
			forgetQuestionError.setText("choose!");
		} else {
			forgetQuestionError.setText("");
			questionFormat = true;
		}
	}

	private int findIndexQuestoinFormat() {
		int selectedIndex = forgetPassCombo.getSelectionModel().getSelectedIndex();
		return selectedIndex + 1;
	}

	@FXML
	void back(ActionEvent event) {
		forgetPasswordForm.setVisible(false);
		forgetPasswordWayForm.setVisible(true);
		forgetAnswer.setText("");
		forgetAnswerError.setText("");
		forgetQuestionError.setText("");
		forgetUsername.setText("");
		forgetUsernameError.setText("");
	}

	public void showStage() {
		tempStage.show();
	}

    @FXML
    void email_code(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changeEmail.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Password");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/gmail.png")).toString());
		changeEmailController cEC=new changeEmailController();
		tempStage.close();
		cEC.setStage(stage);
		cEC.setAccountController(accountController.aC);
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
		
    }
	
	@FXML
    void question_answer(ActionEvent event) {
    	forgetPasswordForm.setVisible(true);
    	forgetPasswordWayForm.setVisible(false);
    }
	
    @FXML
    void backToLoginForm(ActionEvent event) {
    	forgetPasswordWayForm.setVisible(false);
		loginForm.setVisible(true);
    }
    
    @FXML
    void contactUs(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/contactUs.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Password");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/contactUs.png")).toString());
		stage.getIcons().add(image);
		contactUsController aUC=new contactUsController();
		aUC.setStgae(stage);
		tempStage.close();
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
    }
    
	public void setAllFormVisabiliity() {
		aC.loginForm.setVisible(true);
		aC.forgetPasswordWayForm.setVisible(false);
		aC.forgetPasswordForm.setVisible(false);
	}
}
