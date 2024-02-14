package application;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class changePasswordController {

	@FXML
	private TextField confirmationPassText;

	@FXML
	private Label confirmationPasswordError;

	@FXML
	private PasswordField confirmationPasswordPass;

	@FXML
	private Label newPasswordError;

	@FXML
	private PasswordField newPasswordPass;

	@FXML
	private TextField newPasswordText;

	@FXML
	private ImageView showPasswordInChange;

    private static String tempWayToChange;
    private static Stage stage;
	private boolean confirmPassEntered;
	private boolean newPassEntered;
	private boolean passwordIsChanged;
	private static accountController accontCont;
	
	public void setTempWayToChange(String tempWayToChange) {
		changePasswordController.tempWayToChange=tempWayToChange;
	}
	public void setAccountController(accountController accountCont) {
		changePasswordController.accontCont=accountCont;
	}
	@FXML
	void backFromChangePassword(ActionEvent event) {
		newPasswordText.setText("");
		newPasswordPass.setText("");
		newPasswordError.setText("");
		confirmationPasswordError.setText("");
		confirmationPassText.setText("");
		confirmationPasswordPass.setText("");
		stage.close();
		if(accontCont!=null) {	
			accountController aC=new accountController();
			aC.showStage();
			aC.setAllFormVisabiliityToBack();
			accontCont=null;
		}else {
			settingController sC=new settingController();
			sC.showStage();
		}
	}

	@FXML
	void changePassowrd(ActionEvent event) throws IOException {
		checkHaveNewPass();
		checkHaveConfirmPass();
		if (!(newPasswordPass.isVisible())) {
			newPasswordPass.setText(newPasswordText.getText());
			confirmationPasswordPass.setText(confirmationPassText.getText());
		}
					if (newPassEntered && confirmPassEntered) {
						accountController aC=new accountController();
						if (!(aC.checkPassToHaveDigit(newPasswordPass.getText()))) {
							newPasswordError.setText("Letter and Digit!");
						} else {
							newPasswordError.setText("");
							if (bothPassIsEqual()) {
                                try {
									setNewPassToOldPass();
                                } catch (IOException e) {
                                    notificationsClass nC=new notificationsClass();
									nC.showNotificaitonSomethingWrong();
                                }
                            } else {
								confirmationPasswordError.setText("Is not The Same!");
							}
						}
					}
					if (passwordIsChanged) {
						stage.close();
						if (accontCont != null) {
							accountController aC = new accountController();
							aC.showStage();
							aC.setAllFormVisabiliity();
							accontCont = null;
						} else {
							settingController sC = new settingController();
							sC.showStage();
						}
						newPasswordPass.setText("");
						newPasswordText.setText("");
						confirmationPasswordPass.setText("");
						confirmationPassText.setText("");
						notificationsClass nC = new notificationsClass();
						nC.showNotificaitonPasswordChangeSuccussfuly();
		confirmPassEntered = false;
		newPassEntered = false;
		passwordIsChanged = false;
		}
	}

	private boolean bothPassIsEqual() {
		return (newPasswordPass.getText().equals(confirmationPasswordPass.getText()));
	}

	private void setNewPassToOldPass() throws IOException {
		DatabaseConnection databaseCon = new DatabaseConnection();
		if (enterEmailController.isGmailAddress(tempWayToChange)) {
			if (databaseCon.checkToSameOldPassWithEmail(tempWayToChange, newPasswordPass.getText())) {
				newPasswordError.setText("Same Old Password!");
			} else {
				databaseCon.setNewToOldPasswordWithEmail(tempWayToChange, newPasswordPass.getText());
				passwordIsChanged = true;
			}
		}
		else {
			if (databaseCon.checkToSameOldPass(tempWayToChange, newPasswordPass.getText())) {
				newPasswordError.setText("Same Old Password!");
			} else {
				databaseCon.setNewToOldPassword(tempWayToChange, newPasswordPass.getText());
				passwordIsChanged = true;
			}
		}
	}

	private void checkHaveConfirmPass() {
		if (confirmationPasswordPass.getText().isEmpty()) {
			confirmationPasswordError.setText("Empty!");
		}
		else if (confirmationPasswordPass.getText().length()>=20) {
			confirmationPasswordError.setText("Too Long!!!");
			confirmationPasswordPass.setStyle("-fx-border-color: red;");
		}
		else {
			confirmationPasswordError.setText("");
			confirmationPasswordPass.setStyle("-fx-border-color: #0077b6;");
			confirmPassEntered = true;
		}
	}

	private void checkHaveNewPass() {
		if (newPasswordPass.getText().isEmpty()) {
			newPasswordError.setText("Empty!");
		}else if (newPasswordPass.getText().length()>=20) {
			newPasswordError.setText("Too Long!!!");
			newPasswordPass.setStyle("-fx-border-color: red;");
		} 
		else {
			newPasswordError.setText("");
			newPasswordPass.setStyle("-fx-border-color: #0077b6;");
			newPassEntered = true;
		}
	}

	@FXML
	void newShowPass(MouseEvent event) {
		if (showPasswordInChange.getImage().getUrl().equals(getClass().getResource("/image/showPass.png").toString())) {
			newPasswordPass.setVisible(false);
			newPasswordText.setVisible(true);
			confirmationPasswordPass.setVisible(false);
			confirmationPassText.setVisible(true);
			newPasswordText.setText(newPasswordPass.getText());
			confirmationPassText.setText(confirmationPasswordPass.getText());
			Image image = new Image(getClass().getResource("/image/hidePass.png").toString());
			showPasswordInChange.setImage(image);
		} else {
			newPasswordPass.setVisible(true);
			newPasswordText.setVisible(false);
			confirmationPasswordPass.setVisible(true);
			confirmationPassText.setVisible(false);
			confirmationPasswordPass.setText(confirmationPassText.getText());
			newPasswordPass.setText(newPasswordText.getText());
			Image image = new Image(getClass().getResource("/image/showPass.png").toString());
			showPasswordInChange.setImage(image);
		}
	}
	public void setStage(Stage stage) {
		changePasswordController.stage=stage;
	}

}
