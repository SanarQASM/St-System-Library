package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class enterCodeController implements Initializable {
	private static Stage tempStage;
	private static String tempEmail;
	private int remainingSeconds = 60;
	private static int countResend = 1;
	public static accountController aCC;
	@FXML
	private AnchorPane changePassword;

	@FXML
	private TextField code;

	@FXML
	private Label errorCode;

	@FXML
	private ImageView resendEmailImage;

	@FXML
	private Button enterCodeButton;

	@FXML
	private Button resendCodeButton;

	@FXML
	private Label time;
	
    @FXML
    private Label resendCodeAfter;
    
	@FXML
	void backToEnterEmail(ActionEvent event) {
		enterEmailController eEC = new enterEmailController();
		eEC.showStage();
		tempStage.close();
	}

	@FXML
	void enterCodeButton(ActionEvent event) {
		if (code.getText().length()>=15) {
			errorCode.setText("Too Long!!!");
			code.setStyle("-fx-border-color: red;");
		}else if (!(code.getText().isEmpty())) {
			if (emailSender.randomNumbers.equals(code.getText())) {
				errorCode.setText("");
				code.setStyle("-fx-border-color:red;");
					aCC.showStage();
				aCC.registerDone();
				tempStage.close();
			} 
			else {
				errorCode.setText("Not Same!");
				code.setStyle("-fx-border-color: red;");
			}
		} 
		else {
			errorCode.setText("Empty!");
			code.setStyle("-fx-border-color: red;");
		}

	}

	@FXML
	void resendEmailButton(ActionEvent event) {
		resendCodeButton.setDisable(true);
		countResend++;
				emailSender eSender = new emailSender();
		if (countResend <= 3) {
			remainingSeconds=60;
			eSender.startSendMail(tempEmail);
				final Timeline timeline = new Timeline();
				time.setText("01:00");
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
					remainingSeconds--;
					int minutes = remainingSeconds / 60;
					int seconds = remainingSeconds % 60;
					String formattedTime = String.format("%02d:%02d", minutes, seconds);
					time.setText(formattedTime);
					if (remainingSeconds == 0) {
						resendCodeButton.setDisable(false);
						timeline.stop();
					}
				}));
					timeline.setCycleCount(Timeline.INDEFINITE);
					timeline.play();
		} else {
			notificationsClass nC=new notificationsClass();
			nC.showNotificationTooManyTry();
			countResend=1;
			tempStage.close();
		}
	}

	public void setStage(Stage stage) {
		tempStage = stage;
	}

	public void setEmail(String email) {
		tempEmail = email;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
			remainingSeconds--;
			int minutes = remainingSeconds / 60;
			int seconds = remainingSeconds % 60;
			String formattedTime = String.format("%02d:%02d", minutes, seconds);
			time.setText(formattedTime);
			if (remainingSeconds == 0) {
				resendCodeButton.setDisable(false);
				timeline.stop();
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		notificationsClass nC=new notificationsClass();
		nC.showNotificaitonCheckYourEmail();
	}

	public String getTempEmail() {
		return tempEmail;
	}
	public void setAccountController(accountController aCC) {
		enterCodeController.aCC=aCC;
	}
	public accountController getAccountController() {
		return aCC;
	}
}
