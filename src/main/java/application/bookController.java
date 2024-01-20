package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class bookController implements Initializable {
	private Stage stage;
	private boolean checkClicked = false;
	private boolean checkStar = false;
	public static Map<String, Integer> starLevelMap = new HashMap<>();
	public static Map<String, String> imageURLMap = new HashMap<>();
	public static Map<String, String> nameMap = new HashMap<>();
	public static Map<String, String> categoriesMap = new HashMap<>();
	private List<String> parentStarId = new ArrayList<>();
	private String imageTemp = "";
	private String nameTemp = "";
	private String StarIdTemp = "";
	private int valueStarTemp = 0;
	private String categoriesType[] = new String[20];

	@FXML
	private ComboBox<String> categories;

	@FXML
	private Button editButton;

	@FXML
	private Button submitButton;

	@FXML
	private Button deleteButton;

	@FXML
	private HBox AddBookHbox;

	@FXML
	private HBox hboxThreeButton;

	@FXML
	private VBox vboxAllcomponent;

	@FXML
	private ImageView bookImage;

	@FXML
	private TextField bookName;

	@FXML
	private Label rewriteBookName;

	@FXML
	private ImageView starFive;

	@FXML
	private HBox hboxStar;

	@FXML
	private ImageView starFour;

	@FXML
	private ImageView starOne;

	@FXML
	private ImageView starThree;

	@FXML
	private ImageView starTwo;

	private mainController mainController;
	private ImageView imageListStar[] = { starOne, starTwo, starThree, starFour, starFive };

	public mainController getMainController() {
		return mainController;
	}

	public void setMainController(mainController mainController) {
		this.mainController = mainController;
	}

	@FXML
	void clickBookImage(MouseEvent event) {// halbzhardni imagek bapey hande type
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));

		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			Image image = new Image(file.toURI().toString(), 136, 136, false, true);
			bookImage.setImage(image);// bo naw bookimage day danein
			imageTemp = file.toURI().toString();// bo zyad krdni bo naw map
		}
	}

	@FXML
	void starOneHover(MouseEvent event) {
		if (!checkClicked) {
			starOne.setOpacity(1.0);
		}
	}

	@FXML
	void starTwoHover(MouseEvent event) {
		if (!checkClicked) {
			starTwo.setOpacity(1.0);
			starOne.setOpacity(1.0);
		}
	}

	@FXML
	void starThreeHover(MouseEvent event) {
		if (!checkClicked) {
			starThree.setOpacity(1.0);
			starTwo.setOpacity(1.0);
			starOne.setOpacity(1.0);
		}
	}

	@FXML
	void starFourHover(MouseEvent event) {
		if (!checkClicked) {
			starThree.setOpacity(1.0);
			starTwo.setOpacity(1.0);
			starOne.setOpacity(1.0);
			starFour.setOpacity(1.0);
		}
	}

	@FXML
	void starFiveHover(MouseEvent event) {
		if (!checkClicked) {
			starThree.setOpacity(1.0);
			starTwo.setOpacity(1.0);
			starOne.setOpacity(1.0);
			starFour.setOpacity(1.0);
			starFive.setOpacity(1.0);
		}
	}

	@FXML
	void starOneExit(MouseEvent event) {
		if (!checkClicked) {
			starOne.setOpacity(0.4);
		}
	}

	@FXML
	void starTwoExit(MouseEvent event) {
		if (!checkClicked) {
			starTwo.setOpacity(0.4);
			starOne.setOpacity(0.4);
		}
	}

	@FXML
	void starThreeExit(MouseEvent event) {
		if (!checkClicked) {
			starThree.setOpacity(0.4);
			starTwo.setOpacity(0.4);
			starOne.setOpacity(0.4);
		}
	}

	@FXML
	void starFourExit(MouseEvent event) {
		if (!checkClicked) {
			starFour.setOpacity(0.4);
			starThree.setOpacity(0.4);
			starTwo.setOpacity(0.4);
			starOne.setOpacity(0.4);
		}
	}

	@FXML
	void starFiveExit(MouseEvent event) {
		if (!checkClicked) {
			starFive.setOpacity(0.4);
			starFour.setOpacity(0.4);
			starThree.setOpacity(0.4);
			starTwo.setOpacity(0.4);
			starOne.setOpacity(0.4);
		}
	}

	@FXML
	void starOneAction(MouseEvent event) {
		starOne.setOpacity(1.0);
		starTwo.setOpacity(0.4);
		starThree.setOpacity(0.4);
		starFour.setOpacity(0.4);
		starFive.setOpacity(0.4);
		checkStar = true;
		valueStarTemp = 1;
		checkClicked = true;
		setIdForStar(starOne.getId(), starOne);
	}

	@FXML
	void starTwoAction(MouseEvent event) {// agar click la star 2 bkat
		starTwo.setOpacity(1.0);
		starOne.setOpacity(1.0);
		starThree.setOpacity(0.4);
		starFour.setOpacity(0.4);
		starFive.setOpacity(0.4);
		checkStar = true;
		checkClicked = true;
		valueStarTemp = 2;
		setIdForStar(starTwo.getId(), starTwo);
	}

	@FXML
	void starThreeAction(MouseEvent event) {
		starTwo.setOpacity(1.0);
		starOne.setOpacity(1.0);
		starThree.setOpacity(1.0);
		starFour.setOpacity(0.4);
		starFive.setOpacity(0.4);
		checkStar = true;
		checkClicked = true;
		valueStarTemp = 3;
		setIdForStar(starThree.getId(), starThree);
	}

	@FXML
	void starFourAction(MouseEvent event) {
		starTwo.setOpacity(1.0);
		starOne.setOpacity(1.0);
		starThree.setOpacity(1.0);
		starFour.setOpacity(1.0);
		starFive.setOpacity(0.4);
		checkStar = true;
		checkClicked = true;
		valueStarTemp = 4;
		setIdForStar(starFour.getId(), starFour);
	}

	@FXML
	void starFiveAction(MouseEvent event) {
		starTwo.setOpacity(1.0);
		starOne.setOpacity(1.0);
		starThree.setOpacity(1.0);
		starFour.setOpacity(1.0);
		starFive.setOpacity(1.0);
		checkStar = true;
		checkClicked = true;
		valueStarTemp = 5;
		setIdForStar(starFive.getId(), starFive);
	}

	private boolean checkImageView() {// bo zanini away image daxl krawa yan na
		return bookImage.getImage().getUrl().equals(getClass().getResource("/image/cover.png").toString());
	}

	private boolean checkStar() {// bo zanini away aya star click kraya yan na
		return !checkStar;
	}

	private boolean checkBookName() {// bo zanini aya name daxl krawa yan na
		return bookName.getText().isEmpty();
	}

	@FXML
	void deleteButtonClick(MouseEvent event) {// delele button when clicked
		String mainHBox = null;
		if (deleteButton.getParent() instanceof HBox) {
			HBox parentHBox = (HBox) deleteButton.getParent();
			if (parentHBox.getParent() instanceof VBox) {
				VBox parentVBox = (VBox) parentHBox.getParent();
				if (parentVBox.getParent() instanceof HBox) {
					HBox parentMainHBox = (HBox) parentVBox.getParent();
					mainHBox = parentMainHBox.getId();// id aw button deletay ka click kraya
				}
			}
			mainController.deleteParentHbox(mainHBox);// aw stringa id buttony deleta wata ba pey id hbox delete dakain
			recommendItem rI = new recommendItem();
			rI.removeVboxParent(mainHBox.charAt(mainHBox.length() - 1));// kota digit danere bo delete krdni vbox
			mainController.startAddingGrid(generateRandomNumbers());// dubara ba vboxakan zyad bkat bo naw grid ba pey
																	// listek la random number ka disan barham detawa
		}
	}

	@FXML
	void editButtonClick(MouseEvent event) {
		submitButton.setDisable(false);
		editButton.setDisable(true);
		hboxStar.setDisable(false);
		bookName.setDisable(false);
		bookImage.setDisable(false);
		starTwo.setDisable(false);
		starOne.setDisable(false);
		starThree.setDisable(false);
		starFour.setDisable(false);
		starFive.setDisable(false);
		categories.setDisable(false);
	}

	@FXML
	void submitButtonClick(MouseEvent event) throws IOException {
		// set text to label
		if (!(bookName.getText().equals(null))) {// aw label la xuar text name haya
			rewriteBookName.setText(bookName.getText());
			nameTemp = bookName.getText();
		}
		boolean image = checkImageView();
		boolean name = checkBookName();
		boolean star = checkStar();
		boolean categoriesCheck = checkCategories();
		if ((image == true) && (name == false && star == false && categoriesCheck == false)) {// agar bas image batal
																								// bet
			scaleImage();
		} else if ((name == true) && (image == false && star == false && categoriesCheck == false)) {// agar bas name
																										// batal bet
			scaleName();
		} else if ((star == true) && (name == false && image == false && categoriesCheck == false)) {// agar bas star
																										// batal bet
			scaleStar();
		} else if ((categoriesCheck == true) && (name == false && image == false && star == false)) {// agar bas
																										// categories
																										// batal bet
			scaleCategories();
		} else if ((image == true && name == true) && (star == false && categoriesCheck == false)) {// agar image w name
																									// batal bet
			scaleName();
			scaleImage();
		} else if ((star == true && name == true) && (image == false && categoriesCheck == false)) {// agar name w star
																									// batal bet
			scaleName();
			scaleStar();
		} else if ((categoriesCheck == true && name == true) && (image == false && star == false)) {// agar name w
																									// categories batal
																									// bet
			scaleName();
			scaleCategories();
		} else if ((star == true && image == true) && (name == false && categoriesCheck == false)) {// agar image w star
																									// batal bet
			scaleImage();
			scaleStar();
		} else if ((categoriesCheck == true && image == true) && (name == false && star == false)) {// agar image w
																									// categories batal
																									// bet
			scaleImage();
			scaleCategories();
		} else if ((star == true && categoriesCheck == true) && (name == false && image == false)) {// agar categories w
																									// star batal bet
			scaleCategories();
			scaleStar();
		} else if ((name == true && categoriesCheck == true && image == true) && (star == false)) {// agar categories w
																									// name w iamge
																									// batal bet
			scaleCategories();
			scaleName();
			scaleImage();
		} else if ((name == true && categoriesCheck == true && star == true) && (image == false)) {// agar categories w
																									// name w star batal
																									// bet
			scaleCategories();
			scaleName();
			scaleStar();
		} else if ((name == true && star == true && image == true) && (categoriesCheck == false)) {// agar star w name w
																									// iamge batal bet
			scaleStar();
			scaleName();
			scaleImage();
		} else if ((star == true && categoriesCheck == true && image == true) && (name == false)) {// agar categories w
																									// star w iamge
																									// batal bet
			scaleCategories();
			scaleStar();
			scaleImage();
		} else if (image == true && name == true && star == true && categoriesCheck == true) {// agar hamwyan batal bn
																								// batal bet
			scaleName();
			scaleStar();
			scaleImage();
			scaleCategories();
		} else {// agar hichyan batal nabet
			submitButton.setDisable(true);// submit batn bkawa
			editButton.setDisable(false);// edit batn daxa
			// hamw hamw informationakan bkawa
			hboxStar.setDisable(true);
			bookName.setDisable(true);
			bookImage.setDisable(true);
			starTwo.setDisable(true);
			starOne.setDisable(true);
			starThree.setDisable(true);
			starFour.setDisable(true);
			starFive.setDisable(true);
			categories.setDisable(true);
			// zyad krdni id bo image w star
			setIdForImage();
			setIdForName();
			setIdForComboBoxCategorise();
			// zyad krdni information bo har mapek
			setImageUrl();
			setNameBook();
			setStarLevel();
			setCategories();
			recommendItem rI = new recommendItem();
			int in = getParentSubmitButton();// kota digity har submit buttonek pashan -1 wata agar id 1 bu daikata 0 ba
												// kurti wak index bakar det
			rI.settingDataWithoutAddingVbox(in);// datakan add akat bo vbox ba pey har indexek agar hboxy 3 bw datakan
												// zyad akan bo aw vboxay la listaka la index 2 ama ka dakata vbox id 3
			mainController.startAddingGrid(generateRandomNumbers());// dubara vbox zyad bka ba pey har listek la random
																	// number
			// dujar bang dakre jarak la delete jarek la submit
		}
	}

	private void setCategories() {
		int getCategories = getItemFromCategories();
		String getCategoriesItem = categoriesType[getCategories];
		if (!(categoriesMap.containsKey(categories.getId()))) {
			categoriesMap.put(categories.getId(), getCategoriesItem);
		} else if (categoriesMap.containsKey(categories.getId())) {
			categoriesMap.put(categories.getId(), getCategoriesItem);
		}
	}

	private int getItemFromCategories() {
		try {
			return categories.getSelectionModel().getSelectedIndex();
		} catch (Exception e) {
			return -1;
		}
	}

	private void scaleCategories() {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), categories);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		scaleTransition.play();
	}

	private boolean checkCategories() {
		int getIndexCategor = categories.getSelectionModel().getSelectedIndex();
		return (getIndexCategor == -1);
	}

	private void setIdForComboBoxCategorise() {
		if (categories.getId().equals("categories")) {
			categories.setId(categories.getId() + getIdParentImage());
		}
	}

	private List<Integer> generateRandomNumbers() {// barham henani listek la zhmara
		List<Integer> randomNumbers = new ArrayList<>();
		int sizeList = nameMap.size();// between what to what generate the number nmuna agar size mapaka 3 bw
		int size = getSize(sizeList);// بۆ فۆرەکەی ناو گێنێرێتە بۆ ئەوەی چەند بروا ئەگەر یەکە بەس با یەک بروا ئەگەر
										// دوە با دوو ژمارە بەرهەم بێنێ جا تا کۆتای.... madam size mapaka seya kawaya
										// yak zhmara barham bena la newan 0 bo 2
		Random random = new Random();
		randomNumbers.clear();
		for (int i = 0; i < size; i++) {
			int randomNumber;
			do {
				randomNumber = random.nextInt(sizeList); // Change 100 to the desired range of your numbers
			} while (contains(randomNumbers, randomNumber));

			randomNumbers.add(randomNumber);
		}
		return randomNumbers;
	}

	public static boolean contains(List<Integer> randomNumbers, int number) {
		for (int element : randomNumbers) {
			if (element == number) {
				return true;
			}
		}
		return false;
	}

	public int getSize(int sizeList) {
		if (sizeList == 1 || sizeList == 2 || sizeList == 3) {
			return 1;
		} else {
			return (int) sizeList / 2;
		}
	}

	private int getParentSubmitButton() {
		int in = 0;
		if (submitButton.getParent() instanceof HBox) {
			HBox parentHBox = (HBox) submitButton.getParent();
			if (parentHBox.getParent() instanceof VBox) {
				VBox parentVBox = (VBox) parentHBox.getParent();
				if (parentVBox.getParent() instanceof HBox) {
					HBox parentMainHBox = (HBox) parentVBox.getParent();
					char index = parentMainHBox.getId().toString().charAt(parentMainHBox.getId().length() - 1);
					String stringValue = String.valueOf(index);
					in = Integer.parseInt(stringValue);
				}
			}
		}
		return in;
	}

	private void setIdForName() {
		if (bookName.getId().equals("bookName")) {
			bookName.setId("bookName" + getIdParentName());
		}
	}

	private char getIdParentName() {
		if (bookName.getParent() instanceof VBox) {
			VBox parentVBox = (VBox) bookName.getParent();
			if (parentVBox.getParent() instanceof HBox) {
				HBox parentHBox = (HBox) parentVBox.getParent();
				return parentHBox.getId().toString().charAt(parentHBox.getId().length() - 1);
			}
		}
		return ' ';
	}

	private void setIdForImage() {
		if (bookImage.getId().equals("bookImage")) {
			bookImage.setId("bookImage" + getIdParentImage());
		}
	}

	private char getIdParentImage() {

		if (bookImage.getParent() instanceof VBox) {
			VBox parentVBox = (VBox) bookImage.getParent();
			if (parentVBox.getParent() instanceof HBox) {
				HBox parentHBox = (HBox) parentVBox.getParent();
				return parentHBox.getId().toString().charAt(parentHBox.getId().length() - 1);
			}
		}

		return ' ';
	}

	private void setIdForStar(String id, ImageView star) {
		String tempParent = findParentStar(star);
		if (checkHaveParentStar(tempParent)) {
			parentStarId.add(tempParent);
			StarIdTemp = id + tempParent.charAt(tempParent.length() - 1);
		}
	}

	private String findParentStar(ImageView Star) {
		String mainHBox = "";
		if (Star.getParent() instanceof HBox) {
			HBox parentHBox = (HBox) Star.getParent();
			if (parentHBox.getParent() instanceof VBox) {
				VBox parentVBox = (VBox) parentHBox.getParent();
				if (parentVBox.getParent() instanceof HBox) {
					HBox parentMainHBox = (HBox) parentVBox.getParent();
					mainHBox = parentMainHBox.getId();
				}
			}
		}
		return mainHBox;
	}

	private boolean checkHaveParentStar(String temp) {
		if (parentStarId.size() == 0) {
			return true;
		} else {
			for (int i = 0; i < parentStarId.size(); i++) {
				if (parentStarId.get(i).equals(temp)) {
					return false;
				}
			}
		}
		return true;
	}

	// set krdni hamw datakan ba pey id
	private void setStarLevel() {
		if (!(starLevelMap.containsKey(StarIdTemp))) {
			starLevelMap.put(StarIdTemp, valueStarTemp);
		} else if (starLevelMap.containsKey(StarIdTemp)) {
			starLevelMap.put(StarIdTemp, valueStarTemp);
		}
	}

	private void setNameBook() {
		if (!(nameMap.containsKey(bookName.getId()))) {
			nameMap.put(bookName.getId(), nameTemp);
		} else if (nameMap.containsKey(bookName.getId())) {
			nameMap.put(bookName.getId(), nameTemp);
		}
	}

	private void setImageUrl() {
		if (!(imageURLMap.containsKey(bookImage.getId()))) {
			imageURLMap.put(bookImage.getId(), imageTemp);
		} else if (imageURLMap.containsKey(bookImage.getId())) {
			imageURLMap.put(bookImage.getId(), imageTemp);
		}
	}

	// bo gaura krdn bakar den bo maway 0.15 chrka
	private void scaleImage() {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), bookImage);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		scaleTransition.play();
	}

	private void scaleName() {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), bookName);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		scaleTransition.play();
	}

	private void scaleStar() {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), hboxStar);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		scaleTransition.play();
		for (ImageView imageView : imageListStar) {
			scaleTransition = new ScaleTransition(Duration.seconds(0.15), imageView);
			scaleTransition.setAutoReverse(true);
			scaleTransition.setCycleCount(2);
			scaleTransition.setToX(1.2);
			scaleTransition.setToY(1.2);
			scaleTransition.play();
		}
	}

	// printy mapakan
	public void printBothMap() {
		imageURLMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
		nameMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
		starLevelMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
		categoriesMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
	}

	// rashkrdnaway mapakan pash delete krdni har hboxeki parent
	public void deleteThreeMap(int lastDigit) {// lanaw mapakan ba last digit ka dakata id rash dakainawa
		Iterator<String> iterator = imageURLMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == lastDigit) {
				iterator.remove();
			}
		}
		Iterator<String> iterator1 = nameMap.keySet().iterator();
		while (iterator1.hasNext()) {
			String key = iterator1.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == lastDigit) {
				iterator1.remove();
			}
		}
		Iterator<String> iterator2 = starLevelMap.keySet().iterator();
		while (iterator2.hasNext()) {
			String key = iterator2.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == lastDigit) {
				iterator2.remove();
			}
		}
		Iterator<String> iterator3 = categoriesMap.keySet().iterator();
		while (iterator3.hasNext()) {
			String key = iterator3.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == lastDigit) {
				iterator3.remove();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String categoriesType[] = { "Fantasy", "Romance", "Thriller", "Historical Fiction", "Biography/Memoir",
				"Self-Help", "Science", "Business/Economics", "Travel", "Cooking/Food", "Children's Books",
				"Young Adult (YA)", "Graphic Novels/Comics", "Poetry", "Religion/Spirituality", "Manga",
				"Technology/Computer Science", "Art/Photography", "Health/Fitness", "Philosophy" };
		for (int i = 0; i < categoriesType.length; i++) {
			this.categoriesType[i] = categoriesType[i];
		}
		categories.getItems().addAll(categoriesType);

	}
}
