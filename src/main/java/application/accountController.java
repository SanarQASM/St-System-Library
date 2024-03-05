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
	private static String messageTemp;
    private boolean questionFormat;
	private boolean answerForget;
	private boolean questionIndatabase;
	private boolean answerIndatabse;
	private static Stage tempStage;
	private static accountController aC;
	private static notificationsClass nC;
	private final Preferences pref = Preferences.userRoot().node("Rememberme");
    private static boolean visabilityPasswordButton;

	@FXML
	private ImageView registerIcon;

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
	public void setController(accountController aC,notificationsClass nC) {
		accountController.nC=nC;
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
		ParallelTransition parallelTransition = new ParallelTransition(slider1, rotateTransition, slider);
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
		ParallelTransition parallelTransition = new ParallelTransition(slider1, rotateTransition, slider);
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
//		openDialogButton.setOnAction(e -> {
//			Stage dialogStage = new Stage();
//			dialogStage.initModality(Modality.APPLICATION_MODAL);
//			dialogStage.initOwner(tempStage);
//			dialogStage.setTitle("Modal Dialog");
//			StackPane dialogLayout = new StackPane(new Button("Close"));
//			dialogLayout.setPrefSize(200, 100);
//			dialogStage.setScene(new Scene(dialogLayout));
//			dialogStage.showAndWait();
//		});
	}
    
	@FXML
	void si_logIn(ActionEvent event){
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
                si_username.setText(si_username.getText().trim());
                checked(rememberMe.isSelected());
                if (usernameOrEmail()) {
					checkUsernameSi(si_username.getText());
					if (!(visabilityPasswordButton)) {
						checkPasswordSi(si_username.getText(),si_passwordText.getText());
					}else {
						checkPasswordSi(si_username.getText(),si_passwordText.getText());
					}
				} else {
					checkEmailIndatabase(si_username.getText());
					if (!(visabilityPasswordButton)) {
						checkPasswordSi(si_username.getText(),si_passwordPass.getText());
					}else {
						checkPasswordSi(si_username.getText(),si_passwordPass.getText());
					}
                }
				return null;
			}
		};
		task.setOnFailed(_ ->{
			Platform.runLater(() ->{
				nC.showNotificaitonSomethingWrong(messageTemp);
				stackPane.setDisable(false);
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/login.png")).toString());
				logInIcon.setImage(image);
			});
		});

		task.setOnSucceeded(_ -> {
			Platform.runLater(() -> {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/MainSceneBookShop.fxml"));
					Parent root;
					root = loader.load();
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
					stage.setScene(scene);
					stage.setTitle("System Book St Library");
					Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/mainImageBookSystem.png")).toString());
					mainController mC = new mainController();
					tempStage.close();
					mC.setStage(stage);
					stage.getIcons().add(image);
					stage.show();
					stage.setOnCloseRequest(event1 -> {
						event1.consume();
						Main main = new Main();
						main.logout(stage);
					});
                    stackPane.setDisable(false);
                    image = new Image(Objects.requireNonNull(getClass().getResource(
                            "/image/login.png")).toString());
                    logInIcon.setImage(image);
				} catch (IOException ex) {
					nC.showNotificaitonSomethingWrong("failed to get Data");
				}
			});
		});

		new Thread(task).start();
		Platform.runLater(() -> {
			Image image = new Image(Objects.requireNonNull(getClass().getResource(
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


	private boolean usernameOrEmail() {
        return (!si_username.getText().endsWith("@gmail.com"));
	}

	private void checkEmailIndatabase(String SIUsername) throws Exception{
            if (SIUsername.isEmpty()) {
                Platform.runLater(() -> {
                    si_errorUserName.setText("Empty!");
					messageTemp="Email can not Empty!";
				});
				throw new Exception(messageTemp);
            } else if (SIUsername.length() >= 35) {
                Platform.runLater(() -> {
                    si_errorUserName.setText("Too Long!!!");
                    si_username.setStyle("-fx-border-color: red;");
					messageTemp="Email too long";
				});
                throw new Exception(messageTemp);
            }  else {
                DatabaseConnection databaseCon = new DatabaseConnection(nC);
                if (!(databaseCon.checkEmailInDatabase(SIUsername))) {
                    Platform.runLater(() -> {
                        si_errorUserName.setText("not found!");
						messageTemp="Email Not Found!";
					});
					throw new Exception(messageTemp);
                } else {
					aC.si_errorUserName.setText("");
					aC.si_username.setStyle("-fx-border-color: #0077b6;");
				}
            }
	}
	private void checkUsernameSi(String SIUsername) throws Exception{
            if (SIUsername.isEmpty()) {
                Platform.runLater(() -> {
                    si_errorUserName.setText("Empty!");
					messageTemp="username Is Empty!";
				});
                throw new Exception(messageTemp);
            } else if (SIUsername.length() >= 35) {
                Platform.runLater(() -> {
                    si_errorUserName.setText("Too Long!!!");
                    si_username.setStyle("-fx-border-color: red;");
					messageTemp="username too long!";
				});
                throw new Exception(messageTemp);
            } else {
                DatabaseConnection databaseCon = new DatabaseConnection(nC);
                if (!(databaseCon.checkUsername(SIUsername))) {
                    Platform.runLater(() -> {
                        si_errorUserName.setText("not found!");
						messageTemp="username Not Found!";
					});
					throw new Exception(messageTemp);
                } else {
					Platform.runLater(()->{
						aC.si_errorUserName.setText("");
						aC.si_username.setStyle("-fx-border-color: #0077b6;");
					});
				}
            }

	}
    private void checkPasswordSi(String SIUsername,String SIPassword) throws Exception {
		if (SIPassword.isEmpty()) {
			Platform.runLater(() -> {
				si_errorPassword.setText("Empty!");
				messageTemp="password can not Empty!";
			});
			throw new Exception(messageTemp);
		} else if (SIPassword.length() >= 35) {
			Platform.runLater(() -> {
				si_errorPassword.setText("Too Long!!!");
				si_errorPassword.setStyle("-fx-border-color: red;");
				messageTemp="password too long!";
			});
			throw new Exception(messageTemp);
		} else {
			DatabaseConnection databaseCon = new DatabaseConnection(nC);
			if (usernameOrEmail()) {
				EncryptionAndDecryptionPass EADP=new EncryptionAndDecryptionPass(databaseCon.getSecretKey(SIUsername));
				SIPassword=databaseCon.getPasswordThroughusername(SIUsername);
				if (!(databaseCon.checkPasswordThrougUsername(SIUsername,EADP.decrypt(SIPassword)))) {
					Platform.runLater(() -> {
						si_errorPassword.setText("not found!");
						messageTemp="password not found through username!";
					});
					throw new Exception(messageTemp);
				} else {
					Platform.runLater(() -> {
						si_errorPassword.setText("");
						si_passwordPass.setStyle("-fx-border-color: #0077b6;");
						si_passwordText.setStyle("-fx-border-color: #0077b6;");
						if (usernameOrEmail()) {
							new mainController(si_username.getText(),nC);
						} else {
							new mainController(databaseCon.getUsernameByEmail(SIUsername),nC);
						}
					});
				}
			} else {
				if (!(databaseCon.checkPasswordThrougEmail(SIUsername, SIPassword))) {
					Platform.runLater(() -> {
						si_errorPassword.setText("Not found!");
						messageTemp="password not found through Email!";
					});
					throw new Exception(messageTemp);
				} else {
					Platform.runLater(() -> {
						si_errorPassword.setText("");
						si_passwordPass.setStyle("-fx-border-color: #0077b6;");
						si_passwordText.setStyle("-fx-border-color: #0077b6;");
						if (usernameOrEmail()) {
							new mainController(si_username.getText(),nC);
						} else {
							new mainController(databaseCon.getUsernameByEmail(si_username.getText()),nC);
						}
					});
				}
			}
		}
    }
	@FXML
	void su_signUp(ActionEvent event){
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				su_username.setText(su_username.getText().trim());
				checkUsername(su_username.getText());
				String password;
				if (!(visabilityPasswordButton)) {
					password =si_passwordText.getText();
				}else {
					password=si_passwordPass.getText();
				}
				checkPassword(password);
				checkComboBox(su_question.getValue());
				su_answer.setText(su_answer.getText());
				checkAnswer(su_answer.getText());
				return null;
			}
		};

		task.setOnSucceeded(_ -> {
			Platform.runLater(() -> {
				nC.showNotificationEnterEmail();
				stackPane.setDisable(false);
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/register.png")).toString());
				registerIcon.setImage(image);
				try {
					openSu_email();
				} catch (IOException e) {
					nC.showNotificaitonSomethingWrong("failed to open email");
				}
		    });
		});
		task.setOnFailed(_ -> {
			Platform.runLater(() -> {
				nC.showNotificationFieldEmpty();
				stackPane.setDisable(false);
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/register.png")).toString());
				registerIcon.setImage(image);
			});
		});
		new Thread(task).start();
		Platform.runLater(() -> {
			Image image = new Image(Objects.requireNonNull(getClass().getResource(
					"/image/changeToLoading.gif")).toString());
			registerIcon.setImage(image);
			stackPane.setDisable(true);
		});
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
		notificationsClass nC=new notificationsClass();
		new enterEmailController(nC,stage);
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

	public void registerDone() throws Exception {
		enterCodeController eCC = new enterCodeController();
		DatabaseConnection databaseCon = new DatabaseConnection(nC);
		EncryptionAndDecryptionPass EADP=new EncryptionAndDecryptionPass();
		String generatedKey=EADP.generateSecretKey();
		String encryption=EADP.encrypt(su_passwordPass.getText());
		databaseCon.setAllInformationIndatabase(su_username.getText(), encryption,eCC.getTempEmail(), findIndexQuestoin(), su_answer.getText(),generatedKey);
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

	private void checkAnswer(String suAnswer) {
		if (suAnswer.isEmpty()) {
			Platform.runLater(() -> {
				su_errorAnswer.setText("Answer!");
				su_answer.setStyle("-fx-border-color: red;");
			});
		} else if (suAnswer.length() >= 45) {
			Platform.runLater(() -> {
				su_errorAnswer.setText("Too Long!!!");
				su_answer.setStyle("-fx-border-color: #0077b6;");
			});
		} else {
			Platform.runLater(() -> {
				su_errorAnswer.setText("");
			});
		}
	}

	private void checkComboBox(String suQuestion) throws Exception {
		if (suQuestion== null) {
			Platform.runLater(() -> {
				su_errorQuestion.setText("choose!");
				messageTemp="Please Choose one of the Question";
			});
			throw new Exception(messageTemp);
		} else {
			Platform.runLater(() -> {
				su_errorQuestion.setText("");
			});
		}
	}

	private void checkPassword(String suPassword) throws Exception {
		if (!(suPassword.isEmpty())) {
			boolean strong = checkPassToHaveDigit(suPassword);
			if (suPassword.length() >= 35) {
				Platform.runLater(() -> {
					su_errorUserPass.setText("Too Long!!!");
					su_passwordText.setStyle("-fx-border-color: red;");
					su_passwordPass.setStyle("-fx-border-color: red;");
					messageTemp="Password is Too Long";
				});
				throw new Exception(messageTemp);
			} else if (strong) {
				Platform.runLater(() -> {
							su_errorUserPass.setText("");
							su_passwordPass.setStyle("-fx-border-color: #0077b6;");
							su_passwordText.setStyle("-fx-border-color: #0077b6;");
				});
			} else {
				Platform.runLater(() -> {
					su_errorUserPass.setText("letter & Digit!");
					messageTemp="Password Must Contain Letter and digit";
				});
				throw new Exception(messageTemp);
			}
		} else {
			Platform.runLater(() -> {
				su_errorUserPass.setText("Empty!");
				su_passwordPass.setStyle("-fx-border-color: red;");
				su_passwordText.setStyle("-fx-border-color: red;");
				messageTemp="Password can not Empty";
			});
			throw new Exception(messageTemp);
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

	private void checkUsername(String suUsername) throws Exception {
				if (!(suUsername.isEmpty())) {
					DatabaseConnection databaseCon=new DatabaseConnection(nC);
					boolean strong = checkUserToHaveDigit(suUsername);
					if (suUsername.length() >= 35) {
						Platform.runLater(() -> {
							su_errorUserName.setText("Too long!!!");
							su_username.setStyle("-fx-border-color: red;");
							messageTemp="Please Enter Short Name";
						});
						throw new Exception(messageTemp);
					}
					else if(databaseCon.checkUsername(suUsername)){
						Platform.runLater(() -> {
							su_errorUserName.setText("Username Is Takin!!!");
							su_username.setStyle("-fx-border-color: red;");
							messageTemp="Please Enter different Username";
						});
						throw new Exception(messageTemp);
					}
					else if (strong) {
						Platform.runLater(() -> {
									su_errorUserName.setText("");
									su_username.setStyle("-fx-border-color:#0077b6;");
						});

					}

					else {
						Platform.runLater(() -> {
						su_errorUserName.setText("letter & Digit!");
							messageTemp="Username Must Contain Letter and digit";
						});
						throw new Exception(messageTemp);
					}
				} else {
					Platform.runLater(() -> {
						su_errorUserName.setText("Empty!");
						su_username.setStyle("-fx-border-color: red;");
						messageTemp="Username Can not Empty";
					});
					throw new Exception(messageTemp);
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
	void proceed(ActionEvent event){
		boolean result=false;
		boolean usernameHave=false;
		forgetUsername.setText(forgetUsername.getText().trim());
		if(forgetUsername.getText().length()>=100) {
			forgetUsernameError.setText("Too Long!!!");
			forgetUsername.setStyle("-fx-border-color: red;");
		}
		else if (forgetUsername.getText().isEmpty()) {
			forgetUsernameError.setText("Empty!");
			forgetUsername.setStyle("-fx-border-color: red;");
		}
		else{
			result= enterEmailController.isGmailAddress(forgetUsername.getText());
			usernameHave=true;
			forgetUsernameError.setText("");
			forgetUsername.setStyle("-fx-border-color: #0077b6");
		}
		checkQuestionFormat();
		checkAnswerFormat();
		if (questionFormat && answerForget && usernameHave){
			boolean finalResult = result;
			Task<Void> task = new Task<>() {
				@Override
				protected Void call(){
					if(finalResult){
						checkForgetEmailIndatabase();
					}else{
						checkUsernameIndatabase();
					}
					return null;
				}
			};
			new Thread(task).start();
			task.setOnSucceeded(_ -> {
				stackPane.setDisable(false);
				Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/forgot-password.png")).toString());
				forgetIcon.setImage(image);
				if (questionIndatabase && answerIndatabse) {
					Platform.runLater(() -> {
						forgetPasswordForm.setVisible(false);
						try {
							openChangePasswordController(forgetUsername.getText());
						} catch (IOException e) {
							nC.showNotificaitonSomethingWrong("failed to Register");
						}
					});
				}
			});
			Platform.runLater(() -> {
				Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/changeToLoading.gif")).toString());
				forgetIcon.setImage(image);
				stackPane.setDisable(true);
			});
		}

	}
    public void openChangePasswordController(String Gemail) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changePassword.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Password");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/reset-password.png")).toString());
		notificationsClass nCController=new notificationsClass();
		changePasswordController cPC=new changePasswordController(Gemail,stage,nCController);
		tempStage.hide();
		cPC.setAccountController(accountController.aC);
		stage.getIcons().add(image);
		stage.show();
		answerIndatabse=false;
		questionIndatabase=false;
		answerForget=false;
		questionFormat=false;
		stage.setOnCloseRequest(event -> {
			event.consume();
			Main main = new Main();
			main.logout(stage);
		});
    }

	private void checkForgetEmailIndatabase() {
		DatabaseConnection databaseCon = new DatabaseConnection(nC);
		if (databaseCon.checkEmailInDatabase(forgetUsername.getText())) {
			Platform.runLater(() -> {
				forgetUsernameError.setText("");
				if (answerForget && questionFormat) {
					databaseCon.checkAnswerAndQuestion(databaseCon.getUsernameByEmail(forgetUsername.getText()), findIndexQuestoinFormat(), forgetAnswer.getText());
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
			});
		} else {
			Platform.runLater(() -> {
				forgetUsernameError.setText("Not Found!");
				forgetUsername.setStyle("-fx-border-color: #0077b6");
			});
		}
	}

	private void checkUsernameIndatabase(){
		DatabaseConnection databaseCon = new DatabaseConnection(nC);
		if (databaseCon.checkUsername(forgetUsername.getText())) {
			Platform.runLater(() -> {
				forgetUsernameError.setText("");
				if (answerForget && questionFormat) {
					databaseCon.checkAnswerAndQuestion(forgetUsername.getText(), findIndexQuestoinFormat(), forgetAnswer.getText());
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
			});
		} else {
			Platform.runLater(() -> {
				forgetUsernameError.setText("Not Found!");
				forgetUsername.setStyle("-fx-border-color: #0077b6");
			});
		}
	}

	private void checkAnswerFormat() {
		forgetAnswer.setText(forgetAnswer.getText().trim());
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
		notificationsClass nCController =new notificationsClass();
		changeEmailController cEC=new changeEmailController(stage,nCController,loader.getController());
		tempStage.close();
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
		contactUsController aUC=new contactUsController(nC);
		aUC.setStgae(stage);
		tempStage.close();
		stage.show();
		stage.setResizable(false);
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
	public void setAllFormVisabiliityToBack() {
		aC.loginForm.setVisible(false);
		aC.forgetPasswordWayForm.setVisible(false);
		aC.forgetPasswordForm.setVisible(true);
	}
}
