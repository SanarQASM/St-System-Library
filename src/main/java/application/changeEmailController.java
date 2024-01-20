package application;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class changeEmailController {
	private static Stage tempStage;
	private static accountController aC;
	private int remainingSeconds = 60;
	private static int countResend = 1;
	
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
    private Label resendCodeAfter;

    @FXML
    private Label time;
    
    
    public void setStage(Stage tempStage) {
    	changeEmailController.tempStage=tempStage;
    }
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
    void process(ActionEvent event) throws IOException {
    	code.setText(code.getText().trim());
    	if (code.getText().isEmpty()) {
    		codeError.setText("Empty!");
			code.setStyle("-fx-border-color: red;");
    	}
    	else if(!(emailSender.randomNumbers.equals(code.getText()))){
    		codeError.setText("Not Same!");
			code.setStyle("-fx-border-color: red;");
    	}
    	else {
    		codeError.setText("");
			code.setStyle("-fx-border-color: #0077b6;");
			tempStage.close();
			if (aC!=null) {
				aC.openChangePasswordController(code.getText());
				aC=null;
			}else {
				DatabaseConnection databaseCon=new DatabaseConnection();
				mainController mC=new mainController();
				notificationsClass nC=new notificationsClass();
				nC.showNotificaitonNewEmailSetSuccussfully();
				databaseCon.setNewEmailToOldThroughUsername(mC.getTempUsername(),email.getText());
				mC.showStage();
				
			}
				
    	}
    }

    @FXML
    void sendCode(ActionEvent event) {
    		code.setDisable(true);
    		processButtonToSendCode.setDisable(true);
    	checkEmailToHave();
    }
    private void checkEmailToHave() {
    	email.setText(email.getText().trim());
    	DatabaseConnection databaseCon=new DatabaseConnection();
		if (email.getText().isEmpty()) {
			emailError.setText("Empty!");
			email.setStyle("-fx-border-color: red;");
		}else if (!(enterEmailController.isGmailAddress(email.getText()))) {
			emailError.setText("Not Found!!!");
			email.setStyle("-fx-border-color: red;");
		}
		else if (databaseCon.checkEmailInDatabase(email.getText())){
			emailError.setText("The Same Old Email!!!");
			email.setStyle("-fx-border-color: red;");
		}
		else {
			emailError.setText("");
			email.setStyle("-fx-border-color: #0077b6;");
			emailSender emailSender=new emailSender();
			int result =emailSender.startSendMail(email.getText());
			if (result ==1) {
				notificationsClass nC=new notificationsClass();
				nC.showNotificaitonNointernet();
			}else if(result ==2){
				notificationsClass nC=new notificationsClass();
				nC.showNotificaitonSomethingWrong();
			}
			else {
				code.setDisable(false);
	    		processButtonToSendCode.setDisable(false);
	    		sendCodeButton.setDisable(true);
	    		startTime();
				notificationsClass nC=new notificationsClass();
				nC.showNotificaitonCheckYourEmail();
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
}
