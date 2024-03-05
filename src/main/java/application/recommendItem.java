package application;

import java.util.Iterator;
import java.util.Map.Entry;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class recommendItem {

	@FXML
	private VBox vboxParent;

	@FXML
	private ImageView imageView;

	@FXML
	private Label labelName;

	@FXML
	private ImageView starFive;

	@FXML
	private ImageView starFour;

	@FXML
	private ImageView starOne;

	@FXML
	private ImageView starThree;

	@FXML
	private ImageView starTwo;

	public recommendItem() {
	}// empty constructor

	// bo away asterakan ba pey levelakayan haman level wargrn
	private void opacityImageFive() {
		starOne.setOpacity(1);
		starTwo.setOpacity(1);
		starThree.setOpacity(1);
		starFour.setOpacity(1);
		starFive.setOpacity(1);
	}

	private void opacityImageFour() {
		starOne.setOpacity(1);
		starTwo.setOpacity(1);
		starThree.setOpacity(1);
		starFour.setOpacity(1);
	}

	private void opacityImageThree() {
		starOne.setOpacity(1);
		starTwo.setOpacity(1);
		starThree.setOpacity(1);
	}

	private void opacityImageTwo() {
		starOne.setOpacity(1);
		starTwo.setOpacity(1);
	}

	private void opacityImageOne() {
		starOne.setOpacity(1);
	}

	public void setStarLevel(int digit) {// danani nrx bo starakan ba pey last digit
//		Iterator<Entry<String, Integer>> iterator = application.bookController.starLevelMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry<String, Integer> entry = iterator.next();
//			String lastDigitOfEachElementInMap = entry.getKey();
//			char lastDigit = lastDigitOfEachElementInMap.charAt(lastDigitOfEachElementInMap.length() - 1);
//			lastDigitOfEachElementInMap = String.valueOf(lastDigit);
//			int in = Integer.parseInt(lastDigitOfEachElementInMap);
//			if (digit == in) {
//				int starLevel = entry.getValue();
//				if (starLevel == 1) {
//					opacityImageOne();
//				} else if (starLevel == 2) {
//					opacityImageTwo();
//				} else if (starLevel == 3) {
//					opacityImageThree();
//				} else if (starLevel == 4) {
//					opacityImageFour();
//				} else {
//					opacityImageFive();
//				}
//				break;
//			}
//		}
	}

	public void setImageView(int digit) {// danani nrx bo image ba pey last digit
//		Iterator<Entry<String, String>> iterator = application.bookController.imageURLMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry<String, String> entry = iterator.next();
//			String lastDigitOfEachElementInMap = entry.getKey();
//			char lastDigit = lastDigitOfEachElementInMap.charAt(lastDigitOfEachElementInMap.length() - 1);
//			lastDigitOfEachElementInMap = String.valueOf(lastDigit);
//			int in = Integer.parseInt(lastDigitOfEachElementInMap);
//			if (digit == in) {
//				Image image = new Image(entry.getValue(), 159, 161, false, true);
//				imageView.setImage(image);
//				break;
//			}
//		}
	}

	public void setLabelText(int digit) {// danani nrx bo nawi ba pey last digit
//		Iterator<Entry<String, String>> iterator = application.bookController.nameMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry<String, String> entry = iterator.next();
//			String lastDigitOfEachElementInMap = entry.getKey();
//			char lastDigit = lastDigitOfEachElementInMap.charAt(lastDigitOfEachElementInMap.length() - 1);
//			lastDigitOfEachElementInMap = String.valueOf(lastDigit);
//			int in = Integer.parseInt(lastDigitOfEachElementInMap);
//			if (digit == in) {
//				labelName.setText(entry.getValue());
//				break;
//			}
//		}
	}

	public void settingDataWithoutAddingVbox(int digit) {// set kdni data la map bo har vboxek
		VBox parent = null;
		for (VBox getVboxWithLastDigit : mainController.listOfVbox) {// agar hich vbox nabe awa wata hich datayak daxl
																		// nakrawa balam hboxman haya
			String lastDigitOfEachElementInMap = getVboxWithLastDigit.getId();
			char lastDigit = lastDigitOfEachElementInMap.charAt(lastDigitOfEachElementInMap.length() - 1);
			lastDigitOfEachElementInMap = String.valueOf(lastDigit);
			int in = Integer.parseInt(lastDigitOfEachElementInMap);
			if (digit == in) {
				parent = getVboxWithLastDigit;
				break;
			}
		}
		if (parent != null) {// wata parentekman haya la naw listaka ka last digit yaksana lagal map
			parent.getChildren().forEach(child -> {// banaw childakani har vboxek daroin
				if (child instanceof Label) {// agar childaka labela
					labelName = (Label) child;
					setLabelText(digit);
				}
				if (child instanceof Pane) {// agar childaka pane
					Node paneChild = ((Pane) child).getChildren().get(0);// lanaw paneish yak childman haya awish image
					if (paneChild.getId().equals("imageView")) {
						imageView = (ImageView) paneChild;
						setImageView(digit);
					}
				}
				if (child instanceof HBox) {// agar childaka hbox
					for (Node hboxChild : ((Parent) child).getChildrenUnmodifiable()) {// lanaw hbox banaw hamw
																						// childakani bro
						if (hboxChild.getId().equals("starOne")) {
							starOne = (ImageView) hboxChild;
						} else if (hboxChild.getId().equals("starTwo")) {
							starTwo = (ImageView) hboxChild;
						} else if (hboxChild.getId().equals("starThree")) {
							starThree = (ImageView) hboxChild;
						} else if (hboxChild.getId().equals("starFour")) {
							starFour = (ImageView) hboxChild;
						} else if (hboxChild.getId().equals("starFive")) {
							starFive = (ImageView) hboxChild;
						}
					}
					setStarLevel(digit);
				}
			});
		}
	}

	public void removeVboxParent(char parentVboxId) {// rashkrdnaway vbox la naw list ba pey last digit
		Iterator<VBox> iterator = mainController.listOfVbox.iterator();
		while (iterator.hasNext()) {
			VBox childList = iterator.next();
			if ((childList.getId().toString().charAt(childList.getId().length() - 1)) == parentVboxId) {
				iterator.remove();
				break;
			}
		}
	}
}