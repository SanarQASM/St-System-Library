package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class bookController implements  Initializable{

	@FXML
	private Label additionalInfo;

	@FXML
	private Label autherName;

	@FXML
	private ImageView bookImage;

	@FXML
	private Label bookName;

	@FXML
	private Label categoriesName;

	@FXML
	private Button deleteButton;

	@FXML
	private Button editButton;

	@FXML
	private HBox hboxThreeButton;

	@FXML
	private Label timeAdd;

	@FXML
	private VBox vboxAllcomponent;

	@FXML
	private HBox hboxParent;

	@FXML
	private Label year;

	private static bookInformations objectOfInformations;
	private static Stage tempStage;
	private static notificationsClass nC;
	@FXML
	void clickBookImage(MouseEvent event) {

	}

	@FXML
	void deleteButtonClick(MouseEvent event) {

	}

	@FXML
	void editButtonClick(MouseEvent event) throws IOException {
		openYesNoStage();
	}
	public void findParentId(){
		if (editButton.getParent() instanceof HBox) {
			HBox parentButton = (HBox) hboxParent.getParent();
			if (parentButton.getParent() instanceof VBox) {
				VBox parentHbox = (VBox) parentButton.getParent();
				if (parentHbox.getParent() instanceof HBox) {
					HBox parentVbox = (HBox) parentHbox.getParent();
					deleteDataFromDatabaseAndScrollPane(parentVbox.getId());
					System.out.println(parentVbox.getId());
				}
			}
		}
	}
	private void openYesNoStage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/deleteStage.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fxmlFile/application.css")).toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Delete Book");
		Image image =new Image(Objects.requireNonNull(getClass().getResource("/image/delete.png")).toString());
		stage.getIcons().add(image);
		bookController bC=new bookController();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initOwner(tempStage);
		stage.showAndWait();
		stage.show();
		stage.setResizable(false);
		new deleteStageController(bC,stage);
	}

	private void deleteDataFromDatabaseAndScrollPane(String id) {
		DatabaseConnection databaseCon=new DatabaseConnection(nC);
		databaseCon.deleteBookInformation(id);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			autherName.setText(objectOfInformations.getAuthorName());
			firebase firebaseCon=new firebase();
			InputStream filepath=firebaseCon.downloadFile(objectOfInformations.getBookUrl(), objectOfInformations.getDestinationsPath());
            Image image = new Image(filepath);
			bookImage.setImage(image);
			bookName.setText(objectOfInformations.getBookTitle());
			addBookInformation aBI=new addBookInformation();
			categoriesName.setText(aBI.findCatigoriesNameByIndex(objectOfInformations.getTemp_index()));
			timeAdd.setText(objectOfInformations.getTime());
			year.setText(objectOfInformations.getYearofWriting());
			if (objectOfInformations.getTmep_reward()==null){
				additionalInfo.setText(STR."(\{objectOfInformations.getYear()})\{objectOfInformations.getPublisher()
						}.\{objectOfInformations.getNumberofPage()}.\{objectOfInformations.getLanguage()}");
			}
			else {
				additionalInfo.setText(objectOfInformations.getTmep_reward());
			}
		hboxParent.setId(Integer.toString(objectOfInformations.getID()));
	}
	public bookController(bookInformations objectOfInformations,Stage tempStage,notificationsClass nC){
		bookController.nC=nC;
		bookController.tempStage=tempStage;
		bookController.objectOfInformations =objectOfInformations;
	}
	public bookController(){}
}
