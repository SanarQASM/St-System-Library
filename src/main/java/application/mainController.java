package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainController implements Initializable {
	
	@FXML
	private AnchorPane TopBookForm;

	@FXML
	private AnchorPane browseForm;

    @FXML
    private ScrollPane srcollPane;
    
	@FXML
	private HBox browseTab;

	@FXML
	private HBox categoriesTab;

	@FXML
	private Label countBook;

	@FXML
	private Label reviewedBooks;

	@FXML
	private HBox favoritesTab;

	@FXML
	private GridPane gridPane;

	@FXML
	private HBox hBoxScrollPane;

	@FXML
	private HBox historyTab;

	@FXML
	private AnchorPane mainForm;

	@FXML
	private GridPane otherTobBookGridPane;

	@FXML
	private Pane paneNothingUploadedByOther;

	@FXML
	private Pane paneNothingUploadedByYour;

	@FXML
	private HBox readingTab;

	@FXML
	private Label resultSearch;

	@FXML
	private Label resultSearchInTopBook;

	@FXML
	private AnchorPane searchInUploaded;

	@FXML
	private TextField searchInTopBook;

	@FXML
	private AnchorPane searchIntobBook;

	@FXML
	private TextField searchText;

	@FXML
	private HBox topBookTab;

	@FXML
	private AnchorPane uploadedForm;

	@FXML
	private HBox uploadedTab;

	@FXML
	private Label usernameLabel;

	@FXML
	private HBox yourShelvesTab;

	@FXML
	private GridPane yourTobBookGridPane;

	private static int count = 0;
	private static List<HBox> listOfHbox = new ArrayList<>();
	public static List<VBox> listOfVbox = new ArrayList<>();
	private static List<Character> lastDigit = new ArrayList<>();
	private static String tempUsernameAccount;
	private static Stage tempStage;
	
	public mainController(String text) {
		tempUsernameAccount = text;
	}

	public mainController() {
	}

	@FXML
	void createShelves(ActionEvent event) {
		bookController bC = new bookController();
		bC.printBothMap();
	}

	@FXML
	void searchClick(MouseEvent event) {
		List<HBox> listOfHboxSearch = new ArrayList<>();
		listOfHboxSearch.clear();

		if (!(searchText.getText().isEmpty()) && !bookController.nameMap.isEmpty()) {
			getAllLabelName();
			for (HBox eachHbox : listOfHbox) {
				for (char eachChar : lastDigit) {
					if (eachHbox.getId().charAt(eachHbox.getId().length() - 1) == eachChar) {
						listOfHboxSearch.add(eachHbox);
					}
				}
			}
			if (listOfHboxSearch.isEmpty()) {
				resultSearch.setText("Nothing Found!!!");
			} else {
				resultSearch.setText(STR."\{listOfHboxSearch.size()} Result Found.");
			}
			hBoxScrollPane.getChildren().clear();
			hBoxScrollPane.getChildren().setAll(listOfHboxSearch);
		} else if (!(searchText.getText().isEmpty()) || searchText.getText().isEmpty()) {
			resultSearch.setText("Nothing Found, Add Book.");
			resultSearch.setScaleY(1.1);
			resultSearch.setScaleX(1.1);
		} else {
			addAndGenerateIdForHbox();
		}
	}

	private void getAllLabelName() {
		Iterator<Entry<String, String>> iterator = application.bookController.nameMap.entrySet().iterator();
		lastDigit.clear();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			if (entry.getValue().contains(searchText.getText())) {
				String lastDigitOfEachElementInMap = entry.getKey();
				lastDigit.add(lastDigitOfEachElementInMap.charAt(lastDigitOfEachElementInMap.length() - 1));
			}
		}
	}

	@FXML
	public void addBookButton(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/addNewBook.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(getClass().getResource("/fxmlFile/application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("System Book St Library");
		Image image =new Image(getClass().getResource("/image/mainImageBookSystem.png").toString());
		addBookInformation aDI=new addBookInformation();
		aDI.setStage(stage);
		tempStage.hide();
		stage.getIcons().add(image);
		stage.show();
		stage.setResizable(false);
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main main = new Main();
			main.logout(stage);
		});
	}

	public void addAndGenerateIdForHbox() {
		hBoxScrollPane.getChildren().clear();
		for (HBox eachHbox : listOfHbox) {
			hBoxScrollPane.getChildren().add(eachHbox);
		}
	}

	public void deleteParentHbox(String parentId) {// id parent detawa ka damanawe bisring, datwanin ba kota zhmarash
													// bikain
		HBox findParent = null;// hboxek bo bakar henan
		int index = 0;
		for (HBox hbox : listOfHbox) {
			if (parentId.equals(hbox.getId())) {// kama hbox lanaw list haman id lagal hbox rashkraw haya
				findParent = hbox;// pashan aw hboxa bo naw hbox temp zyad bka
				break;
			}
			index++;
		}
		if (findParent != null) {// agar aw tempa batal be awa hich yake lawana maka wata hich hboxekman nya bo
									// rashkrdnawa
			listOfHbox.remove(index);
			int lastDigit = Character.getNumericValue(parentId.charAt(parentId.length() - 1));// zhmaray parent id ka la
																								// kota pit haya
			bookController bC = new bookController();
			bC.deleteThreeMap(lastDigit);// aw digita danerin bo rashkrnaway hamw aw informationanay ka la mapakan haya
			addAndGenerateIdForHbox();
			count--;// bo labelaka kami dakainawa
			if (count <= 0) {// agar count bwitawa 0 awa
				countBook.setText("No Book");
			} else {// agar zhmaray ktabakan 0 nya
				countBook.setText("Last add is: " + count);
			}
		}
	}

	public void addRecommand(int index) throws IOException {// bo zyad krdni vbox
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxmlFile/recommendItem.fxml"));
		VBox vboxParent = loader.load();
		vboxParent.setId("Vbox" + index);
		listOfVbox.add(vboxParent);
	}

	public void startAddingGrid(List<Integer> randomNumbers) {// listek la random number detawa ka komale zhmaray tedaya
																// ba pey har hboxek ka zyad krawa la book
		gridPane.getChildren().clear();// clear the grid pane
		gridPane.getRowConstraints().clear();
		gridPane.getColumnConstraints().clear();
		int row = 0;
		int column = 0;
		for (int i = 0; i < randomNumbers.size(); i++) {
			if (column >= 5) {
				column = 0;
				row++;
			}
			gridPane.add(listOfVbox.get(randomNumbers.get(i)), column++, row);// tawakw size randomnumber tawaw dabe atu
			// vbox rash bkawa ba pey index

		}
	}

	@FXML
	void log_out(MouseEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("We Will Do The Best🤍");
		if (alert.showAndWait().get() == ButtonType.OK) {
			tempStage.close();
			accountController aC=new accountController();
			aC.showStage();
		}
		
	}
	@FXML
	void setting(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFile/settingController.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(getClass().getResource("/fxmlFile/application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Setting");
		settingController sC=new settingController();
		tempStage.hide();
		sC.setStage(stage);
		Image image =new Image(getClass().getResource("/image/setting.png").toString());
		stage.getIcons().add(image);
		stage.show();
		stage.setOnCloseRequest(event1 -> {
			event1.consume();
			Main m = new Main();
			m.logout(stage);
		});
	}

	public String getTempUsername() {
		return tempUsernameAccount;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    srcollPane.setStyle("-fx-background-color: black;");
		usernameLabel.setText(tempUsernameAccount);
		HBox allHboxTab[] = { yourShelvesTab, readingTab, historyTab, favoritesTab, browseTab, categoriesTab,
				topBookTab, uploadedTab };
		for (HBox eachHbox : allHboxTab) {
			eachHbox.setStyle("-fx-background-color: transparent;" + "-fx-opacity: 1;");
		}
		uploadedTab.setStyle("-fx-background-color: #18f08a;" + "-fx-opacity:0.7;");
		updateReviewedBooks();
		updateMainFrame();
	}

	@FXML
	void referesh(MouseEvent event) {
		usernameLabel.setText(tempUsernameAccount);
		updateReviewedBooks();
	}

	@FXML
	void TopBookClicked(MouseEvent event) throws IOException {
		unselectedUploadedTab();
		// do this for all selected tab....
		selectTobBookTab();
	}

	private void selectTobBookTab() throws IOException {
		topBookTab.setStyle("-fx-background-color: #18f08a;" + "	 -fx-opacity:0.7;");
		uploadedForm.setVisible(false);
		searchInUploaded.setVisible(false);
		checkYourTopBook();
	}

	private void unselectedUploadedTab() {
		uploadedTab.setStyle("-fx-background-color: transparent;" + "-fx-opacity: 1;");
		TopBookForm.setVisible(true);
		searchIntobBook.setVisible(true);
	}

	@FXML
	void uploadedTabClicked(MouseEvent event) {
		unselectedTopBookTab();
		// do this for all selected tab....
		selectUploadedTab();
	}

	private void selectUploadedTab() {
		uploadedTab.setStyle("-fx-background-color: #18f08a;" + "	 -fx-opacity:0.7;");
		TopBookForm.setVisible(false);
		searchIntobBook.setVisible(false);
	}

	private void unselectedTopBookTab() {
		topBookTab.setStyle("-fx-background-color: transparent;" + "-fx-opacity: 1;");
		uploadedForm.setVisible(true);
		searchInUploaded.setVisible(true);
	}

	@FXML
	void searchInTobBookClicked(MouseEvent event) {
//	    	if (!(searchInTopBook.getText().isEmpty())) {
//	    		
//	    	}
		// do other task
	}

	private void checkYourTopBook() throws IOException {
		List<Integer> star4And5ID = new ArrayList<Integer>();
		Iterator<String> iterator2 = bookController.starLevelMap.keySet().iterator();
		while (iterator2.hasNext()) {
			String key = iterator2.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			int valueInStarMap = bookController.starLevelMap.get(key);
			if (valueInStarMap == 4 || valueInStarMap == 5) {
				star4And5ID.add(last);
			}
		}

		if (!(star4And5ID.size() == 0)) {
			addYourTobBookToGridPane(star4And5ID);
		}
	}

	private void addYourTobBookToGridPane(List<Integer> star4And5ID) throws IOException {
		List<AnchorPane> listOfAnchorPane = new ArrayList<AnchorPane>();
		for (int i = 0; i < star4And5ID.size(); i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxmlFile/yourTobBookScene.fxml"));
			new yourTopBookController(star4And5ID.get(i));
			AnchorPane anchorPane = loader.load();// loady main hbox dakat
			String countId = "anchorPane" + (i + 1);// lera String bo id nwe drust dakain
			anchorPane.setId(countId);// bo har hboxek id nwe daxl akain
			listOfAnchorPane.add(anchorPane);
		}
		paneNothingUploadedByYour.setVisible(false);
		yourTobBookGridPane.getChildren().clear();// clear the grid pane
		yourTobBookGridPane.getRowConstraints().clear();
		yourTobBookGridPane.getColumnConstraints().clear();
		int row = 0;
		int column = 0;
		for (int i = 0; i < listOfAnchorPane.size(); i++) {
			if (column >= 1) {
				column = 0;
				row++;
			}
			yourTobBookGridPane.add(listOfAnchorPane.get(i), column++, row);// tawakw size randomnumber tawaw dabe atu
																			// vbox rash bkawa ba pey index
		}
	}

	public void setStage(Stage tempStage) {
		mainController.tempStage=tempStage;
	}         
	public void showStage() {
		tempStage.show();
	}

	public void updateMainFrame() {
		DatabaseConnection databaseCon=new DatabaseConnection();
		int result=databaseCon.getNumberOfBook(tempUsernameAccount);
		if (!(result==0)){
			countBook.setText(STR."Last add is: \{result}");
		}
		else{
			countBook.setText("No Books");
		}
	}
	private void updateReviewedBooks() {
		DatabaseConnection databaseCon=new DatabaseConnection();
		reviewedBooks.setText(STR."Reviewed books: \{databaseCon.getNumberOfReviewedBooks(tempUsernameAccount)}");
	}
}
