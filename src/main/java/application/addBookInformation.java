package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.filechooser.FileNameExtensionFilter;
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

public class addBookInformation implements Initializable{
    @FXML
    private StackPane stackPane;
    private static Stage tempStage;
    private static String tempReward;
    private static int tempIndex;
    private static File tempFileFile,tempFileImage;
    private static String fileName;
    private static String imageName;
    @FXML
    private TextArea Description;

    @FXML
    private Label imageError;

    @FXML
    private TextField bookName;

    @FXML
    private ImageView bookUrl;

    @FXML
    private Label allowedSize;

    @FXML
    private AnchorPane enterEnformationForm;

    @FXML
    private AnchorPane loadingFram;

    @FXML
    private ImageView loadingImage;

    @FXML
    private Label timeAndDate;

    @FXML
    private TextField booklanguage;

    @FXML
    private TextField numberOfPage;

    @FXML
    private TextField publisher;

    @FXML
    private TextField reward;

    @FXML
    private TextField year;

    @FXML
    private TextField FileName;

    @FXML
    private TextField NameofTheTranslator;

    @FXML
    private ComboBox<String> categories;

    @FXML
    private TextField editionNumber;

    @FXML
    void addBook(ActionEvent event) {
        checkThenSend();
    }

    private void checkThenSend() {
        boolean descriptionBook = checkDescription();
        boolean bookName = checkBookName();
        boolean languageBook = checklanguage();
        boolean numberPageBook = checknumberOfPage();
        boolean publisherBook = checkPublisher();
        checkReward();
        boolean yearPublicationBook = checkYear();
        boolean filePath = checkFileUrl();
        boolean imageBook = checkImageBook();
        boolean editionNum=checkEditionNumber();
        boolean nameofTranslator=checkNameTranslator();
        boolean cate=checkCategories();
        if (descriptionBook && bookName && languageBook && numberPageBook &&
                publisherBook && yearPublicationBook && editionNum && nameofTranslator && cate && filePath && imageBook) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() {
                    succussfullySend();
                    return null;
                }
            };

            task.setOnSucceeded(_ -> {
                notificationsClass nC = new notificationsClass();
                nC.showNotificationSendToReview();
                loadingFram.setVisible(false);
                enterEnformationForm.setVisible(true);
                backToMainStage();
            });
            new Thread(task).start();
            Platform.runLater(() -> {
                stackPane.setDisable(true);
                enterEnformationForm.setVisible(false);
                Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/loading.gif")).toString());
                loadingImage.setImage(image);
                loadingFram.setVisible(true);
            });
        } else {
            notificationsClass nC = new notificationsClass();
            nC.showNotificationFieldEmpty();
        }
    }

    private boolean checkCategories() {
        int index=getItemFromCategories();
        if (index==-1){
            categories.setStyle("-fx-border-color: red;");
            return false;
        }
        else{
            categories.setStyle("-fx-border-color: white;");
            tempIndex=index;
            return true;
        }
    }

    private boolean checkNameTranslator() {
        NameofTheTranslator.setText(NameofTheTranslator.getText().trim());
        if(NameofTheTranslator.getText().isEmpty()) {
            NameofTheTranslator.setStyle("-fx-border-color: red;");
            return false;
        }else {
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

    private boolean checkYear() {
        boolean result;
        year.setText(year.getText().trim());
        if (year.getText().isEmpty()) {
            result = false;
        } else result = checkItIsNumber(year.getText());
        if (result){
            year.setStyle("-fx-border-color:white;");
            return true;
        }else {
            year.setStyle("-fx-border-color:red");
            return false;
        }
    }

    private void checkReward() {
        reward.setText(reward.getText().trim());
        if (!reward.getText().isEmpty()){
            tempReward=reward.getText();
        }else {
            tempReward=null;
        }
    }

    private boolean checkPublisher() {
        publisher.setText(publisher.getText().trim());
        if(publisher.getText().isEmpty()) {
            publisher.setStyle("-fx-border-color: red;");
            return false;
        }else {
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
        }else {
            booklanguage.setStyle("-fx-border-color: white;");
            return true;
        }
    }

    private boolean checkBookName() {
        bookName.setText(bookName.getText().trim());
        if(bookName.getText().isEmpty()) {
            bookName.setStyle("-fx-border-color: red;");
            return false;
        }else {
            bookName.setStyle("-fx-border-color: white;");
            return true;
        }
    }

    private boolean checkDescription() {
        Description.setText(Description.getText().trim());
        if((Description.getText().isEmpty())||(Description.getText().length()>=200)) {
            Description.setStyle("-fx-border-color: red;");
            return false;
        }else {
            Description.setStyle("-fx-border-color: white;");
            return true;
        }

    }
    private void succussfullySend() {

        try {
            //upload to Storage
            firebase fb = new firebase();
            String imageHTTP = fb.uploadImageUrl(tempFileImage.getAbsolutePath(), STR."image/\{imageName}");
            String fileHTTP=fb.uploadFileUrl(tempFileFile.getAbsolutePath(), STR."file/\{fileName}");
            //add to database
            mainController mC = new mainController();
            DatabaseConnection databaseCon = new DatabaseConnection();
            databaseCon.addBookInformationByUsername(mC.getTempUsername(),bookName.getText()
                    , Description.getText(), booklanguage.getText(), Integer.parseInt(numberOfPage.getText()),
                    publisher.getText(), tempReward, Integer.parseInt(year.getText()), Integer.parseInt(editionNumber.getText()),
                    NameofTheTranslator.getText(),imageName,fileName,
                    imageHTTP,fileHTTP,tempIndex,timeAndDate.getText(),false);
            notificationsClass nC=new notificationsClass();
            nC.showNotificationSendToReview();
        }
        catch (Exception e){
            notificationsClass nC=new notificationsClass();
            nC.showNotificaitonSomethingWrong();
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
                imageName = file.getName();
//            fb.deleteFile(STR."image/\{file.getName()}");
            }
        } catch (Exception e) {
            notificationsClass nC = new notificationsClass();
            nC.showNotificationInvalideType();
        }
    }
    @FXML
    void backToMainScene(ActionEvent event) {
       backToMainStage();
    }
    private void backToMainStage(){
        tempStage.close();
        mainController mC = new mainController();
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
                fileName = selectedFile.getName();
                FileName.setText(fileName);
            }
        } catch (Exception e) {
            notificationsClass nC = new notificationsClass();
            nC.showNotificationInvalideType();
        }

    }

    private static boolean hasValidExtension(File file, FileNameExtensionFilter filter) {
        String[] allowedExtensions = filter.getExtensions();
        String fileExtension = getFileExtension(file.getName());

        for (String allowedExtension : allowedExtensions) {
            if (fileExtension.equalsIgnoreCase(allowedExtension)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1);
    }

    public void setStage(Stage tempStage) {
        addBookInformation.tempStage = tempStage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] categoriesType = {
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
        categories.getItems().addAll(categoriesType);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        timeAndDate.setText(formattedDateTime);
        Description.textProperty().addListener((observable, oldValue, newValue) -> {
            int characterCount = newValue.trim().length();
            allowedSize.setText(String.valueOf(characterCount));
            if (characterCount >= 200) {
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
    private int getItemFromCategories() {
        try {
            return categories.getSelectionModel().getSelectedIndex();
        } catch (Exception e) {
            return -1;
        }
    }
}
