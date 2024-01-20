package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class contactUsController {

	private static Stage tempStage;
	
    @FXML
    private TextField email;

    @FXML
    private Label errorEmail;

    @FXML
    private ImageView imageMessage;

    @FXML
    private TextArea message;

    @FXML
    private Label messageError;

    @FXML
    private Label imageError;

    private static boolean emailIsOk;
    private static boolean messageIsOk;
    private static boolean imageIsOk;
    public void setStgae(Stage tempStage) {
    	contactUsController.tempStage=tempStage;
    }
    @FXML
    void backToLoginForm(ActionEvent event) {
    	tempStage.close();
    	accountController aC=new accountController();
    	aC.showStage();
    }

    @FXML
    void sendMessage(ActionEvent event) {
    	checkEmail();
    	checkMessage();
    	checkImage();
    	
    }
	private void checkImage() {
		if (!(imageMessage.getImage().getUrl().toString().equals(getClass().getResource("/image/imageSelector.png").toString()))) {
			imageError.setText("");
			imageIsOk=true;
		}
		else{
			imageError.setText("Empty!!!");
		}
		if (emailIsOk&&messageIsOk&&imageIsOk) {
    		emailSender eM=new emailSender();
    		int result=eM.sendEmailToForget(email.getText(),message.getText(),imageMessage.getImage().getUrl());
    		if (result==1) {
    			notificationsClass nC=new notificationsClass();
    			nC.showNotificaitonNointernet();
    		}
    		else if (result ==-1) {
    			notificationsClass nC=new notificationsClass();
    			nC.showNotificaitonReplayYourEmail();
    		}
    		else {
    			notificationsClass nC=new notificationsClass();
    			nC.showNotificaitonSomethingWrong();
    		}
    	}
	}
	private void checkMessage() {
		if (message.getText().isEmpty()) {
			messageError.setText("Empty!");
		    message.setStyle("-fx-border-color: red;");
		}
		else {
			messageError.setText("");
		    message.setStyle("-fx-border-color: #0077b6;");
			messageIsOk=true;
		}
	}
	private void checkEmail() {
		email.setText(email.getText().trim());
		if (email.getText().isEmpty()) {
			errorEmail.setText("Empty!!!");
			email.setStyle("-fx-border-color: red;");
		}
		else if (!(enterEmailController.isGmailAddress(email.getText()))) {
			errorEmail.setText("Not Found!!!");
			email.setStyle("-fx-border-color: red;");
		}else {
			email.setStyle("-fx-border-color: #0077b6;");
			errorEmail.setText("");
			emailIsOk=true;
		}
	}
    @FXML
    void openFilePeker(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
        	Image image = new Image(selectedFile.toURI().toString());
            imageMessage.setImage(image);
        }
    }
}
