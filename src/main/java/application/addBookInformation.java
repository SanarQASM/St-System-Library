package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class addBookInformation implements Initializable{
    private static mainController mC;
    private static notificationsClass nC;
    private static addBookInformation aDI;
    private static Stage tempStage;
    private static String tempReward;
    private static String tempNameTranslator;
    private static int tempIndex;
    private static File tempFileFile,tempFileImage;
    private static String fileName;
    private static String imageName;
    private int remainingSeconds = 60;
    private static String onlyTimeToName;
    @FXML
    private Label NetwordIsSlow;

    @FXML
    private Label time;

    @FXML
    private TextArea Description;

    @FXML
    private TextField FileName;

    @FXML
    private TextField NameOfAuthor;

    @FXML
    private TextField NameofTheTranslator;

    @FXML
    private Label allowedSize;

    @FXML
    private TextField bookName;

    @FXML
    private ImageView bookUrl;

    @FXML
    private TextField booklanguage;

    @FXML
    private ComboBox<String> categories;

    @FXML
    private TextField editionNumber;

    @FXML
    private AnchorPane enterEnformationForm;

    @FXML
    private Label imageError;

    @FXML
    private AnchorPane loadingFram;

    @FXML
    private ImageView loadingImage;

    @FXML
    private TextField numberOfPage;

    @FXML
    private TextField publisher;

    @FXML
    private TextField reward;

    @FXML
    private StackPane stackPane;

    @FXML
    private Label timeAndDate;

    @FXML
    private TextField year;

    @FXML
    private TextField yearOfAuthor;

    @FXML
    void addBook(ActionEvent event) {
        boolean descriptionBook = checkDescription();
        boolean bookName = checkBookName();
        boolean languageBook = checklanguage();
        boolean numberPageBook = checknumberOfPage();
        boolean publisherBook = checkPublisher();
        boolean rewired =checkReward();
        boolean yearPublicationBook = checkYear();
        boolean nameAuthor=chechAuthorName();
        boolean yearAuthor=checkAuthorYear();
        boolean filePath = checkFileUrl();
        boolean imageBook = checkImageBook();
        boolean editionNum = checkEditionNumber();
        boolean nameTranslator=checkNameTranslator();
        boolean cate = checkCategories();
        if (descriptionBook && bookName && languageBook && numberPageBook &&
                publisherBook && yearPublicationBook && editionNum
                && cate && filePath && imageBook && nameAuthor && yearAuthor && rewired && nameTranslator) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    succussfullySend();
                    return null;
                }
            };
            task.setOnFailed(_ ->{
                Platform.runLater(() ->{
                    stackPane.setDisable(false);
                    loadingFram.setVisible(false);
                    enterEnformationForm.setVisible(true);
                    remainingSeconds=60;
                });
            });
            task.setOnSucceeded(_ -> {
                Platform.runLater(() -> {
                    nC.showNotificationSendToReview();
                    stackPane.setDisable(false);
                    loadingFram.setVisible(false);
                    enterEnformationForm.setVisible(true);
                    backToMainStage();
                    mC.updateMainFrame();
                });
            });
            new Thread(task).start();
            Platform.runLater(() -> {
                stackPane.setDisable(true);
                enterEnformationForm.setVisible(false);
                Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/loading.gif")).toString());
                loadingImage.setImage(image);
                loadingFram.setVisible(true);
                final Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    remainingSeconds--;
                    int minutes = remainingSeconds / 60;
                    int seconds = remainingSeconds % 60;
                    String formattedTime = String.format("%02d:%02d", minutes, seconds);
                    time.setText(formattedTime);
                    if (remainingSeconds <= 30) {
                        NetwordIsSlow.setVisible(true);
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), NetwordIsSlow);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.setCycleCount(2);
                        scaleTransition.setToX(1.2);
                        scaleTransition.setToY(1.2);
                        scaleTransition.play();
                        if (remainingSeconds==0){
                            timeline.stop();
                        }
                    }
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            });
        } else {
            Platform.runLater(() -> {
                nC.showNotificationFieldEmpty();
            });
        }
    }

    private void succussfullySend() throws Exception {
            //upload to Storage
            firebase fb = new firebase();
            String imageHTTP = fb.uploadImageUrl(tempFileImage.getAbsolutePath(), STR."image/\{STR."\{bookName.getText()}.\{imageName}"}");
            if (Objects.equals(imageHTTP, "0")){
                throw new Exception("check the Internet");
            }
            String fileHTTP=fb.uploadFileUrl(tempFileFile.getAbsolutePath(), STR."file/\{STR."\{bookName.getText()}.\{fileName}"}");
            if (Objects.equals(imageHTTP, "0")){
                throw new Exception("check the Internet");
            }
            DatabaseConnection databaseCon = new DatabaseConnection(nC);
            int result=databaseCon.addBookInformationByUsername(mC.getTempUsername(),bookName.getText()
                    , Description.getText(), booklanguage.getText(), Integer.parseInt(numberOfPage.getText()),
                    publisher.getText(), tempReward, year.getText(), Integer.parseInt(editionNumber.getText()),
                    tempNameTranslator,imageName,fileName,
                    imageHTTP,fileHTTP,tempIndex,timeAndDate.getText(),false,"file","image",yearOfAuthor.getText(), NameOfAuthor.getText());
            if (result==0){
                throw new Exception(databaseCon.getMessageExceptions());
            }
    }
    private boolean checkCategories() {
        int index=getItemFromCategories();
        if (index==-1){
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), categories);
            scaleTransition.setAutoReverse(true);
            scaleTransition.setCycleCount(2);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
            return false;
        }
        else{
            tempIndex=index;
            return true;
        }
    }

    private boolean checkNameTranslator() {
        NameofTheTranslator.setText(NameofTheTranslator.getText().trim());
        if(NameofTheTranslator.getText().isEmpty()) {
            NameofTheTranslator.setStyle("-fx-border-color: white;");
            tempNameTranslator=null;
           return true;
        }else if(checkItIsNumber(reward.getText())) {
            NameofTheTranslator.setStyle("-fx-border-color: red;");
            return false;
        }
        else {
           tempNameTranslator=NameofTheTranslator.getText();
            NameofTheTranslator.setStyle("-fx-border-color: white;");
            return true;
        }
    }

    private boolean checkEditionNumber() {
        editionNumber.setText(editionNumber.getText().trim());
        boolean result;
        if (editionNumber.getText().isEmpty()) {
            result = false;
        } else result = checkItIsNumber(editionNumber.getText());
        if (result){
            editionNumber.setStyle("-fx-border-color: white");
        }else {
            editionNumber.setStyle("-fx-border-color: red");
        }
        return result;
    }

    private boolean checkImageBook() {
        boolean result=false;
        if (imageName != null) {
            if (!(imageName.equals("cover.png")))
                result = true;
        }
        if (result){
            imageError.setText("");
        }else{
            imageError.setText("Require");
        }
        return result;
    }

    private boolean checkFileUrl() {
        boolean result = false;
        if (fileName != null) {
            if (!(fileName.isEmpty()))
                result = true;
        }
        if (result){
            FileName.setStyle("-fx-border-color: white;");
        }else {
            FileName.setStyle("-fx-border-color: red;");
        }
        return result;
    }

    private boolean chechAuthorName(){
        boolean result;
        NameOfAuthor.setText(NameOfAuthor.getText().trim());
        if (NameOfAuthor.getText().isEmpty()) {
            result=false;
            NameOfAuthor.setStyle("-fx-border-color: red;");
        }else if(checkItIsNumber(NameOfAuthor.getText())) {
            NameOfAuthor.setStyle("-fx-border-color: red;");
            result=false;
        }
        else{
            result=true;
            NameOfAuthor.setStyle("-fx-border-color: white;");
        }
        return result;
    }
    private boolean checkAuthorYear(){
        boolean result;
        yearOfAuthor.setText(yearOfAuthor.getText().trim());
        if (yearOfAuthor.getText().isEmpty()) {
            result=false;
            yearOfAuthor.setStyle("-fx-border-color: red;");
        }
        else result = isValidDate(yearOfAuthor.getText());
        if (result){
            yearOfAuthor.setStyle("-fx-border-color:white;");
            return true;
        }else {
            yearOfAuthor.setStyle("-fx-border-color:red");
            return false;
        }
    }
    private boolean checkYear() {
        boolean result;
        year.setText(year.getText().trim());
        if (year.getText().isEmpty()) {
            result = false;
        }
        else result = isValidDate(year.getText());
        if (result){
            year.setStyle("-fx-border-color:white;");
            return true;
        }else {
            year.setStyle("-fx-border-color:red");
            return false;
        }
    }

    private boolean checkReward() {
        reward.setText(reward.getText().trim());
        if (!reward.getText().isEmpty()){
            if (checkItIsNumber(reward.getText())){
                reward.setStyle("-fx-border-color: white;");
                tempReward=reward.getText();
                return true;
            }else if(checkItIsNumber(reward.getText())) {
                reward.setStyle("-fx-border-color: red;");
                return false;
            }
            else{
                reward.setStyle("-fx-border-color: white;");
                tempReward=null;
                return true;
            }
        }
        else {
            reward.setStyle("-fx-border-color: white;");
            tempReward=null;
            return true;
        }
    }

    private boolean checkPublisher() {
        publisher.setText(publisher.getText().trim());
        if(publisher.getText().isEmpty()) {
            publisher.setStyle("-fx-border-color: red;");
            return false;
        }else if(checkItIsNumber(publisher.getText())) {
            publisher.setStyle("-fx-border-color: red;");
            return false;
        }
        else {
            publisher.setStyle("-fx-border-color: white;");
            return true;
        }
    }

    private boolean checknumberOfPage() {
        boolean result;
        numberOfPage.setText(numberOfPage.getText().trim());
        if (numberOfPage.getText().isEmpty()) {
            result = false;
        } else result = checkItIsNumber(numberOfPage.getText());
        if (result){
            numberOfPage.setStyle("-fx-border-color: white;");
            return true;
        }else{
            numberOfPage.setStyle("-fx-border-color:red");
            return false;
        }
    }

    public static boolean isValidDate(String date) {
        // Define the regular expression patterns
        String yyyyPattern = "\\d{4}";
        String yyyyMMPattern = "\\d{4}-\\d{2}";
        String yyyyMMddPattern = "\\d{4}-\\d{2}-\\d{2}";
        // Compile the regular expression patterns
        Pattern patternYYYY = Pattern.compile(yyyyPattern);
        Pattern patternYYYYMM = Pattern.compile(yyyyMMPattern);
        Pattern patternYYYYMMDD = Pattern.compile(yyyyMMddPattern);
        // Match the string with the regular expression patterns
        Matcher matcherYYYY = patternYYYY.matcher(date);
        Matcher matcherYYYYMM = patternYYYYMM.matcher(date);
        Matcher matcherYYYYMMDD = patternYYYYMMDD.matcher(date);

        // Return true if any of the patterns match
        return matcherYYYY.matches() || matcherYYYYMM.matches() || matcherYYYYMMDD.matches();
    }

    private boolean checkItIsNumber(String numberToCheck) {
        try {
            Integer.parseInt(numberToCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checklanguage() {
        booklanguage.setText(booklanguage.getText().trim());
        if(booklanguage.getText().isEmpty()){
            booklanguage.setStyle("-fx-border-color: red;");
            return false;
        }else if(checkItIsNumber(booklanguage.getText())){
            booklanguage.setStyle("-fx-border-color: red;");
            return false;
        }
        else {
            booklanguage.setStyle("-fx-border-color: white;");
            return true;
        }
    }

    private boolean checkBookName() {
        bookName.setText(bookName.getText().trim());
        if(bookName.getText().isEmpty()) {
            bookName.setStyle("-fx-border-color: red;");
            return false;
        }else if(checkItIsNumber(bookName.getText())) {
            bookName.setStyle("-fx-border-color: red;");
            return false;
        }
        else {
            bookName.setStyle("-fx-border-color: white;");
            return true;
        }
    }

    private boolean checkDescription() {
        Description.setText(Description.getText().trim());
        if((Description.getText().isEmpty())||(Description.getText().length()>=200)) {
            Description.setStyle("-fx-border-color: red;");
            return false;
        }else if(checkItIsNumber(Description.getText())) {
            Description.setStyle("-fx-border-color: red;");
            return false;
        }
        else {
            Description.setStyle("-fx-border-color: white;");
            return true;
        }

    }

    @FXML
    void addImageBook(MouseEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));
            File file = fileChooser.showOpenDialog(tempStage);
            tempFileImage = file;
            if (file != null) {
                Image image = new Image(file.toURI().toString(), 198, 208, false, true);
                double newWidth = 198;
                double newHeight = 208;
                bookUrl.setFitWidth(newWidth);
                bookUrl.setFitHeight(newHeight);
                bookUrl.setImage(image);// bo naw bookimage day danein
                imageName=file.getName();
                imageName = STR."\{bookName.getText()}\{onlyTimeToName}."+imageName.substring(imageName.lastIndexOf('.') + 1).toLowerCase();
            }
        } catch (Exception e) {
            nC.showNotificationInvalideType();
        }
    }
    @FXML
    void backToMainScene(ActionEvent event) {
       backToMainStage();
    }
    private void backToMainStage(){
        tempStage.close();
        mC.showStage();
    }
    @FXML
    void selectFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Supported Files", "*.pptx", "*.pdf", "*.docx", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(tempStage);
            if (selectedFile != null) {
                tempFileFile = selectedFile;
                fileName=tempFileFile.getName();
                fileName = STR."\{bookName.getText()}\{onlyTimeToName}."+fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
                FileName.setText(fileName);
            }
        } catch (Exception e) {
            nC.showNotificationInvalideType();
        }

    }

    public void setStage(Stage tempStage) {
        addBookInformation.tempStage = tempStage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [] categoriesType=getcategoriesType();
        categories.getItems().addAll(categoriesType);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        onlyTimeToName=now.format(formatter);
        timeAndDate.setText(formattedDateTime);
        Description.textProperty().addListener((observable, oldValue, newValue) -> {
            int characterCount = newValue.trim().length();
            allowedSize.setText(String.valueOf(characterCount));
            if (characterCount >= 225 || characterCount==0) {
                allowedSize.setTextFill(Color.RED);
            } else {
                allowedSize.setTextFill(Color.WHITE);
            }
        });
        bookUrl.imageProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                bookUrl.setFitWidth(newValue.getWidth());
                bookUrl.setFitHeight(newValue.getHeight());
            }
        });
        bookUrl.setPreserveRatio(true);
    }
    public String[] getcategoriesType(){
        return new String[]{
                "Thriller", "Crime", "Detective", "Suspense", "Romance",
                "Science Fiction", "Fantasy", "Dystopian", "Space Opera",
                "Adventure", "Action", "Horror", "Paranormal", "Urban Fantasy",
                "Humor", "Satire", "Comedy", "Drama", "Poetry", "Epic Poetry",
                "Biography", "Autobiography", "Memoir", "Self-Help", "Motivational",
                "Psychology", "Philosophy", "Science", "Physics", "Chemistry",
                "Biology", "Mathematics", "Technology", "Computer Science", "Programming",
                "Business", "Economics", "Finance", "History", "Geography",
                "Political Science", "Social Sciences", "Anthropology", "Sociology",
                "Cultural Studies", "Religion", "Philosophy of Religion"
        };
    }
    private int getItemFromCategories() {
        try {
            return categories.getSelectionModel().getSelectedIndex();
        } catch (Exception e) {
            return -1;
        }
    }

    public void setController(mainController mController,addBookInformation aDIController) {
        aDI =aDIController;
        mC=mController;
    }
    public String findCatigoriesNameByIndex(int index){
        String [] category=getcategoriesType();
        return category[index];
    }
    public void setNotificationsClass(notificationsClass nClass){
        nC=nClass;
    }
}
