package application;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class contactUsController implements Initializable {

	private static Stage tempStage;
	private static File tempFileFile;
	private static String fileName;
	private static File tempFileImage;
	private static String imageName;
	private static String tempResult;
	private static notificationsClass nC;

	@FXML
	private ImageView delete;

	@FXML
	private Label allowedSize;

	@FXML
	private AnchorPane contactUsAnchor;

	@FXML
	private ImageView contactUsImage;

	@FXML
	private TextField email;

	@FXML
	private Label errorEmail;

	@FXML
	private ImageView fileMessage;

	@FXML
	private Label imageError;

	@FXML
	private ImageView imageMessage;

	@FXML
	private TextArea message;

	@FXML
	private Label messageError;

	public contactUsController(){}
	public contactUsController(notificationsClass nC){
		contactUsController.nC=nC;
	}

    public void setStgae(Stage tempStage) {
    	contactUsController.tempStage=tempStage;
    }
    @FXML
    void backTomainForm(ActionEvent event) {
    	mainForm();
    }
	private void mainForm(){
		tempStage.close();
		accountController aC=new accountController();
		aC.showStage();
	}
    @FXML
    void sendMessage(ActionEvent event) {
		boolean emailIsOk = checkEmail();
        boolean messageIsOk= checkMessage();
		boolean imageIsOk = checkImage();
		if (emailIsOk && messageIsOk && imageIsOk) {
			Task<Void> sendEmailTask = new Task<>() {
				@Override
				protected Void call() throws Exception {
					String imageHTTP;
					String fileHTTP;
					firebase fb = new firebase();
					if (tempResult.equals("Image")) {
						imageHTTP = fb.uploadImageUrl(tempFileImage.getAbsolutePath(), STR."image_Contact/\{imageName}");
						DatabaseConnection databaseCon = new DatabaseConnection(nC);
						databaseCon.insertIntoContactUs(email.getText(), message.getText(), imageName, imageHTTP, false,"image_Contact");
					} else if (tempResult.equals("File")) {
						fileHTTP = fb.uploadFileUrl(tempFileFile.getAbsolutePath(), STR."file_Contact/\{fileName}");
						if (Objects.equals(fileHTTP, "0")){
							throw new Exception("check the Internet");
						}
						DatabaseConnection databaseCon = new DatabaseConnection(nC);
						databaseCon.insertIntoContactUs(email.getText(), message.getText(), fileName, fileHTTP, false,"file_Contact");
					}
					return null;
				}
			};

			sendEmailTask.setOnSucceeded(e -> {
				Platform.runLater(() -> {
					nC.showNotificaitonReplayYourEmail();
					Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/contactUs.png")).toString());
					contactUsImage.setImage(image);
					mainForm();
					contactUsAnchor.setDisable(false);
				});
			});

			sendEmailTask.setOnFailed(e -> {
				Platform.runLater(() -> {
					nC.showNotificaitonSomethingWrong("failed to send Message");
					contactUsAnchor.setDisable(false);
					Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/contactUs.png")).toString());
					contactUsImage.setImage(image);
				});
			});

			Platform.runLater(() -> {
				Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/changeToLoading.gif")).toString());
				contactUsImage.setImage(image);
				contactUsAnchor.setDisable(true);
			});

			new Thread(sendEmailTask).start();
		}
    }

	private boolean checkMessage() {
		if (message.getText().isEmpty()){
			messageError.setText("Empty!");
			message.setStyle("-fx-border-color: red;");
			return false;
		}else if (message.getText().length()>250){
			messageError.setText("Too Long!");
			message.setStyle("-fx-border-color: red;");
			return false;
		}
		else {
			messageError.setText("");
			message.setStyle("-fx-border-color: #0077b6;");
			return true;
		}
	}

	private boolean  checkImage() {
		if(!(imageName==null)) {
			imageError.setText("");
			tempResult="Image";
			return true;
		}
		else if (!(fileName==null)){
			imageError.setText("");
			tempResult="File";
			return true;
		}else{
			imageError.setText("Choose one of them!");
			return false;
		}
	}
	private boolean checkEmail() {
		email.setText(email.getText().trim());
		if (email.getText().isEmpty()) {
			errorEmail.setText("Empty!!!");
			email.setStyle("-fx-border-color: red;");
			return false;
		}else if (email.getText().length()>100){
			errorEmail.setText("Too long!!!");
			email.setStyle("-fx-border-color: red");
			return false;
		}
		else if (!(enterEmailController.isGmailAddress(email.getText()))) {
			errorEmail.setText("Not Found!!!");
			email.setStyle("-fx-border-color: red;");
			return false;
		}
		else {
			email.setStyle("-fx-border-color: #0077b6;");
			errorEmail.setText("");
			return true;
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
			imageName = selectedFile.getName();
			tempFileImage = selectedFile;
			fileMessage.setDisable(true);
			delete.setDisable(false);
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.textProperty().addListener((_, _, newValue) -> {
			int characterCount = newValue.trim().length();
			allowedSize.setText(String.valueOf(characterCount));
			if (characterCount >= 250 || characterCount==0) {
				allowedSize.setTextFill(Color.RED);
				if (characterCount==0) {
					messageError.setText("Empty!");
					message.setStyle("-fx-border-color: red;");
				}else {
					messageError.setText("Too Long!");
					message.setStyle("-fx-border-color: red;");
				}
			} else {
				allowedSize.setTextFill(Color.web("#00b4d8"));
				messageError.setText("");
				message.setStyle("-fx-border-color: #0077b6;");
			}
		});
	}
	@FXML
	void openFileDisplay(MouseEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open File");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Supported Files", "*.pptx", "*.pdf", "*.docx", "*.txt"));
			File selectedFile = fileChooser.showOpenDialog(tempStage);
			if (selectedFile != null) {
				tempFileFile = selectedFile;
				fileName = selectedFile.getName();
				Image image =new Image(Objects.requireNonNull(getClass().getResource(
						"/image/open_file.png")).toString());
				fileMessage.setImage(image);
				imageMessage.setDisable(true);
				delete.setDisable(false);
			}
		} catch (Exception e) {
			nC.showNotificationInvalideType();
		}
	}

	@FXML
	void delete(MouseEvent event) {
		delete.setDisable(true);
		Image image =new Image(Objects.requireNonNull(getClass().getResource(
				"/image/folders.png")).toString());
		fileMessage.setImage(image);
		fileMessage.setDisable(false);
		image=new Image(Objects.requireNonNull(getClass().getResource(
				"/image/imageSelector.png")).toString());
		imageMessage.setImage(image);
		imageMessage.setDisable(false);
	}
}
