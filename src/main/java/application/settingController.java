package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class settingController {

	private static Stage tempStage;
	
	public void setStage(Stage tempStage) {
		settingController.tempStage=tempStage;
	}
	
    @FXML
    void backToMainController(ActionEvent event) {
    	tempStage.close();
    	mainController mC=new mainController();
    	mC.showStage();
    }

    @FXML
    void changeEmail(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changeEmail.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(getClass().getResource("/fxmlFile/application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Email");
		Image image =new Image(getClass().getResource("/image/gmail.png").toString());
		changeEmailController cPC=new changeEmailController();
		tempStage.close();
		cPC.setStage(stage);
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changePassword.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(getClass().getResource("/fxmlFile/application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Password");
		Image image =new Image(getClass().getResource("/image/reset-password.png").toString());
		changePasswordController cPC=new changePasswordController();
		tempStage.close();
		mainController mC=new mainController();
		cPC.setTempWayToChange(mC.getTempUsername());
		cPC.setStage(stage);
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
    }

    @FXML
    void changeUsername(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changeUsername.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(getClass().getResource("/fxmlFile/application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Change Username");
		changeUsernameController cUC=new changeUsernameController();
		tempStage.hide();
		cUC.setStage(stage);
		Image image =new Image(getClass().getResource("/image/username.png").toString());
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main m = new Main();
			m.logout(stage);
		});
    }
	public void showStage() {
		tempStage.show();
	}

}
