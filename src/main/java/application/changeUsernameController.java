package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class changeUsernameController {
	private static Stage tempStage;
	private static notificationsClass nC;
	@FXML
	private TextField changeUsername;

	@FXML
	private Label changeUsernameError;

	public changeUsernameController(Stage stage,notificationsClass nC){
		changeUsernameController.tempStage=stage;
		changeUsernameController.nC=nC;
	}
	public changeUsernameController (){}
    @FXML
    void backToSettingForm(ActionEvent event) {
    	settingController sC=new settingController();
		tempStage.close();
		sC.showStage();
    }
	@FXML
	void changeUsername(ActionEvent event){
		if (changeUsername.getText().isEmpty()) {
			changeUsernameError.setText("Empty!");
			nC.showNotificaitonSomethingWrong("please Enter Username!");
		} else {
			accountController aC = new accountController();
			DatabaseConnection databaseCon = new DatabaseConnection(nC);
			if (!(aC.checkPassToHaveDigit(changeUsername.getText()))) {
				changeUsernameError.setText("Digit & Letter!");
				nC.showNotificaitonSomethingWrong("at least one Digit or Letter");
			} else if (databaseCon.checkToSameUsername(changeUsername.getText())) {
				changeUsernameError.setText("The Same Username!");
				nC.showNotificationsEnternewUsername();
			} else {
				mainController mC = new mainController();
				nC.showNotificaitonNewUsernameSetSuccussfully();
				databaseCon.setOldWithNewUsername(mC.getTempUsername(), changeUsername.getText());
				new mainController(changeUsername.getText(),nC);
				tempStage.close();
				mC.showStage();
			}
		}
	}

}
