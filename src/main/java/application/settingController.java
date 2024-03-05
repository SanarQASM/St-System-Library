package application;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class settingController {

	private static Stage tempStage;
	private static notificationsClass nC;

	public settingController(Stage tempStage,notificationsClass nC){
		settingController.tempStage=tempStage;
		settingController.nC=nC;
	}
	public settingController(){}
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
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Email");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/gmail.png")).toString());
		notificationsClass nCController=new notificationsClass();
		new changeEmailController(stage,nCController,loader.getController());
		tempStage.close();
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
		nC.showNotificationEnterEmail();
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changePassword.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Password");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/reset-password.png")).toString());
		mainController mC=new mainController();
		notificationsClass nCController =new notificationsClass();
		new changePasswordController(mC.getTempUsername(),stage,nCController);
		tempStage.close();
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
		nC.showNotificationsEnterNewPassword();
    }

    @FXML
    void changeUsername(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/changeUsername.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Change Username");
			notificationsClass nCContoller = new notificationsClass();
			nC.showNotificationsEnternewUsername();
			new changeUsernameController(stage, nCContoller);
			tempStage.hide();
			Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/username.png")).toString());
			stage.getIcons().add(image);
			stage.show();
			stage.setOnCloseRequest(event1 -> {
				event1.consume();
				Main m = new Main();
				m.logout(stage);
			});
		}catch(IOException e){
			e.printStackTrace();
		}

    }
	public void showStage() {
		tempStage.show();
	}

}
