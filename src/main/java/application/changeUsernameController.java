package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class changeUsernameController {
	private static Stage tempStage;

	@FXML
	private TextField changeUsername;

	@FXML
	private Label changeUsernameError;

	@FXML
	private ImageView changeUsernameIcon;

    @FXML
    void backToSettingForm(ActionEvent event) {
    	settingController sC=new settingController();
		tempStage.close();
		sC.showStage();
    }
	@FXML
	void changeUsername(ActionEvent event) throws IOException {
		if (changeUsername.getText().isEmpty()) {
			changeUsernameError.setText("Empty!");
		} else {
			accountController aC = new accountController();
			DatabaseConnection databaseCon = new DatabaseConnection();
			if (!(aC.checkPassToHaveDigit(changeUsername.getText()))) {
				changeUsernameError.setText("Digit & Letter!");
			} else if (databaseCon.checkToSameUsername(changeUsername.getText())) {
				changeUsernameError.setText("The Same Username!");
			} else {
				mainController mC = new mainController();
				notificationsClass nC=new notificationsClass();
				nC.showNotificaitonNewUsernameSetSuccussfully();
				databaseCon.setOldWithNewUsername(mC.getTempUsername(), changeUsername.getText());
				new mainController(changeUsername.getText());
				tempStage.close();
				mC.showStage();
			}
		}
	}

	public void setStage(Stage tempStage) {
		changeUsernameController.tempStage=tempStage;
	}

}
