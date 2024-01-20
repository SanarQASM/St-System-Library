package application;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class yourTopBookController implements Initializable {

	@FXML
	private Label bookNameLabel;

	@FXML
	private Label categoriesNameLabel;

	@FXML
	private ImageView bookImage;

	private static String bookName;
	private static String categoriesName;
	private static Image image;

	public yourTopBookController() {
	}

	public yourTopBookController(Integer digit) {
		Iterator<String> iterator = bookController.imageURLMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == digit) {
				String imageURL = bookController.imageURLMap.get(key);
				image = new Image(imageURL);
			}
		}
		Iterator<String> iterator2 = bookController.nameMap.keySet().iterator();
		while (iterator2.hasNext()) {
			String key = iterator2.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == digit) {
				String nameOfBook = bookController.nameMap.get(key);
				bookName = nameOfBook;
			}
		}
		Iterator<String> iterator3 = bookController.categoriesMap.keySet().iterator();
		while (iterator3.hasNext()) {
			String key = iterator3.next();
			int last = Character.getNumericValue(key.charAt(key.length() - 1));
			if (last == digit) {
				String categories = bookController.categoriesMap.get(key);
				categoriesName = categories;
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookNameLabel.setText(bookName);
		bookImage.setImage(image);
		categoriesNameLabel.setText(categoriesName);
	}
}
