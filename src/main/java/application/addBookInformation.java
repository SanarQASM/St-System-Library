package application;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class addBookInformation {
	private static Stage tempStage;
	private String tempUrl;
	private static String fileUrl;
	private static File file;
	private static String imageBookUrl;
    @FXML
    private TextArea Description;

    @FXML
    private TextField bookName;

    @FXML
    private ImageView bookUrl;

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
    void addBook(ActionEvent event) {
    	    checkThenSend();
    }

    private void checkThenSend() { 
    	boolean descriptionBook= checkDescription();
		boolean bookName = checkBookName();
		boolean languageBook = checklanguage();
		boolean numberPageBook = checknumberOfPage();
		boolean publisherBook = checkPublisher();
		boolean rewardNameBook = checkReward();
		boolean yearPublicationBook=checkYear();
		boolean filePath=checkFileUrl();
		boolean imageBook=checkImageBook();
		if (descriptionBook&&bookName&&languageBook&&numberPageBook&&
			publisherBook&&rewardNameBook&&yearPublicationBook&&filePath&&imageBook) {
			succussfullySend();
			notificationsClass nC=new notificationsClass();
			nC.showNotificationSendToReview();
		}
		else {
			notificationsClass nC=new notificationsClass();
			nC.showNotificationFieldEmpty();
		}
	}

	private boolean checkImageBook() {
		boolean result = false;
		if(imageBookUrl!=null) {
			if (!(imageBookUrl.equals(getClass().getResource("/image/cover.png").toString()))){
				result = false;
			}else {
				System.out.println("file is add succussfully: "+imageBookUrl );
				result = true;
			}
		}
		return result;
	}

	private boolean checkFileUrl() {
		boolean result = false;
		if(fileUrl!=null) {
			if (fileUrl.isEmpty()){
				result = false;
			}else {
				System.out.println("file is exist: "+fileUrl);
				result = true;
			}
		}
		return result;
	}

	private boolean checkYear() {
		boolean result = false;
		year.setText(year.getText().trim());
		if (year.getText().isEmpty()) {
			result = false;
		} else if (checkItIsNumber(year.getText())) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean checkReward() {
		boolean result = false;
		reward.setText(reward.getText().trim());
		if (reward.getText().isEmpty()) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean checkPublisher() {
		boolean result = false;
		publisher.setText(publisher.getText().trim());
		if (publisher.getText().isEmpty()) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean checknumberOfPage() {
		boolean result = false;
		numberOfPage.setText(numberOfPage.getText().trim());
		if (numberOfPage.getText().isEmpty()) {
			result = false;
		} else if (checkItIsNumber(numberOfPage.getText())) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean checkItIsNumber(String numberToCheck) {
		try {
			Integer.parseInt(numberToCheck);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	private boolean checklanguage() {
		boolean result = false;
		booklanguage.setText(booklanguage.getText().trim());
		if (booklanguage.getText().isEmpty()) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean checkBookName() {
		boolean result = false;
		bookName.setText(bookName.getText().trim());
		if (bookName.getText().isEmpty()) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean checkDescription() {
		boolean result = false;
		Description.setText(Description.getText().trim());
		if (Description.getText().isEmpty()) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private void succussfullySend() {
		//add to database
		mainController mC=new mainController();
		DatabaseConnection databaseCon=new DatabaseConnection();
		databaseCon.addBookInformationByUsername(mC.getTempUsername(),bookName.getText(),tempUrl
				,Description.getText(),booklanguage.getText(),numberOfPage.getText(),
				publisher.getText(),reward.getText(),year.getText(),file);
		
	}

    @FXML
    void addImageBook(MouseEvent event) throws Exception {
    	FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));

		File file = fileChooser.showOpenDialog(tempStage);
		if (file != null) {
			Image image = new Image(file.toURI().toString(), 198, 208, false, true);
			bookUrl.setImage(image);// bo naw bookimage day danein
			imageBookUrl=file.getPath();
		}
    }

    @FXML
    void backToMainScene(ActionEvent event) {
    	tempStage.close();
    	mainController mC=new mainController();
    	mC.showStage();
    }

    @FXML
    void selectFile(ActionEvent event) throws IOException {
    	File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Supported Files", "pptx", "pdf", "docx", "txt");
        fileChooser.setFileFilter(filter);
		int response = fileChooser.showOpenDialog(null);
		if(response == JFileChooser.APPROVE_OPTION) {
			file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			if (!hasValidExtension(file, filter)) {
				notificationsClass nC=new notificationsClass();
				nC.showNotificationInvalideType();
			} else {
			FileName.setText(file.getName());
			fileUrl=file.getAbsolutePath();
            }
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
    	addBookInformation.tempStage=tempStage;
    }

}
