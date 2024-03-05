package application;

import java.io.IOException;
import java.util.Objects;

import com.mysql.cj.protocol.x.XMessage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class changeEmailController {
	private static Stage tempStage;
	private static accountController aC;
	private static notificationsClass nC;
	private static changeEmailController cEC;
	private int remainingSeconds = 60;
	private static int countResend = 1;
	private static String messageTemp;
	
    @FXML
    private TextField code;

    @FXML
    private Label codeError;

    @FXML
    private TextField email;

    @FXML
    private Label emailError;

    @FXML
    private Button processButtonToSendCode;
    
    @FXML
    private Button sendCodeButton;

	@FXML
	private AnchorPane emailCodeFrame;

	@FXML
	private ImageView emailCodeIcon;

    @FXML
    private Label time;
    
    public changeEmailController(Stage stage,notificationsClass nC,changeEmailController cEC){
		changeEmailController.tempStage=stage;
		changeEmailController.nC=nC;
		changeEmailController.cEC =cEC;
	}
	public changeEmailController(){}

    public void setAccountController(accountController aC) {
    	changeEmailController.aC=aC;
    }
    @FXML
    void backToSetting(ActionEvent event) {
    	tempStage.close();
    	if (aC!=null) {
    		aC.showStage();
    		aC=null;
    	}else {
	    	settingController sC=new settingController();
	    	sC.showStage();
    	}
    }

    @FXML
    void process(ActionEvent event){

		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				code.setText(code.getText().trim());
				if (code.getText().isEmpty()) {
					codeError.setText("Empty!");
					code.setStyle("-fx-border-color: red;");
					messageTemp="Code can not Empty!";
					throw new Exception(messageTemp);
				} else if (!(emailSender.randomNumbers.equals(code.getText()))) {
					codeError.setText("Not Same!");
					code.setStyle("-fx-border-color: red;");
					messageTemp="Enter The Same Code!";
					throw new Exception(messageTemp);
				} else {
					codeError.setText("");
					code.setStyle("-fx-border-color: #0077b6;");
					tempStage.close();
				}
				return null;
			}
		};
		task.setOnFailed(_ -> {
			Platform.runLater(() -> {
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/forgetEmail.png")).toString());
				emailCodeIcon.setImage(image);
				emailCodeFrame.setDisable(false);
				nC.showNotificaitonSomethingWrong(messageTemp);
			});
		});

		task.setOnSucceeded(_ -> {
				Platform.runLater(() -> {
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/forgetEmail.png")).toString());
				emailCodeIcon.setImage(image);
				emailCodeFrame.setDisable(false);
				nC.showNotificaitonCheckYourEmail();
					if (aC!=null) {
                        try {
                            aC.openChangePasswordController(email.getText());
                        } catch (IOException e) {
                            throw new RuntimeException("Can Not Loading the FXML File");
                        }
                        aC=null;
					}else {
						DatabaseConnection databaseCon=new DatabaseConnection(nC);
						mainController mC=new mainController();
						nC.showNotificaitonNewEmailSetSuccussfully();
						databaseCon.setNewEmailToOldThroughUsername(mC.getTempUsername(),email.getText());
						mC.showStage();
					}
			});
		});
		new Thread(task).start();
		Platform.runLater(() -> {
			Image image = new Image(Objects.requireNonNull(getClass().getResource(
					"/image/changeToLoading.gif")).toString());
			emailCodeIcon.setImage(image);
			emailCodeFrame.setDisable(true);
		});

    }

    @FXML
    void sendCode(ActionEvent event) {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				code.setDisable(true);
				processButtonToSendCode.setDisable(true);
				email.setText(email.getText().trim());
				checkEmailToHave(email.getText());
				return null;
			}
		};

		task.setOnFailed(_ -> {
			Platform.runLater(() -> {
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/forgetEmail.png")).toString());
				emailCodeIcon.setImage(image);
				emailCodeFrame.setDisable(false);
				nC.showNotificaitonSomethingWrong(messageTemp);
			});
		});
		task.setOnSucceeded(_ -> {
			Platform.runLater(() -> {
				Image image = new Image(Objects.requireNonNull(getClass().getResource(
						"/image/forgetEmail.png")).toString());
				emailCodeIcon.setImage(image);
				emailCodeFrame.setDisable(false);
				nC.showNotificaitonCheckYourEmail();
			});
		});

		new Thread(task).start();
		Platform.runLater(() -> {
			Image image = new Image(Objects.requireNonNull(getClass().getResource(
					"/image/changeToLoading.gif")).toString());
			emailCodeIcon.setImage(image);
			emailCodeFrame.setDisable(true);
		});

    }
    private void checkEmailToHave(String Gemail) throws Exception {
		if (Gemail.isEmpty()) {
			Platform.runLater(() -> {
				emailError.setText("Empty!");
				email.setStyle("-fx-border-color: red;");
				messageTemp = "Email can not Empty!";
			});
			throw new Exception(messageTemp);
		}else if (!(enterEmailController.isGmailAddress(Gemail))) {
			Platform.runLater(() -> {
				emailError.setText("Not Found!!!");
				email.setStyle("-fx-border-color: red;");
				messageTemp = "Email Not Found Please Enter Again!";
			});
			throw new Exception(messageTemp);
		}
		else if (aC!=null) {
			if (checkEmailIndatabse(Gemail)){
				sendEmail();
			}
			else {
                Platform.runLater(() -> {
				emailError.setText("Not Found!!!");
				email.setStyle("-fx-border-color: red;");
                messageTemp="Email Not Found!";
                });
				throw new Exception(messageTemp);
			}

		}
		else {
			if (checkEmailIndatabse(Gemail)) {
                Platform.runLater(() -> {
                    emailError.setText("Please Enter different Email");
                    email.setStyle("-fx-border-color: red;");
                    messageTemp = "Email Must be Different";
                });
				throw new Exception(messageTemp);
			} else {
				sendEmail();
			}
		}
    }
	private void startTime() {
		countResend++;
		if (countResend <= 3) {
			remainingSeconds=60;
				final Timeline timeline = new Timeline();
				time.setText("01:00");
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
					remainingSeconds--;
					int minutes = remainingSeconds / 60;
					int seconds = remainingSeconds % 60;
					String formattedTime = String.format("%02d:%02d", minutes, seconds);
					time.setText(formattedTime);
					if (remainingSeconds == 0) {
						sendCodeButton.setDisable(false);
						timeline.stop();
					}
				}));
					timeline.setCycleCount(Timeline.INDEFINITE);
					timeline.play();
			}
		}

		private boolean checkEmailIndatabse(String Gemail){
		DatabaseConnection databaseCon=new DatabaseConnection();
            if (databaseCon.checkEmailInDatabase(Gemail)) {
                Platform.runLater(() -> {
                    emailError.setText("");
                    email.setStyle("-fx-border-color: #0077b6;");
                });
                return true;
            }
            else{
                return false;
            }
    }
	private void sendEmail() throws Exception {
		Platform.runLater(() -> {
					emailError.setText("");
					email.setStyle("-fx-border-color: #0077b6;");
				});
			emailSender emailSender = new emailSender();
			int result = emailSender.startSendMail(cEC.email.getText());
		if (result ==1) {
			Platform.runLater(() -> {
				nC.showNotificaitonNointernet();
				messageTemp = "Internet Need To Send Code!";
			});
			throw new Exception(messageTemp);
		}else if(result ==2){
			Platform.runLater(() -> {
				nC.showNotificaitonSomethingWrong("failed to check Email");
				messageTemp = "Failed to Send Email To Your Email!";
			});
			throw new Exception(messageTemp);
		}
		else {
			cEC.code.setDisable(false);
			cEC.processButtonToSendCode.setDisable(false);
			cEC.sendCodeButton.setDisable(true);
			startTime();
			nC.showNotificaitonCheckYourEmail();
		}
	}
}
