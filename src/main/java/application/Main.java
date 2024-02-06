package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;

import java.util.Objects;

public class Main extends Application {
	long start;
	@Override
	public void start(Stage primaryStage) {
		start = System.nanoTime();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/account.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("ST Online Library System");
			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
			primaryStage.setScene(scene);
			Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/mainImage.png")).toString());
			primaryStage.getIcons().add(image);
			accountController aC = loader.getController();
			aC.setController(aC);
			enterCodeController eCC=new enterCodeController();
			eCC.setAccountController(aC);
			aC.setStage(primaryStage);
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);
			});
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			notificationsClass nC=new notificationsClass();
			nC.showNotificaitonSomethingWrong();
		}
	}

	public static void main(String[] args){
		launch(args);
	}

	public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			long Duration = (System.nanoTime() - start) / 1_000_000_000;
			System.out.println(STR."the duration is: \{Duration}");
			stage.close();
		}
	}

}
